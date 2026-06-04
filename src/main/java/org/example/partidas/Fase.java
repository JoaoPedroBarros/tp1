package org.example.partidas;

import java.util.List;

public abstract class Fase {

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
    
    
}