package org.example.ingressos;

import javax.swing.*;

public class MenuPrincipal extends JFrame {

    public MenuPrincipal() {

        setTitle("Sistema de Ingressos");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel titulo = new JLabel("Menu Principal");
        titulo.setBounds(90, 20, 150, 25);
        add(titulo);

        JButton btnIngressos = new JButton("Ingressos");
        btnIngressos.setBounds(70, 60, 150, 30);
        add(btnIngressos);

        JButton btnPublico = new JButton("Público");
        btnPublico.setBounds(70, 100, 150, 30);
        add(btnPublico);

        JButton btnFinanceiro = new JButton("Financeiro");
        btnFinanceiro.setBounds(70, 140, 150, 30);
        add(btnFinanceiro);

        // AÇÕES
        btnIngressos.addActionListener(e -> new TelaIngressoGUI().setVisible(true));
        btnPublico.addActionListener(e -> new TelaControlePublicoGUI().setVisible(true));
        btnFinanceiro.addActionListener(e -> new TelaRelatorioFinanceiroGUI().setVisible(true));
    }
}