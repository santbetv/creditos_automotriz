package com.pichincha.testproject.service;

import com.pichincha.services.server.models.ClienteDto;
import com.pichincha.services.server.models.PatiosDto;
import com.pichincha.testproject.exception.BussinesRuleException;
import jakarta.validation.Valid;

import java.util.List;

public interface PatioService {
    public List<PatiosDto> findAll();

    public PatiosDto findById(Long id) throws BussinesRuleException;

    public PatiosDto save(@Valid PatiosDto patiosDto) throws BussinesRuleException;

    public PatiosDto put(@Valid PatiosDto patiosDto, Long id) throws BussinesRuleException;


}
