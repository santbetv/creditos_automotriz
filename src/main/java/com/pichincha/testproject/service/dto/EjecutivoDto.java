package com.pichincha.testproject.service.dto;

import com.pichincha.services.server.models.PatiosDto;
import com.pichincha.testproject.domain.model.PatioEntity;
import jakarta.persistence.Column;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EjecutivoDto implements Serializable {

    private Long idEjecutivo;
    private String celular;
    private Byte edad;
    private String identificacion;
    private String Nombres;
    private String telefono;
    private String apellidos;
    private String direccion;
    private PatiosDto patio;

}
