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
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String telefono;
    @Column(nullable = false)
    private String direccion;
    @Column(name = "numero_punto_venta", nullable = false)
    private String numeroPuntoVenta;

    @JsonIgnoreProperties({"objPatioEjecutivo"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ejecutivo")
    private EjecutivoEntity objPatioEjecutivo;
}
