package org.example.ingressos;

public class Evento {

    private String evento;
    private String data;
    private int ingressosVendidos;
    private double valorMedio;
    private String ocupacao;

    public Evento(String evento, String data,
                  int ingressosVendidos,
                  double valorMedio,
                  String ocupacao) {

        this.evento = evento;
        this.data = data;
        this.ingressosVendidos = ingressosVendidos;
        this.valorMedio = valorMedio;
        this.ocupacao = ocupacao;
    }

    public String getEvento() {
        return evento;
    }

    public String getData() {
        return data;
    }

    public int getIngressosVendidos() {
        return ingressosVendidos;
    }

    public double getValorMedio() {
        return valorMedio;
    }

    public String getOcupacao() {
        return ocupacao;
    }
}