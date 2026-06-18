/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.administracao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.example.estadioArbitragem.Arbitro;


/**
 *
 * @author arkham
 */
public class GeraRelatorioUsuarios extends Permissao {
    
    // classe para gerar relatorios de usuarios. Mesmo que seja uma uma permissao, optei por nao adiciona-lo ao RBAC, visto que AdministraUsuario ja sugere sua presenca
    // A maior parte dos metodos serve para adicionar os dados a biblioteca JasperReports, responsavel por gerar PDFs. 
    @Override
    public String getNome() {
        return "GERA_RELATORIOS";
    }
    
    PersistenciaUsuario persistencia;
    
    public GeraRelatorioUsuarios(PersistenciaUsuario persistencia) {
        this.persistencia = persistencia;
    }
    
    private Map<String, Integer> contaUsuariosTotal(PersistenciaUsuario persistenciaUsuario) { // preenche os parametros de contagem do pdf
        Map<String, Usuario> mapUsuarios = persistenciaUsuario.getMapUsuarios();
        
        int[] atualizaVetor = {0,0,0,0,0,0,0,0}; // usei vetor para nao perder linhas com a verbosidade dos maps. Abaixo estah a legenda de cada indice
        
        // 0 - numAdministradores; 1 - numOrganizadores; 2 - numArbitros; 3 - numOperadores; 4 - numAtivos; 5 - numAfastados; 6 - numDesligados; 7 - numTotal.
        
        
        for (Map.Entry<String, Usuario> entry : mapUsuarios.entrySet()) { // iterando o map
            Papel papel = entry.getValue().getPapel();
            Usuario.StatusUsuario status = entry.getValue().getStatus();
            
            // instanceof sao mais chatos de mexer com switch case. Por isso usei condicionais msm.
            if (papel instanceof Administrador) atualizaVetor[0]++;
            
            else if (papel instanceof Organizador) atualizaVetor[1]++;
            
            else if (papel instanceof Arbitro) atualizaVetor[2]++;
            
            else if (papel instanceof Operador) atualizaVetor[3]++;
            
            if (null != status) switch (status) { // como status sao enums, switch cases ja sao mais diretos.
                case ATIVO -> atualizaVetor[4]++;
                case AFASTADO -> atualizaVetor[5]++;
                case DESLIGADO -> atualizaVetor[6]++;
            }
            
            atualizaVetor[7]++;    
        }
        
        Map<String, Integer> parametros = Map.of("numAdministradores", atualizaVetor[0], "numOrganizadores", atualizaVetor[1], "numArbitros", atualizaVetor[2], "numOperadores", atualizaVetor[3],
                                                  "numAtivos", atualizaVetor[4], "numAfastados", atualizaVetor[5], "numDesligados", atualizaVetor[6], "numTotal", atualizaVetor[7]);
        // cria o map de parametros com cada indice do JasperReport
        return parametros;
        
    }
    
    public void geraRelatorioUsuario(List<Usuario> listaUsuarios) {
        try {
            File file = new File("src/main/resources/relatorioUsuarios.jrxml"); // pega o modelo de relatorio na pasta resources
            
            try{
                InputStream layoutRelatorio = new FileInputStream(file); // instancia como inputstream
                
                JasperReport jasperReport = JasperCompileManager.compileReport(layoutRelatorio); // inicia a API do jasper

                JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listaUsuarios); // preenche as colunas com os usuarios listados

                Map<String, Object> parametros = new HashMap<>(contaUsuariosTotal(persistencia)); // pega os parametros para contagem

                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource); // printa o relatorio com todos os dados

                JasperExportManager.exportReportToPdfFile(jasperPrint, "relatorioUsuarios.pdf"); // salva o arquivo na pasta raiz do projeto
                JOptionPane.showMessageDialog(null, "Relatorio gerado com sucesso!"); // mostra uma tela de sucesso caso de tudo certo
            }
            
            catch (FileNotFoundException e) { // excecao quando o programa tem problemas ao procurar o arquivo
                System.out.println("arquivo nao encontrado");
                JOptionPane.showMessageDialog(null, "Não foi possível gerar o relatório.", "Erro!", JOptionPane.ERROR_MESSAGE);
            }
                   
            } catch (net.sf.jasperreports.engine.JRException e) { // excecao quando ha algum problema do Jasper em si
                System.out.println("Houve problemas com a geração do PDF");
                JOptionPane.showMessageDialog(null, "Não foi possível gerar o relatório.", "Erro!", JOptionPane.ERROR_MESSAGE);
                
               
            }
        } 
}
