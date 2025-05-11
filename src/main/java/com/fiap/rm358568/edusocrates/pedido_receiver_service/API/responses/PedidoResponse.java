package com.fiap.rm358568.edusocrates.pedido_receiver_service.API.responses;

import com.fiap.rm358568.edusocrates.pedido_receiver_service.dominio.entities.enums.StatusPedido;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record PedidoResponse(
        UUID id,
        UUID clienteId,
        StatusPedido status,
        BigDecimal valorTotal,
        List<ItemPedidoResponse> itens,
        DadosPagamentoResponse dadosPagamento
) {}

