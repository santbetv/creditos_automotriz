package com.pichincha.testproject.service;

import com.pichincha.services.server.models.VehiculoDto;
import com.pichincha.testproject.exception.BussinesRuleException;
import com.pichincha.testproject.service.dto.SolicitudCreditoDto;
import jakarta.validation.Valid;

import java.util.List;

public interface SolicitudCreditoService {
    public SolicitudCreditoDto save(@Valid SolicitudCreditoDto solicitudCreditoDto) throws BussinesRuleException;
}
