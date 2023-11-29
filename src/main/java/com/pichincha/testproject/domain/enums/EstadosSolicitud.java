package com.pichincha.testproject.domain.enums;

import lombok.Getter;

@Getter
public enum EstadosSolicitud {
    REGISTRADA("R"),
    DESPACHADA("D"),
    CANCELADA("C");

    private final String valor;

    EstadosSolicitud(String valor){
        this.valor = valor;
    }
}
