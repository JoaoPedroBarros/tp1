/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.estadioArbitragem;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import org.example.administracao.Permissao;

public class OrganizaEstadio extends Permissao {

    public void CadastrarNovoEstadio(String nome, String localizacao, int capacidade, DefaultTableModel dtmTabela){
        if(capacidade <= 0){
            throw new IllegalArgumentException("A capacidade deve ser maior que zero!");
        }

        Estadio novoEstadio = new Estadio(nome, localizacao, capacidade);
        GerenciadorEstadioJSON gerenciador = new GerenciadorEstadioJSON();
        List<Estadio> lista = gerenciador.carregarEstadio();
        lista.add(novoEstadio);
        gerenciador.salvarEstadios(lista);
        dtmTabela.addRow(new Object[]{nome, localizacao, capacidade});
    } 

    public void ExcluirEstadio(int linhaSelecionada, int indexReal, DefaultTableModel dtmTabela){
        GerenciadorEstadioJSON gerenciador = new GerenciadorEstadioJSON();
        List<Estadio> lista = gerenciador.carregarEstadio();

        if(indexReal >= 0 && indexReal < lista.size()){
            lista.remove(indexReal);
            gerenciador.salvarEstadios(lista);

            dtmTabela.removeRow(linhaSelecionada);
        }
    }

    public void EditarEstadio(int index, int linhaSelecionada, String nome, String localizacao, int capacidade, DefaultTableModel dtmTabela){
        if(capacidade <= 0){
            throw new IllegalArgumentException("A capacidade deve ser maior que zero!");
        }

        GerenciadorEstadioJSON gerenciador = new GerenciadorEstadioJSON();
        List<Estadio> lista = gerenciador.carregarEstadio();

        if (index >= 0 && index < lista.size()){
            Estadio est = lista.get(index);
            est.setNome(nome);
            est.setCapacidade(capacidade);

            gerenciador.salvarEstadios(lista);

            dtmTabela.setValueAt(nome, linhaSelecionada, 0);
            dtmTabela.setValueAt(localizacao, linhaSelecionada, 1);
            dtmTabela.setValueAt(capacidade, linhaSelecionada, 2);
        }
    }

     @Override
     public String getNome() {
         return "CADASTRAR_ESTADIO";
     }

}