package com.pichincha.testproject.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "patios")
public class PatioEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_patio")
    private Long idPatio;
    private String nombre;
    private String telefono;
    private String direccion;
    @Column(name = "numero_punto_venta")
    private String numeroPuntoVenta;

    @JsonIgnoreProperties({"objPatioEjecutivo"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ejecutivo")
    private EjecutivoEntity objPatioEjecutivo;
}
