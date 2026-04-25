package org.example.selecaoJogador.ClassesSelecaoJogador;

import java.util.LinkedList;

public class Selecao {
    private LinkedList<Jogador> time = new LinkedList<>();
    private String nomeTecnico;
    private int grupo;
    private String pais;
    public static final int MAX_MEMBROS = 26;
    public static final int MIN_MEMBROS = 18;
    

    //Construtor
    public Selecao(byte grupo) {
        this.grupo = grupo;
    }

    //Getters e Setters
    public LinkedList<Jogador> getTime() {
        return time;
    }

    //Convocar() Talvez retirar esse setter.
    public void setTime(LinkedList<Jogador> time) {
        this.time = time;
    }
    
    public int getGrupo() {
        return grupo;
    }

    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
    
    //Metodos especializados
    public void removerJogador(Jogador jogador){
    }
    
    public void convocarJogador(Jogador jogador){

    }
    
    public int jogadoresAtivos(){
        return 0;
    }
}
