package com.pichincha.testproject.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public class PersonaEntity implements Serializable {

    @Size(min = 1, max = 10, message = "el tamaño debe de estar entre 1 y 10")
    @Column(unique = true)
    private String identificacion;
    @Column( nullable = false)
    private String Nombres;
    @Column( nullable = false)
    private String telefono;
    @Column( nullable = false)
    private String apellidos;
    @Column( nullable = false)
    private String direccion;



}
