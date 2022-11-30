package com.example.projetoagenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btcadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        btcadastro = findViewById(R.id.cadastro);

        btcadastro.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                segundaTela();
            }
        } );

    }

    private void segundaTela(){
        Intent it = new Intent( this, Cadastro.class);
        startActivityForResult( it, 1 );
    }
}