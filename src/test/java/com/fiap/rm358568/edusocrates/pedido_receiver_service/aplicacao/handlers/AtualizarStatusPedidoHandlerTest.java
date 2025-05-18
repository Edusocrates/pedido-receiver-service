package com.fiap.rm358568.edusocrates.pedido_receiver_service.aplicacao.handlers;


import com.fiap.rm358568.edusocrates.pedido_receiver_service.API.exceptions.PedidoNotFoundException;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.dominio.entities.Pedido;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.dominio.entities.enums.StatusPedido;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.dominio.gateways.PedidoGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AtualizarStatusPedidoHandlerTest {

    @Mock
    private PedidoGateway pedidoGateway;

    @InjectMocks
    private AtualizarStatusPedidoHandler handler;

    @Test
    void deveAtualizarStatusDoPedido_quandoPedidoExistir() {
        // Arrange
        UUID pedidoId = UUID.randomUUID();
        StatusPedido novoStatus = StatusPedido.FECHADO_COM_SUCESSO;
        Pedido pedido = mock(Pedido.class);

        when(pedidoGateway.buscarPorId(pedidoId)).thenReturn(Optional.of(pedido));

        // Act
        handler.execute(pedidoId, novoStatus);

        // Assert
        verify(pedidoGateway).buscarPorId(pedidoId);
        verify(pedido).atualizarStatus(novoStatus);
        verify(pedidoGateway).atualizar(pedido);
    }

    @Test
    void deveLancarPedidoNotFoundException_quandoPedidoNaoExistir() {
        // Arrange
        UUID pedidoId = UUID.randomUUID();
        StatusPedido novoStatus = StatusPedido.FECHADO_COM_SUCESSO;

        when(pedidoGateway.buscarPorId(pedidoId)).thenReturn(Optional.empty());

        // Act & Assert
        PedidoNotFoundException exception = assertThrows(
                PedidoNotFoundException.class,
                () -> handler.execute(pedidoId, novoStatus)
        );
        assertTrue(exception.getMessage().contains(pedidoId.toString()));
        verify(pedidoGateway).buscarPorId(pedidoId);
        verifyNoMoreInteractions(pedidoGateway);
    }
}