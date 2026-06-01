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

public class GerenciadorEstadioJSON {
    private final String CAMINHO_ARQUIVO = "src/main/resources/estadios.json";
    private ObjectMapper mapper;
    
    public GerenciadorEstadioJSON(){
        this.mapper = new ObjectMapper();
    }
    
    public void salvarEstadios(List<Estadio> estadios){
        try{
            mapper.writeValue(new File(CAMINHO_ARQUIVO), estadios);
            System.out.println("Estádios salvos no arquivo com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }
    
    public List<Estadio> carregarEstadio(){
        File arquivo = new File(CAMINHO_ARQUIVO);
        
        if (!arquivo.exists()) {
            return new ArrayList<>();
        }
        
        try {
            return mapper.readValue(arquivo, new TypeReference<List<Estadio>>(){});
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            return new ArrayList<>();
        }
    
    }
}
