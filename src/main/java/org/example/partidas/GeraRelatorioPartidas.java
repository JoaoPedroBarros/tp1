/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.partidas;

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
import org.example.partidas.PartidaCopa;

/**
 *
 * @author arkham
 */
public class GeraRelatorioPartidas {
    
    public void geraRelatorioPartidas(List<PartidaCopa> listaPartidas) {
            try {
            File file = new File("src/main/resources/relatorioPartidas.jrxml");
            
            try{
                InputStream layoutRelatorio = new FileInputStream(file);
                
            
                JasperReport jasperReport = JasperCompileManager.compileReport(layoutRelatorio);

                JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listaPartidas);

                Map<String, Object> parametros = new HashMap<>();

                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource);

                JasperExportManager.exportReportToPdfFile(jasperPrint, "relatorioPartidas.pdf");     
            }
            
            catch (FileNotFoundException e) {
                System.out.println("arquivo nao encontrado");
            }
                   
            } catch (net.sf.jasperreports.engine.JRException e) {
                System.out.println("Houve problemas com a geração do PDF");
                   
        }
    }
    
}
