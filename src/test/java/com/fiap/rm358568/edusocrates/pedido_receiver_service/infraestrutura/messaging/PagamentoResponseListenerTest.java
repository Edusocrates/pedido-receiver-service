package com.fiap.rm358568.edusocrates.pedido_receiver_service.infraestrutura.messaging;

import com.fiap.rm358568.edusocrates.pedido_receiver_service.aplicacao.usecases.AtualizarStatusPedidoUseCase;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.dominio.entities.enums.StatusPedido;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.infraestrutura.messaging.events.PagamentoRespostaDTO;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.infraestrutura.messaging.listeners.PagamentoResponseListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.mockito.Mockito.*;

class PagamentoResponseListenerTest {

    private AtualizarStatusPedidoUseCase atualizarStatusPedidoUseCase;
    private PagamentoResponseListener listener;

    @BeforeEach
    void setUp() {
        atualizarStatusPedidoUseCase = mock(AtualizarStatusPedidoUseCase.class);
        listener = new PagamentoResponseListener(atualizarStatusPedidoUseCase);
    }

    @Test
    void deveAtualizarStatusParaFechadoComSucesso_quandoPagamentoForAprovado() {
        // Arrange
        UUID pedidoId = UUID.randomUUID();
        PagamentoRespostaDTO respostaDTO = new PagamentoRespostaDTO(pedidoId, true, "Pagamento aprovado");

        // Act
        listener.receberRespostaPagamento(respostaDTO);

        // Assert
        verify(atualizarStatusPedidoUseCase).execute(pedidoId, StatusPedido.FECHADO_COM_SUCESSO);
    }

    @Test
    void deveAtualizarStatusParaFechadoSemCredito_quandoPagamentoForRecusado() {
        // Arrange
        UUID pedidoId = UUID.randomUUID();
        PagamentoRespostaDTO respostaDTO = new PagamentoRespostaDTO(pedidoId, false, "Pagamento recusado");

        // Act
        listener.receberRespostaPagamento(respostaDTO);

        // Assert
        verify(atualizarStatusPedidoUseCase).execute(pedidoId, StatusPedido.FECHADO_SEM_CREDITO);
    }
}