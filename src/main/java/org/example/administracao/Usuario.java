package org.example.administracao;



public class Usuario {
    public enum StatusUsuario {
        ATIVO, AFASTADO, DESLIGADO
    }
    
    public static int idController = 0;
    
    protected String nome;
    protected String identificacao;
    protected String email;
    protected String pais;
    protected String senha;
    protected StatusUsuario status;
    protected Papel papel;
    
    public Usuario() {}

    // Constructor padrao da classe
    public Usuario(String nome, String identificacao, String email, String pais, String senha, StatusUsuario status, Papel papel) {
        this.nome = nome;
        this.identificacao = identificacao;
        this.email = email;
        this.pais = pais;
        this.senha = senha;
        this.status = status;
        this.papel = papel;
    }
    
    // Getters e Setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public StatusUsuario getStatus() {
        return status;
    }

    public void setStatus(StatusUsuario status) {
        this.status = status;
    }

    public Papel getPapel() {
        return papel;
    }

    public void setPapel(Papel papel) {
        this.papel = papel;
    }
    
     @Override
    public String toString() {
        return nome;
    }
}
