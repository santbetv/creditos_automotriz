package com.pichincha.testproject.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public class PersonaEntity implements Serializable {

    @Column(unique = true)
    private String identificacion;
    private String Nombres;
    private String telefono;
    private String apellidos;
    private String direccion;



}
