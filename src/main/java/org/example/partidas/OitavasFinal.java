package org.example.partidas;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

public class OitavasFinal extends FaseEliminatoria {

    public OitavasFinal() {

        arquivoPartidas =
                "src/main/resources/partidas_oitavas.json";

        arquivoClassificados =
                "src/main/resources/classificados_quartas.json";
    }

        @Override
        @JsonIgnore
    public String getNome() {
        return "OITAVAS_FINAL";
    }

}