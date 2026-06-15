package org.example.ingressos;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class DadosIngressos {

    public static List<String> partidas = new ArrayList<>();

    public static Map<String, Integer> ingressosPorPartida = new HashMap<>();
    public static Map<String, Double> arrecadadoPorPartida = new HashMap<>();

    public static int capacidade = 50000;
    public static String partidaAtual = "Selecione uma partida";

    static {
        partidas.add("Brasil x Argentina");
        partidas.add("Real Madrid x Barcelona");
    }
}