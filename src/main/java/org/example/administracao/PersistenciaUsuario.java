/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.administracao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author arkham
 */

public class PersistenciaUsuario {
    // o objetivo principal desta classe eh tornar o resto do codigo mais limpo. Se ela nao existisse, cada metodo que mexe com a persistencia teria varios try catches
    // repetitivos. Aqui, tudo eh feito com apenas uma linha.
    
    private final File arquivoUsuarios; // caminho para o arquivo externo
    private final ObjectMapper mapper; // mapper que serializa o JSON
    private Map<String, Usuario> mapUsuarios; // hash map onde os usuarios serao armazenados
    
    public PersistenciaUsuario() {
        this.arquivoUsuarios = new File("src/main/resources/usuarios.json"); // caminnho da persistencia
        this.mapper = new ObjectMapper(); // instancia o mapper do json
        this.mapUsuarios = new HashMap<>(); // instancia o map de usuarios
        
        carregarDados(); // carrega os usuarios no map
    }
    
    private void carregarDados() {
        try {
            mapUsuarios = mapper.readValue(arquivoUsuarios, new TypeReference<Map<String, Usuario>>(){}); // le os usuarios do json
        }
        catch (IOException e) { // excecao basica de io do json
            System.err.println("Erro ao carregar persistência de usuários");
            System.err.println(e.getMessage());
        }
    }
    
    public boolean salvarPersistencia() { // retorna true se o procedimento for bem sucedido, interessante para algumas excecoes
        try {
            mapper.writeValue(arquivoUsuarios, mapUsuarios); // escreve o mapa na persistencia, editando-a casa haja alguma mudanca
            return true;
        }
                
        catch (IOException e) { // excecao de io do json
            System.err.println("Erro ao salvar dados no arquivo");
            return false;
        }
    }

    public Map<String, Usuario> getMapUsuarios() {
        return mapUsuarios;
    }
    
}
