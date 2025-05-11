package com.fiap.rm358568.edusocrates.pedido_receiver_service.infraestrutura.persistence.repostories;

import com.fiap.rm358568.edusocrates.pedido_receiver_service.infraestrutura.persistence.entities.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PedidoEntityRepository extends JpaRepository<PedidoEntity, UUID> {
}
