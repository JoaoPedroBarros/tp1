package org.example.selecaoJogador.ClassesSelecaoJogador;

public class Jogador{
    
    private Posicao posicao;
    private enum Posicao{
        GOLEIRO, ZAGUEIRO, LATERAL,
        VOLANTE, PONTA, CENTROAVANTE,
        RESERVA, TECNICO
    }
    private StatusJogador status;
    private enum StatusJogador{
        ATIVO, LESIONADO, SUSPENSO
    }
    private Selecao selecao;
    private int numero;
    private String nome;
    private String dataNascimento;

    //Construtor
    public Jogador(Posicao posicao) {
        this.posicao = posicao;
        this.status = StatusJogador.ATIVO;
        this.selecao = null;
        //numeracao condicional para jogador
    }
    
    //Getters e Setters
    public Posicao getPosicao() {
        return posicao;
    }

    public void setPosicao(Posicao posicao) {
        this.posicao = posicao;
    }
    
    public StatusJogador getStatus() {
        return status;
    }
 
    public void setStatus(StatusJogador status){
        this.status = status;
    }

    public Selecao getSelecao() {
        return selecao;
    }
    
    public void setSelecao(Selecao selecao){
        this.selecao = selecao;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        if (numero > 0){
            this.numero = numero;
        }
    }
    
    //Metodos Especializados
    public int idade(){
        return 0;
    }
}
