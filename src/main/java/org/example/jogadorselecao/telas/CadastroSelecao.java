/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.example.jogadorselecao.telas;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import org.example.jogadorselecao.Jogador;
import org.example.jogadorselecao.Selecao;
import org.example.jogadorselecao.StatusJogador;
import org.example.jogadorselecao.Tecnico;
import org.example.jogadorselecao.persistencia.ElementoDuplicado;
import org.example.jogadorselecao.persistencia.PersistenciaDeDados;
import org.example.jogadorselecao.persistencia.IOJogador;
import org.example.jogadorselecao.persistencia.IOSelecao;
import org.example.jogadorselecao.persistencia.IOTecnico;

/**
 *
 * @author jp67a
 */
public class CadastroSelecao extends javax.swing.JPanel {

    /**
     * Creates new form CadastroSelecao
     */
    
    private List<Integer> indices = new ArrayList<>();
    private List<Jogador> jogadores = new ArrayList<>();
    private boolean isEditing;
    private int indice;
    private int tamTime;
    private Tecnico tecAux;
    private List<Integer> indiceTecnico = new ArrayList<>();
    
    public CadastroSelecao() {
        initComponents();
        
        jogadores = IOJogador.getMemJogadores(Jogador -> Jogador.getStatus().equals(StatusJogador.ATIVO)
                                              && Jogador.getNomeSelecao() == null, indices);
        
        atualizaTableJog(jogadores); //Atualiza os valores da tabela com jogadores ATIVOS salvos no arquivo jogadores.jsonl
        tabelaJogs.setAutoCreateRowSorter(true); //Permite ordenação simples por duplo clique nos rótulos das colunas
        
        //Faz com que a seleção de uma linha ou coluna seja total (linha toda)
        tabelaJogs.setRowSelectionAllowed(true);
        tabelaJogs.setColumnSelectionAllowed(false);
        
        //Centraliza os conteudos das colunas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tabelaJogs.getColumnCount(); i++){
            tabelaJogs.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        labelContagemSelecionados.setText("0 jogadores selecionados.");
        tabelaJogs.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e){
                if(!e.getValueIsAdjusting()){
                    int totalLinhasSelecionadas = tabelaJogs.getSelectedRowCount();
                    labelContagemSelecionados.setText(totalLinhasSelecionadas + " jogadores selecionados.");
                }
            }
        });
    }
    
    public CadastroSelecao(int indice){
        initComponents();
        
        this.isEditing = true;
        this.indice = indice;
        
        labelContagemSelecionados.setText("0 jogadores selecionados.");
        tabelaJogs.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e){
                if(!e.getValueIsAdjusting()){
                    int totalLinhasSelecionadas = tabelaJogs.getSelectedRowCount();
                    labelContagemSelecionados.setText(totalLinhasSelecionadas + " jogadores selecionados.");
                }
            }
        });

        try{
            Selecao aux = IOSelecao.get(indice);
            
            tecAux = aux.getTecnico();
            txtInputPais.setText(aux.getPais());
            txtInputGrupo.setText(aux.getGrupo());
            txtInputTecnico.setText(aux.getTecnico().getNome());
            
            //Configura tabela.
            tamTime = aux.getTime().size(); //Pega tamanho do time
            
            //Pega membros e seus índices
            jogadores = IOJogador.getMemJogadores(Jogador -> aux.getPais().equals(Jogador.getNomeSelecao()), indices);
            
            List<Integer> indexesVagos = new ArrayList<>();
            List<Jogador> vagos = IOJogador.getMemJogadores(Jogador -> Jogador.getStatus().equals(StatusJogador.ATIVO)
                                              && Jogador.getNomeSelecao() == null, indexesVagos); //Adiciona membros
            
            jogadores.addAll(vagos); //Adiciona jogadores vagos os final da tabela
            indices.addAll(indexesVagos); //Adiciona indices dos jogadores vagos em ordem na lista de indices
            
            
            atualizaTableJog(jogadores); //Atualiza tabela com os jogadores
            tabelaJogs.setAutoCreateRowSorter(true); //Permite ordenação simples por duplo clique nos rótulos das colunas
        
            //Faz com que a seleção de uma linha ou coluna seja total (linha toda)
            tabelaJogs.setRowSelectionAllowed(true);
            tabelaJogs.setColumnSelectionAllowed(false);
        
            //Centraliza os conteudos das colunas
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            for (int i = 0; i < tabelaJogs.getColumnCount(); i++){
                tabelaJogs.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            tabelaJogs.setRowSelectionInterval(0, tamTime-1); //Deixa os membros selecionados
        }
        catch(IOException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO!", JOptionPane.ERROR_MESSAGE);            
        }
    }

    private void atualizaTableJog(List<Jogador> jogadores){
        //Altera dinamicamente a tabela
        String[] colunas = new String[] {"Nome", "Número", "Posição"}; //Configura colunas

        tabelaJogs.clearSelection();
        
        DefaultTableModel modelo = new DefaultTableModel(null, colunas){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
            @Override
            public Class<?> getColumnClass(int columnIndex){
                switch (columnIndex){
                    case 0:
                        return String.class;
                    case 1:
                        return Integer.class;
                    case 2:
                        return String.class;
                    default:
                        return Object.class;
                }
            }
        };
        
        tabelaJogs.setModel(modelo); //Configura modelo da Tabela (Quantidade de colunas e disposição destas)
        tabelaJogs.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        
        for(Jogador jogador : jogadores){
            modelo.addRow(new Object[]{jogador.getNome(), jogador.getNumero(), jogador.getPosicao().toString()});             
        }
        
        //Define seleção da linha inteira
        tabelaJogs.setRowSelectionAllowed(true);
        tabelaJogs.setColumnSelectionAllowed(false);
        
        //Centraliza os conteúdos das colunas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer(){
        };
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tabelaJogs.getColumnCount(); i++){
            tabelaJogs.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelPais = new javax.swing.JLabel();
        labelGrupo = new javax.swing.JLabel();
        labelTecnico = new javax.swing.JLabel();
        txtInputPais = new javax.swing.JTextField();
        txtInputGrupo = new javax.swing.JTextField();
        botaoSalvar = new javax.swing.JButton();
        botaoCancelar = new javax.swing.JButton();
        labelSelecioneJog = new javax.swing.JLabel();
        txtInputTecnico = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaJogs = new javax.swing.JTable();
        labelContagemSelecionados = new javax.swing.JLabel();

        labelPais.setText("País:");

        labelGrupo.setText("Grupo:");

        labelTecnico.setText("Técnico:");

        botaoSalvar.setText("Salvar");
        botaoSalvar.addActionListener(this::botaoSalvarActionPerformed);

        botaoCancelar.setText("Cancelar");
        botaoCancelar.addActionListener(this::botaoCancelarActionPerformed);

        labelSelecioneJog.setText("Selecione 18 a 26 jogadores.");

        tabelaJogs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tabelaJogs);

        labelContagemSelecionados.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelPais)
                                    .addComponent(labelTecnico)
                                    .addComponent(labelGrupo))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtInputGrupo, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtInputPais)
                                    .addComponent(txtInputTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(labelSelecioneJog)
                            .addComponent(labelContagemSelecionados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(botaoSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPais)
                    .addComponent(txtInputPais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelGrupo)
                    .addComponent(txtInputGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtInputTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTecnico))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelSelecioneJog, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelContagemSelecionados, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoSalvar)
                    .addComponent(botaoCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
        // TODO add your handling code here:
                // TODO add your handling code here:
        int res = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja prosseguir?", "Atenção!", JOptionPane.YES_NO_OPTION);
        if (res == JOptionPane.YES_OPTION){
            SwingUtilities.getWindowAncestor(this).dispose();
        }
    }//GEN-LAST:event_botaoCancelarActionPerformed

    private void botaoSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarActionPerformed
        // TODO add your handling code here:
        
       //Pega índices das linhas selecionadas pelo usuário
       int[] indexesDaTabela = tabelaJogs.getSelectedRows(); //Pega indexes selecionados na GUI
       for(int i = 0; i < indexesDaTabela.length; i++){     //Converte indexes selecionados para a referencia no Modelo Base
           indexesDaTabela[i] = tabelaJogs.convertRowIndexToModel(indexesDaTabela[i]);
       }       
       
       List<Integer> indicesRefRegisto = new ArrayList<>(); //Instancia a Lista a ser passada para atualização dos Registros
       for(int i = 0; i < indexesDaTabela.length; i++){     //Constroi a Lista com indices correspondentes no registros
           indicesRefRegisto.add(indices.get(indexesDaTabela[i]));
       }
       indicesRefRegisto.sort(null); //Ordena índices no Registro
       
       //Da desvinculação da equipe antiga
        if(isEditing && tamTime > 0){
            //Adianta o processo para o Tecnico
            List<Tecnico> tecnicoMod = IOTecnico.getMemTecnicos(Tecnico -> Tecnico.getNome().equalsIgnoreCase(tecAux.getNome()), indiceTecnico);
            tabelaJogs.clearSelection(); //Desfaz seleção do usuário
            tabelaJogs.setRowSorter(null); //Desfaz filtragem
            tabelaJogs.setRowSelectionInterval(0, tamTime-1); //Seleciona os membros
            
            //Pega índices dos membros do time no registro
            int[] indexesMembros = tabelaJogs.getSelectedRows(); //Pega indexes selecionados na GUI
            for(int i = 0; i < indexesMembros.length; i++){     //Converte indexes selecionados para a referencia no Modelo Base
                indexesMembros[i] = tabelaJogs.convertRowIndexToModel(indexesMembros[i]);
            }       
            
            List<Integer> indicesMembros = new ArrayList<>(); //Instancia a Lista a ser passada para atualização dos Registros
            for(int i = 0; i < indexesMembros.length; i++){     //Constroi a Lista com indices correspondentes no registros
                indicesMembros.add(indices.get(indexesMembros[i]));
            } 
            indicesMembros.sort(null); //Ordena índices dos membros
            
            //Instancia os membros na memória
            try{
                List<Jogador> membros = IOJogador.getMult(indicesMembros); //Resgata membros do registro
                for(Jogador membro : membros){//Desvincula todos os jogadores do time
                    membro.setNomeSelecao(null);
                }
                tecnicoMod.getFirst().setNomeSelecao(null); //Desvincula técnico
                
                //Atuliza registro jogadores
                IOJogador.insertMult(membros, indicesMembros); //Atualiza membros selecionados no Registro jogadores.jsonl
                IOTecnico.insert(tecnicoMod.getFirst(), indiceTecnico.getFirst());
            }
            catch (IOException e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO!", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
       
       //Da vinculação da nova equipe
       try{
            List<Jogador> selecionados = IOJogador.getMult(indicesRefRegisto); //Resgata jogadores do registro
            
            Tecnico tecnico = new Tecnico(txtInputTecnico.getText()); //Instancia tecnico

            Selecao selecao = new Selecao(txtInputPais.getText(), 
                                          txtInputGrupo.getText(),
                                          tecnico,
                                          selecionados); //Instancia a selecao
            if(isEditing){
                PersistenciaDeDados.insert(tecnico, indiceTecnico.getFirst()); //Adiciona tecnico ao registro
                PersistenciaDeDados.insert(selecao, indice); //Adiciona selecao ao registro selecoes.jsonl //Implementar insertSelecao
            }
            else{
                IOTecnico.appendTecnico(tecnico); //Adiciona tecnico ao registro
                IOSelecao.appendSelecao(selecao); //Adiciona selecao ao registro selecoes.jsonl               
            }

            
            IOJogador.insertMult(selecionados, indicesRefRegisto); //Atualiza jogadores selecionados no Registro jogadores.jsonl
            JOptionPane.showMessageDialog(null, "Operação realizada com sucesso");
            SwingUtilities.getWindowAncestor(this).dispose();
        }
        catch(NoSuchElementException | ElementoDuplicado | IllegalArgumentException | IOException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_botaoSalvarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JButton botaoSalvar;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelContagemSelecionados;
    private javax.swing.JLabel labelGrupo;
    private javax.swing.JLabel labelPais;
    private javax.swing.JLabel labelSelecioneJog;
    private javax.swing.JLabel labelTecnico;
    private javax.swing.JTable tabelaJogs;
    private javax.swing.JTextField txtInputGrupo;
    private javax.swing.JTextField txtInputPais;
    private javax.swing.JTextField txtInputTecnico;
    // End of variables declaration//GEN-END:variables
}
