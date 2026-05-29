package org.example.administracao;


public class Usuario {
    public enum StatusUsuario {
        ATIVO, AFASTADO, DESLIGADO
    }
    
    public enum FuncaoUsuario {
        ADMINISTRADOR, ORGANIZADOR, OPERADOR, ARBITRO
    }
   
    protected String nome;
    protected String identificacao;
    protected String email;
    protected String pais;
    protected String senha;
    protected StatusUsuario status;
    protected FuncaoUsuario funcao;
    
    public Usuario() {}

    // Constructor padrao da classe
    public Usuario(String nome, String identificacao, String email, String pais, String senha, StatusUsuario status, FuncaoUsuario funcao) {
        this.nome = nome;
        this.identificacao = identificacao;
        this.email = email;
        this.pais = pais;
        this.senha = senha;
        this.status = status;
        this.funcao = funcao;
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

    public FuncaoUsuario getFuncao() {
        return funcao;
    }

    public void setFuncao(FuncaoUsuario funcao) {
        this.funcao = funcao;
    }
        
}
