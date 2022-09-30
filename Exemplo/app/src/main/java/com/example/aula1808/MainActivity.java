package com.example.aula1808;
// essa é a classe mainActivity

//front end do app é XML
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // botão cadastro, sendo um atributo, guardando o id
    Button btCadastro;

    @Override       //sobrecarga de metodo = onCreate, que vem da classe MainActivity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

                    //metodo para localizar o botão de cadastro
        btCadastro = findViewById(R.id.cadastro);
                    //checa se o botão foi clicado, e se foi o codigo será ativado indo para outra pagina
        btCadastro.setOnClickListener(new View.OnClickListener() {

            //metodo, levando a proxima pagina
            @Override
                        //metod
            public void onClick(View view) {

                segundaTela();
            }

        });
    }
                //parametro para que vá para outra classe assim mudando de pagina
    private void segundaTela() {

        Intent it = new Intent(this, Cadastro.class);
        startActivityForResult(it, 1);
    }

}