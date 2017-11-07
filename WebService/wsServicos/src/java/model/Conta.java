/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Objects;

/**
 *
 * @author dmartins
 */
public class Conta {

    private int id, tipoAcesso;
    private String nome, login, senha;

    public String getToken() {
        return "TKN" + nome.toUpperCase().substring(1, 2) + hashCode();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + this.id;
        hash = 41 * hash + this.tipoAcesso;
        hash = 41 * hash + Objects.hashCode(this.nome);
        hash = 41 * hash + Objects.hashCode(this.login);
        hash = 41 * hash + Objects.hashCode(this.senha);
        return hash;
    }

//    public static void main(String[] args) {
//        Conta c = new Conta(1, 1, "zezin", "ze", "ze");
//        System.out.println("=> "+c.hashCode());
//        System.out.println("=> "+c.getToken());
//        Conta c1 = new Conta(1, 1, "zezin adas", "ze", "ze");
//        System.out.println("=> "+c1.hashCode());
//        System.out.println("=> "+c1.getToken());
//    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Conta other = (Conta) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.tipoAcesso != other.tipoAcesso) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        if (!Objects.equals(this.senha, other.senha)) {
            return false;
        }
        return true;
    }

    public Conta(int id, int tipoAcesso, String nome, String login, String senha) {
        this.id = id;
        this.tipoAcesso = tipoAcesso;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }

    public Conta() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTipoAcesso() {
        return tipoAcesso;
    }

    public void setTipoAcesso(int tipoAcesso) {
        this.tipoAcesso = tipoAcesso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
