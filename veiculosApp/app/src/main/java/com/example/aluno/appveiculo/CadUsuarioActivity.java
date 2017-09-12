package com.example.aluno.appveiculo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.aluno.appveiculo.crud.ECrud;
import com.example.aluno.appveiculo.dao.UsuarioDAO;
import com.example.aluno.appveiculo.database.DataBase;
import com.example.aluno.appveiculo.model.Administrador;
import com.example.aluno.appveiculo.model.Usuario;

public class CadUsuarioActivity extends AppCompatActivity {

    TextView tvId;
    EditText edtNome, edtLogin, edtSenha;
    RadioButton rdbAdmin, rdbCliente;
    Button btnGravaUsuario, btnApagarUsuario, btnVoltarUsuario;
    ECrud op;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_usuario);

        biding();

        op = (ECrud) getIntent().getExtras().getSerializable("op");

        if(op == ECrud.inserir){
            btnApagarUsuario.setVisibility(View.INVISIBLE);
            tvId.setVisibility(View.INVISIBLE);
        }

        btnGravaUsuario.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //
                Usuario u;
                //Preenher
                if(rdbAdmin.isChecked()){
                    u = new Administrador();
                }else{
                    u = new Usuario();
                }

                u.setNome(edtNome.getText().toString());
                u.setLogin(edtLogin.getText().toString());
                u.setSenha(edtSenha.getText().toString());

                DataBase banco = new DataBase(getApplicationContext());
                new UsuarioDAO(banco).grava(u);
            }
        });
    }

    private void biding() {
        tvId = (TextView) findViewById(R.id.tvId);
        edtNome = (EditText) findViewById(R.id.edtNome);
        edtLogin = (EditText) findViewById(R.id.edtLogin);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
        btnGravaUsuario = (Button) findViewById(R.id.btnGravaUsuario);
        btnGravaUsuario = (Button) findViewById(R.id.btnApagaUsuario);
        btnVoltarUsuario = (Button) findViewById(R.id.btnVoltar);
    }
}
