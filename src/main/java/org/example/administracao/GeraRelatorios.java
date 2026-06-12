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
public class GeraRelatorios extends Permissao {
    
    @Override
    public String getNome() {
        return "GERA_RELATORIOS";
    }
    
    PersistenciaUsuario persistencia;
    
    public GeraRelatorios(PersistenciaUsuario persistencia) {
        this.persistencia = persistencia;
    }
    
    private Map<String, Integer> contaUsuariosTotal(PersistenciaUsuario persistenciaUsuario) {
        Map<String, Usuario> mapUsuarios = persistenciaUsuario.getMapUsuarios();
        
        int[] atualizaVetor = {0,0,0,0,0,0,0,0};
        
        
        for (Map.Entry<String, Usuario> entry : mapUsuarios.entrySet()) {
            Papel papel = entry.getValue().getPapel();
            Usuario.StatusUsuario status = entry.getValue().getStatus();
            
            
            if (papel instanceof Administrador) {
                atualizaVetor[0]++;
            }
            
            else if (papel instanceof Organizador) {
                atualizaVetor[1]++;
            }
            
            else if (papel instanceof Arbitro) {
                atualizaVetor[2]++;
            }
            
            else if (papel instanceof Operador) {
                atualizaVetor[3]++;
            }
            
            if (null != status) switch (status) {
                case ATIVO -> atualizaVetor[4]++;
                case AFASTADO -> atualizaVetor[5]++;
                case DESLIGADO -> atualizaVetor[6]++;
            }
            
            atualizaVetor[7]++;    
        }
        
        Map<String, Integer> parametros = Map.of("numAdministradores", atualizaVetor[0], "numOrganizadores", atualizaVetor[1], "numArbitros", atualizaVetor[2], "numOperadores", atualizaVetor[3],
                                                  "numAtivos", atualizaVetor[4], "numAfastados", atualizaVetor[5], "numDesligados", atualizaVetor[6], "numTotal", atualizaVetor[7]);
        
        return parametros;
        
    }
    
    public void geraRelatorioUsuario(List<Usuario> listaUsuarios) {
        try {
            System.out.println(1);
            
            File file = new File("src/main/resources/relatorioUsuarios.jrxml");
            
            try{
                InputStream layoutRelatorio = new FileInputStream(file);
                
            
                JasperReport jasperReport = JasperCompileManager.compileReport(layoutRelatorio);

                JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listaUsuarios);

                Map<String, Object> parametros = new HashMap<>(contaUsuariosTotal(persistencia));

                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource);

                JasperExportManager.exportReportToPdfFile(jasperPrint, "relatorioUsuarios.pdf");     
            }
            
            catch (FileNotFoundException e) {
                System.out.println("arquivo nao encontrado");
            }
                   
            } catch (net.sf.jasperreports.engine.JRException e) {
                System.out.println("Houve problemas com a geração do PDF");
                
               
            }
        }
    }
