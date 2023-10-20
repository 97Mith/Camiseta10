package date;

public class UserAttribute {
    String nome;
    String atributo;

    public UserAttribute(String nome, String atributo) {
        this.nome = nome;
        this.atributo = atributo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAtributo() {
        return atributo;
    }

    public void setAtributo(String atributo) {
        this.atributo = atributo;
    }
}
