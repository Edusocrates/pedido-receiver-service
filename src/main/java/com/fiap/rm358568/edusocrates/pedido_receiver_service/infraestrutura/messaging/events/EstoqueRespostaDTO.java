package com.fiap.rm358568.edusocrates.pedido_receiver_service.infraestrutura.messaging.events;

import java.util.UUID;

public record EstoqueRespostaDTO(
        UUID pedidoId,
        boolean sucesso,
        String mensagem
) {}
