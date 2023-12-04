package com.pichincha.testproject.service;


import com.pichincha.testproject.exception.BussinesRuleException;
import com.pichincha.testproject.service.dto.EjecutivoDto;

public interface EjecutivoService {
    public EjecutivoDto findById(Long id) throws BussinesRuleException;
}
