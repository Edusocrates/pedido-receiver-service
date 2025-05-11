package com.fiap.rm358568.edusocrates.pedido_receiver_service.infraestrutura.messaging.listeners;

import com.fiap.rm358568.edusocrates.pedido_receiver_service.aplicacao.usecases.AtualizarStatusPedidoUseCase;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.dominio.entities.enums.StatusPedido;
import com.fiap.rm358568.edusocrates.pedido_receiver_service.infraestrutura.messaging.events.PagamentoRespostaDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PagamentoResponseListener {

    private final AtualizarStatusPedidoUseCase atualizarStatusPedidoUseCase;

    @RabbitListener(queues = "#{rabbitMQConfig.PAGAMENTO_RESPOSTA_QUEUE}")
    public void receberRespostaPagamento(PagamentoRespostaDTO respostaDTO) {
        log.info("Recebida resposta de pagamento: {}", respostaDTO);

        if (respostaDTO.sucesso()) {
            atualizarStatusPedidoUseCase.execute(respostaDTO.pedidoId(), StatusPedido.FECHADO_COM_SUCESSO);
        } else {
            atualizarStatusPedidoUseCase.execute(respostaDTO.pedidoId(), StatusPedido.FECHADO_SEM_CREDITO);
        }
    }
}
