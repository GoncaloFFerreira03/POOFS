import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classe abstrata Farmacia que representa um Produto Alimentar
 */
abstract class Farmacia extends Produto {
    /**
     * Construtor da classe Farmacia que me permite armazenar todas as informações sobre os produtos de farmácia
     * @param codigo
     * @param nome
     * @param descricao
     * @param precoSemIva
     * @param quantidade
     */
    public Farmacia(String codigo, String nome, String descricao, double precoSemIva, int quantidade) {
        super(codigo, nome, descricao, precoSemIva, quantidade);
    }

    /**
     * método abstrato que vai permitir calcular a taxa de cada produto dependendo da subclasse do mesmo
     * @param local
     * @return
     */
    public abstract double calcularTaxaProduto(String local);

    /**
     * método abstrato que vai permitir fazer o calculo da taxa extra a pagar tendo em conta a subclasse de cada produto
     * @return
     */

    public abstract double calcularTaxaExtra();

    /**
     * método que faz o cálculo da taxa a pagar tendo em consideração apenas as taxas associdas ao produto
     * @param local
     * @return taxaTotal
     */
    public double calcularTaxa(String local){//fazer dois metodos abstratos, o de calcular a taca normal e o da taxa extra
        double taxaProduto = calcularTaxaProduto(local) + calcularTaxaExtra();
        return taxaProduto;
    }
}
