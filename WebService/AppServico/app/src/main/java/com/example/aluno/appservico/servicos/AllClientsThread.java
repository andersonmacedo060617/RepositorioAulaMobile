package com.example.aluno.appservico.servicos;

import android.os.AsyncTask;
import android.util.Log;

import com.example.aluno.appservico.model.Cidade;
import com.example.aluno.appservico.model.Cliente;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by aluno on 30/10/2017.
 */

public class AllClientsThread extends AsyncTask<String,Void,List<Cliente>> {


    @Override
    protected List<Cliente> doInBackground(String... strings) {
        String urll = "http://192.168.1.81:8084/wsServicos/clienteWs";

        ArrayList<Cliente> resposta = new ArrayList<>();
        HttpURLConnection conn = null;
        try {
            URL url = new URL(urll);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            //conn.setRequestProperty("token", token);
            conn.connect();

            InputStream in = new BufferedInputStream(conn.getInputStream());
            StringBuilder sb = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(in));) {

                String nextLine = "";
                while ((nextLine = reader.readLine()) != null) {
                    sb.append(nextLine);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            String linha = sb.toString();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            JSONArray jArray = new  JSONArray(linha);

            for(int  i =0;i <jArray.length();i++){
                JSONObject json_data =  jArray.getJSONObject(i);
                Cliente u = new Cliente();
                u.setNome(json_data.getString("nome"));
                u.setId(json_data.getInt("id"));
                u.setIdade(json_data.getInt("idade"));
                u.setValorMensalidade(json_data.getDouble("valorMensalidade"));
                try{
                    u.setDataAssociacao(sdf.parse(json_data.getString("dataAssociacao")) );
                }catch (ParseException ex){
                    u.setDataAssociacao(new Date());
                }
                JSONObject json_cidade =  json_data.getJSONObject("cidade");
                u.setCidade( new Cidade(json_cidade.getInt("id"),json_cidade.getString("nome"),json_cidade.getString("estado")) );
                Log.i("log_tag", "nome:  " + json_data.getString("nome") +
                        ", id:  " + json_data.getInt("id")
                );
                resposta.add(u);
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            conn.disconnect();
            return resposta;
        }

    }
}
