/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.estadioArbitragem;

public class Arbitro {
    private String nome;
    private String nacionalidade;
    private int experiencia;

    public Arbitro(String nome, String nacionalidade, int experiencia) {
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.experiencia = experiencia;
    }
    
    public Arbitro(){
        
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }
    
    public void validarNacionalidadePartida(String nacionalidadeSelecao1, String nacionalidadeSelecao2) {
        if (this.nacionalidade.equalsIgnoreCase(nacionalidadeSelecao1) || 
            this.nacionalidade.equalsIgnoreCase(nacionalidadeSelecao2)) {
            throw new IllegalArgumentException("Regra violada: O árbitro não pode atuar em partidas de sua própria nacionalidade.");
        }
    }
}
