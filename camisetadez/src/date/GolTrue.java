package date;

public class GolTrue {
    private String nome;
    private int gol;

    public GolTrue(String nome, int gol) {
        this.nome = nome;
        this.gol = gol;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setGol(int gol) {
        this.gol = gol;
    }

    public String getNome() {
        return nome;
    }

    public int getGol() {
        return gol;
    }
}
