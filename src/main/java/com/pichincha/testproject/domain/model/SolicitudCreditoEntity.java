package com.pichincha.testproject.domain.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "solicitud_creditos")
public class SolicitudCreditoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitud_creditos")
    private Long idSolicitudCreditos;
    @Column(name = "id_cliente")
    private Long idCliente;
    @Column(name = "id_patio")
    private Long idPatio;
    @Column(name = "id_vehiculo")
    private Long idVehiculo;
    @Column(name = "id_ejecutivo")
    private Long idEjecutivo;
    @Column(name = "fecha_de_elaboracion")
    private String fechaElaboracion;
    @Column(name = "meses_plazo")
    private String mesesPlazo;
    private String cuotas;
    private String entrada;
    private String observacion;
    @Column(name = "estado_credito")
    private String estadoSolicitudCredito;
}
