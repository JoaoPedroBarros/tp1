package org.example.jogadorselecao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Objects;

public final class Jogador{
    
    //Atributos base
    private String nome;
    private Posicao posicao;
    private int numero;
    private StatusJogador status;
    private String dataNascimento;
    @JsonIgnore
    private Selecao selecao;
    
    //Atributos estatísticos
    private int amarelos = 0;
    private int vermelhos = 0;
    private int passes = 0;
    private int assistencias = 0;
    private int gols = 0;
    
    
    //Construtores
    public Jogador(){
    }
    
    public Jogador(String nome, Posicao posicao, int numero, StatusJogador status, String dataNascimento) 
            throws IllegalArgumentException
    {
        this.setNome(nome);
        this.posicao = posicao;
        this.setNumero(numero);
        this.status = status;
        this.setDataNascimento(dataNascimento);
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

    public Posicao getPosicao() {
        return posicao;
    }

    public void setPosicao(Posicao posicao) {
        this.posicao = posicao;
    }

    public int getNumero() {
        return numero;
    }

    public final void setNumero(int numero){
        if (numero <= 0){
            throw new IllegalArgumentException("O número do jogador deve ser maior que 0.");
        }
        this.numero = numero;
    }

    public StatusJogador getStatus() {
        return status;
    }

    public void setStatus(StatusJogador status) {
        this.status = status;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        String formatoData = "dd/MM/uuuu";
        DateTimeFormatter formato = DateTimeFormatter
        .ofPattern(formatoData)
        .withResolverStyle(ResolverStyle.STRICT);
        
        try{
            LocalDate data = LocalDate.parse(dataNascimento, formato);
            
            if(LocalDate.now().compareTo(data) < 0){
                throw new IllegalArgumentException("Datas futuras não são permitidas.");
            }
            this.dataNascimento = dataNascimento;
        }
        catch(DateTimeParseException e){
            throw new IllegalArgumentException("Data inválida.");
        }
    }

    public Selecao getSelecao() {
        return selecao;
    }

    protected void setSelecao(Selecao selecao) {
        this.selecao = selecao;
    }

    public int getAmarelos() {
        return amarelos;
    }

    public int getVermelhos() {
        return vermelhos;
    }

    public int getPasses() {
        return passes;
    }

    public int getAssistencias() {
        return assistencias;
    }

    public int getGols() {
        return gols;
    }
    
    //Métodos personalizados
    public void atualizaEstat(int a, int v, int p, int asst, int g) throws IllegalArgumentException{
        if (a < 0 || v < 0 || p < 0 || asst < 0 || g < 0){
            throw new IllegalArgumentException("Dados estatísticos não podem ser negativos.");
        }
        this.amarelos += a;
        this.vermelhos += v;
        this.passes += p;
        this.assistencias += asst;
        this.gols += g;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Jogador j = (Jogador) o;
        return Objects.equals(nome, j.getNome());
    }
    
    @Override
    public int hashCode(){
        return Objects.hash(nome);
    }
    
    public void mostra(){
        System.out.println("Nome: " + getNome());
        System.out.println("Posicao: " + getPosicao());
        System.out.println("Numero: " + getNumero());
        System.out.println("Status: " + getStatus());
        System.out.println("Data de Nascimento: " + getDataNascimento());   
        System.out.println("-------------------------------------------");
    }
    
}
