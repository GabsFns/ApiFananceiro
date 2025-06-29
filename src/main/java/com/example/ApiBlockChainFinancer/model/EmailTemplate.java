package com.example.ApiBlockChainFinancer.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "email_templates")
public class EmailTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String assunto;

    @Column(length = 10000)
    private String corpoHtml;

    private LocalDateTime criadoEm;
}
