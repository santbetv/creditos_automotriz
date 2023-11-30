package com.pichincha.testproject.service.impl;

import com.pichincha.services.server.models.ClienteDto;
import com.pichincha.testproject.domain.model.ClienteEntity;
import com.pichincha.testproject.repository.impl.ClienteRepository;
import com.pichincha.testproject.service.ClienteService;
import com.pichincha.testproject.service.mapper.ClienteResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
public class ClienteServiceImpl  implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteResponseMapper clienteResponseMapper;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository, ClienteResponseMapper clienteResponseMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteResponseMapper = clienteResponseMapper;
    }

    @Override
    public ClienteDto save(ClienteDto ClienteDto, BindingResult result) {
        return null;
    }

    @Override
    @Transactional(readOnly = true) //
    public List<ClienteDto> findAll() {
        List<ClienteEntity> clienteEntitys = clienteRepository.findAll();
        List<ClienteDto> datos =clienteResponseMapper.toListClienteDTORequest(clienteEntitys);
        return datos;
    }
}
