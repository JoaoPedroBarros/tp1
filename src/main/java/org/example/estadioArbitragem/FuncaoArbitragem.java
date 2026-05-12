/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.estadioArbitragem;

/**
 *
 * @author helto
 */
public enum FuncaoArbitragem {
    ARBITRO_PRINCIPAL("Árbitro Principal"),
    ASSISTENTE_1("Assistente 1 (Bandeirinha)"),
    ASSISTENTE_2("Assistente 2 (Bandeirinha)"),
    QUARTO_ARBITRO("Quarto Árbitro"),
    VAR("Árbitro de Vídeo (VAR)");
    
    private String descricao;

    private FuncaoArbitragem(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
    
}
