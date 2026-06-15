package org.example.partidas;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class QuartasFinal extends FaseEliminatoria {

    public QuartasFinal() {

        arquivoPartidas =
                "src/main/resources/partidas_quartas.json";

        arquivoClassificados =
                "src/main/resources/classificados_semifinal.json";
    }

        @Override
        @JsonIgnore
    public String getNome() {
        return "QUARTAS_FINAL";
    }
}