package org.example.administracao;

import java.util.List;
import org.example.estadioArbitragem.DesignacaoArbitro;
import org.example.estadioArbitragem.OrganizaEstadio;
import org.example.ingressos.GerenciaIngressos;
import org.example.jogadorselecao.persistencia.IOJogador;
import org.example.jogadorselecao.persistencia.IOSelecao;
import org.example.partidas.PartidaCopa;

public class Administrador extends Papel{
    // lista de permissoes que o administrador tem.
    
    private final static List<? extends Permissao> listaPermissoes = List.of(new AdministraUsuario(), new IOJogador(), new IOSelecao(),
                                                                             new OrganizaEstadio(), new DesignacaoArbitro(), new PartidaCopa(), new GerenciaIngressos());
    
    public Administrador() {
        this.nomePapel = "ADMINISTRADOR";
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
        return "Administrador";
    }
    
}
