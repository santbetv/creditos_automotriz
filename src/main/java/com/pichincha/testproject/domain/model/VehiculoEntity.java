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
    private String placa;
    private String modelo;
    @Column(name = "nro_de_chasis")
    private String nroDeChasis;
    @ManyToOne
    @JoinColumn(name = "id_marca")
    private MarcaEntity marca;
    private String tipo;
    private String cilindraje;
    private String avaluo;

}
