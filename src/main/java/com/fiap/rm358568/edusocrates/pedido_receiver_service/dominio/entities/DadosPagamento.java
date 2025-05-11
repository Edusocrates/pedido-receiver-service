package com.fiap.rm358568.edusocrates.pedido_receiver_service.dominio.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DadosPagamento {
    private final String numeroCartao;
    private final String nomeTitular;
    private final String dataValidade;
    private final String cvv;
}

