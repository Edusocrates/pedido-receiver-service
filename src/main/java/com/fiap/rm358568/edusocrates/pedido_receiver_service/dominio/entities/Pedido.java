package com.fiap.rm358568.edusocrates.pedido_receiver_service.dominio.entities;

import com.fiap.rm358568.edusocrates.pedido_receiver_service.dominio.entities.enums.StatusPedido;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
public class Pedido {
    private final UUID id;
    private final UUID clienteId;
    private final List<ItemPedido> itens;
    private final DadosPagamento dadosPagamento;
    private StatusPedido status;
    private final BigDecimal valorTotal;

    public Pedido(UUID id, UUID clienteId, List<ItemPedido> itens, DadosPagamento dadosPagamento, BigDecimal valorTotal) {
        this.id = id;
        this.clienteId = clienteId;
        this.itens = itens;
        this.dadosPagamento = dadosPagamento;
        this.status = StatusPedido.ABERTO;
        this.valorTotal = valorTotal;
    }

    public BigDecimal calcularTotal() {
        return itens.stream()
                .map(ItemPedido::calcularSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void fecharComSucesso() {
        this.status = StatusPedido.FECHADO_COM_SUCESSO;
    }

    public void fecharSemEstoque() {
        this.status = StatusPedido.FECHADO_SEM_ESTOQUE;
    }

    public void fecharSemCredito() {
        this.status = StatusPedido.FECHADO_SEM_CREDITO;
    }

    public void atualizarStatus(StatusPedido novoStatus) {
        this.status = novoStatus;
    }
}
