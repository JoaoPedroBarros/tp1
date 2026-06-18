package org.example.ingressos;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import org.example.partidas.JsonUtil;

public class DadosIngressos {

    public static List<String> partidas = new ArrayList<>();

    public static Map<String, Integer> ingressosPorPartida = new HashMap<>();
    public static Map<String, Double> arrecadadoPorPartida = new HashMap<>();

    public static int capacidade = 50000;
    public static String partidaAtual = "Selecione uma partida";

    static {
        DadosIngressos.carregarEventosJson();
    }

    public static void carregarEventosJson() {

        try {

            File arquivo =
                new File("src/main/resources/eventos.json");

            if (!arquivo.exists()) {
                return;
            }

            Evento[] eventos =
                JsonUtil.getMapper().readValue(
                    arquivo,
                    Evento[].class
                );

            for (Evento e : eventos) {
                partidas.add(
                    e.getEvento()
                );

                ingressosPorPartida.put(
                    e.getEvento(),
                    e.getIngressosVendidos()
                );

                arrecadadoPorPartida.put(
                    e.getEvento(),
                    e.getIngressosVendidos()
                    * e.getValorMedio()
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}