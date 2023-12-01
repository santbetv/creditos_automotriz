package com.pichincha.testproject.service.impl;

import com.pichincha.services.server.models.ClienteDto;
import com.pichincha.testproject.domain.model.ClienteEntity;
import com.pichincha.testproject.exception.BussinesRuleException;
import com.pichincha.testproject.repository.impl.ClienteRepository;
import com.pichincha.testproject.service.ClienteService;
import com.pichincha.testproject.service.mapper.ClienteResponseMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Validated
public class ClienteServiceImpl  implements ClienteService {

    private final String INFO_URL = "api/cliente";
    private static final Logger LOG = LoggerFactory.getLogger(ClienteServiceImpl.class);
    private final ClienteRepository clienteRepository;
    private final ClienteResponseMapper clienteResponseMapper;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository, ClienteResponseMapper clienteResponseMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteResponseMapper = clienteResponseMapper;
    }


    @Override
    @Transactional(readOnly = true) //
    public ClienteDto findById(Long id) throws BussinesRuleException {
        Optional<ClienteEntity> cliente = clienteRepository.findById(id);
        if (!cliente.isEmpty()) {
            return clienteResponseMapper.toClienteDTORequest(clienteRepository.findById(id).get());
        } else {
            BussinesRuleException exception = new BussinesRuleException(INFO_URL);
            throw exception;
        }
    }

    @Override
    @Transactional(readOnly = true) //
    public List<ClienteDto> findAll() {
        List<ClienteEntity> clienteEntitys = clienteRepository.findAll();
        List<ClienteDto> datos =clienteResponseMapper.toListClienteDTORequest(clienteEntitys);
        return datos;
    }

    @Override
    public ClienteDto save(@Valid ClienteDto clienteDto) throws BussinesRuleException {
        try {
            ClienteEntity clienteEntity = clienteResponseMapper.toClienteEntity(clienteDto);
            ClienteEntity save = clienteRepository.save(clienteEntity);
            return clienteResponseMapper.toClienteDTORequest(save);
        } catch (Exception e) {
            throw new BussinesRuleException(e.getLocalizedMessage(),e);
        }
    }

    @Override
    public ClienteDto put(@Valid ClienteDto clienteDto, Long id) throws BussinesRuleException {
        Optional<ClienteEntity> find = clienteRepository.findById(id);
        if (!find.isEmpty()) {
            find.get().setNombres(clienteDto.getNombres());
            find.get().setApellidos(clienteDto.getApellidos());
            find.get().setFechaNacimiento(clienteDto.getFechaNacimiento());
            find.get().setSujetoCredito(clienteDto.getSujetoCredito());

            ClienteDto clienteDtoUpdate =clienteResponseMapper.toClienteDTORequest(find.get());
            clienteRepository.save(find.get());
            return clienteDtoUpdate;
        }else{
            throw new BussinesRuleException("No se encuentra en sistema");
        }
    }




}
