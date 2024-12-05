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
     * método abstrato que vai permitir fazer o calculo da taxa extra a pagar tendo em conta a subclasse de cada produto
     * @return
     */
    public abstract double calcularTaxaExtra();
}
