package org.example.administracao;

import java.util.List;

public class Organizador extends Papel{
    
    @Override
    public String getNomePapel() {
        return "Organizador";
    }
    
    @Override
    public List<? extends Permissao> getPermissoes() {
        return null;
    }
    
}
