package logic;

import date.GolTrue;
import date.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FuncaoPTelaJogo {
    public static List<GolTrue> obterUsuariosComGols(List<User> listaUsuarios) {
        List<GolTrue> listaGolTrue = new ArrayList<>();

        for (User user : listaUsuarios) {
            if (user.getGol() > 0) {
                GolTrue golTrue = new GolTrue(user.getNome(), user.getGol());
                listaGolTrue.add(golTrue);
            }
        }

        return listaGolTrue;
    }
    public static List<GolTrue> lerVotos(String nomeDoArquivo) {
        List<GolTrue> listaGolTrue = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(nomeDoArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(",");
                if (partes.length == 2) {
                    String nome = partes[0];
                    int votos = Integer.parseInt(partes[1]);
                    GolTrue jogador = new GolTrue(nome, votos);
                    listaGolTrue.add(jogador);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listaGolTrue;
    }
    public static String jogadorComMaisVotos(List<GolTrue> listaGolTrue) {
        if (listaGolTrue.isEmpty()) {
            return "Nenhum jogador na lista";
        }

        GolTrue jogadorComMaisPontos = listaGolTrue.get(0);

        for (GolTrue jogador : listaGolTrue) {
            if (jogador.getGol() > jogadorComMaisPontos.getGol()) {
                jogadorComMaisPontos = jogador;
            }
        }

        return jogadorComMaisPontos.getNome();
    }

}
