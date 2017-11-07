/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.util;

import java.util.ArrayList;
import java.util.Date;
import model.Cidade;
import model.Conta;
import model.Usuario;

/**
 *
 * @author dmartins
 */
public class DataBaseMemory {
    
    private static ArrayList<Usuario> lista;
    private static ArrayList<Conta> listaConta;

    public static ArrayList<Usuario> getLista() {
        return lista;
    }

    public static ArrayList<Conta> getListaConta() {
        return listaConta;
    }
    
    
    public static Boolean validaToken(String token) {
        
        for (Conta cont : getListaConta()) {
            if (cont.getToken().equals(token)){
                return true;
            }
        }        
        return false;
    }

    
    static {   
        listaConta = new ArrayList<>();
        
        listaConta.add(new Conta(1, 1, "Isabelinha", "isa", "asi"));
        listaConta.add(new Conta(2, 2, "Joaninha", "juju", "juju"));
        
        lista = new ArrayList<>();
        
        Usuario usu = new Usuario(1, "Zezin", 15, new Date(), 150.00);
        Usuario usu1 = new Usuario(2, "Pedrin", 24, new Date(), 75.00);
        Usuario usu2 = new Usuario(3, "Gustin", 14, new Date(), 89.00);
        Usuario usu3 = new Usuario(4, "Gerfin", 8, new Date(), 180.00);
        Usuario usu4 = new Usuario(5, "Rosinha", 13, new Date(), 110.00);
        
        Cidade cid = new Cidade(1, "Juiz de Fora", "MG");
        Cidade cid1 = new Cidade(2, "Rio de Janeiro", "RJ");
        Cidade cid2 = new Cidade(3, "São Paulo", "SP");
        
        usu.setCidade(cid);
        usu1.setCidade(cid1);
        usu2.setCidade(cid2);
        usu3.setCidade(cid1);
        usu4.setCidade(cid2);
        
        lista.add(usu);
        lista.add(usu1);
        lista.add(usu2);
        lista.add(usu3);
        lista.add(usu4);        
    }
    
}
