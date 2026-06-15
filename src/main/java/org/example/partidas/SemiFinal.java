package org.example.partidas;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SemiFinal extends FaseEliminatoria {

    public SemiFinal() {

        arquivoPartidas =
                "src/main/resources/partidas_semifinal.json";

        arquivoClassificados =
                "src/main/resources/classificados_final.json";
    }

        @Override
        @JsonIgnore
    public String getNome() {
        return "SEMI_FINAL";
    }
}