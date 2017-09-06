package com.example.aluno.appveiculo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.aluno.appveiculo.dao.UsuarioDAO;
import com.example.aluno.appveiculo.database.DataBase;
import com.example.aluno.appveiculo.model.Administrador;
import com.example.aluno.appveiculo.model.Cliente;
import com.example.aluno.appveiculo.model.Usuario;

import java.util.Date;

public class PrincipalActivity extends AppCompatActivity {

    final int LOGIN_VIEW = 1;
    final int ADM_VIEW = 2;
    final int CLIENTE_VIEW = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        DataBase conect = new DataBase(getApplicationContext());

        UsuarioDAO uDao = new UsuarioDAO(conect);
        if (uDao.getQuantidadeAdministrador() == 0){
            Administrador adm = new Administrador(1,"Zezin das Coves","ze","123","");
            uDao.grava(adm);
        }
        if (uDao.getQuantidadeCliente() == 0){
            Cliente cli = new Cliente(2,"Pedrin Miranda","ped","123",
                    false,new Date());
            uDao.grava(cli);
        }

        CallLoginView();
    }

    private void CallLoginView() {
        Intent itn = new Intent(getApplicationContext(),LoginActivity.class);
        startActivityForResult(itn, LOGIN_VIEW);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOGIN_VIEW){
          if (resultCode == 1){
              //Administrador
              Usuario user = (Usuario)data.getExtras().getSerializable("usuario");
              Intent itn = new Intent(getApplicationContext(),AdministradorActivity.class);
              itn.putExtra("usuario",user);
              startActivityForResult(itn, ADM_VIEW);
          }else if (resultCode == 2){
              //Cliente
              Usuario user = (Usuario)data.getExtras().getSerializable("usuario");
              Intent itn = new Intent(getApplicationContext(),ClienteActivity.class);
              itn.putExtra("usuario",user);
              startActivityForResult(itn, CLIENTE_VIEW);
          } else if (resultCode == 3){
              //Sair da Aplicação
              finish();
          } else{
              //Usuário Não Logado
              CallLoginView();
          }
        }

        if (requestCode == ADM_VIEW || requestCode == CLIENTE_VIEW){
            CallLoginView();
        }

    }
}
