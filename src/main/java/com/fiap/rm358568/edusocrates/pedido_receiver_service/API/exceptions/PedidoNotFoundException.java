package com.fiap.rm358568.edusocrates.pedido_receiver_service.API.exceptions;

public class PedidoNotFoundException extends RuntimeException {
    public PedidoNotFoundException(String message) {
        super(message);
    }
}
