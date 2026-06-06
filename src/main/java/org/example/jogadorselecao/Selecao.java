package org.example.jogadorselecao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public final class Selecao {
    private String pais;
    private int grupo;
    private Tecnico tecnico;
    private List<Jogador> time = new ArrayList<>();
    private static final int MAX_MEMBROS = 26;
    private static final int MIN_MEMBROS = 18;
    
    //Estatísticas
    private int vitorias;
    private int derrotas;
    private int empates;
    
// danilo: jackson precisa de um construtor vazio
    public Selecao() {
    }
    
    //Construtor
    public Selecao(String pais, int grupo, Tecnico tecnico, List<Jogador> time) throws IllegalArgumentException {
        setPais(pais);
        setGrupo(grupo);
        setTecnico(tecnico);
        setTime(time);
    }

    
    //Getters e Setters
    public String getPais() {
        return pais;
    }

    public final void setPais(String pais){
        if(pais.isBlank() || pais.isEmpty()){
            throw new IllegalArgumentException("O campo Nome não foi preenchido.");  
        }
        else if(pais.matches("^[\\p{L}\\s]+$")){
            this.pais = pais;
        }
        else{
           throw new IllegalArgumentException("Nome não pode conter números ou símbolos especiais.");
        }
    }

    public int getGrupo() {
        return grupo;
    }

    public final void setGrupo(int grupo) {
        if (grupo <= 0){
            throw new IllegalArgumentException("O grupo da seleção deve ser maior que 0.");
        }
        this.grupo = grupo;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public final void setTecnico(Tecnico tecnico) {
        if(tecnico.getNomeSelecao() != null){
            throw new IllegalArgumentException(tecnico.getNome() + 
                    " já está afiliado à selecao do(a) " + tecnico.getNomeSelecao() + ".");
        }
        this.tecnico = tecnico;
        tecnico.setSelecao(this.getPais());
    }

    public List<Jogador> getTime() {
        return time;
    }

    public void setTime(List<Jogador> time) {
        if(time == null){return;}
        
        //Impede set com tamanhos inadequados de time
        if(time.size() < MIN_MEMBROS || time.size() > MAX_MEMBROS){
            throw new IllegalArgumentException("Quantidade inadequada de membros no time.");
        }
   
        //Garante que um jogador não poderá estar vinculado a duas seleções
        for(Jogador jogador : time){
            if(jogador.getNomeSelecao() != null){
                throw new IllegalArgumentException("O jogador " + jogador.getNome() +
                        " já está afiliada à seleção do(a) " + jogador.getNomeSelecao() + ".");
            }
        }
        
        if(this.time != null){ //Para substituir a equipe inteira
          for(Jogador jogador : this.time){
                jogador.setNomeSelecao(null);
                this.time.remove(jogador);
            }     
        }
  
        //Vincula cada jogador da HashSet à atual instância de seleção
        for(Jogador jogador : time){
            jogador.setNomeSelecao(this.getPais());
            this.time.add(jogador);
        } 
    }    

    public int getVitorias() {
        return vitorias;
    }

    public void setVitorias(int vitorias) {
        this.vitorias = vitorias;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
    }

    public int getEmpates() {
        return empates;
    }

    public void setEmpates(int empates) {
        this.empates = empates;
    }

    
    //Metodos personalizados
    public void convocar(Jogador jogador){
        if (time.size() == MAX_MEMBROS){
            throw new IllegalStateException("Não é possível convocar " + jogador.getNome() + ". Pois a"
                    + " seleção do(a) " + this.getPais() +
                    " possui o limite máximo de " + MAX_MEMBROS + " membros.");
        }
        jogador.setNomeSelecao(pais);   //Vincula jogador
        time.add(jogador);
    }
    
    public void dispensar(Jogador jogador){
        if(time.contains(jogador)){
            if(time.size() == MIN_MEMBROS){
                throw new IllegalStateException("Não é possível dispensar " + jogador.getNome() + "pois a seleção do(a) "
                        + this.getPais() + " atingiu o número mínimo de " + MIN_MEMBROS + " membros. Contrate um substituto"
                                + " ou exclua a seleção manualmente.");
            }
            else{
                jogador.setNomeSelecao(null);   //Desvincula jogador
                time.remove(jogador);
            }
        }
    }
    
    public boolean podeJogar(){
        for (Jogador jogador : time){
            if(jogador.getStatus() == StatusJogador.LESIONADO || jogador.getStatus() == StatusJogador.SUSPENSO){
                return false;
            }
        }
        return true;
        
    }
 
    //Métodos personalizados
    public void atualizaEstat(int v, int d, int e) throws IllegalArgumentException{
        if (v < 0 || d < 0 || e < 0){
            throw new IllegalArgumentException("Dados estatísticos não podem ser negativos.");
        }
        this.vitorias += v;
        this.derrotas += d;
        this.empates += e;
    }
    
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Selecao s = (Selecao) o;
        return Objects.equals(pais, s.getPais());
    }
    
    @Override
    public int hashCode(){
        return Objects.hash(pais);
    }

    public void mostra(){
        System.out.println("Pais: " + getPais());
        System.out.println("Grupo: " + getGrupo());
        System.out.println("Tecnico: " + getTecnico().getNome());
        for (Jogador jogador : time){
            //jogador.mostra();
            System.out.print("[ " + jogador.getNome() + ",");
            System.out.println(" ]");
        }
    }
    
}
