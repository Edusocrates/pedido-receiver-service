package com.fiap.rm358568.edusocrates.pedido_receiver_service.infraestrutura.persistence.mappers;

import com.fiap.rm358568.edusocrates.pedido_receiver_service.dominio.entities.Pedido;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.infraestrutura.persistence.entities.PedidoEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class PedidoEntityMapper {

    public static PedidoEntity toEntity(Pedido pedido) {
        return PedidoEntity.builder()
                .id(pedido.getId())
                .clienteId(pedido.getClienteId())
                .status(pedido.getStatus())
                .valorTotal(pedido.getValorTotal())
                .itens(pedido.getItens().stream()
                        .map(ItemPedidoEntityMapper::toEntity)
                        .collect(Collectors.toList()))
                .dadosPagamento(DadosPagamentoEntityMapper.toEntity(pedido.getDadosPagamento()))
                .build();
    }

    public Pedido toDomain(PedidoEntity entity) {
        return new Pedido(
                entity.getId(),
                entity.getClienteId(),
                entity.getItens().stream()
                        .map(ItemPedidoEntityMapper::toDomain)
                        .collect(Collectors.toList()),
                entity.getDadosPagamento() != null ? DadosPagamentoEntityMapper.toDomain(entity.getDadosPagamento()) : null,
                entity.getValorTotal()
        );
    }
}