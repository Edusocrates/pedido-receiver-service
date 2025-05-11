package com.fiap.rm358568.edusocrates.pedido_receiver_service.infraestrutura.persistence.mappers;

import com.fiap.rm358568.edusocrates.pedido_receiver_service.dominio.entities.DadosPagamento;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.infraestrutura.persistence.entities.DadosPagamentoEntity;
import org.springframework.stereotype.Component;

@Component
public class DadosPagamentoEntityMapper {

    public static DadosPagamentoEntity toEntity(DadosPagamento pagamento) {
        return DadosPagamentoEntity.builder()
                .numeroCartao(pagamento.getNumeroCartao())
                .nomeTitular(pagamento.getNomeTitular())
                .dataValidade(pagamento.getDataValidade())
                .cvv(pagamento.getCvv())
                .build();
    }

    public static DadosPagamento toDomain(DadosPagamentoEntity entity) {
        return new DadosPagamento(
                entity.getNumeroCartao(),
                entity.getNomeTitular(),
                entity.getDataValidade(),
                entity.getCvv()
        );
    }
}
