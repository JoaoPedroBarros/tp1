package org.example;

import javax.swing.SwingUtilities;
import org.example.administracao.telas.Login;


public class Main {
    public static void main(String[] args) {
        System.out.printf("Hello and welcome!");
        
        
        SwingUtilities.invokeLater(() -> {
            Login janelaLogin = new Login();
            janelaLogin.setVisible(true);
        });
    }
}
  
