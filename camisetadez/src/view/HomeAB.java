package view;

import date.User;
import logic.HomeFuncoes;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Component;
import logic.FuncoesArquivo;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import javax.swing.table.DefaultTableModel;

import static logic.FuncoesArquivo.carregarJogadores;

public class HomeAB extends JFrame {

    public String nomeTimeA;
    public String nomeTimeB;
    private JPanel contentPane;
    private JTable tableA;
    private JTable tableB;
    private JTable table_1;
    private JTextField txtTimeA;
    private JTable table;
    private JTextField textTimeB;
    private JTable table_2;
    private JTable table_3;
    public static boolean AB; //variável que definirá se os jogadores irão para o lado A ou B
    public static boolean ir = false; //variável que permite avançar a tela te jogos
    DefaultTableModel tableModelA = new DefaultTableModel();
    DefaultTableModel tableModelB = new DefaultTableModel();
    List<User> listaTodos = FuncoesArquivo.carregarJogadores("posicao.txt");
    List<User> caminhoListaTimeA = FuncoesArquivo.carregarJogadores("timeA.txt");
    List<User> caminhoListaTimeB = FuncoesArquivo.carregarJogadores("timeB.txt");
    List<User> listaSemRepetidos = FuncoesArquivo.removerRepetidos(listaTodos, caminhoListaTimeA, caminhoListaTimeB);
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    HomeAB frame = new HomeAB();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public HomeAB() {
        setTitle("Camiseta 10 - Home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1317, 765);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 64, 0));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(new Color(255, 255, 255), 6));
        panel.setBackground(new Color(0, 64, 0));
        FlowLayout flowLayout = (FlowLayout) panel.getLayout();
        flowLayout.setVgap(33);
        contentPane.add(panel, BorderLayout.NORTH);

        JLabel lblLabelTitulo = new JLabel("Bem-Vindo!");
        lblLabelTitulo.setVerticalAlignment(SwingConstants.TOP);
        lblLabelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblLabelTitulo.setForeground(Color.WHITE);
        lblLabelTitulo.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 28));
        panel.add(lblLabelTitulo);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new LineBorder(new Color(255, 255, 255), 6));
        panel_1.setBackground(new Color(0, 64, 0));
        contentPane.add(panel_1, BorderLayout.WEST);

        txtTimeA = new JTextField();
        txtTimeA.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtTimeA.setHorizontalAlignment(SwingConstants.CENTER);
        txtTimeA.setText("Time A");
        nomeTimeA = txtTimeA.getText();
        txtTimeA.setColumns(10);

            //Tabela times A
        tableModelA = new DefaultTableModel(new Object[]{"", "Nome", "N", "Posição"}, 0) {
            @Override
            public Class<?> getColumnClass(int indiceColuna) {
                if (indiceColuna == 0) {
                    return Boolean.class; // boolean como checkboxes
                } else { //super se refere a classe principal (classe pai)
                    return super.getColumnClass(indiceColuna);
                }
            }
        };
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane);
        table = new JTable(tableModelA);
        table.getColumnModel().getColumn(0).setPreferredWidth(5);
        table.getColumnModel().getColumn(1).setPreferredWidth(170);
        table.getColumnModel().getColumn(2).setPreferredWidth(30);
        //**   (procurar um meio de centralizar a coluna 2)
        table.getColumnModel().getColumn(3).setPreferredWidth(145);
        //construção da tabela adicionando cada item de User na Row da tableModel
        for (User usuario : caminhoListaTimeA) {
            tableModelA.addRow(new Object[]{false, usuario.getNome(), usuario.getNumero(), usuario.getPosicao()});
        }
        scrollPane.setViewportView(table);



        JButton btnAddTimeA = new JButton("Adicionar Jogador");
        btnAddTimeA.setBackground(new Color(0, 128, 0));
        btnAddTimeA.setForeground(new Color(255, 255, 255));
        btnAddTimeA.setFont(new Font("Tahoma", Font.PLAIN, 17));

        JButton btnRemovTimeA = new JButton("Remover Jogador");
        btnRemovTimeA.setForeground(Color.WHITE);
        btnRemovTimeA.setFont(new Font("Tahoma", Font.PLAIN, 17));
        btnRemovTimeA.setBackground(new Color(128, 0, 0));

        JButton btnLimparA = new JButton("Limpar Tabela");
        btnLimparA.setForeground(new Color(255, 255, 255));
        btnLimparA.setFont(new Font("Tahoma", Font.PLAIN,17));
        btnLimparA.setBackground(new Color(0, 64, 100));
        GroupLayout gl_panel_1 = new GroupLayout(panel_1);
        gl_panel_1.setHorizontalGroup(
                gl_panel_1.createParallelGroup(Alignment.TRAILING)
                        .addGroup(gl_panel_1.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(btnLimparA, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
                                        .addComponent(btnRemovTimeA, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
                                        .addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
                                                .addComponent(txtTimeA, Alignment.LEADING)
                                                .addComponent(btnAddTimeA, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
                                                .addComponent(table, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        gl_panel_1.setVerticalGroup(
                gl_panel_1.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_1.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txtTimeA, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_panel_1.createSequentialGroup()
                                                .addGap(154)
                                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(gl_panel_1.createSequentialGroup()
                                                .addGap(18)
                                                .addComponent(table, GroupLayout.PREFERRED_SIZE, 298, GroupLayout.PREFERRED_SIZE)))
                                .addGap(18)
                                .addComponent(btnAddTimeA, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(btnRemovTimeA, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
                                .addGap(11)
                                .addComponent(btnLimparA, GroupLayout.DEFAULT_SIZE, 9, Short.MAX_VALUE)
                                .addContainerGap())
        );
        panel_1.setLayout(gl_panel_1);

        JPanel panel_1_1 = new JPanel();
        panel_1_1.setBorder(new LineBorder(new Color(255, 255, 255), 6));
        panel_1_1.setBackground(new Color(0, 64, 0));
        contentPane.add(panel_1_1, BorderLayout.EAST);

        textTimeB = new JTextField();
        textTimeB.setText("Time B");
        nomeTimeB = textTimeB.getText();
        textTimeB.setHorizontalAlignment(SwingConstants.CENTER);
        textTimeB.setFont(new Font("Tahoma", Font.PLAIN, 20));
        textTimeB.setColumns(10);

        tableModelB = new DefaultTableModel(new Object[]{"", "Nome", "N", "Posição"}, 0) {
            @Override
            public Class<?> getColumnClass(int indiceColuna) {
                if (indiceColuna == 0) {
                    return Boolean.class; // boolean como checkboxes
                } else { //super se refere a classe principal (classe pai)
                    return super.getColumnClass(indiceColuna);
                }
            }
        };
        JScrollPane scrollPaneB = new JScrollPane();
        contentPane.add(scrollPaneB);
        table_2 = new JTable(tableModelB);
        table_2.getColumnModel().getColumn(0).setPreferredWidth(5);
        table_2.getColumnModel().getColumn(1).setPreferredWidth(170);
        table_2.getColumnModel().getColumn(2).setPreferredWidth(30);
        //**   (procurar um meio de centralizar a coluna 2)
        table_2.getColumnModel().getColumn(3).setPreferredWidth(145);
        //construção da tabela adicionando cada item de User na Row da tableModel
        for (User usuario : caminhoListaTimeB) {
            tableModelB.addRow(new Object[]{false, usuario.getNome(), usuario.getNumero(), usuario.getPosicao()});
        }
        scrollPane.setViewportView(table_2);

        JButton btnAddTimeB = new JButton("Adicionar Jogador");
        btnAddTimeB.setForeground(Color.WHITE);
        btnAddTimeB.setFont(new Font("Tahoma", Font.PLAIN, 17));
        btnAddTimeB.setBackground(new Color(0, 128, 0));

        JButton btnRemovTimeB = new JButton("Remover Jogador");
        btnRemovTimeB.setForeground(Color.WHITE);
        btnRemovTimeB.setFont(new Font("Tahoma", Font.PLAIN, 17));
        btnRemovTimeB.setBackground(new Color(128, 0, 0));

        JButton btnLimparA_1 = new JButton("Limpar Tabela");
        btnLimparA_1.setForeground(Color.WHITE);
        btnLimparA_1.setFont(new Font("Tahoma", Font.PLAIN,17));
        btnLimparA_1.setBackground(new Color(0, 64, 100));
        GroupLayout gl_panel_1_1 = new GroupLayout(panel_1_1);
        gl_panel_1_1.setHorizontalGroup(
                gl_panel_1_1.createParallelGroup(Alignment.LEADING)
                        .addGroup(Alignment.TRAILING, gl_panel_1_1.createSequentialGroup()
                                .addContainerGap(18, Short.MAX_VALUE)
                                .addGroup(gl_panel_1_1.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(btnLimparA_1, GroupLayout.PREFERRED_SIZE, 383, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(gl_panel_1_1.createParallelGroup(Alignment.LEADING)
                                                .addComponent(textTimeB, GroupLayout.PREFERRED_SIZE, 383, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(table_2, GroupLayout.PREFERRED_SIZE, 383, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(btnAddTimeB, GroupLayout.PREFERRED_SIZE, 383, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(btnRemovTimeB, GroupLayout.PREFERRED_SIZE, 383, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        gl_panel_1_1.setVerticalGroup(
                gl_panel_1_1.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_1_1.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(textTimeB, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(table_2, GroupLayout.PREFERRED_SIZE, 298, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(btnAddTimeB, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(btnRemovTimeB, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(btnLimparA_1, GroupLayout.DEFAULT_SIZE, 10, Short.MAX_VALUE)
                                .addContainerGap())
        );
        panel_1_1.setLayout(gl_panel_1_1);

        JPanel panel_2 = new JPanel();
        panel_2.setBackground(new Color(0, 64, 0));
        contentPane.add(panel_2, BorderLayout.CENTER);

        JButton btnJogoRapido = new JButton("Jogo rápido");
        btnJogoRapido.setForeground(Color.WHITE);
        btnJogoRapido.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnJogoRapido.setBackground(new Color(0, 128, 0));

        JButton btnMarcarJogo = new JButton("Marcar jogo");
        btnMarcarJogo.setForeground(Color.WHITE);
        btnMarcarJogo.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnMarcarJogo.setBackground(new Color(0, 128, 0));

        JButton btnVerListaDe = new JButton("Ver Ranking");
        btnVerListaDe.setForeground(Color.WHITE);
        btnVerListaDe.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnVerListaDe.setBackground(new Color(0, 128, 0));

        JButton btnNovoJogador = new JButton("Novo jogador");
        btnNovoJogador.setForeground(Color.WHITE);
        btnNovoJogador.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnNovoJogador.setBackground(new Color(0, 128, 0));

        table_1 = new JTable();
        table_1.setBackground(new Color(0, 64, 0));
        table_1.setForeground(new Color(0, 64, 0));

        table_3 = new JTable();
        table_3.setForeground(new Color(255, 255, 255));
        table_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
        table_3.setBackground(new Color(0, 64, 0));

        String nomeGolBonito = lerNome();

        JButton btnAtualizar = new JButton("Atualizar Tabelas");
        btnAtualizar.setBackground(new Color(0, 64, 0));
        btnAtualizar.setForeground(new Color(255, 255, 255));
        JLabel lblNewLabel = new JLabel("Tabela dos Reis!");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 20));

        JPanel panel_3 = new JPanel();
        panel_3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panel_3.setBackground(new Color(0, 64, 0));
        JButton btnConfig = new JButton("☼");
        btnConfig.setForeground(new Color(255, 255, 255));
        btnConfig.setFont(new Font("MS Gothic", Font.BOLD, 38));
        btnConfig.setBackground(new Color(128, 0, 0));
        GroupLayout gl_panel_2 = new GroupLayout(panel_2);
        gl_panel_2.setHorizontalGroup(
                gl_panel_2.createParallelGroup(Alignment.TRAILING)
                        .addGroup(gl_panel_2.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(btnAtualizar, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(gl_panel_2.createSequentialGroup()
                                                .addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
                                                        .addComponent(btnVerListaDe, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
                                                        .addComponent(btnMarcarJogo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
                                                        .addComponent(btnJogoRapido, GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE))
                                                .addGap(10))
                                        .addGroup(gl_panel_2.createSequentialGroup()
                                                .addComponent(btnNovoJogador, GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
                                                .addContainerGap())
                                        .addGroup(gl_panel_2.createSequentialGroup()
                                                .addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
                                                .addContainerGap())
                                        .addGroup(gl_panel_2.createSequentialGroup()
                                                .addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
                                                        .addComponent(btnConfig)
                                                        .addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE))
                                                .addGap(18))))
        );
        gl_panel_2.setVerticalGroup(
                gl_panel_2.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_2.createSequentialGroup()
                                .addGap(42)
                                .addComponent(btnJogoRapido, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                                .addGap(33)
                                .addComponent(btnMarcarJogo, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                                .addGap(33)
                                .addComponent(btnVerListaDe, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                                .addGap(34)
                                .addComponent(btnNovoJogador, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(btnConfig, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
                                .addGap(127)
                                .addComponent(btnAtualizar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        JLabel lblGolGold = new JLabel(nomeGolBonito);
        lblGolGold.setHorizontalAlignment(SwingConstants.LEFT);
        lblGolGold.setForeground(Color.YELLOW);
        lblGolGold.setFont(new Font("Verdana", Font.BOLD, 20));

        JLabel lblGolDeOuro = new JLabel("Gol de ouro");
        lblGolDeOuro.setHorizontalAlignment(SwingConstants.LEFT);
        lblGolDeOuro.setForeground(Color.WHITE);
        lblGolDeOuro.setFont(new Font("Verdana", Font.BOLD, 20));

        JLabel lblGoleador = new JLabel("Goleador");
        lblGoleador.setHorizontalAlignment(SwingConstants.LEFT);
        lblGoleador.setForeground(Color.WHITE);
        lblGoleador.setFont(new Font("Verdana", Font.BOLD, 20));

        JLabel lblGoleadorNome = new JLabel("Nome");
        lblGoleadorNome.setHorizontalAlignment(SwingConstants.LEFT);
        lblGoleadorNome.setForeground(Color.YELLOW);
        lblGoleadorNome.setFont(new Font("Verdana", Font.BOLD, 20));
        GroupLayout gl_panel_3 = new GroupLayout(panel_3);
        gl_panel_3.setHorizontalGroup(
                gl_panel_3.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_3.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_panel_3.createParallelGroup(Alignment.TRAILING)
                                        .addGroup(Alignment.LEADING, gl_panel_3.createSequentialGroup()
                                                .addComponent(lblGoleadorNome, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                                                .addComponent(lblGoleador, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(gl_panel_3.createSequentialGroup()
                                                .addComponent(lblGolGold, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                                                .addComponent(lblGolDeOuro, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        gl_panel_3.setVerticalGroup(
                gl_panel_3.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_3.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblGolDeOuro, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblGolGold, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                                .addGap(18)
                                .addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblGoleador, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblGoleadorNome, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(12, Short.MAX_VALUE))
        );
        panel_3.setLayout(gl_panel_3);
        panel_2.setLayout(gl_panel_2);

        //ações dos botões
        btnAddTimeA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ir = false;
                AB = true;
                TelaTabela telaTabela = new TelaTabela(listaSemRepetidos, AB, btnAtualizar);
                telaTabela.setVisible(true);
            }
        });

        btnRemovTimeA.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                ir = false;
                List<User> removidos = FuncoesArquivo.removerUsuariosSelecionados(caminhoListaTimeA, tableModelA);
                FuncoesArquivo.escreverDadosNoTxt(tableModelA,"timeA.txt");
                btnAtualizar.doClick();
            }
        });
        btnLimparA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ir = false;
                //caminhoListaTimeA.clear();
                FuncoesArquivo.limparTabela("timeA.txt");
                FuncoesArquivo.limparTabela("partidaLadoA.txt");
                btnAtualizar.doClick();
            }
        });

        btnAddTimeB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ir = false;
                AB = false;
                TelaTabela telaTabela = new TelaTabela(listaSemRepetidos, AB, btnAtualizar);
                telaTabela.setVisible(true);
            }
        });
        btnRemovTimeB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ir = false;
                List<User> removidos = FuncoesArquivo.removerUsuariosSelecionados(caminhoListaTimeB, tableModelB);
                FuncoesArquivo.escreverDadosNoTxt(tableModelB,"timeB.txt");
                btnAtualizar.doClick();
            }
        });
        btnLimparA_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ir = false;
                //caminhoListaTimeA.clear();
                FuncoesArquivo.limparTabela("timeB.txt");
                FuncoesArquivo.limparTabela("partidaLadoB.txt");
                btnAtualizar.doClick();
            }
        });
        btnAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ir = true;
                EventQueue.invokeLater(new Runnable() {
                    public void run() {

                        try {
                            HomeAB novaJanela = new HomeAB();
                            novaJanela.setVisible(true);

                            dispose();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        btnJogoRapido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean acesso = FuncoesArquivo.repetidosAB(caminhoListaTimeA, caminhoListaTimeB);

                int numeroJogadores = caminhoListaTimeA.size() - caminhoListaTimeB.size();
                if (caminhoListaTimeA.size() < 6 || caminhoListaTimeB.size() < 6) {
                    JOptionPane.showMessageDialog(null, "Minimo 7 jogadores por time!", "Erro", JOptionPane.WARNING_MESSAGE);
                    return;
                } else if (caminhoListaTimeA.size() > 10 || caminhoListaTimeB.size() > 10) {
                    JOptionPane.showMessageDialog(null, "Máximo 10 jogadores por time!", "Limite jogadores atingido", JOptionPane.WARNING_MESSAGE);
                    return;
                } else if (numeroJogadores != -1 && numeroJogadores != 0 && numeroJogadores != 1){
                    JOptionPane.showMessageDialog(null, "Cada time deve ter numeros iguais de jogadores.\n (diferença de 1 jogador é permitido)", "Jogo justo", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                boolean acessoA = HomeFuncoes.verificarJogadores(caminhoListaTimeA, "Nota - Time A");

                acesso = HomeFuncoes.verificarJogadores(caminhoListaTimeB, "Nota - Time B");
                if(acessoA && acesso){
                    TelaJogo telaJogo = new TelaJogo(nomeTimeA, nomeTimeB);
                    telaJogo.setVisible(true);
                }
            }
        });btnMarcarJogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean acesso = FuncoesArquivo.repetidosAB(caminhoListaTimeA, caminhoListaTimeB);
                int numeroJogadores = caminhoListaTimeA.size() - caminhoListaTimeB.size();
                //comentario commit
                if(!ir){
                    JOptionPane.showMessageDialog(null, "Atualize as tabelas!", "Nota", JOptionPane.WARNING_MESSAGE);
                } else if (caminhoListaTimeA.size() < 6 || caminhoListaTimeB.size() < 6) {
                    JOptionPane.showMessageDialog(null, "Minimo 7 jogadores por time!", "Erro", JOptionPane.WARNING_MESSAGE);
                } else if (caminhoListaTimeA.size() > 10 || caminhoListaTimeB.size() > 10) {
                    JOptionPane.showMessageDialog(null, "Máximo 10 jogadores por time!", "Limite jogadores atingido", JOptionPane.WARNING_MESSAGE);
                } else if (numeroJogadores != -1 && numeroJogadores != 0 && numeroJogadores != 1){
                    System.out.println(numeroJogadores);
                    JOptionPane.showMessageDialog(null, "Cada time deve ter numeros iguais de jogadores.\n (diferença de 1 jogador é permitido)", "Jogo justo", JOptionPane.WARNING_MESSAGE);
                } else if(acesso){
                    JOptionPane.showMessageDialog(null, "Boa!", "Boa", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        btnNovoJogador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ir = false;
                TelaCadastro telaCadastro = new TelaCadastro();
                telaCadastro.setVisible(true);
            }
        });
        btnConfig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<User> lista = FuncoesArquivo.carregarJogadores("posicao.txt");
                ExcluirJogador excluirJogador = new ExcluirJogador(lista, false, btnAtualizar);
                excluirJogador.setVisible(true);
            }
        });

    }
    public static String lerNome() {
        String nomeDoArquivo = "golMaisBonito.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeDoArquivo))) {
            String nome = reader.readLine();
            return nome;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
