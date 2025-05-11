package com.fiap.rm358568.edusocrates.pedido_receiver_service.infraestrutura.persistence.repostories;

import com.fiap.rm358568.edusocrates.pedido_receiver_service.dominio.entities.enums.StatusPedido;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.infraestrutura.persistence.entities.PedidoEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PedidoEntityRepository extends JpaRepository<PedidoEntity, UUID> {

    @Modifying
    @Transactional
    @Query("UPDATE PedidoEntity p SET p.status = :status WHERE p.id = :id")
    int atualizarStatus(@Param("id") UUID id, @Param("status") StatusPedido status);

}
