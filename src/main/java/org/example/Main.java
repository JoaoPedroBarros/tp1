package org.example;

import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.SwingUtilities;

import org.example.administracao.telas.Login;


public class Main {
    public static void main(String[] args) {
        System.out.printf("Hello and welcome!");
        try {
            FlatDarkLaf.setup();
        } 
        
        catch (Exception ex) {
            System.out.println("Erro ao iniciar o FlatLaf");
        }
        
        SwingUtilities.invokeLater(() -> {
            Login janelaLogin = new Login();
            janelaLogin.setVisible(true);
        });
    }
}
  