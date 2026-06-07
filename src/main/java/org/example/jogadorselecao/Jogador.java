package org.example.jogadorselecao;

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
    private String nomeSelecao;
    
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
        if(dataNascimento.isEmpty()){
            throw new IllegalArgumentException("O campo Data de Nascimento não foi preenchido.");
        }
        
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

    public String getNomeSelecao() {
        return nomeSelecao;
    }

    public void setNomeSelecao(String nomeSelecao) {
        this.nomeSelecao = nomeSelecao;
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
    public void atualizaEstat(int[] estatisticas) throws IllegalArgumentException{
        for(int i = 0; i < estatisticas.length; i++){
            if(estatisticas[i] < 0){ return;}
        }
        
        //Atualiza atributos estatísticos
        this.amarelos += estatisticas[0];
        this.vermelhos += estatisticas[1];
        this.passes += estatisticas[2];
        this.assistencias += estatisticas[3];
        this.gols += estatisticas[4];
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Jogador j = (Jogador) o;
        return Objects.equals(nome.toLowerCase(), j.getNome().toLowerCase());
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
        System.out.println("Nome da Selecao: " + getNomeSelecao());
        System.out.println("-------------------------------------------");
    }    
}
