package com.fiap.rm358568.edusocrates.pedido_receiver_service.infraestrutura.messaging.events;

import java.math.BigDecimal;
import java.util.UUID;

public record EstornarPagamentoDTO(
        UUID pedidoId,
        BigDecimal valorEstorno
) {}

