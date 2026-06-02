/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.administracao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import static org.example.administracao.AdministraUsuario.USUARIOS_FILE_PATH;

/**
 *
 * @author arkham
 */
public class GeraRelatorios extends Permissao {
    
    @Override
    public String getNome() {
        return "GERA_RELATORIOS";
    }
    
    static public Map<String, Integer> contaUsuarios() {        
        int[] vetorAuxiliar = {0, 0, 0, 0, 0};
        
        ObjectMapper mapper = new ObjectMapper();
        File persistenciaUsuarios = new File(USUARIOS_FILE_PATH);
        
        
        try {
            Map<String, Usuario> mapUsuarios = mapper.readValue(persistenciaUsuarios, new TypeReference<Map<String, Usuario>>(){});
            
            for (Map.Entry<String, Usuario> entry : mapUsuarios.entrySet()) {
                vetorAuxiliar[0]++;
                
                switch (entry.getValue().papel) {
                    case Administrador u -> {
                        vetorAuxiliar[1]++;
                    }
                    
                    case Organizador u -> {
                        vetorAuxiliar[2]++;
                    }
                    
                    case Operador u -> {
                        vetorAuxiliar[3]++;
                    }
                    
                    default -> {
                        // nao faz nada
                    }
                    
                } 
            }
        }
        
        catch (JsonMappingException e) {
            System.err.println("Houve algum problema no mapeamento do JSON ao gerar relatórios de usuário");
        }
        
        catch (IOException e) {
            System.err.println("Houve algum problema ao manipular o arquivo ao gerar relatórios de usuário");
        }
        
        Map<String, Integer> mapaUsuarios = Map.of (
                "numTotalUsuarios", vetorAuxiliar[0],
                "numAdministradores", vetorAuxiliar[1],
                "numOrganizadores", vetorAuxiliar[2],
                "numOperadores", vetorAuxiliar[3],
                "numArbitros", vetorAuxiliar[4]
        );
        
        return mapaUsuarios;
    }
}
