package org.example.jogadorselecao;

public enum Posicao {
    GOLEIRO("GOLEIRO"),
    DEFENSOR("DEFENSOR"),
    MEIO_CAMPISTA("MEIO-CAMPISTA"),
    ATACANTE("ATACANTE");
    
    private final String nomeFormatado;
    
    Posicao(String nomeFormatado){
        this.nomeFormatado = nomeFormatado;
    }
    
    @Override
    public String toString(){
        return this.nomeFormatado;
    }
}
