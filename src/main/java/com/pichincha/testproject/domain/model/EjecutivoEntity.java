package com.pichincha.testproject.domain.model;

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
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "objPatioEjecutivo", cascade = CascadeType.ALL)
    private List<PatioEntity> patio;
}
