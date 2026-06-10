package org.example.partidas;

import java.util.List;
import org.example.administracao.Permissao;

public abstract class Fase extends Permissao {

    protected String arquivoPartidas;

    protected String arquivoClassificados;
    
    public Fase() {
    }

    public String getArquivoPartidas() {
        return arquivoPartidas;
    }

    public void setArquivoPartidas(
            String arquivoPartidas) {

        this.arquivoPartidas =
                arquivoPartidas;
    }

    public String getArquivoClassificados() {
        return arquivoClassificados;
    }

    public void setArquivoClassificados(
            String arquivoClassificados) {

        this.arquivoClassificados =
                arquivoClassificados;
    }

    public abstract void criarPartida(
            PartidaCopa partida)
            throws Exception;

    public abstract void registrarResultado(
            int numeroPartida,
            int gols1,
            int gols2,
            String vencedorPenaltis)
            throws Exception;

    public abstract void gerarClassificados()
            throws Exception;

    public abstract List<PartidaCopa> listarPartidas()
            throws Exception;
    
    public String mostrarPartidas() throws Exception {

        StringBuilder sb = new StringBuilder();

        for (PartidaCopa p : listarPartidas()) {

            sb.append("Partida ")
              .append(p.getNumero())
              .append("\n");

            sb.append(p.getSelecao1().getPais())
              .append(" ");

            sb.append(
                    p.getGolsSelecao1() == null
                    ? "-"
                    : p.getGolsSelecao1());

            sb.append(" x ");

            sb.append(
                    p.getGolsSelecao2() == null
                    ? "-"
                    : p.getGolsSelecao2());

            sb.append(" ");

            sb.append(
                    p.getSelecao2().getPais());

            sb.append("\n");

            if (p.getVencedor() != null) {

                sb.append("Vencedor: ")
                  .append(p.getVencedor())
                  .append("\n");
            }

            sb.append("\n");
        }

        return sb.toString();
    }
    
    public PartidaCopa buscarPartida(int numero) throws Exception {

        for (PartidaCopa p :
                listarPartidas()) {

            if (p.getNumero() == numero) {

                return p;
            }
        }

        return null;
    }
    
}