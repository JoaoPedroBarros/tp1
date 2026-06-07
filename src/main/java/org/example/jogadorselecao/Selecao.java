package org.example.jogadorselecao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Selecao {
    private String pais;
    private int grupo;
    private Tecnico tecnico;
    private List<Jogador> time = new ArrayList<>();
    private static final int MAX_MEMBROS = 26;
    private static final int MIN_MEMBROS = 18;
    private boolean isCopying;  //Define estado de transferência de dados do Registro para a JTable
    
    //Estatísticas
    private int vitorias;
    private int derrotas;
    private int empates;
    
// danilo: jackson precisa de um construtor vazio
    public Selecao() {
        this.isCopying = true;
    }
    
    //Construtor
    public Selecao(String pais, int grupo, Tecnico tecnico, List<Jogador> time) throws IllegalArgumentException {
        this.isCopying = false;
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
        if(isCopying){
            this.tecnico = tecnico;
            return;
        }
        
        if(tecnico.getNomeSelecao() != null){
            throw new IllegalArgumentException(tecnico.getNome() + 
                    " já está afiliado à selecao do(a) " + tecnico.getNomeSelecao() + ".");
        }
        this.tecnico = tecnico;
        tecnico.setNomeSelecao(this.getPais());
    }

    public List<Jogador> getTime() {
        return time;
    }

    public void setTime(List<Jogador> time) {
        if(isCopying){
            for(Jogador jogador : time){
                this.time.add(jogador);
            }
            return;
        }

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
    public boolean podeJogar(){
        for (Jogador jogador : time){
            if(jogador.getStatus() == StatusJogador.LESIONADO || jogador.getStatus() == StatusJogador.SUSPENSO){
                return false;
            }
        }
        return true;
        
    }
 
    //Métodos personalizados
    public void atualizaEstat(int[][] estatJogadores, int[] estatSelecao) throws IllegalArgumentException{
        //Valida se todos os jogadores foram contemplados
        if(estatJogadores.length != time.size()){
            throw new IllegalArgumentException("O vetor de estatísticas dos Jogadores tem tamanho indevido");
        }
        
        //Valida se cada linha contempla todas as estatísticas de cada jogador
        for(int i = 0; i < estatJogadores.length; i++){
            if(estatJogadores[i].length != 5){
                throw new IllegalArgumentException("As estatísticas do jogador " + i + "não foram devidas fornecidas.");
            }
        }
        
        //Valida se todas as estaíticas da seleção foram contempladas
        if(estatSelecao.length != 3){
            throw new IllegalArgumentException("O vetor de estatísticas da Seleção tem tamanho indevido");
        }
       
        //Valida se alguma estatística de Seleção é negativa
        for(int i = 0; i < estatSelecao.length; i++){
            if(estatSelecao[i] < 0){
                String msg = "O número de ";
                switch(i){
                    case 0:
                        msg += "vitórias";
                    case 1:
                        msg += "derrotas";
                    case 2:
                        msg += "empates";
                }
                msg += " não pode ser negativo.";
                throw new IllegalArgumentException(msg);
            }
        }        
        
        //Atualiza estatísticas dos membros do time
        for (int i = 0; i < estatJogadores.length; i++){
            time.get(i).atualizaEstat(estatJogadores[i]);
        }
        
        //Atualiza estatísticas próprias da seleção.
        this.vitorias += estatSelecao[0];
        this.derrotas += estatSelecao[1];
        this.empates += estatSelecao[2];
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
