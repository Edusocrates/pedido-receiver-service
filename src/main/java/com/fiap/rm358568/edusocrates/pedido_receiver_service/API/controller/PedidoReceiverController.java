package com.fiap.rm358568.edusocrates.pedido_receiver_service.API.controller;

import com.fiap.rm358568.edusocrates.pedido_receiver_service.API.requests.CriarPedidoRequest;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.API.responses.PedidoResponse;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.aplicacao.usecases.AtualizarStatusPedidoUseCase;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.aplicacao.usecases.BuscarPedidoPorIdUseCase;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.aplicacao.usecases.BuscarTodosPedidosUseCase;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.aplicacao.usecases.CriarPedidoUseCase;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.dominio.entities.enums.StatusPedido;
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
public class PedidoReceiverController {

    private final CriarPedidoUseCase criarPedidoUseCase;
    private final BuscarPedidoPorIdUseCase buscarPedidoPorIdUseCase;
    private final BuscarTodosPedidosUseCase buscarTodosPedidosUseCase;
    private final AtualizarStatusPedidoUseCase atualizarStatusPedidoUseCase;

    @PostMapping
    public ResponseEntity<PedidoResponse> criarPedido(@Valid @RequestBody CriarPedidoRequest request) {
        log.info("Iniciando o cadastro do pedido!");
        PedidoResponse response = criarPedidoUseCase.executar(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponse> buscarPorId(@PathVariable UUID id) {
        log.info("Iniciando Busca pedido com ID: {}", id);
        PedidoResponse response = buscarPedidoPorIdUseCase.execute(id);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/status/{novoStatus}")
    public ResponseEntity<Void> atualizarStatus(
            @PathVariable UUID id,
            @PathVariable String novoStatus
    ) {
        atualizarStatusPedidoUseCase.execute(id, StatusPedido.valueOf(novoStatus));
        return ResponseEntity.noContent().build();
    }
}
