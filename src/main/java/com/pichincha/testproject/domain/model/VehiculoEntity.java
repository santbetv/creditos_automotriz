package com.pichincha.testproject.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "vehiculos")
public class VehiculoEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vehiculo")
    private Long idVehiculo;
    @Column(nullable = false, unique = true)
    private String placa;
    @Column(nullable = false)
    private String modelo;
    @Column(name = "nro_de_chasis",nullable = false)
    private String nroDeChasis;
    @ManyToOne
    @JoinColumn(name = "id_marca",nullable = false)
    private MarcaEntity marca;
    private String tipo;
    @Column(nullable = false)
    private String cilindraje;
    @Column(nullable = false)
    private String avaluo;

}
