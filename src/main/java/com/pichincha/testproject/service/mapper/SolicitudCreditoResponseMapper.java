package com.pichincha.testproject.service.mapper;


import com.pichincha.testproject.domain.model.SolicitudCreditoEntity;
import com.pichincha.testproject.service.dto.SolicitudCreditoDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SolicitudCreditoResponseMapper {

    //fuente SolicitudCreditoDTO destino SolicitudCreditoEntity
    @Mappings({
            @Mapping(source = "clienteDto.idCliente", target = "idCliente"),
            @Mapping(source = "patiosDto.idPatio", target = "idPatio"),
            @Mapping(source = "vehiculoDto.idVehiculo", target = "idVehiculo"),
            @Mapping(source = "ejecutivoDto.idEjecutivo", target = "idEjecutivo")
    })
    SolicitudCreditoEntity toSolicitudCreditoEntity(SolicitudCreditoDto c);
    List<SolicitudCreditoEntity> toListSolicitudCreditoEntity(List<SolicitudCreditoDto> SolicitudCreditos);

    //fuente SolicitudCreditoEntity destino SolicitudCreditoDTO
    @InheritInverseConfiguration
    @Mappings({
            @Mapping(source = "idCliente", target = "clienteDto.idCliente"),
            @Mapping(source = "idPatio", target = "patiosDto.idPatio"),
            @Mapping(source = "idVehiculo", target = "vehiculoDto.idVehiculo"),
            @Mapping(source = "idEjecutivo", target = "ejecutivoDto.idEjecutivo")
    })
    SolicitudCreditoDto toSolicitudCreditoDTORequest(SolicitudCreditoEntity c);

    List<SolicitudCreditoDto> toListSolicitudCreditoDTORequest(List<SolicitudCreditoEntity> SolicitudCreditos);
}
