package com.example.guardarsenha;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

    public class BancoDeDados  {

        public static final int VERSAO_BANCO = 1;
        public static final String BANCO_SALVARSENHA = "db_salvarsenha";

        public BancoDeDados(Context context) {
            super(context, BANCO_SALVARSENHA, null, VERSAO_BANCO);

        }

        public static final String TABELA_SALVARSENHA = "tab_salvarsenha";

        public static final String COLUNA_CODIGO = "codigo";
        public static final String COLUNA_NOME = "nome";
        public static final String COLUNA_SENHA = "senha";


        @Override
        public void onCreate(SQLiteDatabase db) {

            String CRIAR_TABELA = "CREATE TABLE " + TABELA_SALVARSENHA + "(" + COLUNA_CODIGO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUNA_NOME + " TEXT," + COLUNA_SENHA + " TEXT)";

            db.execSQL(CRIAR_TABELA);

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }

        void addPessoa(Psenha psenha) {

            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues valor = new ContentValues();

            valor.put(COLUNA_NOME, psenha.getNome());
            valor.put(COLUNA_SENHA, psenha.getSenha());

            db.insert(TABELA_SALVARSENHA, null, valor);
            db.close();


        }

        void apagarSenha(Psenha psenha) {

            SQLiteDatabase db = this.getWritableDatabase();

            db.delete(TABELA_SALVARSENHA, COLUNA_CODIGO + " = ?", new String[]{
                    String.valueOf(psenha.getCodigo())
            });

            db.close();

        }

        Psenha selecionarSenha(int codigo) {

            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.query(TABELA_SALVARSENHA, new String[]{COLUNA_CODIGO, COLUNA_NOME, COLUNA_SENHA},
                    COLUNA_CODIGO + " = ?", new String[]{String.valueOf(codigo)}, null, null, null, null);

            if (cursor != null) {
                cursor.moveToFirst();
            }


            Psenha psenha = new Psenha(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));


            return psenha;

        }


        void atualizarSenha(Psenha psenha) {

            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues valor = new ContentValues();

            valor.put(COLUNA_NOME, psenha.getNome());
            valor.put(COLUNA_SENHA, psenha.getSenha());

            db.update(TABELA_SALVARSENHA, valor, COLUNA_CODIGO + " = ?", new String[]{
                    String.valueOf(psenha.getCodigo())});

        }

        public List<Psenha> listaSenha() {

            List<Psenha> pessoaLista = new ArrayList<Psenha>();

            String query = "SELECT * FROM " + TABELA_SALVARSENHA;

            SQLiteDatabase db = this.getWritableDatabase();

            Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {

                    Psenha psenha = new Psenha();

                    psenha.setCodigo(Integer.parseInt(cursor.getString(0)!=null?cursor.getString( 0 ):"0"));
                    psenha.setNome(cursor.getString(1));
                    psenha.setSenha(cursor.getString(2));

                    pessoaLista.add(psenha);

                } while (cursor.moveToNext());
            }

            return senhaLista;

        }

    }










