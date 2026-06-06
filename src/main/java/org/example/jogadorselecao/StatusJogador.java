package org.example.jogadorselecao;

public enum StatusJogador {
    ATIVO("ATIVO"),
    LESIONADO("LESIONADO"),
    SUSPENSO("SUSPENSO");
    
    private String statusFormatado;
    
    StatusJogador(String statusFormatado){
        this.statusFormatado = statusFormatado;
    }

    @Override
    public String toString() {
        return this.statusFormatado;
    }
}
