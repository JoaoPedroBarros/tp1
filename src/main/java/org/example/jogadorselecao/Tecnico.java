package org.example.jogadorselecao;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Tecnico {
    private String nome;
    @JsonIgnore
    private Selecao selecao = null;
    
    //Construtor
    public Tecnico(String nome) {
        this.nome = nome;
    }  
    
    //Getters e Setters

    public String getNome() {
        return nome;
    }

    public final void setNome(String nome){
        if(nome.matches(".*\\d.*") || nome.matches(".*[^a-zA-Z0-9 ].*")){
           throw new IllegalArgumentException("Nome não pode conter números ou símbolos especiais.");
        }
        this.nome = nome;
    }

    public Selecao getSelecao() {
        return selecao;
    }

    protected void setSelecao(Selecao selecao) {
        this.selecao = selecao;
    }


}
