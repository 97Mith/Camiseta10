package logic;

import date.GolTrue;
import date.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;
import java.util.*;
import java.util.List;

public class FuncoesArquivo {
    DefaultTableModel model = new DefaultTableModel();
    public static void carregarUsuarios(String caminhoArquivo, List<User> listaUsuarios) {
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            int i = 1;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                if (partes.length == 3) {
                    User jogador = new User();
                    jogador.setId(i++);
                    jogador.setNome(partes[0].trim());
                    jogador.setNumero(Integer.parseInt(partes[1].trim()));
                    jogador.setPosicao(partes[2].trim());
                    listaUsuarios.add(jogador);

                    //System.out.println(jogador.getId());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void preencherTabela(List<User> listaUsuarios, JTable table) {
        DefaultTableModel model = new DefaultTableModel();
        //List<Boolean> checkboxValor = new ArrayList<>();

        // Define as colunas da tabela
        model.addColumn(" ");
        //model.addColumn("ID");
        model.addColumn("Nome");
        model.addColumn("Num");
        model.addColumn("Posição");


        Vector<Object> linhaTabela = null;
        for (User usuario : listaUsuarios) {
            linhaTabela = new Vector<>();
            linhaTabela.add(false); // Caixa de seleção
            linhaTabela.add(usuario.getNome());
            linhaTabela.add(usuario.getNumero());
            linhaTabela.add(usuario.getPosicao());
            model.addRow(linhaTabela);

            //boolean valorCheckBox = (boolean) linhaTabela.get(0);
            //checkboxValor.add(valorCheckBox);
        }

        for (int i=0; i<model.getRowCount(); i++) {
            boolean valor = (boolean) model.getValueAt(i, 0);
            if (valor) {

            }
        }
        // modelo da tabela
        table.setModel(model);

        // renderização das caixas de seleção
        table.getColumnModel().getColumn(0).setCellRenderer(table.getDefaultRenderer(Boolean.class));
        table.getColumnModel().getColumn(0).setCellEditor(table.getDefaultEditor(Boolean.class));


        //tamanho das colunas
        table.getColumnModel().getColumn(0).setPreferredWidth(5);
        table.getColumnModel().getColumn(1).setPreferredWidth(190);
        table.getColumnModel().getColumn(2).setPreferredWidth(10);
        //**   (procurar um meio de centralizar a coluna 2)
        table.getColumnModel().getColumn(3).setPreferredWidth(145);
    }



    /*public static void passarDados(List<User> tabela1, List<User> tabela2) {
        for (User usuario : tabela1) {
            if (usuario.isSelected()) {

                User timeAJogador = new User();
                timeAJogador.setNome(usuario.getNome());
                timeAJogador.setNumero(usuario.getNumero());
                timeAJogador.setPosicao(usuario.getPosicao());


                tabela2.add(timeAJogador);
            }
        }
    }*/

    //função caixa de seleção

    public static void checkTrue(){
        DefaultTableModel model = new DefaultTableModel();
        for (int i=0; i<model.getRowCount(); i++) {
            boolean valor = (boolean) model.getValueAt(i, 0);
            if (valor) {
                System.out.println("oi");
            }
        }
    }
    public static List<User> carregarJogadores(String caminhoArquivo) {
        List<User> listaUsuarios = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                User usuario = new User();
                if (partes.length == 3) {
                    usuario.setNome(partes[0].trim());
                    usuario.setNumero(Integer.parseInt(partes[1].trim()));
                    usuario.setPosicao(partes[2].trim());

                    //User usuario = new User(nome, numero, posicao);
                    listaUsuarios.add(usuario);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listaUsuarios;
    }public static List<User> carregarGols(String caminhoArquivo) {
        List<User> listaUsuarios = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                User usuario = new User();
                if (partes.length == 3) {
                    usuario.setNome(partes[0].trim());
                    usuario.setNumero(Integer.parseInt(partes[1].trim()));
                    usuario.setId(Integer.parseInt(partes[2].trim()));

                    //User usuario = new User(nome, numero, posicao);
                    listaUsuarios.add(usuario);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listaUsuarios;
    }
    public static List<User> removerRepetidos(List<User> listaP, List<User> listaAB, List<User> listaB) {
        List<User> resultado = new ArrayList<>();
        resultado = listaP;

        for (int i = 0; i < listaP.size(); i++) {
            for (int j = 0; j < listaAB.size(); j++) {
                if (listaP.get(i).getNome().equals(listaAB.get(j).getNome())) {
                    resultado.remove(i);
                    i--;
                    break;
                }
            }
        }
    //verificar lista B
        for (int k = 0; k < listaP.size(); k++) {
            for (int l = 0; l < listaB.size(); l++) {
                if (listaP.get(k).getNome().equals(listaB.get(l).getNome())) {
                    resultado.remove(k);
                    k--;
                    break;
                }
            }
        }
    return resultado;
    }
    public static List<User> removerUsuariosSelecionados(List<User> listaUsuarios, DefaultTableModel tableModel) {
        List<User> usuariosSelecionados = new ArrayList<>();

        for (int i = tableModel.getRowCount() - 1; i >= 0; i--) {
            boolean selecionado = (boolean) tableModel.getValueAt(i, 0);

            if (selecionado) {
                usuariosSelecionados.add(listaUsuarios.get(i));
                tableModel.removeRow(i);
            }
        }
        return usuariosSelecionados;

    }
    public static void escreverDadosNoTxt(DefaultTableModel model, String caminhoArquivo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(caminhoArquivo))) {
            int rowCount = model.getRowCount();
            int colCount = model.getColumnCount();

            for (int row = 0; row < rowCount; row++) {
                StringBuilder line = new StringBuilder();
                for (int col = 1; col < colCount; col++) {
                    Object value = model.getValueAt(row, col);
                    if (value != null) {
                        line.append(value.toString());
                    }
                    if (col < colCount - 1) {
                        line.append(",");
                    }
                }
                writer.println(line.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static boolean repetidosAB(List<User> listaA, List<User> listaB){
        boolean acesso = true;
        for(int i = 0; i<listaA.size(); i++){
            for (int j = 0; j<listaB.size();j++){
                if(listaA.get(i).getNome().equals(listaB.get(j).getNome())){
                    acesso = false;
                    JOptionPane.showMessageDialog(null, "Remova os jogadores repetidos e atualize as tabelas\n\nNão se pode jogar em dois times diferentes", "Times com jogadores iguais", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        return acesso;
    }
    public static void limparTabela(String caminho){
        try (PrintWriter writer = new PrintWriter(new FileWriter(caminho, false))) {

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static List<GolTrue> criarRanking(List<GolTrue> jogadores) {

        Collections.sort(jogadores, Comparator.comparingInt(GolTrue::getGol).reversed());

        return jogadores;
    }
}

