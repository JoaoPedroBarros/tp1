package org.example.ingressos;

import org.example.administracao.Permissao;
import java.util.ArrayList;
import java.util.List;
import org.example.partidas.JsonUtil;

public class GerenciaIngressos extends Permissao {

    @Override
    public String getNome() {
        return "GERENCIA_INGRESSOS";
    }

    public static void comprarIngresso(String partida, int qtd, double valor) {

        if (qtd <= 0) {
            throw new IllegalArgumentException(
                "Quantidade deve ser maior que zero."
            );
        }

        if (qtd > 20) {
            throw new IllegalArgumentException(
                "Máximo de 20 ingressos por compra."
            );
        }

        if (valor < 0) {
            throw new IllegalArgumentException(
                "Valor não pode ser negativo."
            );
        }

        DadosIngressos.partidaAtual = partida;

        int atual = DadosIngressos.ingressosPorPartida.getOrDefault(partida, 0);

        if (atual + qtd > DadosIngressos.capacidade) {
            throw new IllegalArgumentException(
                "Capacidade máxima do estádio excedida!"
            );
        }

        DadosIngressos.ingressosPorPartida.put(
            partida,
            atual + qtd
        );

        double total =
            DadosIngressos.arrecadadoPorPartida.getOrDefault(partida, 0.0);

        DadosIngressos.arrecadadoPorPartida.put(
            partida,
            total + (qtd * valor)
        );
        
        
    salvarEventosJson();
    }
    
    public static void salvarEventosJson() {

        try {
            JsonUtil.salvar(
                "src/main/resources/eventos.json",
                getEventos()
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static double getTotalGeral() {

        double soma = 0;

        for (double valor : DadosIngressos.arrecadadoPorPartida.values()) {
            soma += valor;
        }

        return soma;
    }

    public static int getTotalIngressosVendidos() {

        int total = 0;

        for (int qtd : DadosIngressos.ingressosPorPartida.values()) {
            total += qtd;
        }

        return total;
    }

    public static double getMediaPublico() {

        if (DadosIngressos.partidas.isEmpty()) {
            return 0;
        }

        return (double) getTotalIngressosVendidos()
                / DadosIngressos.partidas.size();
    }

    public static String getEventoMaiorArrecadacao() {

        String evento = "";
        double maior = 0;

        for (var entry : DadosIngressos.arrecadadoPorPartida.entrySet()) {

            if (entry.getValue() > maior) {
                maior = entry.getValue();
                evento = entry.getKey();
            }
        }

        return evento;
    }
    
    public static List<Evento> getEventos() {

        List<Evento> eventos = new ArrayList<>();

        for (String partida : DadosIngressos.ingressosPorPartida.keySet()) {

            int ingressos =
                DadosIngressos.ingressosPorPartida.getOrDefault(partida, 0);

            double arrecadado =
                DadosIngressos.arrecadadoPorPartida.getOrDefault(partida, 0.0);

            double valorMedio =
                ingressos == 0 ? 0 : arrecadado / ingressos;

            String ocupacao =
                String.format("%.1f%%",
                    (100.0 * ingressos) / DadosIngressos.capacidade);

            eventos.add(
                new Evento(
                    partida,
                    "-",
                    ingressos,
                    valorMedio,
                    ocupacao
                )
            );
        }

        return eventos;
    }
    public static void zerarTemporada() {

        DadosIngressos.ingressosPorPartida.clear();
        DadosIngressos.arrecadadoPorPartida.clear();

        DadosIngressos.partidaAtual =
                "Selecione uma partida";

        try {

            JsonUtil.salvar(
                    "src/main/resources/eventos.json",
                    new ArrayList<Evento>()
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}