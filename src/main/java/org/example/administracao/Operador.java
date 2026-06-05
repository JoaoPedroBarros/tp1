/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.administracao;

import java.util.List;

/**
 *
 * @author arkham
 */
public class Operador extends Papel {
    
    @Override
    public String getNomePapel() {
        return "Operador";
    }
    
    @Override
    public List<? extends Permissao> getPermissoes() {
        return null;
    }
    
}
