package org.example.ingressos;

public class Ingresso {
    private String jogo;
    private double preco;
    private int quantidade;

    public Ingresso(String jogo, double preco, int quantidade) {
        this.jogo = jogo;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public String getJogo() {
        return jogo;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void comprar(int qtd) {
        if (qtd <= quantidade) {
            quantidade -= qtd;
            System.out.println("Compra realizada!");
        } else {
            System.out.println("Ingressos insuficientes");
        }
    }
}