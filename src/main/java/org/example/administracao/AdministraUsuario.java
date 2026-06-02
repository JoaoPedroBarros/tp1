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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author arkham
 */
public class AdministraUsuario extends Permissao {
    // todos os metodos serao booleanos para retornar o resultado da operacao
    // a verificacao de permissao estara nas telas, segundo o RBAC. Isso evita compilacao ciclica
    
    static final String USUARIOS_FILE_PATH = "src/main/resources/usuarios.json";
    
    @Override
    public String getNome() {
        return "ADMINISTRA_USUARIO";
    }
    
    static public boolean criaUsuario(Usuario usuarioCadastro, UsuarioLogado usuarioSessao) {
        
        ObjectMapper mapper = new ObjectMapper();
        File persistenciaUsuarios = new File(USUARIOS_FILE_PATH);
        
        try {
            Map<String,Usuario> mapUsuarios = mapper.readValue(persistenciaUsuarios, new TypeReference<Map<String,Usuario>>(){});
            mapUsuarios.put(usuarioCadastro.getIdentificacao(),usuarioCadastro);
            mapper.writeValue(persistenciaUsuarios, mapUsuarios);
        }
        
        catch (JsonMappingException e) {
            System.err.println("Houve algum problema no mapeamento do JSON");
            return false;
        }
        
        catch (IOException e) {
            System.err.println("Houve algum problema ao manipular o arquivo");
            return false;
        }
                
        return true;
    }
    
    static public List<Usuario> listaUsuario() {
        ObjectMapper mapper = new ObjectMapper();
        File persistenciaUsuarios = new File(USUARIOS_FILE_PATH);
        List<Usuario> retornaListaUsuarios = new ArrayList<>();
        
        try {
            Map<String, Usuario> mapUsuarios = mapper.readValue(persistenciaUsuarios, new TypeReference<Map<String, Usuario>>(){});
            
            for (Map.Entry<String, Usuario> entry : mapUsuarios.entrySet()) {
                retornaListaUsuarios.add(entry.getValue());
            }
        }
        
        catch (JsonMappingException e) {
            System.err.println("Houve algum problema no mapeamento do JSON");
        }
        
        catch (IOException e) {
            System.err.println("Houve algum problema ao manipular o arquivo");
        }
        
        return retornaListaUsuarios;
    }
    
    static public boolean pesquisaUsuario() {
        // TODO: provavelmente nao vai ser elegante criar um poliformismo por overloading.
        // aqui, vou cuidar para, na tela, receber os argumentos. Se ele estiver desabilitado, vou receber como null
        // para ignorar durante a busca.
        return true;
    }
    
    static public boolean excluiUsuario(Usuario usuario) {
        ObjectMapper mapper = new ObjectMapper();
        File persistenciaUsuarios = new File(USUARIOS_FILE_PATH);
        
        try {
            Map<String,Usuario> mapUsuarios = mapper.readValue(persistenciaUsuarios, new TypeReference<Map<String,Usuario>>(){});
            mapUsuarios.remove(usuario.getIdentificacao());
            mapper.writeValue(persistenciaUsuarios, mapUsuarios);
        }
        
        catch (JsonMappingException e) {
            System.err.println("Houve algum problema no mapeamento do JSON");
            return false;
        }
        
        catch (IOException e) {
            System.err.println("Houve algum problema ao manipular o arquivo");
            return false;
        }
        return true;
    }
}
