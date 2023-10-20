package view;

import date.GolTrue;
import date.User;
import date.UserAttribute;
import logic.FuncoesArquivo;
import logic.HomeFuncoes;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static view.HomeAB.AB;
import static view.HomeAB.main;


public class ExcluirJogador extends JFrame {
    private JTable tabela;
    private DefaultTableModel tableModel;
    private List<User> listaUsuarios;
    private List<User> listaSelecionados;
    private List<User> selecionados;


    public List<User> getSelecionados() {
        return selecionados;
    }
    public String caminhoArquivoA = "timeA.txt";
    public String caminhoArquivoB = "timeB.txt";
    public String caminhoGolsA = "partidaLadoA.txt";
    public String caminhoGolsB = "partidaLadoB.txt";
    public ExcluirJogador(List<User> usuarios, boolean AB, JButton botaoAtualizar) {

        if(AB){
            JOptionPane.showMessageDialog(null, "Para acessar as configurações\nnenhum jogador deve estar adicionado", "Erro", JOptionPane.WARNING_MESSAGE);
        }
        this.listaUsuarios = usuarios;
        this.listaSelecionados = new ArrayList<>();
        //this.listaSelecionados = selecionados;
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setTitle("Tabela de jogadores");
        setBounds(100, 100, 424, 616);
        setLocationRelativeTo(null);
        setResizable(false);

        //definir a tablemodel e o molde que ela terá
        tableModel = new DefaultTableModel(new Object[]{"", "Nome", "N", "Posição"}, 0) {
            @Override
            public Class<?> getColumnClass(int indiceColuna) {
                if (indiceColuna == 0) {
                    return Boolean.class; // boolean como checkboxes
                } else { //super se refere a classe principal (classe pai)
                    return super.getColumnClass(indiceColuna);
                }
            }
        };

        tabela = new JTable(tableModel);
        //tamanho das colunas
        tabela.getColumnModel().getColumn(0).setPreferredWidth(5);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(190);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(10);
        //**   (procurar um meio de centralizar a coluna 2)
        tabela.getColumnModel().getColumn(3).setPreferredWidth(145);

        //construção da tabela adicionando cada item de User na Row da tableModel
        for (User usuario : listaUsuarios) {
            tableModel.addRow(new Object[]{false, usuario.getNome(), usuario.getNumero(), usuario.getPosicao()});
        }

        JScrollPane scrollPane = new JScrollPane(tabela);

        JButton btnOk = new JButton("Excluir");
        btnOk.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnOk.setForeground(new Color(255, 255, 255));
        btnOk.setBackground(new Color(128, 0, 0));
        btnOk.setBounds(103, 449, 203, 43);

        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //lista jogadores
                List<User> naoExcluidos = excluirSelecionados();

                //lista ranking
                List<UserAttribute> ranking = HomeFuncoes.lerNomeEAtributo("ranking.txt");
                List<UserAttribute> rankingNaoExcluidos = excluirAtributos(ranking);
                //lista com senhas
                List<UserAttribute> senhas = HomeFuncoes.lerNomeEAtributo("jogadores.txt");
                List<UserAttribute> senhasNaoExcluidas = excluirAtributos(senhas);


                //salvar a lista dos não excluidos nos respectivos arquivos txt
                salvarNumeroPosicao(naoExcluidos,"posicao.txt");
                salvarAtributos(rankingNaoExcluidos, "ranking.txt");
                salvarAtributos(senhasNaoExcluidas, "jogadores.txt");

                botaoAtualizar.doClick();
                dispose();

            }

        });

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnCancelar.setBackground(new Color(0, 64, 0));
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 410, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnOk, GroupLayout.PREFERRED_SIZE, 410, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 410, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 513, GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnOk)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        getContentPane().setLayout(groupLayout);
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // telaTimeA.dispose();
                dispose();
               // telaTimeA.setVisible(true);
            }
        });

    }

    private List<User> excluirSelecionados() {
        List<User> usuariosNaoSelecionados = new ArrayList<>();

        int rowContador = tabela.getRowCount();

        for (int i = 0; i < rowContador; i++) {
            boolean isChecked = (boolean) tabela.getValueAt(i, 0);
            if (!isChecked) {
                String nome = (String) tabela.getValueAt(i, 1);

                for (User usuario : listaUsuarios) {
                    if (usuario.getNome().equals(nome)) {
                        usuariosNaoSelecionados.add(usuario);
                        break;
                    }
                }
            }
        }

        return usuariosNaoSelecionados;
    }private List<UserAttribute> excluirAtributos(List<UserAttribute> listaAtributos) {
        List<UserAttribute> usuariosNaoSelecionados = new ArrayList<>();

        int rowContador = tabela.getRowCount();

        for (int i = 0; i < rowContador; i++) {
            boolean isChecked = (boolean) tabela.getValueAt(i, 0);
            if (!isChecked) {
                String nome = (String) tabela.getValueAt(i, 1);

                for (UserAttribute usuario : listaAtributos) {
                    if (usuario.getNome().equals(nome)) {
                        usuariosNaoSelecionados.add(usuario);
                        break;
                    }
                }
            }
        }

        return usuariosNaoSelecionados;
    }
    public void salvarNumeroPosicao(List<User> listaSelecionados, String nomeArquivo){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (User jogador : listaSelecionados) {
                String linha = jogador.getNome() + "," + jogador.getNumero() + "," + jogador.getPosicao();
                writer.write(linha);
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro ao escrever no arquivo: " + nomeArquivo);
        }
    }
    public void salvarAtributos(List<UserAttribute> lista, String nomeArquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (UserAttribute userAttribute : lista) {
                String linha = userAttribute.getNome() + "," + userAttribute.getAtributo();
                writer.write(linha);
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*public static void main(String[] args) {
        List<User> lista = FuncoesArquivo.carregarJogadores("posicao.txt");
        ExcluirJogador excluirJogador = new ExcluirJogador(lista, false);
        excluirJogador.setVisible(true);
    }*/

}
