package com.example.restaurantefinal.dto;

public class PedidoErrorDTO extends PedidoDTO{

    private String mensajeError;

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

}
