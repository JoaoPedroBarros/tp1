package org.example.jogadorselecao.persistencia;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.jogadorselecao.Jogador;
import com.fasterxml.jackson.core.JsonGenerator;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;
import javax.imageio.IIOException;
import org.example.administracao.Permissao;

public class IOJogador extends Permissao{
    
    //Metodos personalizados
    @Override
    public String getNome(){
        return "ORGANIZA_JOGADOR";
    }    
    
    //Verifica se o jogador já está salvo no arquivo (Responde a pergunta: Já existe um jogador com mesmo nome?)
    //Se o jogador estiver no arquivo de persistencia, retorna a linha em que foi achado.
    //Se o jogador não estiver no arquivo, retorna -1.
    public static int containsJogador(Jogador jogador){
        File arquivo = new File("src/main/resources/jogadores.jsonl");
        ObjectMapper mapper = new ObjectMapper();// Instancia Mapeamento padrao da biblioteca Jackson
        String linha;
        int index = 1;
        
        try(BufferedReader leitura = new BufferedReader(new FileReader(arquivo))){
            String jsonStr = mapper.writeValueAsString(jogador); // Conversão da classe jogador para string de Json
            
            while((linha = leitura.readLine()) != null){
                //Compara somente os nomes dos jogadores
                if(linha.substring(0, linha.indexOf(","))
                   .equalsIgnoreCase(jsonStr.substring(0, jsonStr.indexOf(",")))){ //Compara somente os nomes do Jogadores
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
    public static void appendJogador(Jogador jogador) throws ElementoDuplicado{
        if (containsJogador(jogador) != -1){
            throw new ElementoDuplicado("Jogador já está registrado no sistema.");
        }
        
        ObjectMapper mapper = new ObjectMapper();          // Instancia Mapeamento padrao da biblioteca Jackson
        mapper.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
        
        try (FileOutputStream os = new FileOutputStream("src/main/resources/jogadores.jsonl", true)) {
            mapper.writeValue(os, jogador);
            os.write("\n".getBytes());
        } catch (IOException e) {
            System.err.println("Nao foi possivel adicionar o jogador ao arquivo de persistencia.");
        }
    }
    
    //Apaga jogador do arquivo de persistencia dado o nome do Jogador
    public static void deleteJogador(String nome) throws IOException{
        File arquivoOriginal = new File("src/main/resources/jogadores.jsonl");
        File arquivoTemp = new File("src/main/resources/temp.jsonl");

        String linha; //String auxiliar para leitura    
        
        try (BufferedReader leitura = new BufferedReader(new FileReader(arquivoOriginal));
             BufferedWriter escrita = new BufferedWriter(new FileWriter(arquivoTemp))){

            String comparador = "{\"nome\":\"" + nome + "\"";
                    
            //Atualiza arquivo temporário com todos os dados, exceto o deletado
            while((linha = leitura.readLine()) != null){
                if(linha.substring(0, linha.indexOf(",")).equals(comparador)){ //Compara somente os nomes do Jogadores
                    continue; //Não escreve a linha que se deseja apagar
                }
                escrita.write(linha + "\n"); //Escreve linha no arquivo temporário
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
    
    //Retorna uma HashSet de Jogadores da Memória, com a caractéristica fornecida
    //Se não houver jogadores com a característica escolhida, retorna nulo.
    //Caso contrário, retorna uma HashSet de Jogadores com a caractéristica escolhida
    //No vetor indice
    public static List<Jogador> getMemJogadores(Predicate<Jogador> criterio, List<Integer> indices){
        
        List<Jogador> listaFiltrada = new ArrayList<>();
        File save = new File("src/main/resources/jogadores.jsonl");
        ObjectMapper mapper = new ObjectMapper();// Instancia Mapeamento padrao da biblioteca Jackson
        String linha; //String auxiliar para leitura
        int cont = 0;
        Jogador aux;
        
        try(BufferedReader leitura = new BufferedReader(new FileReader(save))){            
            while((linha = leitura.readLine()) != null){
                aux = mapper.readValue(linha, Jogador.class);
                if(criterio.test(aux)){
                    listaFiltrada.add(aux);
                    indices.add(cont);
                }
                cont++;
            }
        } catch (IOException ex) {
            System.err.println("Erro ao abrir o arquivo.");
        }
        
        return listaFiltrada;
    }
    
    //Edita um objeto Jogador da Memória, se ele estiver na memória
    //Caso não exista, não faz nada.
    public static void setMemJogador(Jogador jogador){
        int indice;
        if((indice = containsJogador(jogador)) == -1){return;}
        
        File save = new File("src/main/resources/jogadores.jsonl");
        File temp = new File("src/main/resources/temp.jsonl");
        ObjectMapper mapper = new ObjectMapper();// Instancia Mapeamento padrao da biblioteca Jackson
        String linha; //String auxiliar para leitura
        int cont = 0;
        
        try(BufferedReader leitura = new BufferedReader(new FileReader(save));
            BufferedWriter escrita = new BufferedWriter(new FileWriter(temp))){
            String jsonStr = mapper.writeValueAsString(jogador);
            
            while((linha = leitura.readLine()) != null){
                cont++;
                if(cont == indice){
                    escrita.write(jsonStr + "\n");
                }
                else{
                    escrita.write(linha + "\n");
                }
            }
        } catch (IOException ex) {
            System.err.println("Erro ao abrir o arquivo.");
        }
        
        if(save.delete()){
           temp.renameTo(save); //Renomeia o arquivo temporário para o nome padrão
        }
        else{
            System.err.println("Nao foi possivel deletar o arquivo.");
        }
    }  
    
    public static void insert(Jogador jogador, int index) throws IOException{
        File arquivoOriginal = new File("src/main/resources/jogadores.jsonl");
        File arquivoTemp = new File("src/main/resources/temp.jsonl");

        String linha; //String auxiliar para leitura
        ObjectMapper mapper = new ObjectMapper();          // Instancia Mapeamento padrao da biblioteca Jackson
        int cont = 0;
        try (BufferedReader leitura = new BufferedReader(new FileReader(arquivoOriginal));
             BufferedWriter escrita = new BufferedWriter(new FileWriter(arquivoTemp))){
             
            String jsonStr = mapper.writeValueAsString(jogador);

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
    }

    public static void insertMult(List<Jogador> jogadores, List<Integer> indices) throws IOException{
        if(jogadores.size() != indices.size()){
            throw new IOException("Tamanhos das listas não coincidem.");
        }
        
        File arquivoOriginal = new File("src/main/resources/jogadores.jsonl");
        File arquivoTemp = new File("src/main/resources/temp.jsonl");

        String linha; //String auxiliar para leitura
        ObjectMapper mapper = new ObjectMapper();          // Instancia Mapeamento padrao da biblioteca Jackson
        int cont = 0;
        try (BufferedReader leitura = new BufferedReader(new FileReader(arquivoOriginal));
             BufferedWriter escrita = new BufferedWriter(new FileWriter(arquivoTemp))){
             
            String jsonStr;

            //Atualiza arquivo temporário com todos os dados, exceto o deletado
            while((linha = leitura.readLine()) != null){
                if(!indices.isEmpty() && cont == indices.get(0)){ //Compara somente os nomes do Jogadores
                    indices.remove(0);
                    jsonStr = mapper.writeValueAsString(jogadores.get(0));
                    jogadores.remove(0);
                    escrita.write(jsonStr + "\n");
                    cont++;
                    continue;
                }
                escrita.write(linha + "\n"); //Escreve linha no arquivo temporário
                cont++;
            }
            //Escrita na ultima posicao
             if(!indices.isEmpty()){
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

    
    public static Jogador get(int index) throws IOException{
        if(index == -1){return null;}
        
        File arquivo = new File("src/main/resources/jogadores.jsonl");
        ObjectMapper mapper = new ObjectMapper();// Instancia Mapeamento padrao da biblioteca Jackson
        String linha;
        Jogador jogador = null;
        int cont = 0;
        
        try(BufferedReader leitura = new BufferedReader(new FileReader(arquivo))){            
            while((linha = leitura.readLine()) != null){
                if(cont == index){
                    jogador = mapper.readValue(linha, Jogador.class);
                }
                cont++;
            }          
        }
        catch(IOException e){
            throw new IOException("Ocorreu um erro inesperado na leitura do arquivo.");
        } 
        
        if(jogador == null){
            throw new IOException("Jogador não encontrado. Get não foi executado corretamente.");
        }
        
        return jogador;
    }
    
    public static List<Jogador> getMult(List<Integer> indices) throws IOException, IllegalArgumentException{
        if(indices.isEmpty() || indices.get(0) == -1){
            throw new IllegalArgumentException("Lista de indices inválida.");
        }
        
        File arquivo = new File("src/main/resources/jogadores.jsonl");
        ObjectMapper mapper = new ObjectMapper();// Instancia Mapeamento padrao da biblioteca Jackson
        String linha;
        List<Jogador> jogadores = new ArrayList<>();
        int iterador = 0;
        int cont = 0;
        
        try(BufferedReader leitura = new BufferedReader(new FileReader(arquivo))){            
            while(iterador < indices.size() && (linha = leitura.readLine()) != null){
                if(cont == indices.get(iterador)){
                    jogadores.add(mapper.readValue(linha, Jogador.class));
                    iterador++;
                }
                cont++;
            }          
        }
        catch(IOException e){
            throw new IOException("Ocorreu um erro inesperado na leitura do arquivo.");
        } 
        
        if(iterador < indices.size()){
            throw new IOException("Jogador não encontrado. GetMul não foi executado corretamente.");
        }
        
        return jogadores;        
    }
    
    
    //Função extra (mais para legibilidade)
    //Guarda todos os novos jogadores distintos no arquivo de persistência
    public static void adicionarJogadores(List<Jogador> jogadores) throws ElementoDuplicado{
        for(var jogador : jogadores){
            appendJogador(jogador);
        }
    }
    
    //Métodos não escalonáveis
    //Mantive esses métodos para discutir depois a real praticidade e impacto de usar procedimenro não escalonáveis
    //NE significa Não Escalonável
    public static void appendJogNE(Jogador jogador){
        
    }
    
    public static void atualizaJogsNE(HashSet<Jogador> jogadores){
        
    }
}
