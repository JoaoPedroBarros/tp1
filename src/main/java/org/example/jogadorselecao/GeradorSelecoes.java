package org.example.jogadorselecao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.example.jogadorselecao.Jogador;
import org.example.jogadorselecao.Posicao;
import org.example.jogadorselecao.Selecao;
import org.example.jogadorselecao.StatusJogador;
import org.example.jogadorselecao.Tecnico;

public class GeradorSelecoes {

    public static void main(String[] args) {

        try {

                String[][] selecoesInfo = {

                        {"A","Brasil"},
                        {"A","Canada"},
                        {"A","Japao"},
                        {"A","Nigeria"},

                        {"B","Argentina"},
                        {"B","Mexico"},
                        {"B","Croacia"},
                        {"B","Australia"},

                        {"C","Franca"},
                        {"C","Dinamarca"},
                        {"C","Egito"},
                        {"C","Coreia do Sul"},

                        {"D","Alemanha"},
                        {"D","Suica"},
                        {"D","Marrocos"},
                        {"D","Estados Unidos"},

                        {"E","Espanha"},
                        {"E","Uruguai"},
                        {"E","Camaroes"},
                        {"E","Servia"},

                        {"F","Portugal"},
                        {"F","Colombia"},
                        {"F","Gana"},
                        {"F","Polonia"},

                        {"G","Inglaterra"},
                        {"G","Belgica"},
                        {"G","Tunisia"},
                        {"G","Equador"},

                        {"H","Italia"},
                        {"H","Paises Baixos"},
                        {"H","Senegal"},
                        {"H","Chile"}
                };

                String[] nomesBase = {

                        "Andre",
                        "Bruno",
                        "Carlos",
                        "Daniel",
                        "Eduardo",
                        "Felipe",
                        "Gabriel",
                        "Henrique",
                        "Igor",
                        "Joao",
                        "Kaique",
                        "Lucas",
                        "Marcos",
                        "Nathan",
                        "Otavio",
                        "Paulo",
                        "Rafael",
                        "Tiago"
                };

                List<Selecao> selecoes =
                        new ArrayList<>();

                for (String[] dados : selecoesInfo) {

                    String grupo =
                            dados[0];

                    String pais =
                            dados[1];

                    Tecnico tecnico =
                            new Tecnico();

                    tecnico.setNome(
                            "Tecnico " +
                            pais.replace(" ", ""));

                    List<Jogador> jogadores =
                            new ArrayList<>();

                    for (int i = 0;
                         i < 18;
                         i++) {

                        Jogador jogador =
                                new Jogador();

                        jogador.setNome(
                                nomesBase[i]
                                + " "
                                + pais.replace(" ", ""));

                        jogador.setNumero(
                                i + 1);

                        jogador.setStatus(
                                StatusJogador.ATIVO);

                        jogador.setDataNascimento(
                                "01/01/2000");

                        if (i < 2) {

                            jogador.setPosicao(
                                    Posicao.GOLEIRO);

                        }

                        else if (i < 8) {

                            jogador.setPosicao(
                                    Posicao.DEFENSOR);

                        }

                        else if (i < 13) {

                            jogador.setPosicao(
                                    Posicao.MEIO_CAMPISTA);

                        }

                        else {

                            jogador.setPosicao(
                                    Posicao.ATACANTE);
                        }

                        jogadores.add(
                                jogador);
                    }

                    Selecao selecao =
                            new Selecao(
                                    pais,
                                    grupo,
                                    tecnico,
                                    jogadores);

                    selecao.setVitorias(0);
                    selecao.setEmpates(0);
                    selecao.setDerrotas(0);

                    selecoes.add(
                            selecao);
                }

                ObjectMapper mapper =
            new ObjectMapper();

    File arquivo =
            new File(
                    "src/main/resources/selecoes2.jsonl");

    try (BufferedWriter writer =
            new BufferedWriter(
                    new FileWriter(arquivo))) {

        for (Selecao selecao : selecoes) {

            writer.write(
                    mapper.writeValueAsString(
                            selecao));

            writer.newLine();
        }
    }

            }

            catch (Exception e) {

                e.printStackTrace();
            }
        }
    }