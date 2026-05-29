package org.example.ingressos;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class Sistema {

    public static List<String> partidas = new ArrayList<>();

    public static Map<String, Integer> ingressosPorPartida = new HashMap<>();
    public static Map<String, Double> arrecadadoPorPartida = new HashMap<>();

    public static int capacidade = 50000;
    public static String partidaAtual = "Selecione uma partida";
    public static double getTotalGeral() {
        double soma = 0;

        for (double valor : arrecadadoPorPartida.values()) {
            soma += valor;
        }

        return soma;
    }

    static {
        partidas.add("Brasil x Argentina");
        partidas.add("Real Madrid x Barcelona");
    }
}