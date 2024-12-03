import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

abstract class Alimentar extends Produto {
    private boolean biologico;

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

    public abstract double calcularTaxaProduto(String local);

    public abstract double calcularTaxaExtra();

    public double calcularTaxa(String local) {
        double taxaTotal = 0;
        if(biologico)
            taxaTotal = (calcularTaxaProduto(local) + calcularTaxaExtra()) * 0.9;
        else
            taxaTotal = (calcularTaxaProduto(local) + calcularTaxaExtra());
        return taxaTotal;
    }
}
