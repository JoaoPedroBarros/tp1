package org.example.partidas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeradorPartidasGrupos {

    public static void main(String[] args) {

        try {

            String[][] grupos = {

                    {"Brasil", "Canada", "Japao", "Nigeria"},
                    {"Argentina", "Mexico", "Croacia", "Australia"},
                    {"Franca", "Dinamarca", "Egito", "Coreia do Sul"},
                    {"Alemanha", "Suica", "Marrocos", "Estados Unidos"},
                    {"Espanha", "Uruguai", "Camaroes", "Servia"},
                    {"Portugal", "Colombia", "Gana", "Polonia"},
                    {"Inglaterra", "Belgica", "Tunisia", "Equador"},
                    {"Italia", "Paises Baixos", "Senegal", "Chile"}
            };

            String[] estadios = {
                    "Castelao",
                    "MorumBIS",
                    "Maracana"
            };

            String[] arbitros = {
                    "Joao Pedro Barros",
                    "Carlos Alberto",
                    "Ricardo Lima",
                    "Fernando Costa",
                    "Marcos Oliveira",
                    "Pedro Henrique"
            };

            Random random = new Random();

            List<PartidaCopa> partidas =
                    new ArrayList<>();

            int dia = 1;

            for (String[] grupo : grupos) {

                adicionarPartida(
                        partidas,
                        grupo[0],
                        grupo[1],
                        dia++,
                        estadios,
                        arbitros,
                        random);

                adicionarPartida(
                        partidas,
                        grupo[0],
                        grupo[2],
                        dia++,
                        estadios,
                        arbitros,
                        random);

                adicionarPartida(
                        partidas,
                        grupo[0],
                        grupo[3],
                        dia++,
                        estadios,
                        arbitros,
                        random);

                adicionarPartida(
                        partidas,
                        grupo[1],
                        grupo[2],
                        dia++,
                        estadios,
                        arbitros,
                        random);

                adicionarPartida(
                        partidas,
                        grupo[1],
                        grupo[3],
                        dia++,
                        estadios,
                        arbitros,
                        random);

                adicionarPartida(
                        partidas,
                        grupo[2],
                        grupo[3],
                        dia++,
                        estadios,
                        arbitros,
                        random);
            }

            JsonUtil.salvar(
                    "src/main/resources/partidas_grupos2.json",
                    partidas);

            System.out.println(
                    "48 partidas geradas com sucesso.");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private static void adicionarPartida(
            List<PartidaCopa> partidas,
            String selecao1,
            String selecao2,
            int dia,
            String[] estadios,
            String[] arbitros,
            Random random) {

        PartidaCopa partida =
                new PartidaCopa();

        int gols1 =
                random.nextInt(6);

        int gols2 =
                random.nextInt(6);

        partida.setNumero(
                100000 + partidas.size());

        partida.setFase(
                "GRUPOS");

        partida.setData(
                String.format(
                        "%02d/06/2026",
                        dia));

        partida.setHorario(
                String.format(
                        "%02d:00",
                        12 + random.nextInt(10)));

        partida.setEstadio(
                estadios[
                        random.nextInt(
                                estadios.length)]);

        partida.setArbitro(
                arbitros[
                        random.nextInt(
                                arbitros.length)]);

        partida.setSelecao1(
                selecao1);

        partida.setSelecao2(
                selecao2);

        partida.setGolsSelecao1(
                gols1);

        partida.setGolsSelecao2(
                gols2);

        if (gols1 > gols2) {

            partida.setVencedor(
                    selecao1);

        } else if (gols2 > gols1) {

            partida.setVencedor(
                    selecao2);

        } else {

            partida.setVencedor(
                    "EMPATE");
        }

        partidas.add(
                partida);
    }
}