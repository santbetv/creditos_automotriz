package com.pichincha.testproject.service.impl;

import com.pichincha.testproject.domain.model.EjecutivoEntity;
import com.pichincha.testproject.exception.BussinesRuleException;
import com.pichincha.testproject.repository.impl.EjecutivoRepository;
import com.pichincha.testproject.service.EjecutivoService;
import com.pichincha.testproject.service.dto.EjecutivoDto;
import com.pichincha.testproject.service.mapper.EjecutivoResponseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Service
@Validated
public class EjecutivoServiceImpl implements EjecutivoService {

    private final String INFO_URL = "api/ejecutivo";
    private static final Logger LOG = LoggerFactory.getLogger(EjecutivoServiceImpl.class);
    private final EjecutivoRepository ejecutivoRepository;

    private final EjecutivoResponseMapper ejecutivoResponseMapper;

    @Autowired
    public EjecutivoServiceImpl(EjecutivoRepository ejecutivoRepository, EjecutivoResponseMapper ejecutivoResponseMapper) {
        this.ejecutivoRepository = ejecutivoRepository;
        this.ejecutivoResponseMapper = ejecutivoResponseMapper;
    }

    @Override
    @Transactional(readOnly = true) //
    public EjecutivoDto findById(Long id) throws BussinesRuleException {
        Optional<EjecutivoEntity> patio = ejecutivoRepository.findById(id);
        if (!patio.isEmpty()) {
            return ejecutivoResponseMapper.toEjecutivoDTORequest(ejecutivoRepository.findById(id).get());
        } else {
            BussinesRuleException exception = new BussinesRuleException(INFO_URL,"No se encuentra el ejecutivo: "+id);
            throw exception;
        }
    }
}
