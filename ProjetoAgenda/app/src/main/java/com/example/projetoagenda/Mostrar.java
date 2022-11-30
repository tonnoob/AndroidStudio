package com.example.projetoagenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Mostrar extends AppCompatActivity {

    TextView mNome;
    TextView mCelular;
    TextView mEmail;
    TextView mEndereco;

    Button mVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_mostrar );

        mNome = findViewById( R.id.vNome );
        mCelular = findViewById( R.id.vCelular );
        mEmail = findViewById( R.id.vEmail );
        mEndereco = findViewById( R.id.vEndereco );

        mVoltar = findViewById( R.id.btMostrar );


        Intent intentMostrar = getIntent();

        String valor1 = intentMostrar.getStringExtra( "Valornome" );
        String valor2 = intentMostrar.getStringExtra( "Valorcelular" );
        String valor3 = intentMostrar.getStringExtra( "Valoremail" );
        String valor4 = intentMostrar.getStringExtra( "Valorendereco" );

        mNome.setText( valor1 );
        mCelular.setText( valor2 );
        mEmail.setText( valor3 );
        mEndereco.setText( valor4 );

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