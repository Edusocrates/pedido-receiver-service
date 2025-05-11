package com.fiap.rm358568.edusocrates.pedido_receiver_service.aplicacao.handlers;

import com.fiap.rm358568.edusocrates.pedido_receiver_service.API.requests.CriarPedidoRequest;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.API.responses.PedidoResponse;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.aplicacao.mappers.PedidoMapper;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.aplicacao.usecases.CriarPedidoUseCase;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.dominio.entities.Pedido;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.dominio.gateways.DadosPagamentoGateway;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.dominio.gateways.ItemPedidoGateway;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.dominio.gateways.PedidoGateway;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CriarPedidoHandler implements CriarPedidoUseCase {

    private final PedidoGateway pedidoGateway;
    private final ItemPedidoGateway itemPedidoGateway;
    private final DadosPagamentoGateway dadosPagamentoGateway;
    private final PedidoMapper pedidoMapper;

    @Override
    @Transactional
    public PedidoResponse executar(CriarPedidoRequest request) {
        log.info("Iniciando o cadastro do pedido");
        Pedido pedido = pedidoMapper.toDomain(request);

        dadosPagamentoGateway.salvar(pedido.getDadosPagamento());
        itemPedidoGateway.salvarTodos(pedido.getItens());

        Pedido salvo = pedidoGateway.salvar(pedido);
        log.info("Pedido cadastrado com sucesso: {}", salvo);
        return pedidoMapper.toResponse(salvo);
    }
}

