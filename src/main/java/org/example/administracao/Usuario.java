package org.example.administracao;


public class Usuario {
    private StatusUsuario statusUsuario;
    protected enum StatusUsuario {
        ATIVO, AFASTADO, DESLIGADO
    }
    
    private FuncaoUsuario funcaoUsuario;
    protected enum FuncaoUsuario {
        ADMINISTRADOR, ORGANIZADOR, OPERADOR, ARBITRO
    }
    
    private String email;
    private String nome;
    private String senha;
    private String pais;
    private String identificacao;

    // Constructor padrao da classe
    public Usuario(StatusUsuario status, FuncaoUsuario funcao, String email, String nome, String senha, String pais, String identificacao) {
        this.statusUsuario = status;
        this.funcaoUsuario = funcao;
        this.email = email;
        this.nome = nome;
        this.senha = senha;
        this.pais = pais;
        this.identificacao = identificacao;
    }

    // Getters e Setters
    public StatusUsuario getStatus() {
        return statusUsuario;
    }

    public void setStatus(StatusUsuario status) {
        this.statusUsuario = status;
    }

    public FuncaoUsuario getFuncaoUsuario() {
        return funcaoUsuario;
    }

    public void setFuncaoUsuario(FuncaoUsuario funcaoUsuario) {
        this.funcaoUsuario = funcaoUsuario;
    }
    
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }
            
}
