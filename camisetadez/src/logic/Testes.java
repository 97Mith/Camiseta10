package logic;

import date.GolTrue;
import date.User;

import java.util.List;

public class Testes {
    private static List<GolTrue> times = FuncaoPTelaJogo.lerVotos("ranking.txt");
    private static List<User> timeA = FuncoesArquivo.carregarGols("partidaLadoA.txt");

    public static void main(String[] args) {
        for (GolTrue jog:times) {
            System.out.println(jog.getNome() + jog.getGol());
        }
       /* for (User jog:timeA) {
            System.out.println(jog.getNome() + jog.getGol());
        }*/
    }
}
