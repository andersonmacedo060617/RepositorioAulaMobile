package com.example.aluno.appservico;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.aluno.appservico.model.Cliente;
import com.example.aluno.appservico.servicos.AllClientsThread;
import com.example.aluno.appservico.servicos.SaveClientThread;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    Button btn,btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.button);
        btn1 = (Button) findViewById(R.id.button2);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    String msg = new SaveClientThread().execute("AbrãoZin","14","100").get();

                        Toast.makeText(getApplicationContext(),
                                "Mensagem:: "+msg,
                                Toast.LENGTH_LONG).show();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }


            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    List<Cliente> lista = new AllClientsThread().execute("").get();

                    for (Cliente c : lista){
                        Toast.makeText(getApplicationContext(),
                                "Nome Cliente: "+c.getNome(),
                                Toast.LENGTH_LONG).show();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }


            }
        });
    }
}
