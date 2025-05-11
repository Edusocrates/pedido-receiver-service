package com.fiap.rm358568.edusocrates.pedido_receiver_service.infraestrutura.messaging.producers;

import com.fiap.rm358568.edusocrates.pedido_receiver_service.infraestrutura.messaging.events.EstornarPagamentoDTO;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.infraestrutura.messaging.events.SolicitacaoPagamentoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PagamentoProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.routing-key.solicitar-pagamento}")
    private String routingKeySolicitarPagamento;

    @Value("${rabbitmq.routing-key.estornar-pagamento}")
    private String routingKeyEstornarPagamento;

    @Value("${rabbitmq.exchange.pedido}")
    private String exchange;

    public void enviarSolicitacaoPagamento(SolicitacaoPagamentoDTO dto) {
        rabbitTemplate.convertAndSend(exchange, routingKeySolicitarPagamento, dto);
    }

    public void enviarSolicitacaoEstorno(EstornarPagamentoDTO dto) {
        rabbitTemplate.convertAndSend(exchange, routingKeyEstornarPagamento, dto);
    }
}

