package com.fiap.rm358568.edusocrates.pedido_receiver_service.dominio.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ItemPedido {
    private final String skuProduto;
    private final int quantidade;
    private final BigDecimal precoUnitario;

    public BigDecimal calcularSubtotal() {
        return precoUnitario.multiply(BigDecimal.valueOf(quantidade));
    }
}

