package com.fiap.rm358568.edusocrates.pedido_receiver_service.aplicacao.usecases;

import com.fiap.rm358568.edusocrates.pedido_receiver_service.dominio.entities.enums.StatusPedido;

import java.util.UUID;

public interface AtualizarStatusPedidoUseCase {
    void execute(UUID pedidoId, StatusPedido statusPedido);
}
