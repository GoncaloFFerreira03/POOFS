import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * Classe Intermedia que representa os produtos alimentares de taxa intermedia
 */
public class Intermedia extends Alimentar{
    /**
     * categoria que me permite saber o tipo de alimento dentro da taxa intermedia
     */
    private String categoria;

    /**
     * Construtor da classe Intermedia que permite armazenar todas as informções dos produtos alimentares de taxa intermédia
     * @param codigo
     * @param nome
     * @param descricao
     * @param precoSemIva
     * @param quantidade
     * @param biologico
     * @param categoria
     */
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

    /**
     * método que verifica se o produto é da categoria de vinho pois, se for tem um agravamento da taxa
     * @return taxaExtra
     */
    public double calcularTaxaExtra(){
        int taxaExtra = 0;
        if (categoria.toLowerCase().equals("vinho")){
            taxaExtra +=1;
        }
        return taxaExtra;
    }

    /**
     * método que calcula a taxa de cada produto com base na localização do cliente
     * @param local
     * @return taxaTotal
     */
    public double calcularTaxa(String local){
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

        if(isBiologico())
            taxaTotal += taxaTotal * 0.9;

        return taxaTotal;
    }
}
