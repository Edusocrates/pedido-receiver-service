package com.fiap.rm358568.edusocrates.pedido_receiver_service.infraestrutura.persistence.gateways;

import com.fiap.rm358568.edusocrates.pedido_receiver_service.dominio.entities.DadosPagamento;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.dominio.gateways.DadosPagamentoGateway;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.infraestrutura.persistence.mappers.DadosPagamentoEntityMapper;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.infraestrutura.persistence.repostories.DadosPagamentoEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DadosPagamentoGatewayImpl implements DadosPagamentoGateway {

    private final DadosPagamentoEntityRepository dadosPagamentoJpaRepository;
    private final DadosPagamentoEntityMapper mapper;

    @Override
    public DadosPagamento salvar(DadosPagamento dadosPagamento) {
        log.info("Salvando dados de pagamento no Banco de Dados! ");
        return mapper.toDomain(
                dadosPagamentoJpaRepository.save(mapper.toEntity(dadosPagamento))
        );
    }
}
