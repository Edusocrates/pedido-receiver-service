package com.fiap.rm358568.edusocrates.pedido_receiver_service.aplicacao.mappers;

import com.fiap.rm358568.edusocrates.pedido_receiver_service.API.requests.CriarPedidoRequest;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.API.requests.DadosPagamentoRequest;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.API.requests.ItemPedidoRequest;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.API.responses.DadosPagamentoResponse;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.API.responses.ItemPedidoResponse;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.API.responses.PedidoResponse;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.dominio.entities.DadosPagamento;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.dominio.entities.ItemPedido;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.dominio.entities.Pedido;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.stream.Collectors;

@Component
public class PedidoMapper {

    public static Pedido toDomain(CriarPedidoRequest request) {
        return new Pedido(
                null, // ID será gerado posteriormente
                request.clienteId(),
                request.itens().stream()
                        .map(PedidoMapper::toDomain)
                        .collect(Collectors.toList()),
                toDomain(request.dadosPagamento()),
                request.itens().stream()
                        .map(item -> item.precoUnitario().multiply(BigDecimal.valueOf(item.quantidade())))
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
        );
    }

    private static ItemPedido toDomain(ItemPedidoRequest itemRequest) {
        return new ItemPedido( // ID será gerado posteriormente
                itemRequest.skuProduto(),
                itemRequest.quantidade(),
                itemRequest.precoUnitario()
        );
    }

    private static DadosPagamento toDomain(DadosPagamentoRequest pagamentoRequest) {
        return new DadosPagamento(
                pagamentoRequest.numeroCartao(),
                pagamentoRequest.nomeTitular(),
                pagamentoRequest.dataValidade(),
                pagamentoRequest.cvv()
        );
    }

    public PedidoResponse toResponse(Pedido salvo) {
        return new PedidoResponse(
                salvo.getId(),
                salvo.getClienteId(),
                salvo.getStatus(),
                salvo.getValorTotal(),
                salvo.getItens().stream()
                        .map(item -> new ItemPedidoResponse(
                                item.getSkuProduto(),
                                item.getQuantidade(),
                                item.getPrecoUnitario(),
                                item.getPrecoUnitario().multiply(BigDecimal.valueOf(item.getQuantidade()))
                        ))
                        .collect(Collectors.toList()),
                new DadosPagamentoResponse(
                        salvo.getDadosPagamento().getNumeroCartao(),
                        salvo.getDadosPagamento().getNomeTitular(),
                        salvo.getDadosPagamento().getDataValidade()
                )
        );
    }
}
