import java.io.Serializable;

public class Cliente implements Serializable {
    private String nome;
    private String contribuinte;
    private String localizacao;

    public Cliente(String nome, String contribuinte, String localizacao) {
        this.nome = nome;
        this.contribuinte = contribuinte;
        this.localizacao = localizacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getContribuinte() {
        return contribuinte;
    }

    public void setContribuinte(String contribuinte) {
        this.contribuinte = contribuinte;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }
}
