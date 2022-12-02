package com.example.guardarsenha;

//import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Mostrar extends AppCompatActivity {
    TextView mNome;
    TextView mSenha;

    Button mVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_mostrar );

        mNome = findViewById( R.id.vNome );
        mSenha= findViewById( R.id.vSenha );

        mVoltar = findViewById( R.id.btMostrar );


        Intent intentMostrar = getIntent();

        String valor1 = intentMostrar.getStringExtra( "Valornome" );
        String valor2 = intentMostrar.getStringExtra( "Valorsenha" );


        mNome.setText( valor1 );
        mSenha.setText( valor2 );


        mVoltar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                voltar();
            }
        } );

    }
    //Comentários
    private void voltar() {
        //segunda
        Intent it = new Intent( this, MainActivity.class );
        //segunda
        startActivityForResult( it, 1 );

    }
}
