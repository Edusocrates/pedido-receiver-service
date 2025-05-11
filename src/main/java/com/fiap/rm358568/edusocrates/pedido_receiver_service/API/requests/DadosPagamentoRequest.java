package com.fiap.rm358568.edusocrates.pedido_receiver_service.API.requests;

public record DadosPagamentoRequest(
        String numeroCartao,
        String nomeTitular,
        String dataValidade,
        String cvv
) {}

