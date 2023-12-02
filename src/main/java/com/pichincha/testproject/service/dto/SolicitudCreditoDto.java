package com.pichincha.testproject.service.dto;

import com.pichincha.services.server.models.ClienteDto;
import com.pichincha.services.server.models.PatiosDto;
import com.pichincha.services.server.models.VehiculoDto;
import jakarta.persistence.Column;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudCreditoDto  implements Serializable {

    private Long idSolicitudCreditos;
    private ClienteDto clienteDto;
    private PatiosDto patiosDto;
    private VehiculoDto vehiculoDto;
    private EjecutivoDto ejecutivoDto;
    private String fechaElaboracion;
    private String mesesPlazo;
    private String cuotas;
    private String entrada;
    private String observacion;
    private String estadoSolicitudCredito;

}
