package view;

import date.GolTrue;
import date.User;
import logic.FuncaoPTelaJogo;
import logic.FuncoesArquivo;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.border.LineBorder;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Votacao extends JFrame {
    //int contador = 0;
    private JPanel contentPane;
    private List<GolTrue> galeraQFezGol = FuncaoPTelaJogo.lerVotos("C:\\Users\\Matheus\\Desktop\\PI2\\Camiseta10\\camisetadez\\src\\date\\tabelasTemporarias\\votacao.txt");

    /**
     * Launch the application.
     */
   /* public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Votacao frame = new Votacao();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }*/

    /**
     * Create the frame.
     */
    public Votacao(/*List<GolTrue> galeraQFezGol, */List<User> quemJogou, int contador) {

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 684, 617);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 64, 0));
        contentPane.setForeground(new Color(0, 64, 0));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(new Color(255, 255, 255), 6));
        panel.setBackground(new Color(0, 64, 0));
        panel.setForeground(new Color(0, 64, 0));
        panel.setBounds(10, 10, 650, 101);
        contentPane.add(panel);

        JLabel lblNewLabel = new JLabel("Votação");
        panel.add(lblNewLabel);
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setBackground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Rubik", Font.BOLD, 49));

        JLabel lblFulano = new JLabel(quemJogou.get(contador).getNome() + ",");
        lblFulano.setForeground(Color.WHITE);
        lblFulano.setFont(new Font("Source Sans Pro", Font.PLAIN, 35));
        lblFulano.setBackground(Color.WHITE);
        lblFulano.setBounds(261, 121, 299, 50);
        contentPane.add(lblFulano);

        JLabel lblEscolhaOJogador = new JLabel("escolha o jogador que em sua opinião, fez o gol mais bonito");
        lblEscolhaOJogador.setForeground(Color.WHITE);
        lblEscolhaOJogador.setFont(new Font("Source Sans Pro", Font.PLAIN, 24));
        lblEscolhaOJogador.setBackground(Color.WHITE);
        lblEscolhaOJogador.setBounds(28, 170, 613, 50);
        contentPane.add(lblEscolhaOJogador);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new LineBorder(new Color(255, 255, 255), 6, true));
        panel_1.setBackground(new Color(0, 64, 0));
        panel_1.setBounds(35, 230, 599, 260);
        contentPane.add(panel_1);

        JComboBox<String> comboBox = new JComboBox<>();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

        for (GolTrue golTrue : galeraQFezGol) {
            model.addElement(golTrue.getNome());
        }

        comboBox.setModel(model);

        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));

        JButton btnBranco = new JButton("BRANCO");
        btnBranco.setBackground(new Color(255, 255, 255));
        btnBranco.setFont(new Font("Tahoma", Font.PLAIN, 20));

        JButton btnConfirmar = new JButton("CONFIRMAR");
        btnConfirmar.setForeground(new Color(255, 255, 255));
        btnConfirmar.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnConfirmar.setBackground(new Color(0, 128, 0));
        GroupLayout gl_panel_1 = new GroupLayout(panel_1);
        gl_panel_1.setHorizontalGroup(
                gl_panel_1.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_1.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
                                        .addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
                                                .addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 354, GroupLayout.PREFERRED_SIZE)
                                                .addGap(126))
                                        .addGroup(gl_panel_1.createSequentialGroup()
                                                .addComponent(btnBranco, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                                                .addComponent(btnConfirmar, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap())))
        );
        gl_panel_1.setVerticalGroup(
                gl_panel_1.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_1.createSequentialGroup()
                                .addGap(19)
                                .addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
                                .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
                                        .addComponent(btnBranco, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnConfirmar, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );
        panel_1.setLayout(gl_panel_1);

        JButton btnCancelar = new JButton("CANCELAR VOTAÇÃO");
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnCancelar.setBackground(new Color(128, 0, 0));
        btnCancelar.setBounds(207, 500, 256, 54);
        contentPane.add(btnCancelar);

        //tabela de votação:
        String nomeSelecionado;
        //List<GolTrue> votacao = votos(galeraQFezGol);
        List<GolTrue> votacao = FuncaoPTelaJogo.lerVotos("C:\\Users\\Matheus\\Desktop\\PI2\\Camiseta10\\camisetadez\\src\\date\\tabelasTemporarias\\votacao.txt");

        salvarNomeVoto(votacao,"C:\\Users\\Matheus\\Desktop\\PI2\\Camiseta10\\camisetadez\\src\\date\\tabelasTemporarias\\votacao.txt",false);
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeSelecionado = (String) comboBox.getSelectedItem();
                /*if (nomeSelecionado != null) {
                    System.out.println("Item selecionado: " + nomeSelecionado);
                    // Faça o que você precisa com o nome selecionado
                }*/
            }
        });
        btnBranco.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int finalContador = contador;
                int contador = finalContador;
                contador ++;

                Votacao novaJanela = new Votacao(/*galeraQFezGol,*/quemJogou, contador);
                novaJanela.setVisible(true);
                dispose();
            }
        });
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int finalContador = contador;
                EventQueue.invokeLater(new Runnable() {
                    public void run() {

                        try {
                            int contador = finalContador;
                            if(contador == (quemJogou.size() -1)){
                                contador = 0;
                                String nomeVencedor = FuncaoPTelaJogo.jogadorComMaisVotos(votacao);
                                salvarNome(nomeVencedor);
                                JOptionPane.showMessageDialog(null, "O eleito com gol mais bonito é\n"+ nomeVencedor, "PARABÉNS!", JOptionPane.INFORMATION_MESSAGE);
                                dispose();

                            }else{
                                contador ++;

                                for (GolTrue jogador:votacao) {
                                    if(jogador.getNome().equals(comboBox.getSelectedItem())){
                                        int votos = jogador.getGol();
                                        jogador.setGol(votos + 1);
                                        salvarNomeVoto(votacao,"C:\\Users\\Matheus\\Desktop\\PI2\\Camiseta10\\camisetadez\\src\\date\\tabelasTemporarias\\votacao.txt",false);

                                    }
                                }

                                Votacao novaJanela = new Votacao(/*galeraQFezGol,*/quemJogou, contador);
                                novaJanela.setVisible(true);
                                dispose();

                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
    public static List<GolTrue> votos(List<GolTrue> quemFezGol){
      List<GolTrue> galera = new ArrayList<>();
      for(int i=0; i<quemFezGol.size(); i++){
          quemFezGol.get(i).setGol(0);
          galera.add(quemFezGol.get(i));

         /* System.out.print(galera.get(i).getNome());
          System.out.println(galera.get(i).getGol());*/
      }
        return galera;
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
    public void salvarNome(String nome) {
        String arquivo = "C:\\Users\\Matheus\\Desktop\\PI2\\Camiseta10\\camisetadez\\src\\date\\tabelasTemporarias\\golMaisBonito.txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(arquivo, false))) {
                writer.println(nome);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
