/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.administracao;

/**
 *
 * @author arkham
 */
public class Operador extends Usuario {
    public Operador(Usuario.StatusUsuario status, Usuario.FuncaoUsuario funcao, String email, String nome, String senha, String pais, String identificacao) {
        super(status, funcao, email, nome, senha, pais, identificacao);
    }
    
}
