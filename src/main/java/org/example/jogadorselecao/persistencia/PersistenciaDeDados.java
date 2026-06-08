package org.example.jogadorselecao.persistencia;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.example.administracao.Permissao;
import org.example.jogadorselecao.Jogador;
import org.example.jogadorselecao.Selecao;
import org.example.jogadorselecao.Tecnico;

public class PersistenciaDeDados extends Permissao{
    
    //Metodos personalizados
    @Override
    public String getNome(){
        return "ORGANIZA_JOGADOR_SELECAO_TECNICO";
    }    

    private static <T> File getFile(T objeto){
        if(objeto instanceof Jogador){
            return new File("src/main/resources/jogadores.jsonl"); 
        }
        else if(objeto instanceof Selecao){
            return new File("src/main/resources/selecoes.jsonl");            
        }
        else if(objeto instanceof Tecnico){
            return new File("src/main/resources/tecnicos.jsonl");            
        }
        else{
            return new File(""); //Tipo onvalido
        }        
    }
    
    //Esse <T> é de Generics
    public static <T> boolean insert(T objeto, int index) throws IOException{
        File arquivoOriginal = getFile(objeto);
        File arquivoTemp = new File("src/main/resources/temp.jsonl");

        String linha; //String auxiliar para leitura
        ObjectMapper mapper = new ObjectMapper();          // Instancia Mapeamento padrao da biblioteca Jackson
        int cont = 0;
        try (BufferedReader leitura = new BufferedReader(new FileReader(arquivoOriginal));
             BufferedWriter escrita = new BufferedWriter(new FileWriter(arquivoTemp))){
             
            String jsonStr = mapper.writeValueAsString(objeto);

            //Atualiza arquivo temporário com todos os dados, exceto o deletado
            while((linha = leitura.readLine()) != null){
                if(cont == index){ //Compara somente os nomes do Jogadores
                    escrita.write(jsonStr + "\n");
                    cont++;
                    continue;
                }
                escrita.write(linha + "\n"); //Escreve linha no arquivo temporário
                cont++;
            }
            //Escrita na ultima posicao
             if(cont <= index){
                 throw new IOException("Index maior do que o tamanho do arquivo.");
             }            
            
        } catch (IOException e) {
            throw new IOException("Nao foi possivel abrir o arqiuivo para deleção.");
        }
        
 
        if(arquivoOriginal.delete()){
            arquivoTemp.renameTo(arquivoOriginal); //Renomeia o arquivo temporário para o nome padrão
        }
        else{
            throw new IOException("Nao foi possivel deletar o arquivo.");
        }
        
        return true;
    }   
    
    public static <T> void insertMult(List<T> objetos, List<Integer> indices) throws IOException{
        if(objetos.size() != indices.size()){
            throw new IOException("Tamanhos das listas não coincidem.");
        }
        
        File arquivoOriginal = getFile(objetos.getFirst());
        File arquivoTemp = new File("src/main/resources/temp.jsonl");

        String linha; //String auxiliar para leitura
        ObjectMapper mapper = new ObjectMapper();          // Instancia Mapeamento padrao da biblioteca Jackson
        int indiceRegistro = 0;
        int indiceListas = 0;
        int tamanhoLista = objetos.size();

        try (BufferedReader leitura = new BufferedReader(new FileReader(arquivoOriginal));
             BufferedWriter escrita = new BufferedWriter(new FileWriter(arquivoTemp))){
             
            String jsonStr;

            //Substitue cada linha com índice em indices pelo correspondente em objetos
            while((linha = leitura.readLine()) != null){
                if(indiceListas >= tamanhoLista || indiceRegistro != indices.get(indiceListas)){
                    escrita.write(linha + "\n"); //Escreve linha no arquivo temporário
                    indiceRegistro++; 
                    continue;
                }
                jsonStr = mapper.writeValueAsString(objetos.get(indiceListas));
                escrita.write(jsonStr + "\n");
                indiceListas++;
                indiceRegistro++;
            }
            //Escrita na ultima posicao
             if(indiceRegistro < indices.get(indiceListas)){
                 throw new IOException("Index maior do que o tamanho do arquivo.");
             }            
            
        } catch (IOException e) {
            throw new IOException("Nao foi possivel abrir o arqiuivo para deleção.");
        }
        
 
        if(arquivoOriginal.delete()){
            arquivoTemp.renameTo(arquivoOriginal); //Renomeia o arquivo temporário para o nome padrão
        }
        else{
            throw new IOException("Nao foi possivel deletar o arquivo.");
        }
    }
    
}