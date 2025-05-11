package com.fiap.rm358568.edusocrates.pedido_receiver_service.infraestrutura.persistence.gateways;

import com.fiap.rm358568.edusocrates.pedido_receiver_service.dominio.entities.Pedido;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.dominio.gateways.PedidoGateway;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.infraestrutura.persistence.mappers.PedidoEntityMapper;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.infraestrutura.persistence.repostories.PedidoEntityRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class PedidoGatewayImpl implements PedidoGateway {

    private final PedidoEntityRepository pedidoJpaRepository;
    private final PedidoEntityMapper mapper;

    @Override
    @Transactional
    public Pedido salvar(Pedido pedido) {
        log.info("Salvando pedido no banco de dados! pedido: {}", pedido);
        return mapper.toDomain(pedidoJpaRepository.save(mapper.toEntity(pedido)));
    }

    @Override
    public Optional<Pedido> buscarPorId(UUID id) {
        log.info("Buscando pedido na base com ID: {}", id);
        return pedidoJpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public void deletarPorId(UUID id) {
        log.info("Deletando pedido da base com ID: {}", id);
        pedidoJpaRepository.deleteById(id);
    }

    @Override
    public List<Pedido> buscarTodos() {
        log.info("Buscando todos os pedidos na base");
        return pedidoJpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    @Transactional
    public int atualizar(Pedido pedido) {
        log.info("Atualizando pedido no banco de dados! pedido: {}", pedido);
        return pedidoJpaRepository.atualizarStatus(pedido.getId(), pedido.getStatus());

    }
}