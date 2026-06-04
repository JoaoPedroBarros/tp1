package org.example.partidas;

import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FaseGrupos extends Fase {

    public FaseGrupos() {

        arquivoPartidas =
                "partidas_grupos.json";

        arquivoClassificados =
                "classificados_oitavas.json";
    }

    @Override
    public void criarPartida(
            PartidaCopa partida)
            throws Exception {

        List<PartidaCopa> partidas =
                listarPartidas();

        partidas.add(partida);

        JsonUtil.salvar(
                arquivoPartidas,
                partidas);
    }

    @Override
    public List<PartidaCopa> listarPartidas()
            throws Exception {

        File arquivo =
                new File(arquivoPartidas);

        if (!arquivo.exists()) {

            return new ArrayList<>();
        }

        return JsonUtil
                .getMapper()
                .readValue(
                        arquivo,
                        new TypeReference<
                                List<PartidaCopa>>() {
                        });
    }

    @Override
    public void registrarResultado(
            int numeroPartida,
            int gols1,
            int gols2,
            String vencedorPenaltis)
            throws Exception {

        List<PartidaCopa> partidas =
                listarPartidas();

        for (PartidaCopa partida :
                partidas) {

            if (partida.getNumero()
                    == numeroPartida) {

                partida.setGolsSelecao1(
                        gols1);

                partida.setGolsSelecao2(
                        gols2);

                if (gols1 > gols2) {

                    partida.setVencedor(
                            partida
                                    .getSelecao1()
                                    .getPais());
                }

                else if (gols2 > gols1) {

                    partida.setVencedor(
                            partida
                                    .getSelecao2()
                                    .getPais());
                }

                else {

                    partida.setVencedor(
                            "EMPATE");
                }
            }
        }

        JsonUtil.salvar(
                arquivoPartidas,
                partidas);
    }

    @Override
    public void gerarClassificados()
            throws Exception {

        // implementação da tabela
        // de grupos
    }
}