package com.fiap.rm358568.edusocrates.pedido_receiver_service.dominio.gateways;

import com.fiap.rm358568.edusocrates.pedido_receiver_service.dominio.entities.Pedido;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PedidoGateway {
    Pedido salvar(Pedido pedido);
    Optional<Pedido> buscarPorId(UUID id);
    void deletarPorId(UUID id);
    List<Pedido> buscarTodos();
    int atualizar(Pedido pedido);
}
