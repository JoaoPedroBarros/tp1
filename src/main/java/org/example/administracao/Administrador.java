package org.example.administracao;

import java.util.List;

public class Administrador extends Papel{
    // lista de permissoes que o administrador tem.
    final static List<? extends Permissao> permissoesAdministrador = List.of(new AdministraUsuario());
    
    public Administrador() {
        this.nomePapel = "ADMINISTRADOR";
    }
    
    @Override
    public String getNomePapel() {
        return nomePapel;
    }
    
}
