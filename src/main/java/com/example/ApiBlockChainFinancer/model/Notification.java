package com.example.ApiBlockChainFinancer.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String mensagem;

    private boolean lida;

    private LocalDateTime dataEnvio;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User usuario;
}
