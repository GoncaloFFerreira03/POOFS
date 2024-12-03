import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

abstract class Farmacia extends Produto {

    public Farmacia(String codigo, String nome, String descricao, double precoSemIva, int quantidade) {
        super(codigo, nome, descricao, precoSemIva, quantidade);
    }


    public abstract double calcularTaxaProduto(String local);

    public abstract double calcularTaxaExtra();

    //este é o metodo final
    public double calcularTaxa(String local){//fazer dois metodos abstratos, o de calcular a taca normal e o da taxa extra
        return calcularTaxaProduto(local) + calcularTaxaExtra();
    }
}
