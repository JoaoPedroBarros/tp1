/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.administracao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author arkham
 */

// essa classe serve para administrar o arquivo de persistencia de usuarios.
// O .jar nao permite que arquivos internos, como resources, sejam modificados
// externamente. Dessa maneira, modificacoes diretas sao possiveis apenas na IDE.
// Essa classe cria um arquivo externo que mantem as mudancas e faz com que a persistencia
// seja possivel independente da plataforma

public class PersistenciaUsuario {
    private final File arquivoUsuarios; // caminho para o arquivo externo
    private final ObjectMapper mapper; // mapper que serializa o JSON
    private Map<String, Usuario> mapUsuarios; // hash map onde os usuarios serao armazenados
    
    public PersistenciaUsuario() {
        this.arquivoUsuarios = new File("usuarios_ext.json"); 
        this.mapper = new ObjectMapper();
        this.mapUsuarios = new HashMap<>();
        
        carregarDados();
    }
    
    private void carregarDados() {
        try {
            if (arquivoUsuarios.exists()) {
                mapUsuarios = mapper.readValue(arquivoUsuarios, new TypeReference<Map<String, Usuario>>(){});
            }
            
            else {
                try (InputStream is = PersistenciaUsuario.class.getResourceAsStream("/usuarios.json")) {
                    if (is != null) {
                        mapUsuarios = mapper.readValue(is, new TypeReference<Map<String, Usuario>>(){});
                    }
                }
                salvarPersistencia();
            }
        }
        
        catch (IOException e) {
            System.err.println("Erro ao carregar persistência de usuários");
            System.err.println(e.getMessage());
        }
    }
    
    public void salvarPersistencia() {
        try {
            mapper.writeValue(arquivoUsuarios, mapUsuarios);
        }
                
        catch (IOException e) {
            System.err.println("Erro ao salvar dados no arquivo");
        }
    }

    public Map<String, Usuario> getMapUsuarios() {
        return mapUsuarios;
    }
    
}
