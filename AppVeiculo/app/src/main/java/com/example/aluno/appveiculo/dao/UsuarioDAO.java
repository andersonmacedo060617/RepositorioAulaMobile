package com.example.aluno.appveiculo.dao;

import com.example.aluno.appveiculo.model.Cliente;
import com.example.aluno.appveiculo.model.Usuario;
import com.example.aluno.appveiculo.model.Administrador;

import java.util.Date;

/**
 * Created by aluno on 23/08/2017.
 */

public class UsuarioDAO {

    public static Usuario findLoginAndSenha(Usuario u) {

        if(u.getLogin().equals("admin") && u.getSenha().equals("123")){
            return new Administrador(1,"Administrador","admin","123","");
        }else if(u.getLogin().equals("zezin") && u.getSenha().equals("321")){
            return new Cliente(2,"Zezin","zezin","321",true,new Date());

        }else{
            return null;
        }


    }
}
