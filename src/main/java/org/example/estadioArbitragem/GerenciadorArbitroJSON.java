/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.estadioArbitragem;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorArbitroJSON {
    private final String CAMINHO_ARQUIVO = "src/main/resources/arbitros.json";
    private ObjectMapper mapper;
    
    public GerenciadorArbitroJSON(){
        this.mapper = new ObjectMapper();
    }
    
    public void salvarArbitro(List<Arbitro> arbitro){
        try{
            mapper.writeValue(new File(CAMINHO_ARQUIVO), arbitro);
            System.out.println("Arquivo salvo com sucesso");
        } catch(IOException e){
            System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }
    
    public List<Arbitro> carregarArbitro(){
        File arquivo = new File(CAMINHO_ARQUIVO);
        
        if(!arquivo.exists()){
            return new ArrayList<>();
        }
        try{
            return mapper.readValue(arquivo, new TypeReference<List<Arbitro>>(){});
             
        } catch (IOException e){
            System.out.println("Erro ao ler o arquivo: "+e.getMessage());
            return new ArrayList<>();
        }
    }
}
