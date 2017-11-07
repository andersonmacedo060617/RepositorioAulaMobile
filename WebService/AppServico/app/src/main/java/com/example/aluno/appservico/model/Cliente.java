package com.example.aluno.appservico.model;

import java.util.Date;

/**
 * Created by aluno on 30/10/2017.
 */

public class Cliente {
    private int id,idade;
    private String nome;
    private double valorMensalidade;
    private Date dataAssociacao;
    private Cidade cidade;

    public Cliente() {
    }

    public Cliente(int id, int idade, String nome, double valorMensalidade, Date dataAssociacao, Cidade cidade) {
        this.id = id;
        this.idade = idade;
        this.nome = nome;
        this.valorMensalidade = valorMensalidade;
        this.dataAssociacao = dataAssociacao;
        this.cidade = cidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValorMensalidade() {
        return valorMensalidade;
    }

    public void setValorMensalidade(double valorMensalidade) {
        this.valorMensalidade = valorMensalidade;
    }

    public Date getDataAssociacao() {
        return dataAssociacao;
    }

    public void setDataAssociacao(Date dataAssociacao) {
        this.dataAssociacao = dataAssociacao;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
}
