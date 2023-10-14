package logic;

import date.GolTrue;
import date.User;

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

}
