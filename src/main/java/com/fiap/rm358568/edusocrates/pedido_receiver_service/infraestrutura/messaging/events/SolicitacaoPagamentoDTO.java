package com.fiap.rm358568.edusocrates.pedido_receiver_service.infraestrutura.messaging.events;

import java.math.BigDecimal;
import java.util.UUID;

public record SolicitacaoPagamentoDTO(
        UUID pedidoId,
        UUID clienteId,
        BigDecimal valorTotal,
        String numeroCartao,
        String nomeTitular,
        String dataValidade,
        String cvv
) {}

