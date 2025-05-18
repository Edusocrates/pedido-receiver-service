package com.fiap.rm358568.edusocrates.pedido_receiver_service.aplicacao.handlers;


import com.fiap.rm358568.edusocrates.pedido_receiver_service.API.responses.PedidoResponse;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.aplicacao.mappers.PedidoMapper;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.dominio.entities.Pedido;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.dominio.gateways.PedidoGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BuscarTodosPedidosHandlerTest {

    @Mock
    private PedidoGateway pedidoGateway;

    @Mock
    private PedidoMapper pedidoMapper;

    @InjectMocks
    private BuscarTodosPedidosHandler handler;

    @Test
    void deveRetornarListaDePedidoResponse_quandoExistiremPedidos() {
        // Arrange
        Pedido pedido1 = mock(Pedido.class);
        Pedido pedido2 = mock(Pedido.class);
        List<Pedido> pedidos = Arrays.asList(pedido1, pedido2);

        PedidoResponse response1 = mock(PedidoResponse.class);
        PedidoResponse response2 = mock(PedidoResponse.class);

        when(pedidoGateway.buscarTodos()).thenReturn(pedidos);
        when(pedidoMapper.toResponse(pedido1)).thenReturn(response1);
        when(pedidoMapper.toResponse(pedido2)).thenReturn(response2);

        // Act
        List<PedidoResponse> responses = handler.executar();

        // Assert
        assertNotNull(responses);
        assertEquals(2, responses.size());
        assertTrue(responses.contains(response1));
        assertTrue(responses.contains(response2));
        verify(pedidoGateway).buscarTodos();
        verify(pedidoMapper).toResponse(pedido1);
        verify(pedidoMapper).toResponse(pedido2);
    }

    @Test
    void deveRetornarListaVazia_quandoNaoExistiremPedidos() {
        // Arrange
        when(pedidoGateway.buscarTodos()).thenReturn(List.of());

        // Act
        List<PedidoResponse> responses = handler.executar();

        // Assert
        assertNotNull(responses);
        assertTrue(responses.isEmpty());
        verify(pedidoGateway).buscarTodos();
        verifyNoInteractions(pedidoMapper);
    }
}
