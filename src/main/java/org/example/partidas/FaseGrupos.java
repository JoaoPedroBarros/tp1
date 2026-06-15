package org.example.partidas;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FaseGrupos extends Fase {

    public FaseGrupos() {

        arquivoPartidas =
                "src/main/resources/partidas_grupos.json";

        arquivoClassificados =
                "src/main/resources/classificados_oitavas.json";
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
            int numeroPartida, int gols1, int gols2, String vencedorPenaltis)
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
                            partida.getSelecao1());
                }

                else if (gols2 > gols1) {

                    partida.setVencedor(
                            partida.getSelecao2());
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
    
       public boolean faseFinalizada()
            throws Exception {

        List<PartidaCopa> partidas =
                listarPartidas();

        if (partidas.size() < 48) {
            return false;
        }

        for (PartidaCopa p : partidas) {

            if (p.getGolsSelecao1() == null
                    || p.getGolsSelecao2() == null) {

                return false;
            }
        }

        return true;
    }

    @Override
    public void gerarClassificados()
            throws Exception {
        
        if (!faseFinalizada()) {

        throw new Exception(
                "A fase de grupos ainda não foi concluída.");
        }

        List<PartidaCopa> partidas =
        listarPartidas();
        
        Map<String,
    ClassificacaoGrupo>
        tabela =
        new HashMap<>();
        
        for (PartidaCopa p : partidas) {

    String pais1 =
            p.getSelecao1();

    String pais2 =
            p.getSelecao2();

    tabela.putIfAbsent(
            pais1,
            new ClassificacaoGrupo(
                    pais1));

    tabela.putIfAbsent(
            pais2,
            new ClassificacaoGrupo(
                    pais2));

    ClassificacaoGrupo c1 =
            tabela.get(pais1);

    ClassificacaoGrupo c2 =
            tabela.get(pais2);

    int g1 =
            p.getGolsSelecao1();

    int g2 =
            p.getGolsSelecao2();

    c1.setGolsPro(
            c1.getGolsPro() + g1);

    c1.setGolsContra(
            c1.getGolsContra() + g2);

    c2.setGolsPro(
            c2.getGolsPro() + g2);

    c2.setGolsContra(
            c2.getGolsContra() + g1);

    if (g1 > g2) {

        c1.setPontos(
                c1.getPontos() + 3);

    }

    else if (g2 > g1) {

        c2.setPontos(
                c2.getPontos() + 3);

    }

    else {

        c1.setPontos(
                c1.getPontos() + 1);

        c2.setPontos(
                c2.getPontos() + 1);
    }
    }
        
    for (ClassificacaoGrupo c :
        tabela.values()) {

    c.setSaldoGols(
            c.getGolsPro()
            - c.getGolsContra());
    }
    
    List<ClassificacaoGrupo>
        classificacao =
        new ArrayList<>(
                tabela.values());

    classificacao.sort(Comparator.comparingInt(ClassificacaoGrupo::getPontos)

            .thenComparingInt(
                    ClassificacaoGrupo
                            ::getSaldoGols)

            .thenComparingInt(
                    ClassificacaoGrupo
                            ::getGolsPro)

            .reversed()
    );
    
    List<String> classificados =
        new ArrayList<>();

    for (int i = 0;
         i < 16;
         i++) {

        classificados.add(

                classificacao
                        .get(i)
                        .getPais()
        );
    }
    
    JsonUtil.salvar(
        arquivoClassificados,
        classificados);
    
    }

        @Override
        @JsonIgnore
    public String getNome() {
        return "FASE_GRUPOS";
    }
}