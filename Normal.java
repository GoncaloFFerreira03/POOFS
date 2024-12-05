import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classe Normal que representa os produtos alimentares de taxa normal
 */
public class Normal extends Alimentar{
    /**
     * construtor da classe Normal que permite armazenar informações sobre os produtos alimentar de taxa normal
     * @param codigo
     * @param nome
     * @param descricao
     * @param precoSemIva
     * @param quantidade
     * @param biologico
     */
    public Normal(String codigo, String nome, String descricao, double precoSemIva, int quantidade, boolean biologico) {
        super(codigo, nome, descricao, precoSemIva, quantidade, biologico);
    }

    /**
     * os produtos de taxa normal não têm taxa extra
     * @return
     */
    public double calcularTaxaExtra(){
        return 0;
    }

    /**
     * Calcula o taxa de cada produto conforme a localidade de cada cliente
     * @param local
     * @return taxaTotal
     */
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

        return taxaTotal;
    }
}
