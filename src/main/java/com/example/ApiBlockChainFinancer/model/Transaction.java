package com.example.ApiBlockChainFinancer.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipoTransacao;
    private Double valor;
    private String moeda;
    private Double taxa;
    private String status;
    private LocalDateTime dataTransacao;
    private String hashTransacao;

    @ManyToOne
    @JoinColumn(name = "remetente_id")
    private User remetente;

    @ManyToOne
    @JoinColumn(name = "destinatario_id")
    private User destinatario;

    private String observacao;
}
