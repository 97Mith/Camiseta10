package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import date.User;
import logic.FuncoesArquivo;

import static view.HomeAB.AB;


public class TelaTabela extends JFrame {
    private JTable tabela;
    private DefaultTableModel tableModel;
    private List<User> listaUsuarios;
    private List<User> listaSelecionados;
    private List<User> selecionados;
    private List<User> selectA = FuncoesArquivo.carregarJogadores("timeA.txt");
    private List<User> selectB = FuncoesArquivo.carregarJogadores("timeB.txt");

    public List<User> getSelecionados() {
        return selecionados;
    }
    public String caminhoArquivoA = "timeA.txt";
    public String caminhoArquivoB = "timeB.txt";
    public String caminhoGolsA = "partidaLadoA.txt";
    public String caminhoGolsB = "partidaLadoB.txt";
    public TelaTabela(List<User> usuarios, boolean AB, JButton botaoAtualizar) {

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

        JButton btnOk = new JButton("Ok");
        btnOk.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnOk.setForeground(new Color(255, 255, 255));
        btnOk.setBackground(new Color(0, 64, 0));
        btnOk.setBounds(103, 449, 203, 43);

        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                moverSelecionados();
                botaoAtualizar.doClick();
                dispose();

            }

        });

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnCancelar.setBackground(new Color(128, 0, 0));
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


    private void moverSelecionados() {
        //contador recebe a quantidade de row da tabela
        int rowContador = tabela.getRowCount();


        for (int i = 0; i < rowContador; i++) {
            //cria um boolean na coluna 0 onde será exibida a checkbox
            boolean isChecked = (boolean) tabela.getValueAt(i, 0);
            if (isChecked) {
                //pega o nome do usuário
                String nome = (String) tabela.getValueAt(i, 1);

                // Remove o usuário da lista de usuários
                for (User usuario : listaUsuarios) {
                    if (usuario.getNome().equals(nome)) {
                        listaSelecionados.add(usuario);

                        break;
                    }
                }

                //remove a linha da tabela
                tableModel.removeRow(i);
                i--; //diminui o índice, pois a linha foi removida
                rowContador--; // Atualiza o número de linhas na tabela
            }
        }

        // ** fazer o resto da lógica a partir dessa interação com "ok"

        if(AB){
            for (User usuario : listaSelecionados) {
                salvarNumeroPosicao(usuario, caminhoArquivoA);
                salvarNomeGol(usuario, caminhoGolsA);
            }
        }else{
            for (User usuario : listaSelecionados) {
                salvarNumeroPosicao(usuario, caminhoArquivoB);
                salvarNomeGol(usuario, caminhoGolsB);
            }
        }

    }
    public static void main(String[] args) {
        // Exemplo de uso:
        List<User> usuarios = new ArrayList<>();
        ArrayList<User> selecionados = new ArrayList<>();
        FuncoesArquivo.carregarUsuarios("posicao.txt", usuarios);

        /*TelaTabela tela = new TelaTabela(usuarios, true);
        tela.setVisible(true);*/


       /* for(User jogador : usuarios){
            System.out.println(jogador.getNome());
        }*/
    }
    public void salvarNumeroPosicao(User listaSelecionados, String arquivo){
        try (PrintWriter writer = new PrintWriter(new FileWriter(arquivo, true))) {
            writer.println(listaSelecionados.getNome() + "," + listaSelecionados.getNumero() + "," + listaSelecionados.getPosicao());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }public void salvarNomeGol(User listaSelecionados, String arquivo){
        try (PrintWriter writer = new PrintWriter(new FileWriter(arquivo, true))) {
            writer.println(listaSelecionados.getNome() + "," + listaSelecionados.getNumero() + "," + listaSelecionados.getGol());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
