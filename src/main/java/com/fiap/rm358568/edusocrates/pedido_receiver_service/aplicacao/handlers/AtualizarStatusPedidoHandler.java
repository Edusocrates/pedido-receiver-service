package com.fiap.rm358568.edusocrates.pedido_receiver_service.aplicacao.handlers;

import com.fiap.rm358568.edusocrates.pedido_receiver_service.API.exceptions.PedidoNotFoundException;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.aplicacao.usecases.AtualizarStatusPedidoUseCase;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.dominio.entities.enums.StatusPedido;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.dominio.gateways.PedidoGateway;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AtualizarStatusPedidoHandler implements AtualizarStatusPedidoUseCase {

    private final PedidoGateway pedidoGateway;

    @Override
    @Transactional
    public void execute(UUID pedidoId, StatusPedido statusPedido) {
        log.info("Atualizando status do pedido com ID: {}", pedidoId);
        StatusPedido novoStatus = statusPedido;

        var pedido = pedidoGateway.buscarPorId(pedidoId)
                .orElseThrow(() -> new PedidoNotFoundException("Pedido com ID " + pedidoId + " não encontrado"));

        log.info("Pedido encontrado! o Pedido: {} sera atualizado!", pedido);
        pedido.atualizarStatus(novoStatus);
        pedidoGateway.atualizar(pedido);
    }
}
