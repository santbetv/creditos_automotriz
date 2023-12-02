package com.pichincha.testproject.service.impl;

import com.pichincha.services.server.models.VehiculoDto;
import com.pichincha.testproject.domain.model.ClienteEntity;
import com.pichincha.testproject.domain.model.ClientePatioEntity;
import com.pichincha.testproject.domain.model.PatioEntity;
import com.pichincha.testproject.exception.BussinesRuleException;
import com.pichincha.testproject.repository.impl.*;
import com.pichincha.testproject.service.ClientePatioService;
import com.pichincha.testproject.service.dto.ClientePatioDto;
import com.pichincha.testproject.service.mapper.ClientePatioResponseMapper;
import com.pichincha.testproject.service.mapper.ClienteResponseMapper;
import com.pichincha.testproject.service.mapper.PatioResponseMapper;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@Validated
public class ClientePatioServiceImpl implements ClientePatioService {

    private final String INFO_URL = "api/clientePatio";
    private static final Logger LOG = LoggerFactory.getLogger(ClientePatioServiceImpl.class);

    private final ClientePatioRepository clientePatioRepository;

    private final ClienteRepository clienteRepository;
    private final PatioRepository patioRepository;

    private final ClientePatioResponseMapper clientePatioResponseMapper;
    private final ClienteResponseMapper clienteResponseMapper;
    private final PatioResponseMapper patioResponseMapper;

    @Autowired
    public ClientePatioServiceImpl(ClientePatioRepository clientePatioRepository, ClienteRepository clienteRepository, PatioRepository patioRepository, ClientePatioResponseMapper clientePatioResponseMapper, ClienteResponseMapper clienteResponseMapper, PatioResponseMapper patioResponseMapper) {
        this.clientePatioRepository = clientePatioRepository;
        this.clienteRepository = clienteRepository;
        this.patioRepository = patioRepository;
        this.clientePatioResponseMapper = clientePatioResponseMapper;
        this.clienteResponseMapper = clienteResponseMapper;
        this.patioResponseMapper = patioResponseMapper;
    }

    @Override
    public List<ClientePatioDto> findAll() {
        return null;
    }

    @Override
    public ClientePatioDto findById(Long id) throws BussinesRuleException {


        var infoEx = "";
        try {
            Optional<ClientePatioEntity> clientePE = clientePatioRepository.findById(id);
            if (!clientePE.isEmpty()) {
                Optional<ClienteEntity> cliente = clienteRepository.findById(clientePE.get().getIdCliente());
                Optional<PatioEntity> patio = patioRepository.findById(clientePE.get().getIdPatio());
                ClientePatioDto clientePatioDto = new ClientePatioDto().builder()
                        .clienteDto(clienteResponseMapper.toClienteDTORequest(cliente.get()))
                        .patiosDto(patioResponseMapper.toPatioDTORequest(patio.get()))
                        .fechaAsignacion(clientePE.get().getFechaAsignacion())
                        .build();
                return clientePatioDto;
            }else {
                infoEx="No se encuentra CLIENTEPATIO: "+id;
                throw new BussinesRuleException(infoEx);
            }

        } catch (Exception e) {
            throw new BussinesRuleException(e.getLocalizedMessage(),infoEx,e);
        }
    }

    @Override
    public ClientePatioDto save(@Valid ClientePatioDto clientePatioDto) throws BussinesRuleException {

        var infoEx = "";
        try {
            Optional<ClienteEntity> cliente = clienteRepository.findById(clientePatioDto.getClienteDto().getIdCliente());
            Optional<PatioEntity> patio = patioRepository.findById(clientePatioDto.getPatiosDto().getIdPatio());
            if (!cliente.isEmpty()) {
                if (!patio.isEmpty()) {
                    clientePatioDto.setClienteDto(clienteResponseMapper.toClienteDTORequest(cliente.get()));
                    clientePatioDto.setPatiosDto(patioResponseMapper.toPatioDTORequest(patio.get()));
                    ClientePatioEntity clientePE = clientePatioResponseMapper.toClientePatioEntity(clientePatioDto);
                    ClientePatioEntity save = clientePatioRepository.save(clientePE);
                    return this.findById(save.getIdClientePatios());
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

    @Override
    public ClientePatioDto put(@Valid ClientePatioDto clientePatioDto, Long id) throws BussinesRuleException {
        try {
            ClientePatioEntity clientePE = clientePatioRepository.findById(id)
                    .orElseThrow(() -> new BussinesRuleException("No se encuentra CLIENTEPATIO: " + id));

            ClienteEntity cliente = clienteRepository.findById(clientePatioDto.getClienteDto().getIdCliente())
                    .orElseThrow(() -> new BussinesRuleException("No se encuentra CLIENTE: " + clientePatioDto.getClienteDto().getIdCliente()));

            PatioEntity patio = patioRepository.findById(clientePatioDto.getPatiosDto().getIdPatio())
                    .orElseThrow(() -> new BussinesRuleException("No se encuentra PATIO: " + clientePatioDto.getPatiosDto().getIdPatio()));

            clientePE.setIdCliente(cliente.getIdCliente());
            clientePE.setIdPatio(patio.getIdPatio());
            clientePE.setFechaAsignacion(clientePatioDto.getFechaAsignacion());

            ClientePatioEntity vehiculoDtoUpdate = clientePatioRepository.save(clientePE);
            return this.findById(vehiculoDtoUpdate.getIdClientePatios());

        } catch (BussinesRuleException e) {
            throw e;
        } catch (Exception e) {
            throw new BussinesRuleException(e.getLocalizedMessage(), e);
        }
    }

}
