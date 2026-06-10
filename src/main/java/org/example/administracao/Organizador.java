package org.example.administracao;

import java.util.List;
import org.example.estadioArbitragem.OrganizaEstadio;
import org.example.jogadorselecao.persistencia.IOJogador;
import org.example.jogadorselecao.persistencia.IOSelecao;
import org.example.partidas.PartidaCopa;

public class Organizador extends Papel{
    
    private final static List<? extends Permissao> listaPermissoes = List.of(new IOJogador(), new IOSelecao(), new OrganizaEstadio(), new PartidaCopa());
    
    public Organizador() {
        this.nomePapel = "ORGANIZADOR";
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
        return "Organizador";
    }
    
}
