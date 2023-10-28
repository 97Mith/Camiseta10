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
    public static List<GolTrue> atualizarRanking(List<GolTrue> quemfezgols, List<GolTrue> ranking) {
        List<GolTrue> novoRanking = new ArrayList<>();

        for (int i = 0; i < quemfezgols.size(); i++) {
            GolTrue jogadorQuemFezGols = quemfezgols.get(i);
            boolean jogadorEncontrado = false;

            for (int j = 0; j < ranking.size(); j++) {
                GolTrue jogadorRanking = ranking.get(j);

                if (jogadorQuemFezGols.getNome().equals(jogadorRanking.getNome())) {
                    int gol = jogadorQuemFezGols.getGol();
                    int golAnteriores = jogadorRanking.getGol();
                    int total = gol + golAnteriores;
                    GolTrue jogadorAtualizado = new GolTrue(jogadorQuemFezGols.getNome(), total);
                    novoRanking.add(jogadorAtualizado);

                    jogadorEncontrado = true;
                    break;
                }
            }

            if (!jogadorEncontrado) {
                // Se o jogador não foi encontrado no ranking, adicione-o ao novoRanking
                novoRanking.add(new GolTrue(jogadorQuemFezGols.getNome(), jogadorQuemFezGols.getGol()));
            }
        }

        // Adicione os jogadores do ranking que não estão em quemfezgols
        for (GolTrue jogadorRanking : ranking) {
            if (!quemfezgols.stream().anyMatch(j -> j.getNome().equals(jogadorRanking.getNome()))) {
                novoRanking.add(jogadorRanking);
            }
        }

        return novoRanking;
    }


}
