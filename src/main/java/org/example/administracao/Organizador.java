package org.example.administracao;

import java.util.List;
import org.example.jogadorselecao.persistencia.IOJogador;
import org.example.jogadorselecao.persistencia.IOSelecao;

public class Organizador extends Papel{
    
    private final static List<? extends Permissao> listaPermissoes = List.of(new IOJogador(), new IOSelecao());
    
    @Override
    public String getNomePapel() {
        return "Organizador";
    }
    
    @Override
    public List<? extends Permissao> getPermissoes() {
        return listaPermissoes;
    }
    
    @Override
    public String toString() {
        return "ORGANIZADOR";
    }
    
}
