package com.fiap.rm358568.edusocrates.pedido_receiver_service.API.responses;

public record DadosPagamentoResponse(
        String numeroCartao,
        String nomeTitular,
        String dataValidade
) {}

