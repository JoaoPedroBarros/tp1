package org.example.partidas;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.example.administracao.Permissao;
import org.example.jogadorselecao.Selecao;

public class PartidaCopa extends Permissao {

    private int numero;

    private String fase;

    private String data;

    private String horario;

    private String estadio;

    private String arbitro;

    private String selecao1;

    private String selecao2;

    private Integer golsSelecao1;

    private Integer golsSelecao2;

    private String vencedor;

    public PartidaCopa() {
    }

    public PartidaCopa(
            int numero,
            String fase,
            String data,
            String horario,
            String estadio,
            String arbitro,
            String selecao1,
            String selecao2) {

        this.numero = numero;
        this.fase = fase;
        this.data = data;
        this.horario = horario;
        this.estadio = estadio;
        this.arbitro = arbitro;
        this.selecao1 = selecao1;
        this.selecao2 = selecao2;
    }

     public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public String getArbitro() {
        return arbitro;
    }

    public void setArbitro(String arbitro) {
        this.arbitro = arbitro;
    }
    
    public String getSelecao1() {
    return selecao1;
    }

public void setSelecao1(String selecao1) {
    this.selecao1 = selecao1;
    }

public String getSelecao2() {
    return selecao2;
    }

public void setSelecao2(String selecao2) {
    this.selecao2 = selecao2;
    }

    public Integer getGolsSelecao1() {
        return golsSelecao1;
    }

    public void setGolsSelecao1(Integer golsSelecao1) {
        this.golsSelecao1 = golsSelecao1;
    }

    public Integer getGolsSelecao2() {
        return golsSelecao2;
    }

    public void setGolsSelecao2(Integer golsSelecao2) {
        this.golsSelecao2 = golsSelecao2;
    }

    public String getVencedor() {
        return vencedor;
    }

    public void setVencedor(String vencedor) {
        this.vencedor = vencedor;
    }

    @Override
    @JsonIgnore
    public String getNome() {
        return "PARTIDA_COPA";
    }
}