package org.example.ingressos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaIngresso {

    public static void main(String[] args) {

        Ingresso ingresso = new Ingresso("Brasil x Argentina", 100.0, 50);

        JFrame frame = new JFrame("Compra de Ingresso");

        JTextField campoQtd = new JTextField(10);
        JButton botao = new JButton("Comprar");

        JLabel resultado = new JLabel(" ");

        JPanel panel = new JPanel();
        panel.add(new JLabel("Quantidade:"));
        panel.add(campoQtd);
        panel.add(botao);
        panel.add(resultado);

        botao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int qtd = Integer.parseInt(campoQtd.getText());
                    ingresso.comprar(qtd);
                    resultado.setText("Restantes: " + ingresso.getQuantidade());
                } catch (Exception ex) {
                    resultado.setText("Erro na entrada");
                }
            }
        });

        frame.add(panel);
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}