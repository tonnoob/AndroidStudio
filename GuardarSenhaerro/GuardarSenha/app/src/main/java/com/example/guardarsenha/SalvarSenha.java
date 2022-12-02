package com.example.guardarsenha;

import android.app.Service;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SalvarSenha extends AppCompatActivity {

    EditText cCodigo;
    EditText cNome;
    EditText cSenha;
    Button btSalvar;
    Button btExcluir;
    Button btLimpar;
    ListView viewSenha;


    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;

    InputMethodManager imm;

    BancoDeDados db = new BancoDeDados(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salvarsenha);

        imm = (InputMethodManager) this.getSystemService( Service.INPUT_METHOD_SERVICE );

        cCodigo = findViewById(R.id.txtCodigo);

        cNome = findViewById(R.id.edt_nome);
        cSenha = findViewById(R.id.edt_senha);
        btSalvar = findViewById(R.id.btnSalvar);
        btExcluir = findViewById(R.id.btnExcluir);
        btLimpar = findViewById(R.id.btnLimpar);
        viewSenha = findViewById(R.id.listViewSenha);

        cNome.requestFocus();

        viewSenha.setOnItemClickListener((adapterView, view, position, id) -> {

            String conteudo = (String) viewSenha.getItemAtPosition( position );

            String codigo = conteudo.substring( 0, conteudo.indexOf( "-" ) );

            Psenha psenha = db.selecionarSenha( Integer.parseInt( codigo ) );

            cCodigo.setText(String.valueOf( psenha.getCodigo() )  );
            cNome.setText( psenha.getNome() );
            cSenha.setText(psenha.getSenha());
        });

        btLimpar.setOnClickListener(view -> limpaCampos());


        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String codigo = cCodigo.getText().toString();
                String nome = cNome.getText().toString();
                String senha = cSenha.getText().toString();

                if (nome.isEmpty()) {
                    cNome.setError("Este campo é obrigatorio");
                } else if (codigo.isEmpty()) {

                    db.addSenha(new Psenha(nome, senha));
                    Toast.makeText(SalvarSenha.this, "Senha salva com sucesso", Toast.LENGTH_SHORT).show();
                    listarSenhas();
                    limpaCampos();
                    escondeTeclado();

                } else {
                    db.atualizarSenha(new Psenha(Integer.parseInt(codigo), nome, senha));
                    Toast.makeText(SalvarSenha.this, "Senha atualizada com sucesso", Toast.LENGTH_SHORT).show();
                    listarSenhas();
                    limpaCampos();
                    escondeTeclado();

                }
            }

            private void listarSenhas() {
            }
        });

        btExcluir.setOnClickListener(v -> {

            String codigo = cCodigo.getText().toString();
            if (codigo.isEmpty()) {
                Toast.makeText(SalvarSenha.this, "Nenhuma senha está selecionada", Toast.LENGTH_SHORT).show();
            } else {

                Psenha psenha = new Psenha();
                psenha.setCodigo(Integer.parseInt(codigo));
                db.apagarSenha(psenha);
                Toast.makeText(SalvarSenha.this, "Registro da senha foi apagada com sucesso", Toast.LENGTH_SHORT).show();
                cCodigo.setText("");
                listarSenhas();
                limpaCampos();
            }

        });

    }

    //  btMostrar = findViewById( R.id.cadastro );

    /*   btMostrar.setOnClickListener( new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               chamaMostrar();
           }



       } );



   }

       private void chamaMostrar () {
           Intent it = new Intent(this, Mostrar.class);
           it.putExtra("Valornome", cNome.getText().toString());
           it.putExtra("Valorcelular", cCelular.getText().toString());
           it.putExtra("Valoremail", cEmail.getText().toString());
           it.putExtra("Valorendereco", cEndereco.getText().toString());

           startActivityForResult(it, 1);


       }

*/
    void escondeTeclado(){
        imm.hideSoftInputFromWindow( cNome.getWindowToken(),0 );
    }

    public void limpaCampos() {
        cCodigo.setText("");
        cNome.setText("");
        cSenha.setText("");


        cNome.requestFocus();
    }


    public void listarSenhas(){

        List<Psenha> psenhas = db.listaSenha();

        arrayList = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(SalvarSenha.this, android.R.layout.simple_list_item_1, arrayList);

        viewSenha.setAdapter(adapter);

        for(Psenha c : psenhas){
            //Log.d( "Lista", "\nID: " + c.getCodigo() + "Nome: " + c.getNome(  ));            //arrayList. add( c.getCodigo() + "-" + c.getCodigo());
            arrayList.add(c.getCodigo() + "-" + c.getNome());
            adapter.notifyDataSetChanged();
        }

    }

}