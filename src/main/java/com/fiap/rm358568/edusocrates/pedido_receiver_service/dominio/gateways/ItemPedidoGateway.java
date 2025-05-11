package com.fiap.rm358568.edusocrates.pedido_receiver_service.dominio.gateways;

import com.fiap.rm358568.edusocrates.pedido_receiver_service.dominio.entities.ItemPedido;

import java.util.List;

public interface ItemPedidoGateway {
    List<ItemPedido> salvarTodos(List<ItemPedido> itens);
}
