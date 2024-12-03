import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Alimentar extends Produto {
    private boolean biologico;
    private ArrayList<String> certOuCat = new ArrayList<>();//certificados ou categorias
    private String tipoDeTaxa = "normal";

    public Alimentar(String codigo, String nome, String descricao, double precoSemIva, int quantidade, boolean biologico,  ArrayList<String> certOuCat) {
        super(codigo, nome, descricao, precoSemIva, quantidade);
        this.biologico = biologico;
        this.certOuCat = certOuCat;
    }

    public boolean isBiologico() {
        return biologico;
    }

    public void setBiologico(boolean biologico) {
        this.biologico = biologico;
    }

    public ArrayList<String> getCertOuCat() {
        return certOuCat;
    }

    public void setCertOuCat(ArrayList<String> certOuCat) {
        this.certOuCat = certOuCat;
    }

    public String getTipoDeTaxa() {
        return tipoDeTaxa;
    }
    
    //fazer a condição de verificar o que está dentro do array certOuCat para decidir o tipo de taxa
    private void setTipoDeTaxa() {
        List<String> certificados = List.of("ISO22000", "FSSC22000", "HACCP", "GMP");
        List<String> categorias = List.of("Congelado", "Enlatado", "Vinho");
        int listSize= this.certOuCat.size();
        if(listSize>0){
            for(String categoria: categorias){
                if(this.certOuCat.get(0).equals(categoria)) {//vamos assumir que se for categoria, nao precisamos de ver mais
                    this.tipoDeTaxa = "intermédia";
                    break;
                }
            }
            for(String categoria: categorias){
                if(this.certOuCat.get(0).equals(categoria)) {//como no mázimo so há 4, e nesta condição sabemos que existe pelo menos uma, nao precisamos de ver mais
                    this.tipoDeTaxa = "reduzida";
                    break;
                }
            }
        }
        else    this.tipoDeTaxa = "normal";
    }

    private int calcularTaxaExtra(){
        int taxaExtra = 0;
        if(this.tipoDeTaxa.equals("reduzida")){
            String[] certificacoesValidas = {"ISO22000", "FSSC22000", "HACCP", "GMP"};
            if (certOuCat.containsAll(Arrays.asList(certificacoesValidas))) {
                taxaExtra -= 1; // Redução de 1%
            }
        }
        else if(this.tipoDeTaxa.equals("intermédia")){
            if (certOuCat.get(0).equals("Vinho")) {
                taxaExtra += 1; // Aumento de 1%
            }
        }
        else taxaExtra = 0;

        if(biologico) {
            taxaExtra -= 10;
        }
        return taxaExtra;
    }

    public int calcularTaxa(String local){
        int taxaTotal = 0;
        if(local.equals("Portugal Continental"))   {
            if(this.tipoDeTaxa.equals("reduzida")){
                taxaTotal = 6;
            }else if(this.tipoDeTaxa.equals("intermédia")) {
                taxaTotal = 13;
            }else if(this.tipoDeTaxa.equals("normal")) {
                taxaTotal = 23;
            }
        }
        else if(local.equals("Madeira")){
            if(this.tipoDeTaxa.equals("reduzida")){
                taxaTotal = 5;
            }else if(this.tipoDeTaxa.equals("intermédia")){
                taxaTotal = 12;
            }else if(this.tipoDeTaxa.equals("normal")) {
                taxaTotal = 22;
            }
        }
        else if(local.equals("Acores")){
              if(this.tipoDeTaxa.equals("reduzida")){
                  taxaTotal = 4;
              }else if(this.tipoDeTaxa.equals("intermédia")){
                  taxaTotal = 9;
              }else if(this.tipoDeTaxa.equals("normal")) {
                  taxaTotal = 16;
              }
        }

        taxaTotal += calcularTaxaExtra();
        return taxaTotal;
    }

}
