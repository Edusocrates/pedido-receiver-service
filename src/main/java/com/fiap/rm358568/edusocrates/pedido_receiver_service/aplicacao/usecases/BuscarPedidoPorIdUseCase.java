package com.fiap.rm358568.edusocrates.pedido_receiver_service.aplicacao.usecases;

import com.fiap.rm358568.edusocrates.pedido_receiver_service.API.responses.PedidoResponse;

import java.util.UUID;

public interface BuscarPedidoPorIdUseCase {
    PedidoResponse execute(UUID pedidoId);
}