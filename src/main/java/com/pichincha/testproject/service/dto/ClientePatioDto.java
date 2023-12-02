package com.pichincha.testproject.service.dto;

import com.pichincha.services.server.models.ClienteDto;
import com.pichincha.services.server.models.PatiosDto;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientePatioDto implements Serializable {
    private Long idClientePatios;
    private ClienteDto clienteDto;
    private PatiosDto patiosDto;
    private LocalDate fechaAsignacion;
}
