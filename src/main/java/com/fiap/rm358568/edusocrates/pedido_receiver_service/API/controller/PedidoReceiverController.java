package com.fiap.rm358568.edusocrates.pedido_receiver_service.API.controller;

import com.fiap.rm358568.edusocrates.pedido_receiver_service.API.requests.CriarPedidoRequest;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.API.responses.PedidoResponse;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.aplicacao.usecases.AtualizarStatusPedidoUseCase;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.aplicacao.usecases.BuscarPedidoPorIdUseCase;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.aplicacao.usecases.BuscarTodosPedidosUseCase;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.aplicacao.usecases.CriarPedidoUseCase;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.dominio.entities.enums.StatusPedido;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pedidos-receiver")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Configurações Pedido Receiver!", description = "Operações pedido Receiver!")
public class PedidoReceiverController {

    private final CriarPedidoUseCase criarPedidoUseCase;
    private final BuscarPedidoPorIdUseCase buscarPedidoPorIdUseCase;
    private final BuscarTodosPedidosUseCase buscarTodosPedidosUseCase;
    private final AtualizarStatusPedidoUseCase atualizarStatusPedidoUseCase;

    @PostMapping
    @Tag(name = "Criar Pedido Receiver!", description = "Operações criar pedido Receiver!")
    @Operation(summary = "Criar Pedido Receiver!", description = "Operações criar pedido Receiver!")
    public ResponseEntity<PedidoResponse> criarPedido(@Valid @RequestBody CriarPedidoRequest request) {
        log.info("Iniciando o cadastro do pedido!");
        PedidoResponse response = criarPedidoUseCase.executar(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Tag(name = "Buscar Pedido Receiver!", description = "Operações buscar pedido Receiver!")
    public ResponseEntity<PedidoResponse> buscarPorId(@PathVariable UUID id) {
        log.info("Iniciando Busca pedido com ID: {}", id);
        PedidoResponse response = buscarPedidoPorIdUseCase.execute(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Tag(name = "Buscar Todos Pedidos Receiver!", description = "Operações buscar todos pedidos Receiver!")
    @Operation(summary = "Buscar Todos Pedidos Receiver!", description = "Operações buscar todos pedidos Receiver!")
    public ResponseEntity<List<PedidoResponse>> buscarTodos() {
        log.info("Iniciando busca de todos os pedidos");
        List<PedidoResponse> response = buscarTodosPedidosUseCase.executar();
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/status/{novoStatus}")
    @Tag(name = "Atualizar Status Pedido Receiver!", description = "Operações atualizar status pedido Receiver!")
    @Operation(summary = "Atualizar Status Pedido Receiver!", description = "Operações atualizar status pedido Receiver!")
    public ResponseEntity<Void> atualizarStatus(
            @PathVariable UUID id,
            @PathVariable String novoStatus
    ) {
        log.info("Iniciando atualização do status do pedido com ID: {} para o status: {}", id, novoStatus);
        atualizarStatusPedidoUseCase.execute(id, StatusPedido.valueOf(novoStatus));
        return ResponseEntity.noContent().build();
    }
}
