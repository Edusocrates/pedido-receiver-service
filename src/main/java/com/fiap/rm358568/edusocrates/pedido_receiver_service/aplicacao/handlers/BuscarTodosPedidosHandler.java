package com.fiap.rm358568.edusocrates.pedido_receiver_service.aplicacao.handlers;

import com.fiap.rm358568.edusocrates.pedido_receiver_service.API.responses.PedidoResponse;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.aplicacao.mappers.PedidoMapper;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.aplicacao.usecases.BuscarTodosPedidosUseCase;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.dominio.gateways.PedidoGateway;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.infraestrutura.persistence.mappers.PedidoEntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class BuscarTodosPedidosHandler implements BuscarTodosPedidosUseCase {

    private final PedidoGateway pedidoGateway;
    private final PedidoMapper mapper;


    @Override
    public List<PedidoResponse> executar() {
        log.info("Consultando todos os pedidos!");
        return pedidoGateway.buscarTodos()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }
}
