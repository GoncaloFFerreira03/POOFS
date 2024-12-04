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
     * método abstrato que permite o cálculo da taxa a pagar de cada produto alimentar que dependendo das subcalsses(Reduzida,Intermedia,Normal) e das localização do cliente têm preços diferentes dentro dessas mesmas subclasses
     * @param local
     * @return
     */
    public abstract double calcularTaxaProduto(String local);

    /**
     * Método abstrato que me permite calcular a taxaExtra de cada produto que esteja  contido numa subclasse desta classe
     * @return
     */

    public abstract double calcularTaxaExtra();

    /**
     * método que faz o cálculo da taxa a pagar conforme a localidade do cliente, se o produto for biológico ou não e se este produto pertence a alguma categoria especial para ter uma benefício de taxa extra
     * @param local
     * @return taxaTotal
     */
    public double calcularTaxa(String local) {
        double taxaTotal = 0;
        if(biologico)
            taxaTotal = (calcularTaxaProduto(local) + calcularTaxaExtra()) * 0.9;
        else
            taxaTotal = (calcularTaxaProduto(local) + calcularTaxaExtra());
        return taxaTotal;
    }
}
