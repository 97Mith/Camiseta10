package view;

import date.User;
import logic.FuncoesArquivo;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

//import static view.HomeAB.AB;


public class TelaGol extends JFrame {
    private JTable tabela;
    private DefaultTableModel tableModel;
    private List<User> listaUsuarios;
    private List<User> listaUsuarios2;
    private List<User> listaSelecionados;
    public String caminhoGolsA = "C:\\Users\\Matheus\\Desktop\\PI2\\Camiseta10\\camisetadez\\src\\date\\tabelasTemporarias\\partidaLadoA.txt";
    public String caminhoGolsB = "C:\\Users\\Matheus\\Desktop\\PI2\\Camiseta10\\camisetadez\\src\\date\\tabelasTemporarias\\partidaLadoB.txt";


    public TelaGol(JButton botaoAtualizar, boolean AB) {
        getContentPane().setBackground(new Color(210, 210, 0));



        listaUsuarios = FuncoesArquivo.carregarGols(caminhoGolsA);
        listaUsuarios2 = FuncoesArquivo.carregarGols(caminhoGolsB);
        this.listaSelecionados = new ArrayList<>();
        //this.listaSelecionados = selecionados;
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setTitle("GOLLL");
        setBounds(100, 100, 424, 523);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel_2 = new JPanel();
        panel_2.setBackground(new Color(0, 120, 185));

        JLabel lblTempo = new JLabel("GOOOOLL");
        lblTempo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTempo.setForeground(Color.WHITE);
        lblTempo.setFont(new Font("SansSerif", Font.PLAIN, 99));
        panel_2.add(lblTempo);
        //definir a tablemodel e o molde que ela terá
        tableModel = new DefaultTableModel(new Object[]{"", "Nome"}, 0) {
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


        //construção da tabela adicionando cada item de User na Row da tableModel
        if(AB){
            for (User usuario : listaUsuarios) {
                tableModel.addRow(new Object[]{false, usuario.getNome()});
            }
        }else{
            for (User usuario : listaUsuarios2) {
                tableModel.addRow(new Object[]{false, usuario.getNome()});
            }
        }


        JScrollPane scrollPane = new JScrollPane(tabela);

        JButton btnOk = new JButton("Ok");
        btnOk.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnOk.setForeground(new Color(255, 255, 255));
        btnOk.setBackground(new Color(0, 64, 0));
        btnOk.setBounds(103, 25, 203, 43);

        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //bloco if para escrever temporariamente o gol em um txt
                if(AB){
                    moverSelecionados(); //função que adiciona um gol
                    FuncoesArquivo.limparTabela(caminhoGolsA);
                    salvarNomeGol(listaUsuarios, caminhoGolsA);

                }else{
                    moverSelecionados2();
                    FuncoesArquivo.limparTabela(caminhoGolsB);
                    salvarNomeGol(listaUsuarios2, caminhoGolsB);
                }

                botaoAtualizar.doClick();
                dispose();

            }

        });

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnCancelar.setBackground(new Color(128, 0, 0));

        JLabel lblNewLabel = new JLabel("GOOOLLL!");
        lblNewLabel.setFont(new Font("Segoe UI Black", Font.PLAIN, 33));
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(btnOk, GroupLayout.PREFERRED_SIZE, 410, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 410, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 410, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(117)
                                                .addComponent(lblNewLabel)))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 293, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(btnOk)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(181, Short.MAX_VALUE))
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


    private void moverSelecionados(/*boolean AB*/) {
        //contador recebe a quantidade de row da tabela
        int rowContador = tabela.getRowCount();


        for (int i = 0; i < rowContador; i++) {
            //cria um boolean na coluna 0 onde será exibida a checkbox
            boolean isChecked = (boolean) tabela.getValueAt(i, 0);
            if (isChecked) {
                //pega o nome do usuário
                String nome = (String) tabela.getValueAt(i, 1);

                // adiciona gol

                for (User usuario : listaUsuarios) {
                    if (usuario.getNome().equals(nome)) {
                        int gols = usuario.getGol();
                        gols += 1;
                        usuario.setId(gols);
                        break;
                    }
                }

            }
        }
    }
    public static void main(String[] args) {
        // Exemplo de uso:
        List<User> usuarios = new ArrayList<>();
        ArrayList<User> selecionados = new ArrayList<>();
        //FuncoesArquivo.carregarUsuarios("C:\\Users\\Matheus\\Desktop\\PI2\\Camiseta10\\camisetadez\\src\\date\\posicao.txt", usuarios);

        /*TelaTabela tela = new TelaTabela(usuarios, true);
        tela.setVisible(true);*/


       /* for(User jogador : usuarios){
            System.out.println(jogador.getNome());
        }*/
    }
    public void salvarNomeGol(List<User> listaSelecionados, String arquivo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(arquivo, true))) {
            for (int i = 0; i < listaSelecionados.size(); i++) {
                User user = listaSelecionados.get(i);
                writer.println(user.getNome() + "," + user.getNumero() + "," + user.getGol());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void moverSelecionados2(/*boolean AB*/) {
        //contador recebe a quantidade de row da tabela
        int rowContador = tabela.getRowCount();


        for (int i = 0; i < rowContador; i++) {
            //cria um boolean na coluna 0 onde será exibida a checkbox
            boolean isChecked = (boolean) tabela.getValueAt(i, 0);
            if (isChecked) {
                //pega o nome do usuário
                String nome = (String) tabela.getValueAt(i, 1);

                // adiciona gol

                for (User usuario : listaUsuarios2) {
                    if (usuario.getNome().equals(nome)) {
                        int gols = usuario.getGol();
                        gols += 1;
                        usuario.setId(gols);
                        break;
                    }
                }

            }
        }
    }
}
