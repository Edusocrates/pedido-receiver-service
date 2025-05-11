package com.fiap.rm358568.edusocrates.pedido_receiver_service.infraestrutura.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // === EXCHANGES ===
    public static final String ESTOQUE_EXCHANGE = "estoque.exchange";
    public static final String PAGAMENTO_EXCHANGE = "pagamento.exchange";
    public static final String PEDIDO_EXCHANGE = "pedido.exchange";

    // === QUEUES ===
    public static final String ESTOQUE_BAIXA_QUEUE = "estoque.baixa.queue";
    public static final String ESTOQUE_ROLLBACK_QUEUE = "estoque.rollback.queue";
    public static final String PAGAMENTO_SOLICITACAO_QUEUE = "pagamento.solicitacao.queue";
    public static final String PAGAMENTO_ESTORNO_QUEUE = "pagamento.estorno.queue";

    public static final String ESTOQUE_RESPOSTA_QUEUE = "estoque.resposta.queue";
    public static final String PAGAMENTO_RESPOSTA_QUEUE = "pagamento.resposta.queue";

    // === ROUTING KEYS ===
    public static final String ESTOQUE_BAIXA_ROUTING_KEY = "estoque.baixa";
    public static final String ESTOQUE_ROLLBACK_ROUTING_KEY = "estoque.rollback";
    public static final String ESTOQUE_RESPOSTA_ROUTING_KEY = "estoque.resposta";

    public static final String PAGAMENTO_SOLICITACAO_ROUTING_KEY = "pagamento.solicitacao";
    public static final String PAGAMENTO_ESTORNO_ROUTING_KEY = "pagamento.estorno";
    public static final String PAGAMENTO_RESPOSTA_ROUTING_KEY = "pagamento.resposta";

    // === EXCHANGE BEANS ===
    @Bean
    public TopicExchange estoqueExchange() {
        return new TopicExchange(ESTOQUE_EXCHANGE);
    }

    @Bean
    public TopicExchange pagamentoExchange() {
        return new TopicExchange(PAGAMENTO_EXCHANGE);
    }

    @Bean
    public TopicExchange pedidoExchange() {
        return new TopicExchange(PEDIDO_EXCHANGE);
    }

    // === QUEUE BEANS ===
    @Bean
    public Queue estoqueBaixaQueue() {
        return new Queue(ESTOQUE_BAIXA_QUEUE);
    }

    @Bean
    public Queue estoqueRollbackQueue() {
        return new Queue(ESTOQUE_ROLLBACK_QUEUE);
    }

    @Bean
    public Queue estoqueRespostaQueue() {
        return new Queue(ESTOQUE_RESPOSTA_QUEUE);
    }

    @Bean
    public Queue pagamentoSolicitacaoQueue() {
        return new Queue(PAGAMENTO_SOLICITACAO_QUEUE);
    }

    @Bean
    public Queue pagamentoEstornoQueue() {
        return new Queue(PAGAMENTO_ESTORNO_QUEUE);
    }

    @Bean
    public Queue pagamentoRespostaQueue() {
        return new Queue(PAGAMENTO_RESPOSTA_QUEUE);
    }

    // === BINDINGS ===
    @Bean
    public Binding bindingEstoqueBaixa() {
        return BindingBuilder.bind(estoqueBaixaQueue()).to(estoqueExchange()).with(ESTOQUE_BAIXA_ROUTING_KEY);
    }

    @Bean
    public Binding bindingEstoqueRollback() {
        return BindingBuilder.bind(estoqueRollbackQueue()).to(estoqueExchange()).with(ESTOQUE_ROLLBACK_ROUTING_KEY);
    }

    @Bean
    public Binding bindingEstoqueResposta() {
        return BindingBuilder.bind(estoqueRespostaQueue()).to(estoqueExchange()).with(ESTOQUE_RESPOSTA_ROUTING_KEY);
    }

    @Bean
    public Binding bindingPagamentoSolicitacao() {
        return BindingBuilder.bind(pagamentoSolicitacaoQueue()).to(pagamentoExchange()).with(PAGAMENTO_SOLICITACAO_ROUTING_KEY);
    }

    @Bean
    public Binding bindingPagamentoEstorno() {
        return BindingBuilder.bind(pagamentoEstornoQueue()).to(pagamentoExchange()).with(PAGAMENTO_ESTORNO_ROUTING_KEY);
    }

    @Bean
    public Binding bindingPagamentoResposta() {
        return BindingBuilder.bind(pagamentoRespostaQueue()).to(pagamentoExchange()).with(PAGAMENTO_RESPOSTA_ROUTING_KEY);
    }

    // === RabbitTemplate (opcional, útil para facilitar o envio de mensagens) ===
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }
}

