package com.example.aluno.appveiculo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.aluno.appveiculo.model.Usuario;

public class PrincipalActivity extends AppCompatActivity {

    final int LOGIN_VIEW = 1;
    final int ADM_VIEW = 1;
    final int CLIENTE_VIEW = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        CallLoginView();
    }

    private void CallLoginView() {
        Intent itn = new Intent(getApplicationContext(),LoginActivity.class);
        startActivityForResult(itn, LOGIN_VIEW);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == LOGIN_VIEW){
            Usuario user = (Usuario)data.getExtras().getSerializable("usuario");
            if(resultCode == 1){
                //Administrador
                Intent itn = new Intent(getApplicationContext(), AdministradorActivity.class);
                itn.putExtra("usuario", user);
                startActivityForResult(itn, ADM_VIEW);
            }else if(resultCode == 2){
                //Cliente
                Intent itn = new Intent(getApplicationContext(), ClienteActivity.class);
                itn.putExtra("usuario", user);
                startActivityForResult(itn, CLIENTE_VIEW);
            }else if(resultCode == 3){
                //Sair da Aplicação
                finish();
            }else{
                //Usuario não Logado
                CallLoginView();
            }

            if(requestCode == ADM_VIEW || requestCode == CLIENTE_VIEW){
                CallLoginView();
            }
        }
    }
}
