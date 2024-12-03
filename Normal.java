import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Normal extends Alimentar{

    public Normal(String codigo, String nome, String descricao, double precoSemIva, int quantidade, boolean biologico) {
        super(codigo, nome, descricao, precoSemIva, quantidade, biologico);
    }


    public double calcularTaxaExtra(){
        return 0;
    }

    public double calcularTaxaProduto(String local){
        double taxaTotal = 0;

        if(local.equals("Portugal Continental"))   {
            taxaTotal = 23;
        }
        else if(local.equals("Madeira")){
            taxaTotal = 22;
        }
        else if(local.equals("Acores")){
            taxaTotal = 16;
        }

        taxaTotal += calcularTaxaExtra();

        return taxaTotal;
    }
}
