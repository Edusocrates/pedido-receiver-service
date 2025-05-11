package com.fiap.rm358568.edusocrates.pedido_receiver_service.aplicacao.handlers;


import com.fiap.rm358568.edusocrates.pedido_receiver_service.API.exceptions.PedidoNotFoundException;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.API.responses.PedidoResponse;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.aplicacao.mappers.PedidoMapper;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.aplicacao.usecases.BuscarPedidoPorIdUseCase;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.dominio.gateways.PedidoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BuscarPedidoPorIdHandler implements BuscarPedidoPorIdUseCase {

    private final PedidoGateway pedidoGateway;
    private final PedidoMapper mapper;

    @Override
    public PedidoResponse execute(UUID pedidoId) {
        return pedidoGateway.buscarPorId(pedidoId)
                .map(mapper::toResponse)
                .orElseThrow(() -> new PedidoNotFoundException("Pedido com ID " + pedidoId + " não encontrado"));
    }
}
