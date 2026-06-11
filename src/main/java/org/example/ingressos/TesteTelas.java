package org.example.ingressos;

import javax.swing.*;

public class TesteTelas {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Teste do Sistema");
        frame.setSize(300, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        JButton btnIngresso = new JButton("Tela Ingresso");
        btnIngresso.setBounds(50, 30, 200, 30);
        frame.add(btnIngresso);

        JButton btnPublico = new JButton("Controle Público");
        btnPublico.setBounds(50, 80, 200, 30);
        frame.add(btnPublico);

        JButton btnFinanceiro = new JButton("Relatório Financeiro");
        btnFinanceiro.setBounds(50, 130, 200, 30);
        frame.add(btnFinanceiro);

        // AÇÕES DOS BOTÕES
        btnIngresso.addActionListener(e -> new TelaIngressoGUI().setVisible(true));
        btnPublico.addActionListener(e -> new TelaControlePublicoGUI().setVisible(true));
        btnFinanceiro.addActionListener(e -> new TelaRelatorioFinanceiroGUI().setVisible(true));

        frame.setVisible(true);
    }

}