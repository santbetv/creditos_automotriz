package com.pichincha.testproject.service.mapper;

import com.pichincha.services.server.models.ClienteDto;
import com.pichincha.testproject.domain.model.ClienteEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteResponseMapper {

    //fuente ClienteDTO destino ClienteEntity
    @Mappings({
            @Mapping(target = "idCliente", ignore = true)
    })
    ClienteEntity toClienteEntity(ClienteDto c);
    List<ClienteEntity> toListClienteEntity(List<ClienteDto> customers);

    //fuente ClienteEntity destino ClienteDTO
    @InheritInverseConfiguration
    @Mappings({})
    ClienteDto toClienteDTORequest(ClienteEntity c);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "idCliente", ignore = true)
    })
    List<ClienteDto> toListClienteDTORequest(List<ClienteEntity> customers);
}
