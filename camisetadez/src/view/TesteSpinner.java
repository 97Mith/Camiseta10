/*package view;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import date.User;
public class PlayerTable extends JPanel {
    private List<User> userList;
    private JTable table;

    public PlayerTable(List<User> userList) {
        this.userList = userList;
        initializeComponents();
    }

    private void initializeComponents() {
        setLayout(new BorderLayout());

        // Crie o modelo de tabela personalizado
        PlayerTableModel model = new PlayerTableModel(userList);

        // Crie a tabela com o modelo personalizado
        table = new JTable(model);

        // Crie uma coluna personalizada para o JSpinner
        TableColumn spinnerColumn = table.getColumnModel().getColumn(1);
        spinnerColumn.setCellRenderer((TableCellRenderer) new SpinnerRenderer());
        spinnerColumn.setCellEditor(new SpinnerEditor());

        // Adicione a tabela a um JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);
    }

    // Modelo de tabela personalizado
    private class PlayerTableModel extends AbstractTableModel {
        private List<User> players;
        private String[] columnNames = {"Nome", "Gols"};

        public PlayerTableModel(List<User> players) {
            this.players = players;
        }

        @Override
        public int getRowCount() {
            return players.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public String getColumnName(int columnIndex) {
            return columnNames[columnIndex];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            User player = players.get(rowIndex);
            if (columnIndex == 0) {
                return player.getNome();
            } else if (columnIndex == 1) {
                return player.getNumGols();
            }
            return null;
        }

        @Override
        public void setValueAt(Object value, int rowIndex, int columnIndex) {
            if (columnIndex == 1) {
                int numGols = (int) value;
                players.get(rowIndex).setNumGols(numGols);
            }
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (columnIndex == 0) {
                return String.class;
            } else if (columnIndex == 1) {
                return Integer.class;
            }
            return Object.class;
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return columnIndex == 1;
        }
    }

    // Renderizador de célula personalizado para o JSpinner
    private class SpinnerRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (value instanceof JSpinner) {
                return (JSpinner) value;
            }
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
    }

    // Editor de célula personalizado para o JSpinner
    private class SpinnerEditor extends DefaultCellEditor {
        public SpinnerEditor() {
            super(new JSpinner());
        }
    }

    public static void main(String[] args) {
        // Exemplo de uso:
        List<User> userList = new ArrayList<>();
        userList.add(new User("Jogador 1", 0));
        userList.add(new User("Jogador 2", 0));
        userList.add(new User("Jogador 3", 0));

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Tabela de Jogadores");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 200);

            PlayerTable playerTable = new PlayerTable(userList);
            frame.add(playerTable);

            frame.setVisible(true);
        });
    }
}
*/