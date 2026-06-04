package org.example.partidas;

import org.example.jogadorselecao.Selecao;

public class PartidaCopa {

    private int numero;

    private String fase;

    private String data;

    private String horario;

    private String estadio;

    private String arbitro;

    private Selecao selecao1;

    private Selecao selecao2;

    private Integer golsSelecao1;

    private Integer golsSelecao2;

    private String vencedor;

    public PartidaCopa() {
    }

    public PartidaCopa(
            int numero,
            String fase,
            String data,
            String horario,
            String estadio,
            String arbitro,
            Selecao selecao1,
            Selecao selecao2) {

        this.numero = numero;
        this.fase = fase;
        this.data = data;
        this.horario = horario;
        this.estadio = estadio;
        this.arbitro = arbitro;
        this.selecao1 = selecao1;
        this.selecao2 = selecao2;
    }

     public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public String getArbitro() {
        return arbitro;
    }

    public void setArbitro(String arbitro) {
        this.arbitro = arbitro;
    }

    public Selecao getSelecao1() {
        return selecao1;
    }

    public void setSelecao1(Selecao selecao1) {
        this.selecao1 = selecao1;
    }

    public Selecao getSelecao2() {
        return selecao2;
    }

    public void setSelecao2(Selecao selecao2) {
        this.selecao2 = selecao2;
    }

    public Integer getGolsSelecao1() {
        return golsSelecao1;
    }

    public void setGolsSelecao1(Integer golsSelecao1) {
        this.golsSelecao1 = golsSelecao1;
    }

    public Integer getGolsSelecao2() {
        return golsSelecao2;
    }

    public void setGolsSelecao2(Integer golsSelecao2) {
        this.golsSelecao2 = golsSelecao2;
    }

    public String getVencedor() {
        return vencedor;
    }

    public void setVencedor(String vencedor) {
        this.vencedor = vencedor;
    }
}