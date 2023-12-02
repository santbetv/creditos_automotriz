package com.pichincha.testproject.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ejecutivos")
public class EjecutivoEntity  extends PersonaEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ejecutivo")
    private Long idEjecutivo;
    private String celular;
    private Byte edad;

//    @JsonIgnoreProperties({"objPatioEjecutivo"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_patio")
    private PatioEntity objPatioEjecutivo;


}
