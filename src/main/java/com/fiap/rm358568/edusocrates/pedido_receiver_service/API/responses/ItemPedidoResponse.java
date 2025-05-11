package com.fiap.rm358568.edusocrates.pedido_receiver_service.API.responses;

import java.math.BigDecimal;

public record ItemPedidoResponse(
        String skuProduto,
        int quantidade,
        BigDecimal precoUnitario,
        BigDecimal subtotal
) {}

