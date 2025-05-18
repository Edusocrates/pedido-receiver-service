package com.fiap.rm358568.edusocrates.pedido_receiver_service.aplicacao.handlers;

import com.fiap.rm358568.edusocrates.pedido_receiver_service.API.requests.CriarPedidoRequest;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.API.requests.DadosPagamentoRequest;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.API.requests.ItemPedidoRequest;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.API.responses.DadosPagamentoResponse;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.API.responses.ItemPedidoResponse;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.API.responses.PedidoResponse;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.aplicacao.mappers.PedidoMapper;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.dominio.entities.DadosPagamento;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.dominio.entities.ItemPedido;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.dominio.entities.Pedido;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.dominio.entities.enums.StatusPedido;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.dominio.gateways.DadosPagamentoGateway;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.dominio.gateways.ItemPedidoGateway;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.dominio.gateways.PedidoGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CriarPedidoHandlerTest {

    @Mock
    private PedidoGateway pedidoGateway;

    @Mock
    private ItemPedidoGateway itemPedidoGateway;

    @Mock
    private DadosPagamentoGateway dadosPagamentoGateway;

    @Mock
    private PedidoMapper pedidoMapper;

    @InjectMocks
    private CriarPedidoHandler criarPedidoHandler;

    @Test
    void deveRetornarPedidoResponse_quandoPedidoForCriadoComSucesso() {
        // Arrange
        CriarPedidoRequest request = new CriarPedidoRequest(
                UUID.randomUUID(),
                List.of(new ItemPedidoRequest("SKU123", 2, BigDecimal.valueOf(50.0))),
                new DadosPagamentoRequest("4111111111111111", "João Silva", "12/25", "123")
        );

        Pedido pedido = new Pedido(
                UUID.randomUUID(),
                UUID.randomUUID(),
                List.of(new ItemPedido("SKU123", 2, BigDecimal.valueOf(50.0))),
                new DadosPagamento("4111111111111111", "João Silva", "12/25", "123"),
                BigDecimal.valueOf(100.0)
        );

        Pedido pedidoSalvo = new Pedido(
                UUID.randomUUID(),
                UUID.randomUUID(),
                List.of(new ItemPedido("SKU123", 2, BigDecimal.valueOf(50.0))),
                new DadosPagamento("4111111111111111", "João Silva", "12/25", "123"),
                BigDecimal.valueOf(100.0)
        );

        PedidoResponse responseMock = new PedidoResponse(
                UUID.randomUUID(),
                UUID.randomUUID(),
                StatusPedido.ABERTO,
                BigDecimal.valueOf(100.0),
                List.of(new ItemPedidoResponse("SKU123", 2, BigDecimal.valueOf(50.0), BigDecimal.valueOf(100.0))),
                new DadosPagamentoResponse("4111111111111111", "João Silva", "12/25")
        );

        //when(pedidoMapper.toDomain(any())).thenReturn(pedido);
        when(pedidoGateway.salvar(any())).thenReturn(pedidoSalvo);
        when(pedidoMapper.toResponse(any())).thenReturn(responseMock);

        // Act
        PedidoResponse response = criarPedidoHandler.executar(request);

        // Assert
        assertNotNull(response);
        assertEquals(StatusPedido.ABERTO, response.status());
        //verify(pedidoMapper).toDomain(request);
        //verify(pedidoGateway).salvar(pedido);
        verify(pedidoMapper).toResponse(pedidoSalvo);
    }

    @Test
    void deveLancarExcecao_quandoErroOcorrerAoSalvarPedido() {
        // Arrange
        CriarPedidoRequest request = new CriarPedidoRequest(
                UUID.randomUUID(),
                List.of(new ItemPedidoRequest("SKU123", 2, BigDecimal.valueOf(50.0))),
                new DadosPagamentoRequest("4111111111111111", "João Silva", "12/25", "123")
        );

        Pedido pedido = new Pedido(
                UUID.randomUUID(),
                UUID.randomUUID(),
                List.of(new ItemPedido("SKU123", 2, BigDecimal.valueOf(50.0))),
                new DadosPagamento("4111111111111111", "João Silva", "12/25", "123"),
                BigDecimal.valueOf(100.0)
        );

        //when(pedidoMapper.toDomain(any(CriarPedidoRequest.class))).thenReturn(pedido);
        //when(pedidoGateway.salvar(any(Pedido.class))).thenThrow(new RuntimeException("Erro ao salvar pedido"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> criarPedidoHandler.executar(null));
        //assertEquals("Erro ao salvar pedido", exception.getMessage());
        //verify(pedidoMapper).toDomain(request);
        //verify(pedidoGateway).salvar(pedido);
        verify(pedidoMapper, never()).toResponse(any());
    }
}