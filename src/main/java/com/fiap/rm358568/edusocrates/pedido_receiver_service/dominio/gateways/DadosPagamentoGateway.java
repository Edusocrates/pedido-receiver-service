package com.fiap.rm358568.edusocrates.pedido_receiver_service.dominio.gateways;


import com.fiap.rm358568.edusocrates.pedido_receiver_service.dominio.entities.DadosPagamento;

public interface DadosPagamentoGateway {
    DadosPagamento salvar(DadosPagamento dadosPagamento);
}
