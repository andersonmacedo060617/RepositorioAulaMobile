package com.example.aluno.appveiculo.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import com.example.aluno.appveiculo.database.DataBase;
import com.example.aluno.appveiculo.model.Administrador;
import com.example.aluno.appveiculo.model.Cliente;
import com.example.aluno.appveiculo.model.Usuario;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by aluno on 23/08/2017.
 */

public class UsuarioDAO {

    private DataBase banco;

    public UsuarioDAO(DataBase banco){
        this.banco = banco;
    }

    public void grava(Usuario user){
        SQLiteDatabase con = banco.getWritableDatabase();

        ContentValues cv = new ContentValues();
        //cv.put(DataBase.USUARIO_ID,user.getId());
        cv.put(DataBase.USUARIO_NOME,user.getNome());
        cv.put(DataBase.USUARIO_LOGIN,user.getLogin());
        cv.put(DataBase.USUARIO_SENHA,user.getSenha());
        if (user instanceof Administrador){
            cv.put(DataBase.USUARIO_TIPO,0);
            cv.put(DataBase.USUARIO_FOTO,((Administrador) user).getFoto());
        }else{
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            cv.put(DataBase.USUARIO_TIPO,1);
            cv.put(DataBase.USUARIO_PAGO, ((Cliente)user).isPago()?1:0 );
            cv.put(DataBase.USUARIO_VENCIMENTO, sdf.format(((Cliente)user).getVencimento()) );
        }
        con.insert(DataBase.TABLE_USUARIO,"",cv);
        con.close();
    }

    public Usuario findLoginAndSenha(Usuario u) {
        SQLiteDatabase con = banco.getWritableDatabase();
        try {
            String sql = "select * from " +
                    DataBase.TABLE_USUARIO+" where "+
                    DataBase.USUARIO_LOGIN+"='"+u.getLogin()+"' and " +
                    DataBase.USUARIO_SENHA+"='"+u.getSenha()+"' ";
            Cursor c = con.rawQuery (sql,null);
            //c.moveToFirst();
            Usuario user = null;
            if (c.moveToNext()){
                if (c.getInt( c.getColumnIndex( DataBase.USUARIO_TIPO ) ) ==0  ){
                    user = new Administrador();
                    ((Administrador)user).
                            setFoto(c.getString( c.getColumnIndex( DataBase.USUARIO_FOTO ) ));
                }else{
                    user = new Cliente();
                    ((Cliente)user).
                            setPago(c.getInt( c.getColumnIndex( DataBase.USUARIO_PAGO ) )==1);
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date dt = sdf.parse(c.getString( c.getColumnIndex( DataBase.USUARIO_VENCIMENTO ) ));
                    ((Cliente)user).setVencimento(dt);
                }
                user.setId(c.getInt( c.getColumnIndex( DataBase.USUARIO_ID ) ));
                user.setNome(c.getString( c.getColumnIndex( DataBase.USUARIO_NOME ) ));
                user.setLogin(c.getString( c.getColumnIndex( DataBase.USUARIO_LOGIN ) ));
                user.setSenha(c.getString( c.getColumnIndex( DataBase.USUARIO_SENHA ) ));
            }
            return user;
        } catch (ParseException e) {
            return null;
        } finally {
            con.close();
        }
        /*if (u.getLogin().equals("admin") && u.getSenha().equals("123")){
            return new Administrador(1,"Administrador","admin","123","");
        }else if (u.getLogin().equals("zezin") && u.getSenha().equals("321")){
            return new Cliente(2,"Zezin da Silva","zezin","321",true,new Date());
        }else {
            return null;
        }*/

    }

    public int getQuantidadeAdministrador() {
        SQLiteDatabase con = banco.getWritableDatabase();
        try {
            String[] colunas = {DataBase.USUARIO_ID};
            String where = DataBase.USUARIO_TIPO+"=0";
            Cursor c = con.query(true,DataBase.TABLE_USUARIO,colunas,where,null
                    ,"","","","");
            c.moveToFirst();
            return c.getCount();
        }finally {
            con.close();
        }
    }

    public int getQuantidadeCliente() {
        SQLiteDatabase con = banco.getWritableDatabase();
        try {
            String sql = "select "+DataBase.USUARIO_ID+" from " +
                    DataBase.TABLE_USUARIO+" where "+DataBase.USUARIO_TIPO+"=1";
            Cursor c = con.rawQuery (sql,null);
            c.moveToFirst();
            return c.getCount();
        }finally {
            con.close();
        }
    }

    public List<Usuario> findAll() {
        SQLiteDatabase con = banco.getWritableDatabase();
        try {
            String sql = "select * from " + DataBase.TABLE_USUARIO;
            Cursor c = con.rawQuery (sql,null);
            List<Usuario> lista = new ArrayList<>();
            while (c.moveToNext()){
                Usuario user = null;
                if (c.getInt( c.getColumnIndex( DataBase.USUARIO_TIPO ) ) ==0  ){
                    user = new Administrador();
                    ((Administrador)user).
                            setFoto(c.getString( c.getColumnIndex( DataBase.USUARIO_FOTO ) ));
                }else{
                    user = new Cliente();
                    ((Cliente)user).
                            setPago(c.getInt( c.getColumnIndex( DataBase.USUARIO_PAGO ) )==1);
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date dt = sdf.parse(c.getString( c.getColumnIndex( DataBase.USUARIO_VENCIMENTO ) ));
                    ((Cliente)user).setVencimento(dt);
                }
                user.setId(c.getInt( c.getColumnIndex( DataBase.USUARIO_ID ) ));
                user.setNome(c.getString( c.getColumnIndex( DataBase.USUARIO_NOME ) ));
                user.setLogin(c.getString( c.getColumnIndex( DataBase.USUARIO_LOGIN ) ));
                user.setSenha(c.getString( c.getColumnIndex( DataBase.USUARIO_SENHA ) ));
                lista.add(user);
            }
            return lista;
        } catch (ParseException e) {
            return null;
        } finally {
            con.close();
        }

    }

    public List<Usuario> findByName(String s) {
        SQLiteDatabase con = banco.getWritableDatabase();
        try {
            String sql = "select * from " + DataBase.TABLE_USUARIO + " where " + DataBase.USUARIO_NOME + " like '%" + s + "%'";
            Cursor c = con.rawQuery (sql,null);
            List<Usuario> lista = new ArrayList<>();
            while (c.moveToNext()){
                Usuario user = null;
                if (c.getInt( c.getColumnIndex( DataBase.USUARIO_TIPO ) ) ==0  ){
                    user = new Administrador();
                    ((Administrador)user).
                            setFoto(c.getString( c.getColumnIndex( DataBase.USUARIO_FOTO ) ));
                }else{
                    user = new Cliente();
                    ((Cliente)user).
                            setPago(c.getInt( c.getColumnIndex( DataBase.USUARIO_PAGO ) )==1);
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date dt = sdf.parse(c.getString( c.getColumnIndex( DataBase.USUARIO_VENCIMENTO ) ));
                    ((Cliente)user).setVencimento(dt);
                }
                user.setId(c.getInt( c.getColumnIndex( DataBase.USUARIO_ID ) ));
                user.setNome(c.getString( c.getColumnIndex( DataBase.USUARIO_NOME ) ));
                user.setLogin(c.getString( c.getColumnIndex( DataBase.USUARIO_LOGIN ) ));
                user.setSenha(c.getString( c.getColumnIndex( DataBase.USUARIO_SENHA ) ));
                lista.add(user);
            }
            return lista;
        } catch (ParseException e) {
            return null;
        } finally {
            con.close();
        }
    }
}
