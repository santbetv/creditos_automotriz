package com.pichincha.testproject.service.mapper;

import com.pichincha.services.server.models.VehiculoDto;
import com.pichincha.testproject.domain.model.VehiculoEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VehiculoResponseMapper {

    //fuente VehiculoDTO destino VehiculoEntity
    @Mappings({
            @Mapping(source = "numeroChasis", target = "nroDeChasis"),
            @Mapping(source = "nombreMarca", target = "marca.nombre"),
            @Mapping(source = "idMarca", target = "marca.idMarca"),
            @Mapping(target = "idVehiculo", ignore = true)
    })
    VehiculoEntity toVehiculoEntity(VehiculoDto c);
    List<VehiculoEntity> toListVehiculoEntity(List<VehiculoDto> vehiculos);

    //fuente VehiculoEntity destino VehiculoDTO
    @InheritInverseConfiguration
    @Mappings({})
    VehiculoDto toVehiculoDTORequest(VehiculoEntity c);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "idVehiculo", ignore = true)
    })
    List<VehiculoDto> toListVehiculoDTORequest(List<VehiculoEntity> vehiculos);
}
