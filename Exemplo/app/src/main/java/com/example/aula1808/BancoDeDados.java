package com.example.aula1808;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BancoDeDados extends SQLiteOpenHelper {

    public static final int VERSAO_BANCO = 1;
    public static final String BANCO_AGENDA = "db_agenda";


    public BancoDeDados(Context context) {
        super(context, BANCO_AGENDA, null, VERSAO_BANCO);
    }

    public static final String TABELA_AGENDA = "tab_agenda";
    public static final String COLUNA_CODIGO = "codigo";
    public static final String COLUNA_NOME = "nome";
    public static final String COLUNA_TELEFONE = "telefone";
    public static final String COLUNA_EMAIL = "email";
    public static final String COLUNA_ENDERECO = "endereco";


    @Override
    public void onCreate(SQLiteDatabase db) {

        String CRIAR_TABELA = "CREATE TABLE " + TABELA_AGENDA + "(" + COLUNA_CODIGO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUNA_NOME + " TEXT," + COLUNA_TELEFONE + " TEXT," + COLUNA_EMAIL + " TEXT," + COLUNA_ENDERECO + " TEXT)";

        db.execSQL(CRIAR_TABELA);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    void addPessoa(Pessoa pessoa){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valor = new ContentValues();

        valor.put(COLUNA_NOME, pessoa.getNome());
        valor.put(COLUNA_TELEFONE, pessoa.getTelefone());
        valor.put(COLUNA_EMAIL, pessoa.getEmail());
        valor.put(COLUNA_ENDERECO, pessoa.getEndereco());

        db.insert(TABELA_AGENDA, null, valor);
        db.close();

    }

    void apagarPessoa(Pessoa pessoa){

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABELA_AGENDA, COLUNA_CODIGO + " = ?", new String[] {
                String.valueOf(pessoa.getCodigo())
        });
        db.close();

    }

    Pessoa selecionarPessoa(int codigo){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABELA_AGENDA, new String[]{COLUNA_CODIGO,COLUNA_NOME,COLUNA_TELEFONE,COLUNA_EMAIL,COLUNA_ENDERECO},
                COLUNA_CODIGO + " = ?", new String[]{String.valueOf(codigo)}, null, null, null, null);

                if(cursor != null){
                    cursor.moveToFirst();
                }

                Pessoa pessoa = new Pessoa(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4));
                db.close();
                return pessoa;
    }

    void atualizarPessoa(Pessoa pessoa){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valor = new ContentValues();

        valor.put(COLUNA_NOME, pessoa.getNome());
        valor.put(COLUNA_TELEFONE, pessoa.getTelefone());
        valor.put(COLUNA_EMAIL, pessoa.getEmail());
        valor.put(COLUNA_ENDERECO, pessoa.getEndereco());

        db.update(TABELA_AGENDA, valor, COLUNA_CODIGO + " = ?", new String[]{
                String.valueOf(pessoa.getCodigo())});

        db.close();
    }

    public List<Pessoa> listaPessoa(){
        List<Pessoa> pessoaLista = new ArrayList<Pessoa>();

        String query = "Select * from " + TABELA_AGENDA;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor =db.rawQuery(query,null);

        if (cursor.moveToFirst()){
            do{
                    Pessoa pessoa = new Pessoa();

                    pessoa.setCodigo(Integer.parseInt(cursor.getString(0) != null?cursor.getString(0):"0"));
                    pessoa.setNome(cursor.getString(1));
                    pessoa.setTelefone(cursor.getString(2));
                    pessoa.setEmail(cursor.getString(3));
                    pessoa.setEndereco(cursor.getString(4));
                } while (cursor.moveToNext());
        }
        db.close();
        return pessoaLista;

    }
}








