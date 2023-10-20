package logic;
import date.GolTrue;
import date.User;
import date.UserAttribute;
import logic.FuncoesArquivo;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeFuncoes {
    public static boolean verificarJogadores(List<User> time, String texto){
        //boolean tudoCerto = false;
        int posicaoZ = 0; int posicaoM = 0; int posicaoA = 0;
        int goleiro = 0;
        for (int i = 0; i < time.size(); i++){
            if (time.get(i).getPosicao().equals("Zagueiro")){
                posicaoZ += 1;
            }if (time.get(i).getPosicao().equals("MeioC")){
                posicaoM += 1;
            }if (time.get(i).getPosicao().equals("Atacante")){
                posicaoA += 1;
            }if (time.get(i).getPosicao().equals("Goleiro")){
                goleiro += 1;
            }
        }
        if(goleiro > 1){
            JOptionPane.showMessageDialog(null, "Não é permitido mais nem menos de 1 goleiro por time", texto, JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (posicaoZ == 0) {
            JOptionPane.showMessageDialog(null, "Precisa de pelo menos um zagueiro", texto, JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (posicaoM == 0) {
            JOptionPane.showMessageDialog(null, "Precisa de pelo menos um meio-campista", texto, JOptionPane.WARNING_MESSAGE);
            return  false;
        } else if (posicaoA == 0) {
            JOptionPane.showMessageDialog(null, "Precisa de pelo menos um atacante", texto, JOptionPane.WARNING_MESSAGE);
            return  false;
        }
        return true;
    }

    public static List<UserAttribute> lerNomeEAtributo(String nomeArquivo) {
        List<UserAttribute> listaUserAttribute = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(",");
                if (partes.length == 2) {
                    String nome = partes[0].trim();
                    String atributo = partes[1].trim();
                    UserAttribute userAttribute = new UserAttribute(nome, atributo);
                    listaUserAttribute.add(userAttribute);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro ao ler o arquivo: " + nomeArquivo);
        }

        return listaUserAttribute;
    }


}
