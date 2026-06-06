package org.example.jogadorselecao;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Tecnico {
    private String nome;
    @JsonIgnore
    private String nomeSelecao;
    
    
    // danilo: jackson precisa de um construtor vazio
    public Tecnico() {
    }
    
    //Construtor
    public Tecnico(String nome) {
        setNome(nome);
    }
    

    //Getters e Setters

    public String getNome() {
        return nome;
    }

    public final void setNome(String nome) throws IllegalArgumentException{
        if(nome.isBlank() || nome.isEmpty()){
            throw new IllegalArgumentException("O campo Nome não foi preenchido.");  
        }
        else if(nome.matches("^[\\p{L}\\s]+$")){
            this.nome = nome;
        }
        else{
           throw new IllegalArgumentException("Nome não pode conter números ou símbolos especiais.");
        }
    }

    public String getNomeSelecao() {
        return nomeSelecao;
    }

    protected void setSelecao(String nomeSelecao) {
        this.nomeSelecao = nomeSelecao;
    }

}
