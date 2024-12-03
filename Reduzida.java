import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Reduzida extends Alimentar{
    private ArrayList<String> certificados = new ArrayList<>();

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

    public double calcularTaxaExtra(){
        int taxaExtra = 0;
        String[] certificacoesValidas = {"ISO22000", "FSSC22000", "HACCP", "GMP"};
        if (certificados.containsAll(Arrays.asList(certificacoesValidas))) {//se tiver todas as certificações, desconta 1%
            taxaExtra -= 1; // Redução de 1%
        }
        return taxaExtra;
    }

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

        taxaTotal += calcularTaxaExtra();

        return taxaTotal;
    }
}
