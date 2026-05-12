package org.example.administracao;

public class Administrador extends Usuario {
    
    public Administrador(StatusUsuario status, FuncaoUsuario funcao, String email, String nome, String senha, String pais, String identificacao) {
        super(status, funcao, email, nome, senha, pais, identificacao);
    }
    
}
