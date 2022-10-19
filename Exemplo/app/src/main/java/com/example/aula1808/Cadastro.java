package com.example.aula1808;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
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

public class Cadastro extends AppCompatActivity {
    
     //Atribuição de campos de texto
    EditText cCodigo;
    EditText cNome;
    EditText cTelefone;
    EditText cEmail;
    EditText cEndereco;
    
    //Atribuição de botões
    Button btSalvar;
    Button btExcluir;
    Button btLimpar;
    
    //Atribuição da listview, que mostra os itens da tabela
    ListView viewPessoa;
    
    //Atribuição de adapter
    ArrayAdapter <String> adapter;
    
     //Array Dinâmico
    ArrayList <String> arrayList;
    
    //InputMethodManager é uma tecnologia usada por um aplicativo para se comunicar com um editor de método de entrada
    InputMethodManager imm;
    
    //Instanciamento de um objeto novo
    BancoDeDados db = new BancoDeDados(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        imm = (InputMethodManager) this.getSystemService(Service.INPUT_METHOD_SERVICE);
        
        //Ligação entre o back-end e o front-end
        cCodigo = findViewById(R.id.txtCodigo);

        cNome = findViewById(R.id.edt_nome);
        cTelefone = findViewById(R.id.edt_celular);
        cEmail = findViewById(R.id.edt_email);
        cEndereco = findViewById(R.id.edt_endereco);

        btSalvar = findViewById(R.id.btnSalvar);
        btExcluir = findViewById(R.id.btnExcluir);
        btLimpar = findViewById(R.id.btnLimpar);

        cNome.requestFocus();
        
        
        //Exibe o registro da pessoa selecionada
        viewPessoa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {

                String conteudo = String viewPessoa.getItemIdAtPosition(posicao);

                String codigo = conteudo.substring(0, conteudo.indexOf( "-"));

                Pessoa pessoa = db.selecionarPessoa(Integer.parseInt(codigo));

                cCodigo.setText(String.valueOf(pessoa.getCodigo()));
                cNome.setText(pessoa.getNome());
                cTelefone.setText(pessoa.getTelefone());
                cEmail.setText((pessoa.getEmail()));
                cEndereco.setText(pessoa.getEndereco());

            }
        });
        //Habilitando botão limpar
        btLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                limparCampos();

            }
        });
        
        //Salva um novo cadastro
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Atribui valores digitados pelo usuário
                String codigo = cCodigo.getText().toString();
                String nome = cNome.getText().toString();
                String telefone = cTelefone.getText().toString();
                String email = cEmail.getText().toString();
                String endereco = cEndereco.getText().toString();
                
                //Se o nome estiver vazio, exibe uma mensagem de erro
                if(nome.isEmpty()) {
                    cNome.setError("Este campo é brigatório!");
                    
                 //Se os campos estiverem preenchidos de maneira correta, adiciona um novo registro no banco de dados
                } else if (codigo.isEmpty()) {
                    db.addPessoa(new Pessoa(nome, telefone, email, endereco));
                    Toast.makeText(Cadastro.this, "Cadastro salvo com sucesso!", Toast.LENGTH_SHORT).show();
                    listarPessoas();
                    limparCampos();
                    escondeTeclado();
                    
                //Se o código já for existente no banco, somente atualiza os valores do registro
                } else {
                    db.atualizarPessoa(new Pessoa(Integer.parseInt(codigo), nome, telefone, email, endereco));
                    Toast.makeText(Cadastro.this, "Cadastro atualizado com Sucesso!", Toast.LENGTH_SHORT).show();

                }
            }
        });
        
        //Exclui o registro da pessoa selecionada
        btExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String codigo = cCodigo.getText().toString();
                 //Se nada estiver selecionado, apenas exibe uma mensagem de erro
                if(codigo.isEmpty()) {
                    Toast.makeText(Cadastro.this, "Nenhuma pessoa está selecionada", Toast.LENGTH_SHORT).show();

                } else {

                    Pessoa pessoa = new Pessoa();
                    pessoa.setCodigo(Integer.parseInt(codigo));
                    db.apagarPessoa(pessoa);
                    Toast.makeText(Cadastro.this, "Registro da pessoa apagado com sucesso!", Toast.LENGTH_SHORT).show();
                    cCodigo.setText("");
                    listarPessoas();
                    limparCampos();
                    escondeTeclado();

                }
            }
        });
    }
    
    //Método que deixa o campo selecionado vazio
    private void limparCampos(){

        cNome.setText("");
        cTelefone.setText("");
        cEmail.setText("");
        cEndereco.setText("");

        // o cursor ira mostrar marcado
        cNome.requestFocus();
    }
    
    //Método que esconde o teclado do android
    void escondeTeclado(){
        imm.hideSoftInputFromWindow(cNome.getWindowToken(),0);
    }
    
    //Método que lista os registros da tabela pessoa
    public void listarPessoas(){

        List<Pessoa> pessoas = db.listaPessoa();

        arrayList = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(Cadastro.this, android.R.layout.simple_list_item_1);

        viewPessoa.setAdapter(adapter);

        for(Pessoa c: pessoas){
            arrayList.add(c.getCodigo() + "-" + c.getNome());
            adapter.notifyDataSetChanged();
        }
    }
}
