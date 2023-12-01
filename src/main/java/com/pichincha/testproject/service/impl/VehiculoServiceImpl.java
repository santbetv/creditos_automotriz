package com.pichincha.testproject.service.impl;

import com.pichincha.services.server.models.PatiosDto;
import com.pichincha.services.server.models.VehiculoDto;
import com.pichincha.testproject.domain.model.MarcaEntity;
import com.pichincha.testproject.domain.model.PatioEntity;
import com.pichincha.testproject.domain.model.VehiculoEntity;
import com.pichincha.testproject.exception.BussinesRuleException;
import com.pichincha.testproject.repository.impl.MarcaRepository;
import com.pichincha.testproject.repository.impl.VehiculoRepository;
import com.pichincha.testproject.service.VehiculoService;
import com.pichincha.testproject.service.mapper.VehiculoResponseMapper;
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
public class VehiculoServiceImpl implements VehiculoService {

    private final String INFO_URL = "api/vehiculo";
    private static final Logger LOG = LoggerFactory.getLogger(VehiculoServiceImpl.class);
    private final VehiculoRepository vehiculoRepository;
    private final MarcaRepository marcaRepository;
    private final VehiculoResponseMapper vehiculoResponseMapper;

    @Autowired
    public VehiculoServiceImpl(VehiculoRepository vehiculoRepository, MarcaRepository marcaRepository, VehiculoResponseMapper vehiculoResponseMapper) {
        this.vehiculoRepository = vehiculoRepository;
        this.marcaRepository = marcaRepository;
        this.vehiculoResponseMapper = vehiculoResponseMapper;
    }

    @Override
    @Transactional(readOnly = true) //
    public List<VehiculoDto> findAll() {
        List<VehiculoEntity> vehiculoEntities = vehiculoRepository.findAll();
        List<VehiculoDto> datos =vehiculoResponseMapper.toListVehiculoDTORequest(vehiculoEntities);
        return datos;
    }

    @Override
    @Transactional(readOnly = true) //
    public VehiculoDto findById(Long id) throws BussinesRuleException {
        Optional<VehiculoEntity> patio = vehiculoRepository.findById(id);
        if (!patio.isEmpty()) {
            return vehiculoResponseMapper.toVehiculoDTORequest(vehiculoRepository.findById(id).get());
        } else {
            BussinesRuleException exception = new BussinesRuleException(INFO_URL,"No se encuentra el vehiculo: "+id);
            throw exception;
        }
    }

    @Override
    public VehiculoDto save(VehiculoDto vehiculoDto) throws BussinesRuleException {
        var infoEx = "";
        try {
            Optional<MarcaEntity> find = marcaRepository.findById(vehiculoDto.getIdMarca());
            if (!find.isEmpty()) {
                VehiculoEntity vehiculoEntity = vehiculoResponseMapper.toVehiculoEntity(vehiculoDto);
                vehiculoEntity.setMarca(find.get());
                VehiculoEntity save = vehiculoRepository.save(vehiculoEntity);
                return vehiculoResponseMapper.toVehiculoDTORequest(save);
            }else{
                infoEx="No se encuentra en sistema la marcar: "+vehiculoDto.getIdMarca();
                throw new BussinesRuleException(infoEx);
            }
        } catch (Exception e) {
            throw new BussinesRuleException(e.getLocalizedMessage(),infoEx,e);
        }
    }

    @Override
    public VehiculoDto put(VehiculoDto vehiculoDto, Long id) throws BussinesRuleException {
        Optional<VehiculoEntity> find = vehiculoRepository.findById(id);
        if (!find.isEmpty()) {
            Optional<MarcaEntity> findMarca = marcaRepository.findById(vehiculoDto.getIdMarca());
            if (!find.isEmpty()) {
                find.get().setPlaca(vehiculoDto.getPlaca());
                find.get().setModelo(vehiculoDto.getModelo());
                find.get().setTipo(vehiculoDto.getTipo());
                find.get().setCilindraje(vehiculoDto.getCilindraje());
                find.get().setAvaluo(vehiculoDto.getAvaluo());
                find.get().setNroDeChasis(vehiculoDto.getNumeroChasis());
                find.get().setMarca(findMarca.get());
                VehiculoDto vehiculoDtoUpdate =vehiculoResponseMapper.toVehiculoDTORequest(find.get());
                vehiculoRepository.save(find.get());
                return vehiculoDtoUpdate;
            }else{
                throw new BussinesRuleException("No se encuentra en sistema la marcar: "+vehiculoDto.getIdMarca());
            }

        }else{
            throw new BussinesRuleException("No se encuentra en sistema");
        }
    }
}
