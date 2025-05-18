package com.fiap.rm358568.edusocrates.pedido_receiver_service.infraestrutura.messaging;

import com.fiap.rm358568.edusocrates.pedido_receiver_service.infraestrutura.messaging.events.RollbackEstoqueDTO;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.infraestrutura.messaging.events.SolicitacaoBaixaEstoqueDTO;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.infraestrutura.messaging.producers.EstoqueProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.mockito.Mockito.*;

class EstoqueProducerTest {

    private RabbitTemplate rabbitTemplate;
    private EstoqueProducer estoqueProducer;

    @BeforeEach
    void setUp() {
        rabbitTemplate = mock(RabbitTemplate.class);
        estoqueProducer = new EstoqueProducer(rabbitTemplate);
        // Usando reflexão para setar os valores das propriedades @Value
        setField(estoqueProducer, "routingKeyBaixaEstoque", "baixa.estoque");
        setField(estoqueProducer, "exchange", "pedido-exchange");
    }

    @Test
    void deveEnviarSolicitacaoBaixaEstoque() {
        // Arrange
        SolicitacaoBaixaEstoqueDTO dto = mock(SolicitacaoBaixaEstoqueDTO.class);

        // Act
        estoqueProducer.enviarSolicitacaoBaixaEstoque(dto);

        // Assert
        verify(rabbitTemplate).convertAndSend("pedido-exchange", "baixa.estoque", dto);
    }

    @Test
    void deveEnviarRollbackEstoque() {
        // Arrange
        RollbackEstoqueDTO dto = mock(RollbackEstoqueDTO.class);

        // Act
        estoqueProducer.enviarRollbackEstoque(dto);

        // Assert
        verify(rabbitTemplate).convertAndSend("pedido-exchange", "rollback.estoque", dto);
    }

    // Método utilitário para setar campos privados via reflexão
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