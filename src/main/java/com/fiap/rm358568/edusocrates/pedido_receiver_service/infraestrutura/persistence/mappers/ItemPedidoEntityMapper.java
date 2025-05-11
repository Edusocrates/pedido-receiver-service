package com.fiap.rm358568.edusocrates.pedido_receiver_service.infraestrutura.persistence.mappers;

import com.fiap.rm358568.edusocrates.pedido_receiver_service.dominio.entities.ItemPedido;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.infraestrutura.persistence.entities.ItemPedidoEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemPedidoEntityMapper {

    public static ItemPedidoEntity toEntity(ItemPedido item) {
        return ItemPedidoEntity.builder()
                .skuProduto(item.getSkuProduto())
                .quantidade(item.getQuantidade())
                .precoUnitario(item.getPrecoUnitario())
                .build();
    }

    public static ItemPedido toDomain(ItemPedidoEntity entity) {
        return new ItemPedido(
                entity.getSkuProduto(),
                entity.getQuantidade(),
                entity.getPrecoUnitario()
        );
    }

    public Iterable<ItemPedidoEntity> toEntityList(List<ItemPedido> itens) {
        return itens.stream()
                .map(ItemPedidoEntityMapper::toEntity)
                .toList();
    }
}
