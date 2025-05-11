package com.fiap.rm358568.edusocrates.pedido_receiver_service.API.requests;

import java.math.BigDecimal;

public record ItemPedidoRequest(
        String skuProduto,
        int quantidade,
        BigDecimal precoUnitario
) {}
