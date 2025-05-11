package com.fiap.rm358568.edusocrates.pedido_receiver_service.infraestrutura.persistence.repostories;

import com.fiap.rm358568.edusocrates.pedido_receiver_service.infraestrutura.persistence.entities.ItemPedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoEntityRepository extends JpaRepository<ItemPedidoEntity, Long> {
}
