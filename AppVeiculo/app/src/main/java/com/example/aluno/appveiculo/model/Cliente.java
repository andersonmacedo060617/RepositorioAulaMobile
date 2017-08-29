package com.example.aluno.appveiculo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by aluno on 23/08/2017.
 */

public class Cliente extends Usuario {

    private boolean pago;
    private Date vencimento;
    private List<Veiculo> veiculos;

    public Cliente() {
        this.veiculos = new ArrayList<>();
    }

    public Cliente(int id, String nome, String login, String senha, boolean pago, Date vencimento) {
        super(id, nome, login, senha);
        this.pago = pago;
        this.vencimento = vencimento;
        this.veiculos = new ArrayList<>();
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public Date getVencimento() {
        return vencimento;
    }

    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
    }

    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }
}
