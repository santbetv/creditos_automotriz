package com.pichincha.testproject.service.impl;

import com.pichincha.testproject.domain.model.SolicitudCreditoEntity;
import com.pichincha.testproject.exception.BussinesRuleException;
import com.pichincha.testproject.repository.impl.*;
import com.pichincha.testproject.service.SolicitudCreditoService;
import com.pichincha.testproject.service.dto.SolicitudCreditoDto;
import com.pichincha.testproject.service.mapper.*;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@Validated
public class SolicitudCreditoServiceImpl implements SolicitudCreditoService {

    private final String INFO_URL = "api/solicitudCredito";
    private static final Logger LOG = LoggerFactory.getLogger(SolicitudCreditoServiceImpl.class);

    private final SolicitudCreditoRepository solicitudCreditoRepository;
    private final ClientePatioRepository clientePatioRepository;
    private final ClienteRepository clienteRepository;
    private final PatioRepository patioRepository;

    private final VehiculoRepository vehiculoRepository;

    private final EjecutivoRepository ejecutivoRepository;

    private final ClienteResponseMapper clienteResponseMapper;
    private final PatioResponseMapper patioResponseMapper;

    private final EjecutivoResponseMapper ejecutivoResponseMapper;

    private final SolicitudCreditoResponseMapper solicitudCreditoResponseMapper;

    public SolicitudCreditoServiceImpl(SolicitudCreditoRepository solicitudCreditoRepository, ClientePatioRepository clientePatioRepository, ClienteRepository clienteRepository, PatioRepository patioRepository, VehiculoRepository vehiculoRepository, EjecutivoRepository ejecutivoRepository, ClienteResponseMapper clienteResponseMapper, PatioResponseMapper patioResponseMapper,EjecutivoResponseMapper ejecutivoResponseMapper, SolicitudCreditoResponseMapper solicitudCreditoResponseMapper) {
        this.solicitudCreditoRepository = solicitudCreditoRepository;
        this.clientePatioRepository = clientePatioRepository;
        this.clienteRepository = clienteRepository;
        this.patioRepository = patioRepository;
        this.vehiculoRepository = vehiculoRepository;
        this.ejecutivoRepository = ejecutivoRepository;
        this.clienteResponseMapper = clienteResponseMapper;
        this.patioResponseMapper = patioResponseMapper;
        this.ejecutivoResponseMapper = ejecutivoResponseMapper;
        this.solicitudCreditoResponseMapper = solicitudCreditoResponseMapper;
    }

    @Override
    public SolicitudCreditoDto save(@Valid SolicitudCreditoDto solicitudCreditoDto) throws BussinesRuleException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        LocalDate fechaEntrada = LocalDate.parse(solicitudCreditoDto.getFechaElaboracion(), formatter);
        if(!solicitudCreditoRepository.existsByIdClienteAndFechaElaboracion(solicitudCreditoDto.getClienteDto().getIdCliente(), fechaEntrada)){
            SolicitudCreditoEntity solicitudCreditoEntity = solicitudCreditoResponseMapper.toSolicitudCreditoEntity(solicitudCreditoDto);
            SolicitudCreditoEntity save = solicitudCreditoRepository.save(solicitudCreditoEntity);
            return solicitudCreditoResponseMapper.toSolicitudCreditoDTORequest(save);
        }else{
            throw new BussinesRuleException("Ya existe una solicitud de el cliente en la fecha enviada");
        }

    }
}
