/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos.ws;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Conta;
import model.Usuario;
import model.util.DataBaseMemory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author dmartins
 */
@WebServlet(name = "ClienteWs", urlPatterns = {"/clienteWs"})
public class ClienteWs extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

//            String token = request.getParameter("token");
//            
//            if (!DataBaseMemory.validaToken(token)) {
//                out.println("Acesso Negado!!!");
//            } else {
            JSONArray lista = new JSONArray();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            for (Usuario usu : DataBaseMemory.getLista()) {
                JSONObject jo = new JSONObject();
                try {
                    jo.put("id", usu.getId());
                    jo.put("nome", usu.getNome());
                    jo.put("idade", usu.getIdade());
                    jo.put("dataAssociacao", sdf.format(usu.getDataAssociacao()));
                    jo.put("valorMensalidade", usu.getValorMensalidade());

                    JSONObject jo1 = new JSONObject();
                    jo1.put("id", usu.getCidade().getId());
                    jo1.put("nome", usu.getCidade().getNome());
                    jo1.put("estado", usu.getCidade().getEstado());

                    jo.put("cidade", jo1);

                    lista.put(jo);
                } catch (JSONException ex) {
                }
//                }
            }
            out.println(lista.toString());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

//    private static Boolean validaToken(java.lang.String token) {
//        servicos.ws.client.ClienteService_Service service = new servicos.ws.client.ClienteService_Service();
//        servicos.ws.client.ClienteService port = service.getClienteServicePort();
//        return port.validaToken(token);
//    }
}
