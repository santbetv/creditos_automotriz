package com.pichincha.testproject.service.impl;

import com.pichincha.services.server.models.ClienteDto;
import com.pichincha.services.server.models.PatiosDto;
import com.pichincha.services.server.models.VehiculoDto;
import com.pichincha.testproject.domain.model.ClienteEntity;
import com.pichincha.testproject.domain.model.ClientePatioEntity;
import com.pichincha.testproject.domain.model.PatioEntity;
import com.pichincha.testproject.domain.model.SolicitudCreditoEntity;
import com.pichincha.testproject.exception.BussinesRuleException;
import com.pichincha.testproject.repository.impl.*;
import com.pichincha.testproject.service.*;
import com.pichincha.testproject.service.dto.ClientePatioDto;
import com.pichincha.testproject.service.dto.EjecutivoDto;
import com.pichincha.testproject.service.dto.SolicitudCreditoDto;
import com.pichincha.testproject.service.mapper.*;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@Validated
public class SolicitudCreditoServiceImpl implements SolicitudCreditoService {

    private final String INFO_URL = "api/solicitudCredito";
    private static final Logger LOG = LoggerFactory.getLogger(SolicitudCreditoServiceImpl.class);

    private final SolicitudCreditoRepository solicitudCreditoRepository;
    private final ClientePatioRepository clientePatioRepository;
    private final ClienteRepository clienteRepository;
    private final PatioRepository patioRepository;

    private final ClienteResponseMapper clienteResponseMapper;
    private final PatioResponseMapper patioResponseMapper;

    private final SolicitudCreditoResponseMapper solicitudCreditoResponseMapper;

    private final ClientePatioResponseMapper clientePatioResponseMapper;

    private final ClienteService clienteService;
    private final PatioService patioService;
    private final VehiculoService vehiculoService;

    private final EjecutivoService ejecutivoService;

    public SolicitudCreditoServiceImpl(SolicitudCreditoRepository solicitudCreditoRepository, ClientePatioRepository clientePatioRepository, ClienteRepository clienteRepository, PatioRepository patioRepository, ClienteResponseMapper clienteResponseMapper, PatioResponseMapper patioResponseMapper, SolicitudCreditoResponseMapper solicitudCreditoResponseMapper, ClientePatioResponseMapper clientePatioResponseMapper, ClienteService clienteService, PatioService patioService, VehiculoService vehiculoService, EjecutivoService ejecutivoService) {
        this.solicitudCreditoRepository = solicitudCreditoRepository;
        this.clientePatioRepository = clientePatioRepository;
        this.clienteRepository = clienteRepository;
        this.patioRepository = patioRepository;
        this.clienteResponseMapper = clienteResponseMapper;
        this.patioResponseMapper = patioResponseMapper;
        this.solicitudCreditoResponseMapper = solicitudCreditoResponseMapper;
        this.clientePatioResponseMapper = clientePatioResponseMapper;
        this.clienteService = clienteService;
        this.patioService = patioService;
        this.vehiculoService = vehiculoService;
        this.ejecutivoService = ejecutivoService;
    }

    @Override
    @Transactional
    public SolicitudCreditoDto save(@Valid SolicitudCreditoDto solicitudCreditoDto) throws BussinesRuleException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        LocalDate fechaEntrada = LocalDate.parse(solicitudCreditoDto.getFechaElaboracion(), formatter);

       EjecutivoDto ejecutivoDto= ejecutivoService.findById(solicitudCreditoDto.getEjecutivoDto().getIdEjecutivo());
       VehiculoDto vehiculoDto= vehiculoService.findById(solicitudCreditoDto.getVehiculoDto().getIdVehiculo());

        if (!solicitudCreditoRepository.existsByIdClienteAndFechaElaboracion(solicitudCreditoDto.getClienteDto().getIdCliente(), fechaEntrada)){
            if(!solicitudCreditoRepository.existsByIdVehiculoAndEstadoSolicitud(solicitudCreditoDto.getVehiculoDto().getIdVehiculo())){

                saveClientePatio(new ClientePatioDto().builder()
                        .clienteDto(new ClienteDto().idCliente(solicitudCreditoDto.getClienteDto().getIdCliente()))
                        .patiosDto(new PatiosDto().idPatio(solicitudCreditoDto.getPatiosDto().getIdPatio()))
                        .fechaAsignacion(fechaEntrada)
                        .build());

                SolicitudCreditoEntity solicitudCreditoEntity = solicitudCreditoResponseMapper.toSolicitudCreditoEntity(solicitudCreditoDto);


                ejecutivoDto.setPatio(patioService.findById(solicitudCreditoDto.getPatiosDto().getIdPatio()));
                SolicitudCreditoDto clientePE = new SolicitudCreditoDto().builder()
                        .clienteDto(clienteService.findById(solicitudCreditoDto.getClienteDto().getIdCliente()))
                        .patiosDto(patioService.findById(solicitudCreditoDto.getPatiosDto().getIdPatio()))
                        .vehiculoDto(vehiculoDto)
                        .ejecutivoDto(ejecutivoDto)
                        .fechaElaboracion(solicitudCreditoDto.getFechaElaboracion())
                        .mesesPlazo(solicitudCreditoDto.getMesesPlazo())
                        .cuotas(solicitudCreditoDto.getCuotas())
                        .entrada(solicitudCreditoDto.getEntrada())
                        .observacion(solicitudCreditoDto.getObservacion())
                        .estadoSolicitudCredito(solicitudCreditoDto.getEstadoSolicitudCredito())
                        .build();

                SolicitudCreditoEntity save = solicitudCreditoRepository.save(solicitudCreditoEntity);

                return clientePE;
            }else{
                throw new BussinesRuleException("El auto que envio para la solicitud de credito ya esta reservado para otro");

            }
        }else{
            throw new BussinesRuleException("Ya existe una solicitud de el cliente en la fecha enviada");
        }
    }


    private ClientePatioEntity saveClientePatio(ClientePatioDto clientePatioDto) throws BussinesRuleException {

        var infoEx = "";
        try {
            Optional<ClienteEntity> cliente = clienteRepository.findById(clientePatioDto.getClienteDto().getIdCliente());
            Optional<PatioEntity> patio = patioRepository.findById(clientePatioDto.getPatiosDto().getIdPatio());
            if (!cliente.isEmpty()) {
                if (!patio.isEmpty()) {
                    clientePatioDto.setClienteDto(clienteResponseMapper.toClienteDTORequest(cliente.get()));
                    clientePatioDto.setPatiosDto(patioResponseMapper.toPatioDTORequest(patio.get()));
                    ClientePatioEntity clientePE = clientePatioResponseMapper.toClientePatioEntity(clientePatioDto);

                    return clientePatioRepository.save(clientePE);
                } else {
                    infoEx="No se encuentra PATIO: "+clientePatioDto.getPatiosDto().getIdPatio();
                    throw new BussinesRuleException(infoEx);
                }
            } else {
                infoEx="No se encuentra CLIENTE: "+"No se encuentra : "+clientePatioDto.getClienteDto().getIdCliente();
                throw new BussinesRuleException(infoEx);
            }
        } catch (Exception e) {
            throw new BussinesRuleException(e.getLocalizedMessage(),infoEx,e);
        }

    }

}
