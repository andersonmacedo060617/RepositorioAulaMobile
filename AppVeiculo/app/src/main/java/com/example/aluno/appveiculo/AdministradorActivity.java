package com.example.aluno.appveiculo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.aluno.appveiculo.model.Usuario;

public class AdministradorActivity extends AppCompatActivity {
    TextView tvAdmNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador);

        tvAdmNome = (TextView) findViewById(R.id.tvAdmNome);

        Usuario user = (Usuario) getIntent().getExtras().getSerializable("usuario");

        tvAdmNome.setText("Administrador: " + user.getNome());
    }
}
