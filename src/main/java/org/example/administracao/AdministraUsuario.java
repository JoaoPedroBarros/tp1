/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.administracao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.example.administracao.excecoes.CamposEmBrancoException;
import org.example.administracao.excecoes.SenhaInsuficienteException;

/**
 *
 * @author arkham
 */
public class AdministraUsuario extends Permissao {
    // todos os metodos serao booleanos para retornar o resultado da operacao
    // a verificacao de permissao estara nas telas, segundo o RBAC. Isso evita compilacao ciclica
    
    
    @Override
    public String getNome() {
        return "ADMINISTRA_USUARIO";
    }
    
    static public void criaUsuario(Usuario usuarioCadastro, PersistenciaUsuario persistencia) throws SenhaInsuficienteException, CamposEmBrancoException {
       Map<String, Usuario> mapUsuario = persistencia.getMapUsuarios();
       
       if (usuarioCadastro.getNome().isBlank() || usuarioCadastro.getEmail().isBlank() || usuarioCadastro.getPais().isBlank()) {
           throw new CamposEmBrancoException("Há campos do cadastro que não foram preenchidos");
       }
       
       if (usuarioCadastro.getSenha().length() < 8) {
           throw new SenhaInsuficienteException("Senha insuficiente");
       }
       
       mapUsuario.put(usuarioCadastro.getIdentificacao(), usuarioCadastro);
       persistencia.salvarPersistencia();
    }
    
    static public List<Usuario> listaUsuario(PersistenciaUsuario persistencia) {
        Map<String, Usuario> mapUsuario = persistencia.getMapUsuarios();
        List<Usuario> listaRetorno = new ArrayList<>();
        
        for (Map.Entry<String, Usuario> entry : mapUsuario.entrySet()) {
            listaRetorno.add(entry.getValue());
        }
        
        return listaRetorno;   
    }
    
    static public List<Usuario> pesquisaUsuario(String nome, String identificacao, String email, String pais, Papel papel, PersistenciaUsuario persistencia) {
        // TODO: provavelmente nao vai ser elegante criar um poliformismo por overloading.
        // aqui, vou cuidar para, na tela, receber os argumentos. Se ele estiver desabilitado, vou receber como null
        // para ignorar durante a busca.
        
        Map<String, Usuario> mapUsuarios = persistencia.getMapUsuarios();
        List<Usuario> retornaListaUsuarios = new ArrayList<>();
        
        System.out.println(email);
            
        // 1º caso: se a identificacao nao for nula, ha apenas um retorno. Portanto, pode simplesmente ver se existe no JSON e retornar uma lista unitaria
        // 2º caso: iterar e coletar os usuarios que coincidem com algum parametro.
            
        if (identificacao.isBlank() == false && mapUsuarios.containsKey(identificacao)) {
            retornaListaUsuarios.add(mapUsuarios.get(identificacao));
        }
            
        else {
            retornaListaUsuarios = listaUsuario(persistencia);
            
            // funciona, mas pensar depois meios pra deixar o codigo mais bonito
            
            if (nome != null && nome.isEmpty() == false) {
                retornaListaUsuarios = retornaListaUsuarios.stream().filter(u -> u.getNome().contains(nome)).collect(Collectors.toList());
            }
            
            if (email != null && email.isEmpty() == false) {
                retornaListaUsuarios = retornaListaUsuarios.stream().filter(u -> u.getEmail().contains(email)).collect(Collectors.toList());
            }
            
            if (pais.isEmpty() == false) {
                retornaListaUsuarios = retornaListaUsuarios.stream().filter(u -> u.getPais().contains(pais)).collect(Collectors.toList());
            }
            
            if (papel != null) {
                retornaListaUsuarios = retornaListaUsuarios.stream().filter(u -> u.getPapel().getClass() == papel.getClass()).collect(Collectors.toList());
            }
        }
        
        return retornaListaUsuarios;
    }
    
    
    static public void setStaticId(Map<String, Usuario> persistenciaUsuario) {
        int maior = 0;
        
        for (Map.Entry<String, Usuario> u : persistenciaUsuario.entrySet()) {
            int idNum = Integer.parseInt(u.getValue().getIdentificacao());
            
            if (idNum > maior) maior = idNum;
        }
        
        Usuario.idController = maior + 1;
    }
}
