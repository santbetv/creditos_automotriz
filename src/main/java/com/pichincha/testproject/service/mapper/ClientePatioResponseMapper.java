package com.pichincha.testproject.service.mapper;


import com.pichincha.testproject.domain.model.ClientePatioEntity;
import com.pichincha.testproject.service.dto.ClientePatioDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientePatioResponseMapper {

    //fuente ClientePatioDTO destino ClientePatioEntity
    @Mappings({
            @Mapping(source = "clienteDto.idCliente", target = "idCliente"),
            @Mapping(source = "patiosDto.idPatio", target = "idPatio")
    })
    ClientePatioEntity toClientePatioEntity(ClientePatioDto c);
    List<ClientePatioEntity> toListClientePatioEntity(List<ClientePatioDto> ClientePatios);

    //fuente ClientePatioEntity destino ClientePatioDTO
    @InheritInverseConfiguration
    @Mappings({})
    ClientePatioDto toClientePatioDTORequest(ClientePatioEntity c);

    @InheritInverseConfiguration
    @Mappings({

    })
    List<ClientePatioDto> toListClientePatioDTORequest(List<ClientePatioEntity> ClientePatios);
}
