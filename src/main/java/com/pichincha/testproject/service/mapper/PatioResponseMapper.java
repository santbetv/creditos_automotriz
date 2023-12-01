package com.pichincha.testproject.service.mapper;

import com.pichincha.services.server.models.PatiosDto;
import com.pichincha.testproject.domain.model.PatioEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PatioResponseMapper {

    //fuente PatioDTO destino PatioEntity
    @Mappings({
            @Mapping(target = "idPatio", ignore = true)
    })
    PatioEntity toPatioEntity(PatiosDto c);
    List<PatioEntity> toListPatioEntity(List<PatiosDto> patioEntities);

    //fuente PatioEntity destino PatioDTO
    @InheritInverseConfiguration
    @Mappings({})
    PatiosDto toPatioDTORequest(PatioEntity c);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "idPatio", ignore = true)
    })
    List<PatiosDto> toListPatioDTORequest(List<PatioEntity> patioEntities);
}
