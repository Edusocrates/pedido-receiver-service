package com.fiap.rm358568.edusocrates.pedido_receiver_service.infraestrutura.messaging;


import com.fiap.rm358568.edusocrates.pedido_receiver_service.infraestrutura.messaging.events.EstornarPagamentoDTO;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.infraestrutura.messaging.events.SolicitacaoPagamentoDTO;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.infraestrutura.messaging.producers.PagamentoProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.mockito.Mockito.*;

class PagamentoProducerTest {

    private RabbitTemplate rabbitTemplate;
    private PagamentoProducer pagamentoProducer;

    @BeforeEach
    void setUp() {
        rabbitTemplate = mock(RabbitTemplate.class);
        pagamentoProducer = new PagamentoProducer(rabbitTemplate);
        setField(pagamentoProducer, "routingKeySolicitarPagamento", "solicitar.pagamento");
        setField(pagamentoProducer, "routingKeyEstornarPagamento", "estornar.pagamento");
        setField(pagamentoProducer, "exchange", "pedido-exchange");
    }

    @Test
    void deveEnviarSolicitacaoPagamento() {
        // Arrange
        SolicitacaoPagamentoDTO dto = mock(SolicitacaoPagamentoDTO.class);

        // Act
        pagamentoProducer.enviarSolicitacaoPagamento(dto);

        // Assert
        verify(rabbitTemplate).convertAndSend("pedido-exchange", "solicitar.pagamento", dto);
    }

    @Test
    void deveEnviarSolicitacaoEstorno() {
        // Arrange
        EstornarPagamentoDTO dto = mock(EstornarPagamentoDTO.class);

        // Act
        pagamentoProducer.enviarSolicitacaoEstorno(dto);

        // Assert
        verify(rabbitTemplate).convertAndSend("pedido-exchange", "estornar.pagamento", dto);
    }

    // Utilitário para setar campos privados via reflexão
    private static void setField(Object target, String fieldName, Object value) {
        try {
            var field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(target, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}