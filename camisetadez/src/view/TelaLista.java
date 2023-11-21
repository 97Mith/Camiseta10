package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import date.GolTrue;
// Classe que representa a tela com a JTable
public class TelaLista extends JFrame {

    private JTable jTable;

    // Construtor que recebe a lista a ser exibida
    public TelaLista(List<GolTrue> listaJogadores) {
        // Configurações da janela
        setTitle("Ranking de Jogadores");

        setSize(400, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        // Criação do modelo da tabela
        DefaultTableModel model = new DefaultTableModel();

        // Adiciona as colunas ao modelo
        model.addColumn("Nome");
        model.addColumn("Gols");

        // Adiciona os dados da lista à tabela
        for (GolTrue jogador : listaJogadores) {
            model.addRow(new Object[]{jogador.getNome(), jogador.getGol()});
        }

        // Cria a tabela com o modelo
        jTable = new JTable(model);
        jTable.getColumnModel().getColumn(0).setPreferredWidth(505);
        jTable.setEnabled(false);
        jTable.setFont(new Font("Tahoma", Font.PLAIN, 15));
        // Adiciona a tabela a um painel de rolagem
        JScrollPane scrollPane = new JScrollPane(jTable);

        // Adiciona o painel de rolagem à tela
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Exibe a tela
        setVisible(true);
    }

    public static void main(String[] args) {
        // Exemplo de utilização
        List<GolTrue> listaJogadores = List.of(
                new GolTrue("Jogador1", 10),
                new GolTrue("Jogador2", 8),
                new GolTrue("Jogador3", 12)
        );

        // Cria a tela e passa a lista para ser exibida
        SwingUtilities.invokeLater(() -> new TelaLista(listaJogadores));
    }
}
