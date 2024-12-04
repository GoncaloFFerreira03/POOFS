import java.io.Serializable;

/**
 * Classe Cliente que representa cada Cliente
 */
public class Cliente implements Serializable {
    /**
     * nome do cliente
     */
    private String nome;
    /**
     * contribuinte do cliente
     */
    private String contribuinte;
    /**
     * localizacao do cliente
     */
    private String localizacao;

    /**
     * Construtor da classe Cliente que me permite armazenar os dados de cada cliente
     * @param nome
     * @param contribuinte
     * @param localizacao
     */
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

    /**
     * Retorna uma string com o nome do cliente, o contribuinte e a localizacao do cliente
     * @return String
     */
    @Override
    public String toString() {
        return "Nome:'" + nome + '\'' +
                ", contribuinte='" + contribuinte + '\'' +
                ", localizacao='" + localizacao + '\'' +
                '}';
    }
}
