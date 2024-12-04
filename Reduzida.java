import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classe  Reduzida que representa os Produtos Alimentares de taxa reduzida
 */
public class Reduzida extends Alimentar{
    /**
     * Lista onde serão armazenados todos os certificados do produto
     */
    private ArrayList<String> certificados = new ArrayList<>();

    /**
     * Construtor da classe Reduzida que me permite armazenar toda a minha informação sobre o produto alimentar de taxa reduzida
     * @param codigo
     * @param nome
     * @param descricao
     * @param precoSemIva
     * @param quantidade
     * @param biologico
     * @param certificados
     */
    public Reduzida(String codigo, String nome, String descricao, double precoSemIva, int quantidade, boolean biologico, ArrayList<String> certificados) {
        super(codigo, nome, descricao, precoSemIva, quantidade, biologico);
        this.certificados = certificados;
    }

    public ArrayList<String> getCertificados() {
        return certificados;
    }

    public void setCertificados(ArrayList<String> certificados) {
        this.certificados = certificados;
    }

    /**
     * Verifica se o produto tem algum tipo de certificado e se for verificada essa condição tem uma redução de 1%
     * @return taxaExtra
     */
    public double calcularTaxaExtra(){
        int taxaExtra = 0;
        String[] certificacoesValidas = {"ISO22000", "FSSC22000", "HACCP", "GMP"};
        if (certificados.containsAll(Arrays.asList(certificacoesValidas))) {//se tiver todas as certificações, desconta 1%
            taxaExtra -= 1; // Redução de 1%
        }
        return taxaExtra;
    }

    /**
     * Método que faz o o cálculo da taza de um produto com base no local onde é o cliente e com base na taxaExtra calculada anteriormente
     * @param local
     * @return taxaTotal
     */
    public double calcularTaxaProduto(String local){
        double taxaTotal = 0;

        if(local.equals("Portugal Continental"))   {
            taxaTotal = 6;
        }
        else if(local.equals("Madeira")){
            taxaTotal = 5;
        }
        else if(local.equals("Acores")){
            taxaTotal = 4;
        }

        return taxaTotal;
    }
}
