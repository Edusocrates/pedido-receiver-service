package com.fiap.rm358568.edusocrates.pedido_receiver_service.API.requests;

import java.util.List;
import java.util.UUID;

public record CriarPedidoRequest(
        UUID clienteId,
        List<ItemPedidoRequest> itens,
        DadosPagamentoRequest dadosPagamento
) {}

