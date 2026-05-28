package org.example;

import javax.swing.SwingUtilities;
import org.example.administracao.telas.Login;
import com.fasterxml.jackson.databind.*;
import org.example.administracao.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;


public class Main {
    public static void main(String[] args) {
        System.out.printf("Hello and welcome!");
        
    
        SwingUtilities.invokeLater(() -> {
            Login janelaLogin = new Login();
            janelaLogin.setVisible(true);
        });
        
        Usuario usuario = null;
        ObjectMapper mapper = new ObjectMapper();
        
        try (InputStream stream = Main.class.getResourceAsStream("/usuarios.json")) {
          
            if (stream == null) {
                System.err.println("Arquivo nao encontrado");
            }
            
            usuario = mapper.readValue(stream, Usuario.class);
            
            System.out.println("Nome: " + usuario.getNome());
        }
        
        catch (JsonMappingException e) {
            System.err.println("Erro de mapeamento");
        }
        
        catch (IOException e) {
            System.err.println("Erro ao fechar ou lear o fluxo do arquivo");
        }
        
        
        
        
        
    }
}
  
