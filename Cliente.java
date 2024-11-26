public class Cliente {
    private String name;
    private String contribuinte;
    private String localizacao;

    public Cliente(String name, String contribuinte, String localizacao) {
        this.name = name;
        this.contribuinte = contribuinte;
        this.localizacao = localizacao;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
