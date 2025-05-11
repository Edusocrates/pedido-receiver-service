package com.fiap.rm358568.edusocrates.pedido_receiver_service.infraestrutura.persistence.gateways;

import com.fiap.rm358568.edusocrates.pedido_receiver_service.dominio.entities.ItemPedido;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.dominio.gateways.ItemPedidoGateway;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.infraestrutura.persistence.mappers.ItemPedidoEntityMapper;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.infraestrutura.persistence.repostories.ItemPedidoEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ItemPedidoGatewayImpl implements ItemPedidoGateway {

    private final ItemPedidoEntityRepository itemPedidoJpaRepository;
    private final ItemPedidoEntityMapper mapper;

    @Override
    public List<ItemPedido> salvarTodos(List<ItemPedido> itens) {
        log.info("Salvando todos os itens do pedido no banco de dados.");
        return itemPedidoJpaRepository.saveAll(mapper.toEntityList(itens))
                .stream()
                .map(item -> mapper.toDomain(item))
                .toList();
    }
}
