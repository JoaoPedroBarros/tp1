package org.example.ingressos;

public class Evento {

    private String evento;
    private String data;
    private int ingressosVendidos;
    private double valorMedio;
    private String ocupacao;

    // Necessário para o Jackson
    public Evento() {
    }

    public Evento(String evento,
                  String data,
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

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getIngressosVendidos() {
        return ingressosVendidos;
    }

    public void setIngressosVendidos(int ingressosVendidos) {
        this.ingressosVendidos = ingressosVendidos;
    }

    public double getValorMedio() {
        return valorMedio;
    }

    public void setValorMedio(double valorMedio) {
        this.valorMedio = valorMedio;
    }

    public String getOcupacao() {
        return ocupacao;
    }

    public void setOcupacao(String ocupacao) {
        this.ocupacao = ocupacao;
    }
}