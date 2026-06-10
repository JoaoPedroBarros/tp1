package org.example.partidas;

import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.util.List;

public class Final
        extends FaseEliminatoria {

    public Final() {

        arquivoPartidas =
                "partidas_final.json";

        arquivoClassificados =
                "campeao.json";
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
    public String getNome() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}