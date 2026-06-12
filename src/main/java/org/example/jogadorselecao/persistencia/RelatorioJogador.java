package org.example.jogadorselecao.persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import org.example.administracao.Permissao;
import org.example.jogadorselecao.Jogador;
import org.example.jogadorselecao.StatusJogador;


/* PASSO A PASSO PARA GERAR RELATORIOS USANDO AS CLASSES ESPECIALIZADAS PARA RELATORIO

RelatorioSelecao relatorioSelecao = new RelatorioSelecao();
RelatorioJogador relatorioJogador = new RelatorioJogador();
try{
    //relatorioSelecao.createRelatorio();
    relatorioJogador.createRelatorio();
    JOptionPane.showMessageDialog(null, "Relatorio gerado com sucesso!");
}
catch(JRException e){
    e.printStackTrace();
    //JOptionPane.showMessageDialog(null, "Não foi possível gerar o relatório.", "Erro!", JOptionPane.ERROR_MESSAGE);
}*/

public class RelatorioJogador extends Permissao{
    
    @Override
    public String getNome() {
        return "GERA_RELATORIOS_JOGADOR";
    }
    
    private List<Jogador> artilheiros = new ArrayList<>();
    private File registro = new File("src/main/resources/jogadores.jsonl");
    private String caminhoJrxml = "src/main/resources/relatorio_jogadores.jrxml";
    private String caminhoFinal = "relatorio_jogadores.pdf";
    private String caminhoLogo = "src/main/resources/images.jpeg";
    private ObjectMapper mapper = new ObjectMapper();
    private int totalJogadores;
    private int totalAnos;
    private int totAmarelos;
    private int totVermelhos;
    Jogador jogMaisPenalizado = new Jogador();
    private int totjogadoresLesionados;
    private int totjogadoresSuspensos;
    
    public RelatorioJogador(){
        
    }
    
    public int calculaIdade(String dataNascimento){
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data = LocalDate.parse(dataNascimento, formatoData);
        LocalDate dataAtual = LocalDate.now();
        return Period.between(data, dataAtual).getYears();
    }
    
    public void getJogadoresDestacados(){
        
        //Inicializa Lista de Artilheiros
        for(int i = 0; i < 3; i++){
            artilheiros.add(new Jogador());
        }
        
        try(BufferedReader leitura = new BufferedReader(new FileReader(registro))){
            Jogador aux;
            String linha;
            while((linha = leitura.readLine()) != null){
                   
                aux = mapper.readValue(linha, Jogador.class); //Pega Jogador da memória
                totalJogadores++; 
                totalAnos += calculaIdade(aux.getDataNascimento());
                totAmarelos += aux.getAmarelos();
                totVermelhos += aux.getVermelhos();
                
                if(aux.getVermelhos() > jogMaisPenalizado.getVermelhos()){
                    jogMaisPenalizado = aux;
                }
                
                if(aux.getStatus() == StatusJogador.LESIONADO){
                    totjogadoresLesionados++;
                }
                else if(aux.getStatus() == StatusJogador.SUSPENSO){
                    totjogadoresSuspensos++;
                }
                
                for(int i = 0; i < 3; i++){
                    if(aux.getGols() > artilheiros.get(i).getGols()){
                        artilheiros.remove(2);
                        artilheiros.add(i, aux);
                        break;
                    }
                }
                
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
     
    public void createRelatorio() throws JRException{    
        getJogadoresDestacados();

        Map<String, Object> parametros = new HashMap<>();

        //Configura os parametros preenchidos no PDF
        parametros.put("TOTAL_ATLETAS", totalJogadores);
        parametros.put("MEDIA_IDADE", (float) totalAnos/totalJogadores);
        parametros.put("TOTAL_CARTOES_AMARELOS", totAmarelos);

        parametros.put("LOGO_CAMPEONATO", caminhoLogo);
        parametros.put("JOGADOR_MAIS_PENALIZADO", jogMaisPenalizado.getNome());

        parametros.put("ARTILHEIRO1_NOME", artilheiros.get(0).getNome());
        parametros.put("ARTILHEIRO1_SELECAO", artilheiros.get(0).getNomeSelecao());
        parametros.put("ARTILHEIRO1_GOLS", artilheiros.get(0).getGols());

        parametros.put("ARTILHEIRO2_NOME", artilheiros.get(1).getNome());
        parametros.put("ARTILHEIRO2_SELECAO", artilheiros.get(1).getNomeSelecao());
        parametros.put("ARTILHEIRO2_GOLS", artilheiros.get(1).getGols());

        parametros.put("ARTILHEIRO3_NOME", artilheiros.get(2).getNome());
        parametros.put("ARTILHEIRO3_SELECAO", artilheiros.get(2).getNomeSelecao());
        parametros.put("ARTILHEIRO3_GOLS", artilheiros.get(2).getGols());

        parametros.put("JOGADOR_MAIS_PENALIZADO_SEL", jogMaisPenalizado.getNomeSelecao());
        parametros.put("JOGADOR_MAIS_PENALIZADO_CARTOES", jogMaisPenalizado.getVermelhos());
        parametros.put("TOTAL_CARTOES_VERMELHOS", totVermelhos);            
        
        parametros.put("TOTAL_ATLETAS_ATIVOS", totalJogadores - totjogadoresLesionados - totjogadoresSuspensos);
        parametros.put("TOTAL_ATLETAS_LESIONADOS", totjogadoresLesionados);
        parametros.put("TOTAL_ATLETAS_SUSPENSOS", totjogadoresSuspensos);

        JasperReport jasperReport = JasperCompileManager.compileReport(caminhoJrxml);
        JRDataSource dataSource = new JREmptyDataSource(); //O dataSource vazio permite que todos os dados fiquem no summary
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, caminhoFinal);
    }
    
}
