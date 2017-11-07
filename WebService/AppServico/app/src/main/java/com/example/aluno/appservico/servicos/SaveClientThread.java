package com.example.aluno.appservico.servicos;

import android.os.AsyncTask;
import android.util.Log;

import com.example.aluno.appservico.model.Cidade;
import com.example.aluno.appservico.model.Cliente;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by aluno on 30/10/2017.
 */

public class SaveClientThread extends AsyncTask<String,Void,String> {


    @Override
    protected String doInBackground(String... strings) {
        String urll = "http://192.168.1.81:8084/wsServicos/clienteSave";

        ArrayList<Cliente> resposta = new ArrayList<>();
        HttpURLConnection conn = null;
        try {
            URL url = new URL(urll);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            String valores = "nome="+URLEncoder.encode(strings[0], "UTF-8")+
                    "&idade="+URLEncoder.encode(strings[1], "UTF-8")+
                    "&valor="+URLEncoder.encode(strings[2], "UTF-8");
            conn.getOutputStream().write(valores.getBytes());

            //conn.setRequestProperty("nome", strings[0]);
            //conn.setRequestProperty("idade", strings[1]);
            //conn.setRequestProperty("valor", strings[2]);
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

            return linha;


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
        }
        return "";

    }
}
