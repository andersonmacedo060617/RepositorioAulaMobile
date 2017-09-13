package com.example.aluno.appveiculo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aluno.appveiculo.crud.ECrud;
import com.example.aluno.appveiculo.dao.UsuarioDAO;
import com.example.aluno.appveiculo.database.DataBase;
import com.example.aluno.appveiculo.model.Administrador;
import com.example.aluno.appveiculo.model.Cliente;
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
        }else if(op == ECrud.visualizar){
            Usuario u = (Usuario)getIntent().getExtras().getSerializable("user");
            edtNome.setText(u.getNome());
            edtLogin.setText(u.getLogin());
            edtSenha.setText(u.getSenha());

            //Verifica o tipo de usuario
            rdbAdmin.setChecked(u instanceof Administrador);
            rdbCliente.setChecked(u instanceof Cliente);

            tvId.setText(Integer.toString(u.getId()));

            edtNome.setEnabled(false);
            edtLogin.setEnabled(false);
            edtSenha.setEnabled(false);
            rdbAdmin.setEnabled(false);
            rdbCliente.setEnabled(false);

            btnGravaUsuario.setText("Alterar");
        }

        btnGravaUsuario.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                try{
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
                    Toast.makeText(getApplicationContext(), "Usuario cadastrado com sucesso!", Toast.LENGTH_LONG).show();
                    setResult(1);
                    finish();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Erro ao salvar!!!", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnApagarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    DataBase banco = new DataBase(getApplicationContext());
                    new UsuarioDAO(banco).apagar(Integer.parseInt(tvId.getText().toString()));
                    setResult(1);
                    finish();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Erro ao apagar!!!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void biding() {
        tvId = (TextView) findViewById(R.id.tvId);
        edtNome = (EditText) findViewById(R.id.edtNome);
        edtLogin = (EditText) findViewById(R.id.edtLogin);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
        btnGravaUsuario = (Button) findViewById(R.id.btnGravaUsuario);
        btnApagarUsuario = (Button) findViewById(R.id.btnApagaUsuario);
        btnVoltarUsuario = (Button) findViewById(R.id.btnVoltar);
        rdbAdmin = (RadioButton) findViewById(R.id.rdbAdmin);
        rdbCliente = (RadioButton) findViewById(R.id.rdbCliente);
    }
}
