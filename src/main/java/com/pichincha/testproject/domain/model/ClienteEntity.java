package com.pichincha.testproject.domain.model;

import jakarta.persistence.*;
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
    @Column(name = "identificacion_conyugue")
    private String identificacionConyugue;
    @Column(name = "nombre_conyugue")
    private String nombreConyugue;
    @Column(name = "sujeto_credito")
    private Boolean sujetoCredito;
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;
}
