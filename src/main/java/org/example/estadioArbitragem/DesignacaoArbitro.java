/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.estadioArbitragem;

public class DesignacaoArbitro {
    private Arbitro arbitro;
    private FuncaoArbitragem funcao;

    public Arbitro getArbitro() {
        return arbitro;
    }

    public void setArbitro(Arbitro arbitro) {
        this.arbitro = arbitro;
    }

    public FuncaoArbitragem getFuncao() {
        return funcao;
    }

    public void setFuncao(FuncaoArbitragem funcao) {
        this.funcao = funcao;
    }

    public DesignacaoArbitro(Arbitro arbitro, FuncaoArbitragem funcao) {
        this.arbitro = arbitro;
        this.funcao = funcao;
    }
    
}
