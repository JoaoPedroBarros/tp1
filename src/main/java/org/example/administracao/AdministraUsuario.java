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
    // essa classe de permissao reune os metodos de administracao de usuario. Apenas papeis que tenham ela em sua lista de permissoes
    // podem realiza-las
    
    @Override
    public String getNome() {
        return "ADMINISTRA_USUARIO";
    }
    
    static public void criaUsuario(Usuario usuarioCadastro, PersistenciaUsuario persistencia) throws SenhaInsuficienteException, CamposEmBrancoException {
       Map<String, Usuario> mapUsuario = persistencia.getMapUsuarios(); // carrega o map de usuarios
       
       if (usuarioCadastro.getNome().isBlank() || usuarioCadastro.getEmail().isBlank() || usuarioCadastro.getPais().isBlank()) {
           throw new CamposEmBrancoException("Há campos do cadastro que não foram preenchidos"); // excecao auto explicativa
       }
       
       if (usuarioCadastro.getSenha().length() < 8) {
           throw new SenhaInsuficienteException("Senha insuficiente"); // excecao auto explicativa
       }
       
       mapUsuario.put(usuarioCadastro.getIdentificacao(), usuarioCadastro); // adiciona o novo usuario ao map
       persistencia.salvarPersistencia(); // salva a persistencia
    }
    
    static public List<Usuario> listaUsuario(PersistenciaUsuario persistencia) { // funcao para listar todos os usuarios
        Map<String, Usuario> mapUsuario = persistencia.getMapUsuarios(); 
        List<Usuario> listaRetorno = new ArrayList<>();
        
        for (Map.Entry<String, Usuario> entry : mapUsuario.entrySet()) {
            listaRetorno.add(entry.getValue());
        }
        
        return listaRetorno;   
    }
    
    static public List<Usuario> pesquisaUsuario(String nome, String identificacao, String email, String pais, Papel papel, PersistenciaUsuario persistencia) {
        // funcao de pesquisa de usuario
        Map<String, Usuario> mapUsuarios = persistencia.getMapUsuarios();
        List<Usuario> retornaListaUsuarios = new ArrayList<>(); // inicia lista vazia para coletar usuarios
            
        // 1º caso: se a identificacao nao for nula, ha apenas um retorno. Portanto, pode simplesmente ver se existe no JSON e retornar uma lista unitaria
        // 2º caso: iterar e coletar os usuarios que coincidem com algum parametro.
            
        if (identificacao.isBlank() == false && mapUsuarios.containsKey(identificacao)) {
            retornaListaUsuarios.add(mapUsuarios.get(identificacao));
        }
        
        // cada condicional avalia se um usuario da lista coincide com um parametro. Se sim, adiciona a lista. Note que utilizamos
        // a API de streams com o metodo filter, alem de converte-los para as colecoes com o metodo collect. Aqui, optou-se por nao utilizar
        // um supplier, para conseguirmos atualizar a lista a cada criterio.
            
        else {
            retornaListaUsuarios = listaUsuario(persistencia);
            
            if (nome != null && nome.isEmpty() == false) {
                retornaListaUsuarios = retornaListaUsuarios.stream().filter(u -> u.getNome().contains(nome)).collect(Collectors.toList());
            }
            
            if (email != null && email.isEmpty() == false) {
                retornaListaUsuarios = retornaListaUsuarios.stream().filter(u -> u.getEmail().contains(email)).collect(Collectors.toList());
            }
            
            if (pais != null && pais.isEmpty() == false) {
                retornaListaUsuarios = retornaListaUsuarios.stream().filter(u -> u.getPais().contains(pais)).collect(Collectors.toList());
            }
            
            if (papel != null) {
                retornaListaUsuarios = retornaListaUsuarios.stream().filter(u -> u.getPapel().getClass() == papel.getClass()).collect(Collectors.toList());
            }
        }
        
        return retornaListaUsuarios;
    }
    
    
    static public void setStaticId(Map<String, Usuario> persistenciaUsuario) { // metodo para atualizar a id de cadastro de usuario. Achei melhor fazer assim que usar uma persistencia
        // exclusiva
        int maior = 0;
        
        for (Map.Entry<String, Usuario> u : persistenciaUsuario.entrySet()) { // simplesmente pega o maior id. O proximo cadastro serah ele mais 1.
            int idNum = Integer.parseInt(u.getValue().getIdentificacao());
            
            if (idNum > maior) maior = idNum;
        }
        
        Usuario.idController = maior + 1;
    }
}
