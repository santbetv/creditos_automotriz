package com.pichincha.testproject.service.mapper;


import com.pichincha.testproject.domain.model.EjecutivoEntity;
import com.pichincha.testproject.service.dto.EjecutivoDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EjecutivoResponseMapper {

    //fuente EjecutivoDTO destino EjecutivoEntity
    @Mappings({
            @Mapping(source = "patio", target = "objPatioEjecutivo")
    })
    EjecutivoEntity toEjecutivoEntity(EjecutivoDto c);
    List<EjecutivoEntity> toListEjecutivoEntity(List<EjecutivoDto> Ejecutivos);

    //fuente EjecutivoEntity destino EjecutivoDTO
    @InheritInverseConfiguration
    @Mappings({})
    EjecutivoDto toEjecutivoDTORequest(EjecutivoEntity c);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(source = "patio", target = "objPatioEjecutivo")
    })
    List<EjecutivoDto> toListEjecutivoDTORequest(List<EjecutivoEntity> Ejecutivos);
}
