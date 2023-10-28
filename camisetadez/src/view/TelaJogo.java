package view;

import date.GolTrue;
import date.User;

import java.awt.*;

import  logic.FuncoesArquivo;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.LineBorder;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import logic.FuncaoPTelaJogo;
import javax.swing.table.DefaultTableModel;

public class TelaJogo extends JFrame {
    private DefaultTableModel tableModelA;
    private DefaultTableModel tableModelB;
    private JPanel contentPane;
    private JTable tableLadoA;
    private JTable tableLadoB;
    private JTextField textFieldTempo;
    private List<User> timeA = FuncoesArquivo.carregarGols("partidaLadoA.txt");
    private List<User> timeB = FuncoesArquivo.carregarGols("partidaLadoB.txt");
    private List<GolTrue> times = FuncaoPTelaJogo.lerVotos("ranking.txt");
    private boolean AB;
    private List<GolTrue> quemFezGols;
    private List<GolTrue> quemFezGols2;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaJogo frame = new TelaJogo("Teste", "teste");
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
    public TelaJogo(String nomeTimeA, String nomeTimeB) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1356, 781);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 64, 0));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(new Color(255, 255, 255), 6));
        panel.setBackground(new Color(0, 64, 100));
        contentPane.add(panel, BorderLayout.WEST);

        JLabel nomeTime1 = new JLabel(nomeTimeA);
        nomeTime1.setFont(new Font("Reem Kufi", Font.PLAIN, 30));
        nomeTime1.setForeground(new Color(255, 255, 255));
        nomeTime1.setHorizontalAlignment(SwingConstants.CENTER);

        JScrollPane scrollLadoA = new JScrollPane();

        JButton btn1 = new JButton("GOL!");
        btn1.setBackground(new Color(0, 0, 100));
        btn1.setForeground(new Color(255, 255, 255));
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addGap(46)
                                .addComponent(nomeTime1, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(44, Short.MAX_VALUE))
                        .addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
                                .addContainerGap(25, Short.MAX_VALUE)
                                .addComponent(scrollLadoA, GroupLayout.PREFERRED_SIZE, 322, GroupLayout.PREFERRED_SIZE)
                                .addGap(23))
                        .addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
                                .addContainerGap(142, Short.MAX_VALUE)
                                .addComponent(btn1, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
                                .addGap(129))
        );
        gl_panel.setVerticalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addGap(89)
                                .addComponent(nomeTime1, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
                                .addGap(44)
                                .addComponent(scrollLadoA, GroupLayout.PREFERRED_SIZE, 467, GroupLayout.PREFERRED_SIZE)
                                .addGap(10)
                                .addComponent(btn1, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                                .addGap(14))
        );
        //table model lado A
        tableModelA = new DefaultTableModel(new Object[]{"Nome", "Gols"}, 0) {
            @Override
            public Class<?> getColumnClass(int indiceColuna) {
                if (indiceColuna == 1) {
                    return Integer.class; // substituir por spinner
                } else { //super se refere a classe principal (classe pai)
                    return super.getColumnClass(indiceColuna);
                }
            }
        };
        //colocar o modelo na tabela
        tableLadoA = new JTable(tableModelA);
        tableLadoA.getColumnModel().getColumn(0).setPreferredWidth(200);
        tableLadoA.getColumnModel().getColumn(1).setPreferredWidth(5);

        //tableLadoA.getColumnModel().getColumn(2).setCellRenderer(new SpinnerRenderer());
        //construir a tabela
        for (User usuario : timeA) {
            tableModelA.addRow(new Object[]{ usuario.getNumero()+ "    -    " + usuario.getNome(), usuario.getGol()});

        }

        tableLadoA.setBackground(Color.WHITE);
        tableLadoA.setForeground(Color.BLACK);
        tableLadoA.setFont(new Font("Arial", Font.PLAIN, 15));


        scrollLadoA.setViewportView(tableLadoA);
        panel.setLayout(gl_panel);

        JPanel panel2 = new JPanel();
        panel2.setBorder(new LineBorder(new Color(255, 255, 255), 6));
        panel2.setBackground(new Color(128, 0, 0));
        contentPane.add(panel2, BorderLayout.EAST);

        JLabel nomeTime2 = new JLabel(nomeTimeB);
        nomeTime2.setHorizontalAlignment(SwingConstants.CENTER);
        nomeTime2.setForeground(Color.WHITE);
        nomeTime2.setFont(new Font("Reem Kufi", Font.PLAIN, 30));

        JScrollPane scrollLadoB = new JScrollPane();

        JButton btn2 = new JButton("GOL!");
        btn2.setForeground(Color.WHITE);
        btn2.setBackground(new Color(100, 0, 0));
        GroupLayout gl_panel2 = new GroupLayout(panel2);
        gl_panel2.setHorizontalGroup(
                gl_panel2.createParallelGroup(Alignment.LEADING)
                        .addGap(0, 382, Short.MAX_VALUE)
                        .addGroup(gl_panel2.createSequentialGroup()
                                .addGap(46)
                                .addComponent(nomeTime2, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(44, Short.MAX_VALUE))
                        .addGroup(Alignment.TRAILING, gl_panel2.createSequentialGroup()
                                .addContainerGap(25, Short.MAX_VALUE)
                                .addComponent(scrollLadoB, GroupLayout.PREFERRED_SIZE, 322, GroupLayout.PREFERRED_SIZE)
                                .addGap(23))
                        .addGroup(Alignment.TRAILING, gl_panel2.createSequentialGroup()
                                .addContainerGap(142, Short.MAX_VALUE)
                                .addComponent(btn2, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
                                .addGap(129))
        );
        gl_panel2.setVerticalGroup(
                gl_panel2.createParallelGroup(Alignment.LEADING)
                        .addGap(0, 734, Short.MAX_VALUE)
                        .addGroup(gl_panel2.createSequentialGroup()
                                .addGap(89)
                                .addComponent(nomeTime2, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
                                .addGap(44)
                                .addComponent(scrollLadoB, GroupLayout.PREFERRED_SIZE, 467, GroupLayout.PREFERRED_SIZE)
                                .addGap(10)
                                .addComponent(btn2, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                                .addGap(14))
        );
        //table model lado A
        tableModelB = new DefaultTableModel(new Object[]{"Nome", "Gols"}, 0) {
            @Override
            public Class<?> getColumnClass(int indiceColuna) {
                if (indiceColuna == 1) {
                    return Integer.class; // substituir por spinner
                } else { //super se refere a classe principal (classe pai)
                    return super.getColumnClass(indiceColuna);
                }
            }
        };

        tableLadoB = new JTable(tableModelB);
        scrollLadoB.setViewportView(tableLadoB);
        panel2.setLayout(gl_panel2);
        tableLadoB.getColumnModel().getColumn(0).setPreferredWidth(200);
        tableLadoB.getColumnModel().getColumn(1).setPreferredWidth(5);

        //tableLadoA.getColumnModel().getColumn(2).setCellRenderer(new SpinnerRenderer());
        //construir a tabela
        for (User usuario : timeB) {
            tableModelB.addRow(new Object[]{ usuario.getNumero()+ "    -    " + usuario.getNome(), usuario.getGol()});
        }

        tableLadoB.setBackground(Color.WHITE);
        tableLadoB.setForeground(Color.BLACK);
        tableLadoB.setFont(new Font("Arial", Font.PLAIN, 15));

        JPanel panelCentro = new JPanel();
        panelCentro.setBorder(new LineBorder(new Color(255, 255, 255), 6));
        panelCentro.setBackground(new Color(0, 64, 0));
        contentPane.add(panelCentro, BorderLayout.NORTH);

        JPanel panelMeio = new JPanel();
        panelMeio.setBackground(new Color(0, 64, 0));
        contentPane.add(panelMeio, BorderLayout.CENTER);

        JPanel panel_2 = new JPanel();
        panel_2.setBackground(new Color(0, 120, 185));

        JLabel lblNewLabel = new JLabel("X");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 99));

        JLabel lblNewLabel_1 = new JLabel("Tempo de Jogo \r\n(Minutos)");
        lblNewLabel_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));

        textFieldTempo = new JTextField();
        textFieldTempo.setFont(new Font("Tahoma", Font.PLAIN, 17));
        textFieldTempo.setColumns(10);

        JButton btnTimerOk = new JButton("Iniciar Jogo");

        //logica somatoria gols
        int totalGolsA = 0;
        for (int i = 0; i < tableModelA.getRowCount(); i++) {
            totalGolsA += (int) tableLadoA.getValueAt(i, 1);
        }

        JLabel lblGolsTimeA = new JLabel(""+totalGolsA);
        lblGolsTimeA.setFont(new Font("Rubik", Font.PLAIN, 99));
        lblGolsTimeA.setHorizontalAlignment(SwingConstants.CENTER);
        lblGolsTimeA.setForeground(new Color(255, 255, 255));

        //logica somatoria gols
        int totalGolsB = 0;
        for (int i = 0; i < tableModelB.getRowCount(); i++) {
            totalGolsB += (int) tableLadoB.getValueAt(i, 1);
        }

        JLabel lblGolsTimeB = new JLabel("" + totalGolsB);
        lblGolsTimeB.setHorizontalAlignment(SwingConstants.CENTER);
        lblGolsTimeB.setForeground(Color.WHITE);
        lblGolsTimeB.setFont(new Font("Rubik", Font.PLAIN, 99));

        JButton btnFinalizarJogo = new JButton("Finalizar jogo");

        JButton btnZerarTempo = new JButton("■");
        btnZerarTempo.setFont(new Font("Tahoma", Font.PLAIN, 16));

        JButton btnLl = new JButton("► / ll");
        btnLl.setFont(new Font("Tahoma", Font.PLAIN, 16));
        GroupLayout gl_panelMeio = new GroupLayout(panelMeio);
        gl_panelMeio.setHorizontalGroup(
                gl_panelMeio.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panelMeio.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_panelMeio.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(panel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
                                        .addGroup(gl_panelMeio.createSequentialGroup()
                                                .addComponent(lblNewLabel_1)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(textFieldTempo, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18)
                                                .addComponent(btnTimerOk, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)))
                                .addContainerGap())
                        .addGroup(gl_panelMeio.createSequentialGroup()
                                .addGap(196)
                                .addComponent(btnFinalizarJogo, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                                .addGap(195))
                        .addGroup(gl_panelMeio.createSequentialGroup()
                                .addGap(36)
                                .addComponent(lblGolsTimeA, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                                .addGap(65)
                                .addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                                .addGap(68)
                                .addComponent(lblGolsTimeB, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                                .addGap(33))
                        .addGroup(gl_panelMeio.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnZerarTempo)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(btnLl)
                                .addContainerGap(420, Short.MAX_VALUE))
        );
        gl_panelMeio.setVerticalGroup(
                gl_panelMeio.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panelMeio.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_panelMeio.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_panelMeio.createParallelGroup(Alignment.BASELINE)
                                                .addComponent(lblNewLabel_1)
                                                .addComponent(textFieldTempo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(btnTimerOk))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(gl_panelMeio.createParallelGroup(Alignment.LEADING)
                                        .addComponent(btnZerarTempo)
                                        .addComponent(btnLl, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
                                .addGap(91)
                                .addGroup(gl_panelMeio.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblGolsTimeA)
                                        .addComponent(lblGolsTimeB, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.RELATED, 225, Short.MAX_VALUE)
                                .addComponent(btnFinalizarJogo)
                                .addContainerGap())
        );
        panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel lblTempo = new JLabel("00:00");
        lblTempo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTempo.setForeground(Color.WHITE);
        lblTempo.setFont(new Font("SansSerif", Font.PLAIN, 99));
        panel_2.add(lblTempo);
        panelMeio.setLayout(gl_panelMeio);

        JButton btnAtualizar = new JButton("Atualizar Tabelas");
        btnAtualizar.setBackground(new Color(0, 64, 0));
        btnAtualizar.setForeground(new Color(255, 255, 255));

        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AB = true;
                TelaGol telaGol = new TelaGol(btnAtualizar,AB);
                telaGol.setVisible(true);
            }
        });btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AB = false;
                TelaGol telaGol = new TelaGol(btnAtualizar, AB);
                telaGol.setVisible(true);
            }
        });
        btnAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    public void run() {

                        try {
                            TelaJogo novaJanela = new TelaJogo("Time1", "Time2");
                            novaJanela.setVisible(true);
                            dispose();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

        btnFinalizarJogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //colocando tudo em uma lista só
                quemFezGols = FuncaoPTelaJogo.obterUsuariosComGols(timeA);
                quemFezGols2 = FuncaoPTelaJogo.obterUsuariosComGols(timeB);
                for(int i=0; i<quemFezGols2.size(); i++){
                    quemFezGols.add(quemFezGols2.get(i));
                }
                List<User> todos = new ArrayList<>();
                for(int i=0; i<timeA.size(); i++){
                    todos.add(timeA.get(i));
                }
                for(int i=0; i<timeB.size(); i++){
                    todos.add(timeB.get(i));
                }
                salvarNomeVoto(quemFezGols, "votacao.txt", false);
                //ranking
                List<GolTrue> ranking = FuncaoPTelaJogo.lerVotos("ranking.txt");
                List<GolTrue> novoRanking = FuncaoPTelaJogo.atualizarRanking(quemFezGols, ranking);
                salvarNomeVoto(novoRanking, "ranking.txt", false);
                String nomeMaisGols = FuncaoPTelaJogo.jogadorComMaisVotos(novoRanking);
                salvarNome(nomeMaisGols, "maisGols.txt");
                int contador = 0;
                Votacao votacao = new Votacao(todos, contador);
                votacao.setVisible(true);
                dispose();

            }
        });
    }
    public void salvarNomeVoto(List<GolTrue> listaSelecionados, String arquivo, boolean aprender) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(arquivo, aprender))) {
            for (int i = 0; i < listaSelecionados.size(); i++) {
                GolTrue user = listaSelecionados.get(i);
                writer.println(user.getNome() + "," + user.getGol());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void salvarNome(String nome, String arquivo) {

        try (PrintWriter writer = new PrintWriter(new FileWriter(arquivo, false))) {
            writer.println(nome);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
