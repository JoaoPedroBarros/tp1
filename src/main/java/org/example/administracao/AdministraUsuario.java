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
import java.util.Random;
import org.example.administracao.excecoes.CamposEmBrancoException;
import org.example.administracao.excecoes.SenhaInsuficienteException;

/**
 *
 * @author arkham
 */
public class AdministraUsuario extends Permissao {
    // todos os metodos serao booleanos para retornar o resultado da operacao
    // a verificacao de permissao estara nas telas, segundo o RBAC. Isso evita compilacao ciclica
    
    static final public String USUARIOS_FILE_PATH = "src/main/resources/usuarios.json";
    
    @Override
    public String getNome() {
        return "ADMINISTRA_USUARIO";
    }
    
    static public void criaUsuario(Usuario usuarioCadastro, PersistenciaUsuario persistencia) throws SenhaInsuficienteException, CamposEmBrancoException {
       Map<String, Usuario> mapUsuario = persistencia.getMapUsuarios();
       
       if (usuarioCadastro.getSenha().length() < 8) {
           throw new SenhaInsuficienteException("Senha insuficiente");
       }
       
       if (usuarioCadastro.getNome().isBlank() || usuarioCadastro.getEmail().isBlank() || usuarioCadastro.getPais().isBlank()) {
           throw new CamposEmBrancoException("Há campos do cadastro que não foram preenchidos");
       }
       
       mapUsuario.put(usuarioCadastro.getIdentificacao(), usuarioCadastro);
       persistencia.salvarPersistencia();
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
            System.err.println("Houve algum problema no mapeamento do JSON ao listar usuários");
        }
        
        catch (IOException e) {
            System.err.println("Houve algum problema ao manipular o arquivo ao listar usuários");
        }
        
        return retornaListaUsuarios;
    }
    
    static public boolean editaUsuario (Usuario usuario) {
        //  ao entrar na tela de editar, instanciar um usuario e copiar os valores editados para a persistencia
       
        return true;
    }
    
    static public List<Usuario> pesquisaUsuario(String nome, String identificacao, String email, String pais, String senha, Usuario.StatusUsuario status, Papel papel) {
        // TODO: provavelmente nao vai ser elegante criar um poliformismo por overloading.
        // aqui, vou cuidar para, na tela, receber os argumentos. Se ele estiver desabilitado, vou receber como null
        // para ignorar durante a busca.
        
        ObjectMapper mapper = new ObjectMapper();
        File persistenciaUsuarios = new File(USUARIOS_FILE_PATH);
        List<Usuario> retornaListaUsuarios = new ArrayList<>();
        
        
        try {
            Map<String, Usuario> mapUsuarios = mapper.readValue(persistenciaUsuarios, new TypeReference<Map<String, Usuario>>(){});
            
            // 1º caso: se a identificacao nao for nula, ha apenas um retorno. Portanto, pode simplesmente ver se existe no JSON e retornar uma lista unitaria
            // 2º caso: iterar e coletar os usuarios que coincidem com algum parametro.
            
            if (identificacao.isEmpty() == false && mapUsuarios.containsKey(identificacao)) {
                retornaListaUsuarios.add(mapUsuarios.get(identificacao));
            }
            
            else {
                for (Map.Entry<String, Usuario> entry : mapUsuarios.entrySet()) {
                    Usuario usuarioIteracao = entry.getValue();
                    
                    if (usuarioIteracao.getNome().equals(nome) || usuarioIteracao.getEmail().equals(email) || usuarioIteracao.getPais().equals(pais)
                        || usuarioIteracao.getStatus() == status || usuarioIteracao.getPapel().getClass() == papel.getClass()) {
                            retornaListaUsuarios.add(usuarioIteracao);
                    }
                    
                    // isso jah trata a duplicata, porque um usuario vai ser analisado apenas uma vez. Melhor do que fazer um if ou switch case para cada caso
                }
                
            }
            
        }
        
        catch (JsonMappingException e) {
            System.err.println("Houve algum problema no mapeamento do JSON durante a pesquisa de usuários");
        }
        
        catch (IOException e) {
            System.err.println("Houve algum problema ao manipular o arquivo durante a pesquisa de usuários");
        }
        
        return retornaListaUsuarios;
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
            System.err.println("Houve algum problema no mapeamento do JSON ao excluir usuário");
            return false;
        }
        
        catch (IOException e) {
            System.err.println("Houve algum problema ao manipular o arquivo ao excluir usuário");
            return false;
        }
        return true;
    }
    
    static public String gerarId(Map<String, Usuario> persistenciaUsuario) {
        Random rand = new Random();
        String id;
        
        do {
            int n = rand.nextInt(40000);
            n += 260000;
            id = Integer.toString(n);
        } while (persistenciaUsuario.containsKey(id) == true);
        
        return id;
    }
}
