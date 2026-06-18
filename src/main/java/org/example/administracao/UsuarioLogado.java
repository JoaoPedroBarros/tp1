/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.administracao;

/**
 *
 * @author arkham
 */
public class UsuarioLogado {
    // classe que representa o usuario logado atualmente no sistema, essencial para a injecao de dependencia. Optamos por 
    // nao usar o singleton por ser considerado, hoje em dia, um anti-padrao de projeto.
    private final Usuario usuario;
    
    public UsuarioLogado(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public Usuario getUsuario() {
        return this.usuario;
    }
}
