package org.example.partidas;
public class Resultado {

    private Partida partida;

    public Resultado(Partida partida) {
        this.partida = partida;
    }

    public void registrarResultado(
            int gols1,
            int gols2) {

        partida.setGolsTime1(gols1);
        partida.setGolsTime2(gols2);

        definirVencedor();
    }

    private void definirVencedor() {

        if (partida.getGolsTime1()
                > partida.getGolsTime2()) {

            partida.setVencedor(
                    partida.getTime1());

        } else if (partida.getGolsTime2()
                > partida.getGolsTime1()) {

            partida.setVencedor(
                    partida.getTime2());

        } else {

            partida.setVencedor(null);
        }
    }
}