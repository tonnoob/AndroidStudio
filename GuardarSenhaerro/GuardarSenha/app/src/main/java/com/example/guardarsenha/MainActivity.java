package com.example.guardarsenha;

import android.
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btsalvarsenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        btsalvarsenha = findViewById(R.id.salvarsenha);

        btsalvarsenha.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                segundaTela();
            }
        } );

    }

    private void segundaTela(){
        Intent it = new Intent( this, SalvarSenha.class);
        startActivityForResult( it, 1 );
    }
}