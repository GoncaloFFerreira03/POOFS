import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classe abstrata que representa um Produto Alimentar
 */
abstract class Alimentar extends Produto {
    /**
     * Indica se o o produto alimentar é biológico ou não
     */
    private boolean biologico;

    /**
     * Construtor da classe Alimentar onde vou armazenar toda a informção sobre esse mesmo produto
     * @param codigo
     * @param nome
     * @param descricao
     * @param precoSemIva
     * @param quantidade
     * @param biologico
     */
    public Alimentar(String codigo, String nome, String descricao, double precoSemIva, int quantidade, boolean biologico) {
        super(codigo, nome, descricao, precoSemIva, quantidade);
        this.biologico = biologico;
    }

    public boolean isBiologico() {
        return biologico;
    }

    public void setBiologico(boolean biologico) {
        this.biologico = biologico;
    }

    /**
     * Método para calcular a taxa extra
     * @return
     */
    public abstract double calcularTaxaExtra();
}
