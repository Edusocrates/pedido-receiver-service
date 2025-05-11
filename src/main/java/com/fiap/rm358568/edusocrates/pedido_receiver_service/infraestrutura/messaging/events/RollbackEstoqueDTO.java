package com.fiap.rm358568.edusocrates.pedido_receiver_service.infraestrutura.messaging.events;

import java.util.UUID;

public record RollbackEstoqueDTO(
        UUID pedidoId,
        String sku,
        Integer quantidade
) {}

