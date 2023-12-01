package com.pichincha.testproject.service;

import com.pichincha.services.server.models.ClienteDto;
import com.pichincha.services.server.models.VehiculoDto;
import com.pichincha.testproject.exception.BussinesRuleException;
import jakarta.validation.Valid;

import java.util.List;

public interface VehiculoService {
    public List<VehiculoDto> findAll();

    public VehiculoDto findById(Long id) throws BussinesRuleException;

    public VehiculoDto save(@Valid VehiculoDto vehiculoDto) throws BussinesRuleException;

    public VehiculoDto put(@Valid VehiculoDto vehiculoDto, Long id) throws BussinesRuleException;


}
