package org.example.jogadorselecao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class Selecao {
    private String pais;
    private int grupo;
    private Tecnico tecnico;
    private HashSet<Jogador> time = new HashSet<>();
    private static final int MAX_MEMBROS = 26;
    private static final int MIN_MEMBROS = 18;
    
// danilo: jackson precisa de um construtor vazio
    public Selecao() {
}
    
    //Construtor
    public Selecao(String pais, int grupo, Tecnico tecnico, HashSet<Jogador> time) throws IllegalArgumentException {
        setPais(pais);
        setGrupo(grupo);
        setTecnico(tecnico);
        setTime(time);
    }

    
    
    //Getters e Setters
    public String getPais() {
        return pais;
    }

    public final void setPais(String pais) {
        if(pais.matches(".*\\d.*") || pais.matches(".*[^a-zA-Z0-9 ].*")){
           throw new IllegalArgumentException("Nome do país não pode conter números ou símbolos especiais.");
        }
        this.pais = pais;
    }

    public int getGrupo() {
        return grupo;
    }

    public final void setGrupo(int grupo) {
        if (grupo <= 0){
            throw new IllegalArgumentException("O número do jogador deve ser maior que 0.");
        }
        this.grupo = grupo;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public final void setTecnico(Tecnico tecnico) {
        if(tecnico.getSelecao() != null){
            throw new IllegalArgumentException(tecnico.getNome() + 
                    " já está afiliado a selecao do(a) " + tecnico.getSelecao().getPais() + ".");
        }
        this.tecnico = tecnico;
        tecnico.setSelecao(this);
    }

    public HashSet<Jogador> getTime() {
        return time;
    }

    public void setTime(HashSet<Jogador> time) {
        //Impede set com tamanhos inadequados de time
        if(time.size() < MIN_MEMBROS || time.size() > MAX_MEMBROS){
            throw new IllegalArgumentException("Quantidade inadequada de membros no time.");
        }
   
        //Garante que um jogador não poderá estar vinculado a duas seleções
        for(Jogador jogador : time){
            if(jogador.getSelecao() != null){
                throw new IllegalArgumentException("O jogador " + jogador.getNome() +
                        " já está afiliada à seleção do(a) " + jogador.getSelecao().getPais() + ".");
            }
        }
        
        if(this.time != null){ //Para substituir a equipe inteira
          for(Jogador jogador : this.time){
                jogador.setSelecao(null);
                this.time.remove(jogador);
            }     
        }
  
        //Vincula cada jogador da HashSet à atual instância de seleção
        for(Jogador jogador : time){
            jogador.setSelecao(this);
            this.time.add(jogador);
        } 
    }    

    //Metodos personalizados
    public void convocar(Jogador jogador){
        if (time.size() == MAX_MEMBROS){
            throw new IllegalStateException("Não é possível convocar " + jogador.getNome() + ". Pois a"
                    + " seleção do(a) " + this.getPais() +
                    " possui o limite máximo de " + MAX_MEMBROS + " membros.");
        }
        jogador.setSelecao(this);   //Vincula jogador
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
                jogador.setSelecao(null);   //Desvincula jogador
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
            jogador.mostra();
        }
    }
    
}
