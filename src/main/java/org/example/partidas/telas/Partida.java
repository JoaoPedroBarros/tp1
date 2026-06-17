
package org.example.partidas.telas;

import java.util.List;
import javax.swing.JOptionPane;
import org.example.administracao.Usuario;
import org.example.administracao.PersistenciaUsuario;
import org.example.administracao.AdministraUsuario;
import org.example.estadioArbitragem.Arbitro;
import org.example.jogadorselecao.Selecao;
import org.example.jogadorselecao.persistencia.IOSelecao;
import org.example.partidas.Fase;
import org.example.partidas.FaseGrupos;
import org.example.partidas.Final;
import org.example.partidas.OitavasFinal;
import org.example.partidas.PartidaCopa;
import org.example.partidas.QuartasFinal;
import org.example.partidas.SemiFinal;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import org.example.estadioArbitragem.Estadio;
import org.example.partidas.JsonUtil;


public class Partida extends javax.swing.JFrame {
    
    private Fase faseAtual;
    private PartidaCopa partidaAtual;
    private PersistenciaUsuario persistencia;
    
    private int gerarNumeroPartida() {

    return (int)
            (System.currentTimeMillis()
            % 1000000);
}
    
    private void carregarEstadios() {

    try {

        ObjectMapper mapper =
                new ObjectMapper();

        File arquivo =
                new File(
                        "src/main/resources/estadios.json");

        List<Estadio> estadios =
                mapper.readValue(
                        arquivo,
                        new TypeReference<List<Estadio>>() {});

        cbEstadio.removeAllItems();

        for (Estadio estadio : estadios) {

            cbEstadio.addItem(
                    estadio.getNome());
        }

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                "Erro ao carregar estádios:\n"
                        + e.getMessage());
    }
    }
    
        private void carregarSelecoes() {

        try {

            cbSelecao1.removeAllItems();
            cbSelecao2.removeAllItems();

            String fase =
                    cbFase1.getSelectedItem()
                            .toString();

            if (fase.equals("GRUPOS")) {

                int i = 0;
                Selecao selecao;

                while ((selecao = IOSelecao.get(i)) != null) {

                    cbSelecao1.addItem(
                            selecao.getPais());

                    cbSelecao2.addItem(
                            selecao.getPais());

                    i++;
                }
            }

            else {

                String arquivo;

                switch (fase) {

                    case "OITAVAS":
                        arquivo =
                                "src/main/resources/classificados_oitavas.json";
                        break;

                    case "QUARTAS":
                        arquivo =
                                "src/main/resources/classificados_quartas.json";
                        break;

                    case "SEMIS":
                        arquivo =
                                "src/main/resources/classificados_semis.json";
                        break;

                    case "FINAL":
                        arquivo =
                                "src/main/resources/classificados_final.json";
                        break;

                    default:
                        return;
                }

                List<String> classificados =
                        JsonUtil.getMapper()
                                .readValue(
                                        new File(arquivo),
                                        new com.fasterxml.jackson.core.type.TypeReference<List<String>>() {});

                for (String pais : classificados) {

                    cbSelecao1.addItem(pais);
                    cbSelecao2.addItem(pais);
                }
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao carregar seleções:\n"
                    + e.getMessage());
        }
    }
    
    private Selecao buscarSelecao(String pais)
        throws Exception {

    int indice =
            IOSelecao.containsSelecao(
                    pais);

    if (indice == -1) {

        throw new Exception(
                "Seleção não encontrada: "
                + pais);
    }

    return IOSelecao.get(indice);
}
    
    private void carregarArbitros() {

    try {

        List<Usuario> arbitros =
                AdministraUsuario.pesquisaUsuario(
                        "",
                        "",
                        "",
                        "",
                        new Arbitro(),
                        persistencia
                );

        cbArbitro.removeAllItems();

        for (Usuario usuario : arbitros) {
            cbArbitro.addItem(usuario.getNome());
        }

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Partida.class.getName());
    public Partida() {

    initComponents();

    persistencia = new PersistenciaUsuario();
    carregarSelecoes();
    carregarArbitros();
    carregarEstadios();

    carregarArbitros();
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtData = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtHorario = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Salvar1 = new javax.swing.JButton();
        Fechar1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        cbFase1 = new javax.swing.JComboBox<>();
        cbArbitro = new javax.swing.JComboBox<>();
        cbSelecao1 = new javax.swing.JComboBox<>();
        cbSelecao2 = new javax.swing.JComboBox<>();
        cbEstadio = new javax.swing.JComboBox<>();
        btnGerarClassificados = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtNumeroPartida = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtGols1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtGols2 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        btnResultado = new javax.swing.JButton();
        cbFase2 = new javax.swing.JComboBox<>();
        cbPenaltis = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtConsulta = new javax.swing.JTextArea();
        btnConsultar = new javax.swing.JButton();
        cbFase3 = new javax.swing.JComboBox<>();
        jScrollBar1 = new javax.swing.JScrollBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Data da Partida :");

        txtData.addActionListener(this::txtDataActionPerformed);

        jLabel2.setText("Horário da Partida :");

        jLabel3.setText("Nome do Estádio :");

        jLabel4.setText("Seleção 1:");

        jLabel5.setText("Seleção 2:");

        jLabel6.setText("Árbitro :");

        Salvar1.setText("Salvar");
        Salvar1.addActionListener(this::Salvar1ActionPerformed);

        Fechar1.setText("Fechar");
        Fechar1.addActionListener(this::Fechar1ActionPerformed);

        jLabel9.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        jLabel9.setText("Partida:");

        cbFase1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "GRUPOS", "OITAVAS", "QUARTAS", "SEMIS", "FINAL" }));
        cbFase1.addActionListener(this::cbFase1ActionPerformed);

        cbSelecao1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbSelecao2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbEstadio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnGerarClassificados.setText("Gerar Classificados");
        btnGerarClassificados.addActionListener(this::btnGerarClassificadosActionPerformed);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(Salvar1)
                        .addGap(45, 45, 45)
                        .addComponent(Fechar1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtData, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                                    .addComponent(txtHorario))
                                .addGap(63, 63, 63))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(btnGerarClassificados)
                                .addGap(39, 39, 39)
                                .addComponent(cbFase1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(80, 80, 80))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbArbitro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbSelecao1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbSelecao2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbEstadio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbFase1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGerarClassificados))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbEstadio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbSelecao1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbSelecao2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(cbArbitro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Salvar1)
                    .addComponent(Fechar1))
                .addContainerGap(72, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cadastro de Partidas", jPanel3);

        jLabel7.setText("Digite o Número da Partida:");

        jLabel10.setText("Gols da Seleção 1:");

        jLabel11.setText("Gols da Seleção 2:");

        jLabel12.setText("(Caso haja um empate depois da Fase de Grupos) Qual seleção ganhou nos pênaltis? :");

        btnResultado.setText("Salvar");
        btnResultado.addActionListener(this::btnResultadoActionPerformed);

        cbFase2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "GRUPOS", "OITAVAS", "QUARTAS", "SEMIS", "FINAL" }));
        cbFase2.addActionListener(this::cbFase2ActionPerformed);

        cbPenaltis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setText("Fase:");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(this::btnBuscarActionPerformed);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNumeroPartida)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                            .addComponent(jLabel11)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtGols2))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(btnBuscar)
                                                .addComponent(jLabel10))
                                            .addGap(18, 18, 18)
                                            .addComponent(txtGols1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(117, 117, 117)
                                        .addComponent(jLabel8)
                                        .addGap(18, 18, 18)
                                        .addComponent(cbFase2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 132, Short.MAX_VALUE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(btnResultado))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(cbPenaltis, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cbFase2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNumeroPartida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscar)
                .addGap(1, 1, 1)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtGols1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtGols2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbPenaltis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(btnResultado)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Registro de Resultados", jPanel4);

        txtConsulta.setColumns(20);
        txtConsulta.setRows(5);
        jScrollPane1.setViewportView(txtConsulta);

        btnConsultar.setText("Mostrar");
        btnConsultar.addActionListener(this::btnConsultarActionPerformed);

        cbFase3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "GRUPOS", "OITAVAS", "QUARTAS", "SEMIS", "FINAL", " " }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnConsultar)
                .addGap(146, 146, 146)
                .addComponent(cbFase3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConsultar)
                    .addComponent(cbFase3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Consulta de Jogos", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnResultadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResultadoActionPerformed
        try {

        if (partidaAtual == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Busque uma partida primeiro.");

            return;
        }

        int gols1 =
                Integer.parseInt(
                        txtGols1.getText());

        int gols2 =
                Integer.parseInt(
                        txtGols2.getText());

        String vencedorPenaltis = null;

        String nomeFase =
                cbFase2
                .getSelectedItem()
                .toString();

        boolean eliminatoria =
                !nomeFase.equals("GRUPOS");

        if (eliminatoria && gols1 == gols2) {

            vencedorPenaltis =
                    cbPenaltis
                    .getSelectedItem()
                    .toString();

            if (vencedorPenaltis == null
                    || vencedorPenaltis.isBlank()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Escolha o vencedor dos pênaltis.");

                return;
            }
        }

        faseAtual.registrarResultado(
                partidaAtual.getNumero(),
                gols1,
                gols2,
                vencedorPenaltis);

        JOptionPane.showMessageDialog(
                this,
                "Resultado registrado com sucesso!");

        partidaAtual = null;

        txtNumeroPartida.setText("");
        txtGols1.setText("");
        txtGols2.setText("");

        cbPenaltis.removeAllItems();

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                e.getMessage());
    }
    }//GEN-LAST:event_btnResultadoActionPerformed

    private void Fechar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Fechar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Fechar1ActionPerformed

    private void Salvar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Salvar1ActionPerformed

    try {

        String faseSelecionada =
                cbFase1
                .getSelectedItem()
                .toString();
        
           if (faseSelecionada.equals("OITAVAS")) {

        FaseGrupos grupos = new FaseGrupos();

        if (!grupos.faseFinalizada()) {

            JOptionPane.showMessageDialog(
                    this,
                    "A fase de grupos ainda não terminou.");

            return;
        }

        java.io.File arquivo =
                new java.io.File(
                        "src/main/resources/classificados_oitavas.json");

        if (!arquivo.exists()) {

            grupos.gerarClassificados();
        }
    }

        Fase fase;

        switch (faseSelecionada) {

            case "GRUPOS":
                fase = new FaseGrupos();
                break;

            case "OITAVAS":
                fase = new OitavasFinal();
                break;

            case "QUARTAS":
                fase = new QuartasFinal();
                break;

            case "SEMIS":
                fase = new SemiFinal();
                break;

            case "FINAL":
                fase = new Final();
                break;

            default:
                throw new Exception(
                        "Fase inválida.");
        }
        
        

        String pais1 =
                cbSelecao1
                .getSelectedItem()
                .toString();

        String pais2 =
                cbSelecao2
                .getSelectedItem()
                .toString();

        if (pais1.equals(pais2)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Uma seleção não pode jogar contra ela mesma.");

            return;
        }


        PartidaCopa partida =
                new PartidaCopa();

        partida.setNumero(
                gerarNumeroPartida());

        partida.setFase(
                faseSelecionada);

        partida.setData(
                txtData.getText());

        partida.setHorario(
                txtHorario.getText());

        partida.setEstadio(
                cbEstadio
                        .getSelectedItem()
                        .toString());

                String nomeArbitro =
                cbArbitro.getSelectedItem().toString();

        Usuario arbitro = null;

        List<Usuario> arbitros =
                AdministraUsuario.pesquisaUsuario(
                        "",
                        "",
                        "",
                        "",
                        new Arbitro(),
                        persistencia);

        for (Usuario u : arbitros) {

            if (u.getNome().equals(nomeArbitro)) {

                arbitro = u;
                break;
            }
        }

        if (arbitro != null &&
        (arbitro.getPais().equalsIgnoreCase(pais1)
         ||
         arbitro.getPais().equalsIgnoreCase(pais2))) {

        throw new Exception(
                "O árbitro não pode apitar partidas envolvendo seu próprio país.");
    }
        
        partida.setArbitro(
        nomeArbitro);

        partida.setSelecao1(
                pais1);

        partida.setSelecao2(
                pais2);

        fase.criarPartida(
                partida);

        JOptionPane.showMessageDialog(
                this,
                "Partida cadastrada com sucesso!");

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                e.getMessage());
    }
    }//GEN-LAST:event_Salvar1ActionPerformed

    private void txtDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDataActionPerformed

    private void cbFase1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFase1ActionPerformed
        carregarSelecoes();
        String fase =
                cbFase1.getSelectedItem()
                        .toString();

        switch (fase) {

            case "GRUPOS" ->
                faseAtual =
                        new FaseGrupos();

            case "OITAVAS" ->
                faseAtual =
                        new OitavasFinal();

            case "QUARTAS" ->
                faseAtual =
                        new QuartasFinal();

            case "SEMIFINAL" ->
                faseAtual =
                        new SemiFinal();

            case "FINAL" ->
                faseAtual =
                        new Final();
        }
    }//GEN-LAST:event_cbFase1ActionPerformed

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed

    try {

        String faseSelecionada =
                cbFase3.getSelectedItem().toString();

        Fase fase;

        switch (faseSelecionada) {

            case "GRUPOS":
                fase = new FaseGrupos();
                break;

            case "OITAVAS":
                fase = new OitavasFinal();
                break;

            case "QUARTAS":
                fase = new QuartasFinal();
                break;

            case "SEMIS":
                fase = new SemiFinal();
                break;

            case "FINAL":
                fase = new Final();
                break;

            default:
                throw new Exception(
                        "Fase inválida.");
        }

        txtConsulta.setText(
                fase.mostrarPartidas());

    }

    catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                e.getMessage());
    }
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed

    try {

        String nomeFase =
                cbFase2
                .getSelectedItem()
                .toString();

        switch (nomeFase) {

            case "GRUPOS":
                faseAtual = new FaseGrupos();
                break;

            case "OITAVAS":
                faseAtual = new OitavasFinal();
                break;

            case "QUARTAS":
                faseAtual = new QuartasFinal();
                break;

            case "SEMIS":
                faseAtual = new SemiFinal();
                break;

            case "FINAL":
                faseAtual = new Final();
                break;

            default:
                throw new Exception(
                        "Fase inválida.");
        }

        int numeroPartida =
                Integer.parseInt(
                        txtNumeroPartida.getText());

        partidaAtual =
                faseAtual.buscarPartida(
                        numeroPartida);

        if (partidaAtual == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Partida não encontrada.");

            return;
        }

        cbPenaltis.removeAllItems();

        cbPenaltis.addItem(
                partidaAtual.getSelecao1());

        cbPenaltis.addItem(
                partidaAtual.getSelecao2());

        JOptionPane.showMessageDialog(
                this,
                "Partida encontrada:\n"
                + partidaAtual.getSelecao1()
                + " x "
                + partidaAtual.getSelecao2());

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                e.getMessage());
    }

    }//GEN-LAST:event_btnBuscarActionPerformed

    private void cbFase2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFase2ActionPerformed

    String fase =
            cbFase2
            .getSelectedItem()
            .toString();

    cbPenaltis.setVisible(
            !fase.equals("GRUPOS"));

    }//GEN-LAST:event_cbFase2ActionPerformed

    private void btnGerarClassificadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerarClassificadosActionPerformed
            try {

            String faseSelecionada =
                    cbFase1
                            .getSelectedItem()
                            .toString();

            Fase fase;

            switch (faseSelecionada) {

                case "GRUPOS":
                    fase = new FaseGrupos();
                    break;

                case "OITAVAS":
                    fase = new OitavasFinal();
                    break;

                case "QUARTAS":
                    fase = new QuartasFinal();
                    break;

                case "SEMIS":
                    fase = new SemiFinal();
                    break;

                case "FINAL":

                    JOptionPane.showMessageDialog(
                            this,
                            "A fase FINAL não gera classificados.");

                    return;

                default:
                    throw new Exception(
                            "Fase inválida.");
            }

            fase.gerarClassificados();

            JOptionPane.showMessageDialog(
                    this,
                    "Classificados gerados com sucesso!");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage());
        }
    }//GEN-LAST:event_btnGerarClassificadosActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Partida().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Fechar1;
    private javax.swing.JButton Salvar1;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnGerarClassificados;
    private javax.swing.JButton btnResultado;
    private javax.swing.JComboBox<String> cbArbitro;
    private javax.swing.JComboBox<String> cbEstadio;
    private javax.swing.JComboBox<String> cbFase1;
    private javax.swing.JComboBox<String> cbFase2;
    private javax.swing.JComboBox<String> cbFase3;
    private javax.swing.JComboBox<String> cbPenaltis;
    private javax.swing.JComboBox<String> cbSelecao1;
    private javax.swing.JComboBox<String> cbSelecao2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea txtConsulta;
    private javax.swing.JTextField txtData;
    private javax.swing.JTextField txtGols1;
    private javax.swing.JTextField txtGols2;
    private javax.swing.JTextField txtHorario;
    private javax.swing.JTextField txtNumeroPartida;
    // End of variables declaration//GEN-END:variables
}
