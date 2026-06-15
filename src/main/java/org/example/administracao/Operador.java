/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.administracao;

import java.util.List;
import org.example.ingressos.GerenciaIngressos;


/**
 *
 * @author arkham
 */
public class Operador extends Papel {
    
    private final static List<? extends Permissao> listaPermissoes = List.of(new GerenciaIngressos());
    
    public Operador() {
        this.nomePapel = "OPERADOR";
    }
    
    @Override
    public String getNomePapel() {
        return nomePapel;
    }
    
    @Override
    public List<? extends Permissao> getPermissoes() {
        return listaPermissoes;
    }
    
    @Override
    public String toString() {
        return "Operador";
    }
    
}
