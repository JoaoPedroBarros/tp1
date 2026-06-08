/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.administracao;

import java.util.Map;

/**
 *
 * @author arkham
 */
public class GeraRelatorios extends Permissao {
    
    @Override
    public String getNome() {
        return "GERA_RELATORIOS";
    }
    
    static public int contaUsuariosTotal(PersistenciaUsuario persistenciaUsuario) {        
        Map<String, Usuario> mapUsuarios = persistenciaUsuario.getMapUsuarios();
        
        int resultado = 0;
        
        for (Map.Entry<String, Usuario> entry : mapUsuarios.entrySet()) {
            resultado++;
        }
        
        return resultado;
    }
}
