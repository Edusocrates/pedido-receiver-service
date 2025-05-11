package com.fiap.rm358568.edusocrates.pedido_receiver_service.infraestrutura.persistence.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "dados_pagamento")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DadosPagamentoEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String numeroCartao;

    @Column(nullable = false)
    private String nomeTitular;

    @Column(nullable = false)
    private String dataValidade;

    @Column(nullable = false)
    private String cvv;
}
