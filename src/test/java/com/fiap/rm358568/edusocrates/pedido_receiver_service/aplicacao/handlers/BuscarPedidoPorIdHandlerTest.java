package com.fiap.rm358568.edusocrates.pedido_receiver_service.aplicacao.handlers;


import com.fiap.rm358568.edusocrates.pedido_receiver_service.API.exceptions.PedidoNotFoundException;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.API.responses.PedidoResponse;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.aplicacao.mappers.PedidoMapper;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.dominio.entities.Pedido;
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
class BuscarPedidoPorIdHandlerTest {

    @Mock
    private PedidoGateway pedidoGateway;

    @Mock
    private PedidoMapper pedidoMapper;

    @InjectMocks
    private BuscarPedidoPorIdHandler handler;

    @Test
    void deveRetornarPedidoResponse_quandoPedidoExistir() {
        // Arrange
        UUID pedidoId = UUID.randomUUID();
        Pedido pedido = mock(Pedido.class);
        PedidoResponse response = mock(PedidoResponse.class);

        when(pedidoGateway.buscarPorId(pedidoId)).thenReturn(Optional.of(pedido));
        when(pedidoMapper.toResponse(pedido)).thenReturn(response);

        // Act
        PedidoResponse result = handler.execute(pedidoId);

        // Assert
        assertNotNull(result);
        assertEquals(response, result);
        verify(pedidoGateway).buscarPorId(pedidoId);
        verify(pedidoMapper).toResponse(pedido);
    }

    @Test
    void deveLancarPedidoNotFoundException_quandoPedidoNaoExistir() {
        // Arrange
        UUID pedidoId = UUID.randomUUID();
        when(pedidoGateway.buscarPorId(pedidoId)).thenReturn(Optional.empty());

        // Act & Assert
        PedidoNotFoundException exception = assertThrows(
                PedidoNotFoundException.class,
                () -> handler.execute(pedidoId)
        );
        assertTrue(exception.getMessage().contains(pedidoId.toString()));
        verify(pedidoGateway).buscarPorId(pedidoId);
        verifyNoInteractions(pedidoMapper);
    }
}
