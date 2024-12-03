import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Intermedia extends Alimentar{
    private String categoria;

    public Intermedia(String codigo, String nome, String descricao, double precoSemIva, int quantidade, boolean biologico, String categoria) {
        super(codigo, nome, descricao, precoSemIva, quantidade, biologico);
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double calcularTaxaExtra(){
        int taxaExtra = 0;
        if (categoria.toLowerCase().equals("vinho")){
            taxaExtra +=1;
        }
        return taxaExtra;
    }

    public double calcularTaxaProduto(String local){
        double taxaTotal = 0;

        if(local.equals("Portugal Continental"))   {
            taxaTotal = 13;
        }
        else if(local.equals("Madeira")){
            taxaTotal = 12;
        }
        else if(local.equals("Acores")){
            taxaTotal = 9;
        }

        taxaTotal += calcularTaxaExtra();

        return taxaTotal;
    }
}
