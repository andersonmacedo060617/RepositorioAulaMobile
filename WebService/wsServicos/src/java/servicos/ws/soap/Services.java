/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos.ws.soap;

import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import model.Conta;
import model.Usuario;
import model.util.DataBaseMemory;

/**
 *
 * @author Daves
 */
@WebService(serviceName = "Services")
public class Services {


    /**
     * Operação de Web service
     * @param nome
     * @param token
     * @return 
     */
    @WebMethod(operationName = "findClienteByName")
    public ArrayList<Usuario> findClienteByName(@WebParam(name = "nome") String nome,@WebParam(name = "token") String token)
    {
        if (!validaToken(token)){
            return null;
        }
        ArrayList<Usuario> lista = new ArrayList<>();
        
        for (Usuario usu : DataBaseMemory.getLista()) {
            if (usu.getNome().contains(nome)){
                lista.add(usu);
            }
        }
        return lista;
    }

    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "getToken")
    public String getToken(@WebParam(name = "login") String login, @WebParam(name = "senha") String senha) {
        
        for (Conta cont : DataBaseMemory.getListaConta()) {
            if (cont.getLogin().equals(login) && cont.getSenha().equals(senha)){
                return cont.getToken();
            }
        }        
        return "";
    }

    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "validaToken")
    public Boolean validaToken(@WebParam(name = "token") String token) {
        
        for (Conta cont : DataBaseMemory.getListaConta()) {
            if (cont.getToken().equals(token)){
                return true;
            }
        }        
        return false;
    }    
    
}
