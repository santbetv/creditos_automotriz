package com.pichincha.testproject.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "clientes_patios")
public class ClientePatioEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_clientes_patios")
    private Long idClientePatios;
    @Column(name = "id_cliente", nullable = false)
    private Long idCliente;
    @Column(name = "id_patio", nullable = false)
    private Long idPatio;
    @Column(name = "fecha_asignacion", nullable = false)
    private LocalDate fechaAsignacion;



}
