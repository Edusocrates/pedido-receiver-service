package com.fiap.rm358568.edusocrates.pedido_receiver_service.infraestrutura.messaging;


import com.fiap.rm358568.edusocrates.pedido_receiver_service.aplicacao.usecases.AtualizarStatusPedidoUseCase;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.dominio.entities.enums.StatusPedido;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.infraestrutura.messaging.events.EstoqueRespostaDTO;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.infraestrutura.messaging.listeners.EstoqueResponseListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.UUID;

import static org.mockito.Mockito.*;

class EstoqueResponseListenerTest {

    private AtualizarStatusPedidoUseCase atualizarStatusPedidoUseCase;
    private EstoqueResponseListener listener;

    @BeforeEach
    void setUp() {
        atualizarStatusPedidoUseCase = mock(AtualizarStatusPedidoUseCase.class);
        listener = new EstoqueResponseListener(atualizarStatusPedidoUseCase);
    }

    @Test
    void deveAtualizarStatusParaAberto_quandoRespostaForSucesso() {
        // Arrange
        UUID pedidoId = UUID.randomUUID();
        EstoqueRespostaDTO respostaDTO = new EstoqueRespostaDTO(pedidoId, true, "Estoque disponível");

        // Act
        listener.receberRespostaEstoque(respostaDTO);

        // Assert
        verify(atualizarStatusPedidoUseCase).execute(pedidoId, StatusPedido.ABERTO);
    }

    @Test
    void deveAtualizarStatusParaFechadoSemEstoque_quandoRespostaForFalha() {
        // Arrange
        UUID pedidoId = UUID.randomUUID();
        EstoqueRespostaDTO respostaDTO = new EstoqueRespostaDTO(pedidoId, false, "Sem estoque");

        // Act
        listener.receberRespostaEstoque(respostaDTO);

        // Assert
        verify(atualizarStatusPedidoUseCase).execute(pedidoId, StatusPedido.FECHADO_SEM_ESTOQUE);
    }
}