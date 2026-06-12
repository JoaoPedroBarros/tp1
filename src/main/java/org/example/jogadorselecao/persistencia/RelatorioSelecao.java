package org.example.jogadorselecao.persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.example.administracao.Permissao;
import org.example.jogadorselecao.Jogador;
import org.example.jogadorselecao.Selecao;
import org.example.jogadorselecao.StatusJogador;
import org.example.jogadorselecao.persistencia.IOJogador;


public class RelatorioSelecao extends Permissao{
    
    @Override
    public String getNome() {
        return "GERA_RELATORIOS_SELECAO";
    }
    private File registro = new File("src/main/resources/selecoes.jsonl");
    private String caminhoJrxml = "src/main/resources/relatorio_selecoes.jrxml";
    private String caminhoFinal = "relatorio_selecoes.pdf";
    private String caminhoLogo = "src/main/resources/images.jpeg";
    private ObjectMapper mapper = new ObjectMapper();
    private int totalSelecoes;
    private HashSet<String> grupos = new HashSet<>();
    Selecao melhorAtaque = new Selecao();
    private int golsMelhorAtaque;
    Selecao piorAtaque = new Selecao();
    private int golsPiorAtaque = Integer.MAX_VALUE;
    Selecao maisFaltosa = new Selecao();
    private int vermelhosMaisFaltosa;
    Selecao maisVitoriosa = new Selecao();
    Selecao maisEmpates = new Selecao();
    
    public RelatorioSelecao(){
        
    }
    
    public void getSelecoesDestacadas(){
        
        try(BufferedReader leitura = new BufferedReader(new FileReader(registro))){
            Selecao aux;
            String linha;
            int contVermelhos;
            int contGols;
            while((linha = leitura.readLine()) != null){
                aux = mapper.readValue(linha, Selecao.class); //Pega Jogador da memória
                totalSelecoes++; 
                grupos.add(aux.getGrupo());
                contVermelhos = 0;
                contGols = 0;
                for(Jogador membro : aux.getTime()){
                    contVermelhos += membro.getVermelhos();
                    contGols += membro.getGols();
                }                
                
                if(contGols > golsMelhorAtaque){
                    melhorAtaque = aux;
                    golsMelhorAtaque = contGols;
                }
                
                if(contGols < golsPiorAtaque){
                    piorAtaque = aux;
                    golsPiorAtaque = contGols;
                }

                if(contVermelhos > vermelhosMaisFaltosa){
                    maisFaltosa = aux;
                    vermelhosMaisFaltosa = contVermelhos;
                }

                if(aux.getVitorias() > maisVitoriosa.getVitorias()){
                    maisVitoriosa = aux;
                }

                if(aux.getEmpates() > maisEmpates.getEmpates()){
                    maisEmpates = aux;
                }
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
     
    public void createRelatorio() throws JRException{    
        getSelecoesDestacadas();

        Map<String, Object> parametros = new HashMap<>();

        //Configura os parametros preenchidos no PDF
        parametros.put("LOGO_CAMPEONATO", caminhoLogo);
        
        parametros.put("TOTAL_SELECOES", totalSelecoes);
        parametros.put("TOTAL_GRUPOS", grupos.size());
        
        parametros.put("MELHOR_ATAQUE_NOME", melhorAtaque.getPais());
        parametros.put("MELHOR_ATAQUE_GOLS", golsMelhorAtaque);

        parametros.put("PIOR_ATAQUE_NOME", piorAtaque.getPais());
        parametros.put("PIOR_ATAQUE_GOLS", golsPiorAtaque);

        parametros.put("SEL_MAIS_FALTOSA_NOME", maisFaltosa.getPais());
        parametros.put("SEL_MAIS_FALTOSA_CARTOES", vermelhosMaisFaltosa);

        parametros.put("MAIS_VITORIOSA_NOME", maisVitoriosa.getPais());
        parametros.put("MAIS_VITORIOSA_VITORIAS", maisVitoriosa.getVitorias());

        parametros.put("MAIS_EMPATES_NOME", maisEmpates.getPais());
        parametros.put("MAIS_EMPATES_EMPATES", maisEmpates.getEmpates());
        
        JasperReport jasperReport = JasperCompileManager.compileReport(caminhoJrxml);
        JRDataSource dataSource = new JREmptyDataSource(); //O dataSource vazio permite que todos os dados fiquem no summary
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, caminhoFinal);
    }
  
}
