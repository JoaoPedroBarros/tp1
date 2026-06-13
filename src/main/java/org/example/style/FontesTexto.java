/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.style;

import java.awt.Font;
import javax.swing.JLabel;

/**
 *
 * @author arkham
 */
public class FontesTexto {
    
    public void fonteTitulo(JLabel label) {
        Font fonteTitulo = new Font(Font.SANS_SERIF, Font.BOLD, 18);
        label.setFont(fonteTitulo);
    }
}
