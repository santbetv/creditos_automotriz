package com.pichincha.testproject.service;

import com.pichincha.services.server.models.ClienteDto;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface ClienteService {
    public ClienteDto save(ClienteDto ClienteDto, BindingResult result);
    public List<ClienteDto> findAll();


}
