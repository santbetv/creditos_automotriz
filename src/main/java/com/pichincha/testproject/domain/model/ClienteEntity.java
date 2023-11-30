package com.pichincha.testproject.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "clientes")
public class ClienteEntity extends PersonaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long idCliente;
    @Column(name = "estado_civil")
    private String estadoCivil;
    @Size(min = 1, max = 10, message = "el tamaño debe de estar entre 1 y 10")
    @Column(name = "identificacion_conyugue")
    private String identificacionConyugue;
    @Column(name = "nombre_conyugue")
    private String nombreConyugue;
    @Column(name = "sujeto_credito")
    private Boolean sujetoCredito;
    @NotEmpty(message = "no puede estar vacio")
    @Column(name = "fecha_nacimiento",nullable = false)
    private LocalDate fechaNacimiento;
}
