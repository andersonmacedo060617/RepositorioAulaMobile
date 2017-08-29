package com.example.aluno.appveiculo.model;

/**
 * Created by aluno on 23/08/2017.
 */

public class Administrador extends Usuario {

    private String foto;

    public Administrador() {
    }

    public Administrador(int id, String nome, String login, String senha, String foto) {
        super(id, nome, login, senha);
        this.foto = foto;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
