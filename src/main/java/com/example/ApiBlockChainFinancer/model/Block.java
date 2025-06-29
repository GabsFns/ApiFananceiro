package com.example.ApiBlockChainFinancer.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "blocks")
public class Block {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer altura;

    private String hashAnterior;

    private String hashAtual;

    private LocalDateTime dataCriacao;

    private Double dificuldade;

    private Long tempoMineracao; // em ms

    @OneToMany(mappedBy = "block", cascade = CascadeType.ALL)
    private List<Transaction> transactions = new ArrayList<>();
}
