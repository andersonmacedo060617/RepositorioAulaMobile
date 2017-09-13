package com.example.aluno.appveiculo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.aluno.appveiculo.model.Usuario;

public class AdministradorActivity extends AppCompatActivity {

    TextView tvAdmNome;
    Button btnClienteList;

    final int LIST_USER_VIEW = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador);

        Binding();
        Usuario user = (Usuario)getIntent().getExtras().getSerializable("usuario");
        tvAdmNome.setText("ADMINISTRADOR: "+user.getNome());

        btnClienteList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),ListUsuarioActivity.class);

                startActivityForResult(i,LIST_USER_VIEW);
            }
        });

    }

    private void Binding() {
        tvAdmNome = (TextView) findViewById(R.id.tvAdmNome);
        btnClienteList = (Button) findViewById(R.id.btnCliente);
    }
}
