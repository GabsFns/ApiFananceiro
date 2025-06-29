package com.example.ApiBlockChainFinancer.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "support_tickets")
public class SupportTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(length = 5000)
    private String descricao;

    private String status; // ABERTO, EM_ATENDIMENTO, FINALIZADO

    private LocalDateTime dataCriacao;

    private LocalDateTime dataFinalizacao;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User usuario;
}
