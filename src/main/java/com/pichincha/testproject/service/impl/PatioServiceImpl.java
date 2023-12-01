package com.pichincha.testproject.service.impl;

import com.pichincha.services.server.models.PatiosDto;
import com.pichincha.testproject.domain.model.PatioEntity;
import com.pichincha.testproject.exception.BussinesRuleException;
import com.pichincha.testproject.repository.impl.PatioRepository;
import com.pichincha.testproject.service.PatioService;
import com.pichincha.testproject.service.mapper.PatioResponseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@Validated
public class PatioServiceImpl implements PatioService {

    private final String INFO_URL = "api/patio";
    private static final Logger LOG = LoggerFactory.getLogger(PatioServiceImpl.class);

    private final PatioRepository patioRepository;
    private final PatioResponseMapper patioResponseMapper;

    @Autowired
    public PatioServiceImpl(PatioRepository patioRepository, PatioResponseMapper patioResponseMapper) {
        this.patioRepository = patioRepository;
        this.patioResponseMapper = patioResponseMapper;
    }

    @Override
    @Transactional(readOnly = true) //
    public List<PatiosDto> findAll() {
        List<PatioEntity> patioEntities = patioRepository.findAll();
        List<PatiosDto> datos =patioResponseMapper.toListPatioDTORequest(patioEntities);
        return datos;
    }

    @Override
    @Transactional(readOnly = true) //
    public PatiosDto findById(Long id) throws BussinesRuleException {
        Optional<PatioEntity> patio = patioRepository.findById(id);
        if (!patio.isEmpty()) {
            return patioResponseMapper.toPatioDTORequest(patioRepository.findById(id).get());
        } else {
            BussinesRuleException exception = new BussinesRuleException(INFO_URL);
            throw exception;
        }
    }

    @Override
    public PatiosDto save(PatiosDto patiosDto) throws BussinesRuleException {

        try {
            PatioEntity patioEntity = patioResponseMapper.toPatioEntity(patiosDto);
            PatioEntity save = patioRepository.save(patioEntity);
            return patioResponseMapper.toPatioDTORequest(save);
        } catch (Exception e) {
            throw new BussinesRuleException(e.getLocalizedMessage(),e);
        }
    }

    @Override
    public PatiosDto put(PatiosDto patiosDto, Long id) throws BussinesRuleException {
        Optional<PatioEntity> find = patioRepository.findById(id);
        if (!find.isEmpty()) {
            find.get().setNombre(patiosDto.getNombre());
            find.get().setTelefono(patiosDto.getTelefono());
            find.get().setDireccion(patiosDto.getDireccion());
            find.get().setNumeroPuntoVenta(patiosDto.getNumeroPuntoVenta());

            PatiosDto patioDtoUpdate =patioResponseMapper.toPatioDTORequest(find.get());
            patioRepository.save(find.get());
            return patioDtoUpdate;
        }else{
            throw new BussinesRuleException("No se encuentra en sistema");
        }
    }
}
