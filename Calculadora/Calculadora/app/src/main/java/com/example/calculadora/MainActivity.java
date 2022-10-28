package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    double num1, num2, resultado;

    @Override /*o Override reescreve a linha, assim que o java o encontra ele reescreve o codigo abaixo*/
    protected void onCreate(Bundle savedInstanceState) { /*onCreate é o metodo,*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Vamos entrar com o codigo*/

        Button btn_somar =(Button) findViewById(R.id.btn_soma);
        


    }
}