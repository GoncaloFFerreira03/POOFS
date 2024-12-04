import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe abstrata que representa o Produto
 */
abstract class Produto implements Serializable {
    /**
     * código característico de cada produto
     */
    private String codigo;
    /**
     * quantidade de cada produto
     */
    private int quantidade;
    /**
     * nome do produto
     */
    private String nome;
    /**
     * descricao de cada produto
     */
    private String descricao;
    /**
     * preco sem Iva de cada produto
     */
    private double precoSemIVA;

    /**
     * Construtor da classe Produto que vai servir para armazenar os dados de cada produto
     * @param codigo
     * @param nome
     * @param descricao
     * @param precoSemIva
     * @param quantidade
     */
    public Produto(String codigo, String nome, String descricao, double precoSemIva, int quantidade) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.precoSemIVA = precoSemIva;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPrecoSemIVA() {
        return precoSemIVA;
    }

    public void setPrecoSemIVA(double precoSemIVA) {
        this.precoSemIVA = precoSemIVA;
    }

    /**
     * método que calcula o preço com iva de cada produto
     * @param localizacao
     * @return o preco do produto
     */
    public double calcularPrecoComIvaIndividual(String localizacao){
        double precoProduto = calcularPrecoTotalSemIva() * (1 + calcularTaxa(localizacao)/100);
        return precoProduto;
    }

    /**
     * classe abstrata que calcula o preco de cada produto na sua subclasse
     * @param local
     * @return
     */
    public abstract double calcularTaxa(String local);

    /**
     * Calcula o preco do produto sem iva
     * @return precoTotal
     */
    public double calcularPrecoTotalSemIva(){
        double precoTotal = 0;
        precoTotal = precoSemIVA*quantidade;
        return precoTotal;
    }
}
