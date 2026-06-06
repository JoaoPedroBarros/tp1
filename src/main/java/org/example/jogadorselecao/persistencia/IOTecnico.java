package org.example.jogadorselecao.persistencia;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonGenerator;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import org.example.administracao.Permissao;
import org.example.jogadorselecao.Tecnico;

public class IOTecnico extends Permissao{
    
    //Metodos personalizados
    @Override
    public String getNome(){
        return "ORGANIZA_TECNICO";
    }    
    
    //Verifica se o tecnico já está salvo no arquivo (Responde a pergunta: Já existe um tecnico com mesmo nome?)
    //Se o tecnico estiver no arquivo de persistencia, retorna a linha em que foi achado.
    //Se o tecnico não estiver no arquivo, retorna -1.
    public static int containsTecnico(Tecnico tecnico){
        File arquivo = new File("src/main/resources/tecnicos.jsonl");
        ObjectMapper mapper = new ObjectMapper();// Instancia Mapeamento padrao da biblioteca Jackson
        String linha;
        int index = 1;
        
        try(BufferedReader leitura = new BufferedReader(new FileReader(arquivo))){
            String jsonStr = mapper.writeValueAsString(tecnico); // Conversão da classe Tecnico para string de Json
            
            while((linha = leitura.readLine()) != null){
                //Compara somente os nomes dos tecnicos
                if(linha.substring(0, linha.indexOf(","))
                   .equalsIgnoreCase(jsonStr.substring(0, jsonStr.indexOf(",")))){ //Compara somente os nomes do Tecnicos
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
    
    //Append tecnico no arquivo tecnicos.jsonl
    public static void appendTecnico(Tecnico tecnico) throws ElementoDuplicado{
        if (containsTecnico(tecnico) != -1){
            throw new ElementoDuplicado("Tecnico já está registrado no sistema.");
        }
        
        ObjectMapper mapper = new ObjectMapper();          // Instancia Mapeamento padrao da biblioteca Jackson
        mapper.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
        
        try (FileOutputStream os = new FileOutputStream("src/main/resources/tecnicos.jsonl", true)) {
            mapper.writeValue(os, tecnico);
            os.write("\n".getBytes());
        } catch (IOException e) {
            System.err.println("Nao foi possivel adicionar o tecnico ao arquivo de persistencia.");
        }
    }
    
    //Apaga tecnico do arquivo de persistencia dado o nome do Tecnico
    public static void deleteTecnico(String nome) throws IOException{
        File arquivoOriginal = new File("src/main/resources/tecnicos.jsonl");
        File arquivoTemp = new File("src/main/resources/temp.jsonl");

        String linha; //String auxiliar para leitura    
        
        try (BufferedReader leitura = new BufferedReader(new FileReader(arquivoOriginal));
             BufferedWriter escrita = new BufferedWriter(new FileWriter(arquivoTemp))){

            String comparador = "{\"nome\":\"" + nome + "\"";
                    
            //Atualiza arquivo temporário com todos os dados, exceto o deletado
            while((linha = leitura.readLine()) != null){
                if(linha.substring(0, linha.indexOf(",")).equals(comparador)){ //Compara somente os nomes do Tecnicos
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
    
    //Retorna uma HashSet de Tecnico da Memória, com a caractéristica fornecida
    //Se não houver tecnicos com a característica escolhida, retorna nulo.
    //Caso contrário, retorna uma List dos Tecnicos com a caractéristica escolhida
    //No vetor indice
    public static List<Tecnico> getMemTecnicos(Predicate<Tecnico> criterio, List<Integer> indices){
        
        List<Tecnico> listaFiltrada = new ArrayList<>();
        File save = new File("src/main/resources/tecnicos.jsonl");
        ObjectMapper mapper = new ObjectMapper();// Instancia Mapeamento padrao da biblioteca Jackson
        String linha; //String auxiliar para leitura
        int cont = 0;
        Tecnico aux;
        
        try(BufferedReader leitura = new BufferedReader(new FileReader(save))){            
            while((linha = leitura.readLine()) != null){
                aux = mapper.readValue(linha, Tecnico.class);
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
    
    //Edita um objeto Tecnico da Memória, se ele estiver na memória
    //Caso não exista, não faz nada.
    public static void setMemTecnico(Tecnico tecnico){
        int indice;
        if((indice = containsTecnico(tecnico)) == -1){return;}
        
        File save = new File("src/main/resources/tecnicos.jsonl");
        File temp = new File("src/main/resources/temp.jsonl");
        ObjectMapper mapper = new ObjectMapper();// Instancia Mapeamento padrao da biblioteca Jackson
        String linha; //String auxiliar para leitura
        int cont = 0;
        
        try(BufferedReader leitura = new BufferedReader(new FileReader(save));
            BufferedWriter escrita = new BufferedWriter(new FileWriter(temp))){
            String jsonStr = mapper.writeValueAsString(tecnico);
            
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
    
    public static void insert(Tecnico tecnico, int index) throws IOException{
        File arquivoOriginal = new File("src/main/resources/tecnicos.jsonl");
        File arquivoTemp = new File("src/main/resources/temp.jsonl");

        String linha; //String auxiliar para leitura
        ObjectMapper mapper = new ObjectMapper();          // Instancia Mapeamento padrao da biblioteca Jackson
        int cont = 0;
        try (BufferedReader leitura = new BufferedReader(new FileReader(arquivoOriginal));
             BufferedWriter escrita = new BufferedWriter(new FileWriter(arquivoTemp))){
             
            String jsonStr = mapper.writeValueAsString(tecnico);

            //Atualiza arquivo temporário com todos os dados, exceto o deletado
            while((linha = leitura.readLine()) != null){
                if(cont == index){ //Compara somente os nomes do Tecnicos
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

    public static void insertMult(List<Tecnico> tecnicos, List<Integer> indices) throws IOException{
        if(tecnicos.size() != indices.size()){
            throw new IOException("Tamanhos das listas não coincidem.");
        }
        
        File arquivoOriginal = new File("src/main/resources/tecnicos.jsonl");
        File arquivoTemp = new File("src/main/resources/temp.jsonl");

        String linha; //String auxiliar para leitura
        ObjectMapper mapper = new ObjectMapper();          // Instancia Mapeamento padrao da biblioteca Jackson
        int cont = 0;
        try (BufferedReader leitura = new BufferedReader(new FileReader(arquivoOriginal));
             BufferedWriter escrita = new BufferedWriter(new FileWriter(arquivoTemp))){
             
            String jsonStr;

            //Atualiza arquivo temporário com todos os dados, exceto o deletado
            while((linha = leitura.readLine()) != null){
                if(!indices.isEmpty() && cont == indices.get(0)){ //Compara somente os nomes do Tecnicos
                    indices.remove(0);
                    jsonStr = mapper.writeValueAsString(tecnicos.get(0));
                    tecnicos.remove(0);
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

    
    public static Tecnico get(int index) throws IOException{
        if(index == -1){return null;}
        
        File arquivo = new File("src/main/resources/tecnicos.jsonl");
        ObjectMapper mapper = new ObjectMapper();// Instancia Mapeamento padrao da biblioteca Jackson
        String linha;
        Tecnico tecnico = null;
        int cont = 0;
        
        try(BufferedReader leitura = new BufferedReader(new FileReader(arquivo))){            
            while((linha = leitura.readLine()) != null){
                if(cont == index){
                    tecnico = mapper.readValue(linha, Tecnico.class);
                }
                cont++;
            }          
        }
        catch(IOException e){
            throw new IOException("Ocorreu um erro inesperado na leitura do arquivo.");
        } 
        
        if(tecnico == null){
            throw new IOException("Técnico não encontrado. Get não foi executado corretamente.");
        }
        
        return tecnico;
    }
    
    public static List<Tecnico> getMult(List<Integer> indices) throws IOException{
        if(indices.isEmpty()){return null;}
        
        File arquivo = new File("src/main/resources/tecnicos.jsonl");
        ObjectMapper mapper = new ObjectMapper();// Instancia Mapeamento padrao da biblioteca Jackson
        String linha;
        List<Tecnico> tecnicos = null;
        int iterador = 0;
        int cont = 0;
        
        try(BufferedReader leitura = new BufferedReader(new FileReader(arquivo))){            
            while((linha = leitura.readLine()) != null){
                if(cont == indices.get(iterador)){
                    tecnicos.add(mapper.readValue(linha, Tecnico.class));
                    iterador++;
                }
                cont++;
            }          
        }
        catch(IOException e){
            throw new IOException("Ocorreu um erro inesperado na leitura do arquivo.");
        } 
        
        if(iterador < indices.size()){
            throw new IOException("Técnico não encontrado. GetMul não foi executado corretamente.");
        }
        
        return tecnicos;        
    }
    
}
