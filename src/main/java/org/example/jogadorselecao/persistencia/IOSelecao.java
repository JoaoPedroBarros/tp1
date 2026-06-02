package org.example.jogadorselecao.persistencia;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.jogadorselecao.Jogador;
import org.example.jogadorselecao.Selecao;
import com.fasterxml.jackson.core.JsonGenerator;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.example.administracao.Permissao;

public class IOSelecao extends Permissao{
    //Metodos personalizados
    @Override
    public String getNome(){
        return "ORGANIZA_SELECAO";
    }
    
    //Verifica se a selecao já está salvo no arquivo (Responde a pergunta: Já existe uma selecao o mesmo pais ou mesmo tecnico?)
    //Se a selecao estiver no arquivo de persistencia, retorna a linha em que foi achado.
    //Se a selecao não estiver no arquivo, retorna -1.
    public static int containsSelecao(Selecao selecao){
        File arquivo = new File("src/main/resources/selecoes.jsonl");
        ObjectMapper mapper = new ObjectMapper();// Instancia Mapeamento padrao da biblioteca Jackson
        String linha;
        int index = 1;
        
        try(BufferedReader leitura = new BufferedReader(new FileReader(arquivo))){
            String jsonStr = mapper.writeValueAsString(selecao); // Conversão da classe Selecao para string de Json
            
            while((linha = leitura.readLine()) != null){
                //Compara somente os nomes dos jogadores
                if(linha.substring(0, linha.indexOf(","))
                   .equals(jsonStr.substring(0, jsonStr.indexOf(",")))){ //Compara somente os nomes do Selecoes
                    break;
                }
                index++;
            }
            
            return linha == null ? -1 : index;            
        }
        catch(IOException e){
            System.err.println("Ocorreu um erro inesperado na leitura do arquivo.");
        } 
        return -1; //Retorno default
    }
    
    //Append jogadores no arquivo jogadores.jsonl
    public static void appendSelecao(Selecao selecao){
        if (containsSelecao(selecao) != -1){return;}
        
        ObjectMapper mapper = new ObjectMapper();          // Instancia Mapeamento padrao da biblioteca Jackson
        mapper.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
        
        try (FileOutputStream os = new FileOutputStream("src/main/resources/selecoes.jsonl", true)) {
            mapper.writeValue(os, selecao);
            os.write("\n".getBytes());
        } catch (IOException e) {
            System.err.println("Nao foi possivel adicionar a selecao ao arquivo de persistencia.");
        }
    }
    
    //Apaga jogador do arquivo de persistencia
    public static void deleteSelecao(Selecao selecao){
        File arquivoOriginal = new File("src/main/resources/selecoes.jsonl");
        File arquivoTemp = new File("src/main/resources/temp.jsonl");

        ObjectMapper mapper = new ObjectMapper();// Instancia Mapeamento padrao da biblioteca Jackson
        String linha; //String auxiliar para leitura    
        int cont = 1;
        
        try (BufferedReader leitura = new BufferedReader(new FileReader(arquivoOriginal));
             BufferedWriter escrita = new BufferedWriter(new FileWriter(arquivoTemp))){

            String jsonStr = mapper.writeValueAsString(selecao); // Conversão da classe jogador para string de Json
            
            //Atualiza arquivo temporário com todos os dados, exceto o deletado
            while((linha = leitura.readLine()) != null){
                if(jsonStr.equals(linha)){
                    cont++;
                    continue; //Não escreve a linha que se deseja apagar
                }
                escrita.write(linha + "\n"); //Escreve linha no arquivo temporário
                cont++;
            }
        } catch (IOException e) {
            System.err.println("Nao foi possivel abrir o arqiuivo para deleção.");
        }
        
        if(arquivoOriginal.delete()){
            arquivoTemp.renameTo(arquivoOriginal); //Renomeia o arquivo temporário para o nome padrão
        }
        else{
            System.err.println("Nao foi possivel deletar o arquivo.");
        }
    }
    
}
