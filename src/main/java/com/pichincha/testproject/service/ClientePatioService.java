package com.pichincha.testproject.service;

import com.pichincha.services.server.models.VehiculoDto;
import com.pichincha.testproject.exception.BussinesRuleException;
import com.pichincha.testproject.service.dto.ClientePatioDto;
import jakarta.validation.Valid;

import java.util.List;

public interface ClientePatioService {

    public List<ClientePatioDto> findAll();

    public ClientePatioDto findById(Long id) throws BussinesRuleException;

    public ClientePatioDto save(@Valid ClientePatioDto clientePatioDto) throws BussinesRuleException;

    public ClientePatioDto put(@Valid ClientePatioDto clientePatioDto, Long id) throws BussinesRuleException;
}
