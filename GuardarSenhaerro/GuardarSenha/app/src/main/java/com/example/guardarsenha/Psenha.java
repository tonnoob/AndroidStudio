package com.example.guardarsenha;

public class Psenha {

    int codigo;
    String nome;
    String senha;


    public Psenha(int codigo, String nome, String senha) {
        this.codigo = codigo;
        this.nome = nome;
        this.senha = senha;

    }

    public Psenha(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }

    public Psenha() {

    }


    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
