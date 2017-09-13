package com.example.aluno.appveiculo;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.aluno.appveiculo.crud.ECrud;
import com.example.aluno.appveiculo.dao.UsuarioDAO;
import com.example.aluno.appveiculo.database.DataBase;
import com.example.aluno.appveiculo.model.Usuario;

import java.util.List;

public class ListUsuarioActivity extends AppCompatActivity {

    TextView search;
    ImageButton btnSearch;
    ListView listaUser;
    Button btnNewUsuario;
    static final int CADUSER_VIEW = 1;
    List<Usuario> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_usuario);

        binding();
        preencheListView();

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preencheListView();
            }
        });

        btnNewUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itn = new Intent(getApplicationContext(), CadUsuarioActivity.class);
                itn.putExtra("op", ECrud.inserir);
                startActivityForResult(itn, CADUSER_VIEW);
            }
        });

        listaUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                Intent itn = new Intent(getApplicationContext(), CadUsuarioActivity.class);
                itn.putExtra("op", ECrud.visualizar);
                itn.putExtra("user", lista.get(i));
                startActivityForResult(itn, CADUSER_VIEW);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Se retornou da tela de cadadstro
        if(requestCode == CADUSER_VIEW){
            //E o resultado foi com sucesso
            if(resultCode == 1){
                search.setText("");
                preencheListView();
            }
        }
    }

    private void preencheListView() {
        //Buscar os usuários
        DataBase banco = new DataBase(getApplicationContext());

        if(search.getText().toString().isEmpty()){
            lista = new UsuarioDAO(banco).findAll();
        }else{
            lista = new UsuarioDAO(banco).findByName(search.getText().toString());
        }

        //Tranasformar em vetor
        String[] vetor = new String[lista.size()];
        int i = 0;
        for(Usuario u : lista){
            vetor[i++] = u.getNome() +" - "+u.getClass().getCanonicalName();
        }
        //Prencher o Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1,vetor);
        //trocar o adpater
        listaUser.setAdapter(adapter);
        listaUser.setBackgroundColor(Color.BLUE);
    }

    private void binding() {
        search = (TextView) findViewById(R.id.edtSearchNome);
        btnSearch = (ImageButton) findViewById(R.id.btnSearchUser);
        listaUser = (ListView) findViewById(R.id.listUser);
        btnNewUsuario = (Button) findViewById(R.id.btnNewUser);
    }
}
