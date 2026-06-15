package org.example.partidas;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.util.List;

public class Final
        extends FaseEliminatoria {

    public Final() {

        arquivoPartidas =
                "src/main/resources/partidas_final.json";

        arquivoClassificados =
                "src/main/resources/campeao.json";
    }
    
    public String obterCampeao()
        throws Exception {

    List<String> campeao =
            JsonUtil.getMapper()
                    .readValue(

                    new File(
                    arquivoClassificados),

                    new TypeReference<
                            List<String>>() {}
            );

    return campeao.get(0);
    }

        @Override
        @JsonIgnore
    public String getNome() {
        return "FINAL";
    }
}