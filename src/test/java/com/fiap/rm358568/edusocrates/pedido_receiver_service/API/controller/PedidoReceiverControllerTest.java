package com.fiap.rm358568.edusocrates.pedido_receiver_service.API.controller;

import com.fiap.rm358568.edusocrates.pedido_receiver_service.API.requests.CriarPedidoRequest;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.API.requests.DadosPagamentoRequest;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.API.requests.ItemPedidoRequest;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.API.responses.DadosPagamentoResponse;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.API.responses.ItemPedidoResponse;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.API.responses.PedidoResponse;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.aplicacao.usecases.AtualizarStatusPedidoUseCase;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.aplicacao.usecases.BuscarPedidoPorIdUseCase;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.aplicacao.usecases.BuscarTodosPedidosUseCase;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.aplicacao.usecases.CriarPedidoUseCase;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.dominio.entities.enums.StatusPedido;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PedidoReceiverControllerTest {

    @Mock
    private CriarPedidoUseCase criarPedidoUseCase;

    @Mock
    private BuscarPedidoPorIdUseCase buscarPedidoPorIdUseCase;

    @Mock
    private BuscarTodosPedidosUseCase buscarTodosPedidosUseCase;

    @Mock
    private AtualizarStatusPedidoUseCase atualizarStatusPedidoUseCase;

    @InjectMocks
    private PedidoReceiverController pedidoReceiverController;

    @Test
    void criarPedido_deveRetornarPedidoResponse_quandoPedidoForCriado() {
        CriarPedidoRequest request = new CriarPedidoRequest(
                UUID.randomUUID(),
                List.of(new ItemPedidoRequest("SKU123", 2, BigDecimal.valueOf(50.0))),
                new DadosPagamentoRequest("4111111111111111", "João Silva", "12/25", "123")
        );
        PedidoResponse responseMock = new PedidoResponse(UUID.randomUUID(), UUID.randomUUID(),StatusPedido.ABERTO, BigDecimal.valueOf(100.0),
                List.of(new ItemPedidoResponse("SKU123", 2, BigDecimal.valueOf(50.0), BigDecimal.valueOf(100.0))),
                new DadosPagamentoResponse("4111111111111111", "João Silva", "12/25")
        );

        when(criarPedidoUseCase.executar(request)).thenReturn(responseMock);

        ResponseEntity<PedidoResponse> response = pedidoReceiverController.criarPedido(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void buscarPorId_deveRetornarPedidoResponse_quandoPedidoExistir() {
        UUID id = UUID.randomUUID();
        PedidoResponse responseMock = new PedidoResponse(UUID.randomUUID(), UUID.randomUUID(),StatusPedido.ABERTO, BigDecimal.valueOf(100.0),
                List.of(new ItemPedidoResponse("SKU123", 2, BigDecimal.valueOf(50.0), BigDecimal.valueOf(100.0))),
                new DadosPagamentoResponse("4111111111111111", "João Silva", "12/25")
        );

        when(buscarPedidoPorIdUseCase.execute(id)).thenReturn(responseMock);

        ResponseEntity<PedidoResponse> response = pedidoReceiverController.buscarPorId(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void buscarTodos_deveRetornarListaDePedidoResponse_quandoExistiremPedidos() {
        List<PedidoResponse> responseMock = List.of(
                new PedidoResponse(UUID.randomUUID(), UUID.randomUUID(),StatusPedido.ABERTO, BigDecimal.valueOf(100.0),
                        List.of(new ItemPedidoResponse("SKU123", 2, BigDecimal.valueOf(50.0), BigDecimal.valueOf(100.0))),
                        new DadosPagamentoResponse("4111111111111111", "João Silva", "12/25")
                ),
                new PedidoResponse(UUID.randomUUID(), UUID.randomUUID(),StatusPedido.ABERTO, BigDecimal.valueOf(100.0),
                        List.of(new ItemPedidoResponse("SKU123", 2, BigDecimal.valueOf(50.0), BigDecimal.valueOf(100.0))),
                        new DadosPagamentoResponse("4111111111111111", "João Silva", "12/25")
                )
        );

        when(buscarTodosPedidosUseCase.executar()).thenReturn(responseMock);

        ResponseEntity<List<PedidoResponse>> response = pedidoReceiverController.buscarTodos();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void atualizarStatus_deveRetornarNoContent_quandoStatusForAtualizado() {
        UUID id = UUID.randomUUID();
        String novoStatus = "FECHADO_COM_SUCESSO";

        doNothing().when(atualizarStatusPedidoUseCase).execute(id, StatusPedido.valueOf(novoStatus));

        ResponseEntity<Void> response = pedidoReceiverController.atualizarStatus(id, novoStatus);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
