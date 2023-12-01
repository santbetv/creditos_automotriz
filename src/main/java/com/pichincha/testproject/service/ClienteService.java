package com.pichincha.testproject.service;

import com.pichincha.services.server.models.ClienteDto;
import com.pichincha.testproject.exception.BussinesRuleException;
import jakarta.validation.Valid;

import java.util.List;

public interface ClienteService {
    public List<ClienteDto> findAll();

    public ClienteDto findById(Long id) throws BussinesRuleException;

    public ClienteDto save(@Valid ClienteDto clienteDto) throws BussinesRuleException;

    public ClienteDto put(@Valid ClienteDto clienteDto, Long id) throws BussinesRuleException;


}
