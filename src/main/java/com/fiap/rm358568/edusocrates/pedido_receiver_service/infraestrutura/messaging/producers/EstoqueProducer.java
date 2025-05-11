package com.fiap.rm358568.edusocrates.pedido_receiver_service.infraestrutura.messaging.producers;

import com.fiap.rm358568.edusocrates.pedido_receiver_service.infraestrutura.messaging.events.RollbackEstoqueDTO;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.infraestrutura.messaging.events.SolicitacaoBaixaEstoqueDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EstoqueProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.routing-key.baixa-estoque}")
    private String routingKeyBaixaEstoque;

    @Value("${rabbitmq.exchange.pedido}")
    private String exchange;

    public void enviarSolicitacaoBaixaEstoque(SolicitacaoBaixaEstoqueDTO dto) {
        rabbitTemplate.convertAndSend(exchange, routingKeyBaixaEstoque, dto);
    }

    public void enviarRollbackEstoque(RollbackEstoqueDTO dto) {
        rabbitTemplate.convertAndSend(exchange, "rollback.estoque", dto);
    }
}

