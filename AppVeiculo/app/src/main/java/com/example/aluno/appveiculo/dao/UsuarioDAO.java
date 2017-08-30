package com.example.aluno.appveiculo.dao;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.aluno.appveiculo.database.DataBase;
import com.example.aluno.appveiculo.model.Cliente;
import com.example.aluno.appveiculo.model.Usuario;
import com.example.aluno.appveiculo.model.Administrador;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by aluno on 23/08/2017.
 */

public class UsuarioDAO {

    private DataBase banco;

    public UsuarioDAO(DataBase banco){
        this.banco = banco;
    }

    public void gravar(Usuario user){
        SQLiteDatabase con = this.banco.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(banco.USUARIO_ID, user.getId());
        cv.put(banco.USUARIO_NOME, user.getNome());
        cv.put(banco.USUARIO_LOGIN, user.getLogin());
        cv.put(banco.USUARIO_SENHA, user.getSenha());

        if(user instanceof Administrador){
            cv.put(banco.USUARIO_TIPO, 0);
            cv.put(banco.USUARIO_FOTO, "");
        }else{
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            cv.put(banco.USUARIO_TIPO, 1);
            cv.put(banco.USUARIO_PAGO, ((Cliente)user).isPago()?1:0);
            cv.put(banco.USUARIO_VENCIMENTO, df.format(((Cliente)user).getVencimento()));
        }

        con.insert(banco.TABLE_USUARIO, "", cv);
        con.close();
    }

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
