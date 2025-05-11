package com.fiap.rm358568.edusocrates.pedido_receiver_service.infraestrutura.messaging.listeners;


import com.fiap.rm358568.edusocrates.pedido_receiver_service.aplicacao.usecases.AtualizarStatusPedidoUseCase;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.dominio.entities.enums.StatusPedido;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.infraestrutura.messaging.events.EstoqueRespostaDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EstoqueResponseListener {

    private final AtualizarStatusPedidoUseCase atualizarStatusPedidoUseCase;

    @RabbitListener(queues = "#{rabbitMQConfig.ESTOQUE_RESPOSTA_QUEUE}")
    public void receberRespostaEstoque(EstoqueRespostaDTO respostaDTO) {
        log.info("Recebida resposta do estoque: {}", respostaDTO);

        if (respostaDTO.sucesso()) {
            atualizarStatusPedidoUseCase.execute(respostaDTO.pedidoId(), StatusPedido.ABERTO);
        } else {
            atualizarStatusPedidoUseCase.execute(respostaDTO.pedidoId(), StatusPedido.FECHADO_SEM_ESTOQUE);
        }
    }
}
