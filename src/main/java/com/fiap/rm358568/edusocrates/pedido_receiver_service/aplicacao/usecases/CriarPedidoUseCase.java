package com.fiap.rm358568.edusocrates.pedido_receiver_service.aplicacao.usecases;

import com.fiap.rm358568.edusocrates.pedido_receiver_service.API.requests.CriarPedidoRequest;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.API.responses.PedidoResponse;

public interface CriarPedidoUseCase {
    PedidoResponse executar(CriarPedidoRequest request);
}
