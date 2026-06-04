package org.example.partidas;
import org.example.jogadorselecao.Selecao;

public class Partida {

    private Selecao time1;
    private Selecao time2;

    private Integer golsTime1;
    private Integer golsTime2;

    private Selecao vencedor;

    public Partida() {
    }

    public Partida(
            Selecao time1,
            Selecao time2) {

        this.time1 = time1;
        this.time2 = time2;
    }

    public Selecao getTime1() {
        return time1;
    }

    public void setTime1(Selecao time1) {
        this.time1 = time1;
    }

    public Selecao getTime2() {
        return time2;
    }

    public void setTime2(Selecao time2) {
        this.time2 = time2;
    }

    public Integer getGolsTime1() {
        return golsTime1;
    }

    public void setGolsTime1(Integer golsTime1) {
        this.golsTime1 = golsTime1;
    }

    public Integer getGolsTime2() {
        return golsTime2;
    }

    public void setGolsTime2(Integer golsTime2) {
        this.golsTime2 = golsTime2;
    }

    public Selecao getVencedor() {
        return vencedor;
    }

    public void setVencedor(Selecao vencedor) {
        this.vencedor = vencedor;
    }
}