/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.estadioArbitragem;

import java.util.List;
import org.example.administracao.Papel;
import org.example.administracao.Permissao;

public class Arbitro extends Papel{
    
    private final static List<? extends Permissao> listaPermissoes = List.of(new DesignacaoArbitro());
    private int experiencia;

    public Arbitro(String nome, String nacionalidade, int experiencia) {
       
        this.experiencia = experiencia;
        this.nomePapel = "ARBITRO";
    }
    
    public Arbitro(){
        this.experiencia = experiencia;
        this.nomePapel = "ARBITRO";
    }
  
    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        if (experiencia < 0 || experiencia > 30){
            throw new IllegalArgumentException("Experiencia inválida.");
        }
        this.experiencia = experiencia;
        
        
    }
    
    @Override
    public String getNomePapel(){
        return nomePapel;
    }
    
    @Override
    public List<? extends Permissao> getPermissoes(){
        return listaPermissoes;
    }
    
    @Override
    public String toString() {
        return "Árbitro";
    }
}