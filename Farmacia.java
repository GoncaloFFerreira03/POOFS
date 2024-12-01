import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Farmacia extends Produto {
    private boolean prescricao;
    private String medOuCat;

    public Farmacia(String codigo, String nome, String descricao, double precoSemIva, int quantidade, String medOuCat) {
        super(codigo, nome, descricao, precoSemIva, quantidade);
        this.prescricao = prescricao;
        this.medOuCat = medOuCat;
    }

    public boolean getPrescricao() {
        return prescricao;
    }

    public String getMedOuCat() {
        return medOuCat;
    }

    private void setPrescricao() {
        String[] palavras = {"beleza", "bem-estar", "bebés", "animais", "outro"};
        for (String palavra : palavras) {
            if (medOuCat.toLowerCase().equals(palavra.toLowerCase())) {
                this.prescricao = true;
                break;
            }
        }
        this.prescricao = false;
    }

    private int calcularTaxaExtra() {
        int taxaExtra = 0;
        if (!prescricao) {
            if (medOuCat.toLowerCase().equals("animais")) {
                taxaExtra -= 1;
                return taxaExtra;
            }
        }
        return taxaExtra;
    }

    public int calcularTaxa(String local){
        int taxaTotal = 0;
        if(local.equals("Portugal"))   {
            if(prescricao)
                taxaTotal = 6;
            else
                taxaTotal = 23;
        }
        else if(local.equals("Madeira")){
            if(prescricao)
                taxaTotal = 5;
            else
                taxaTotal = 23;
        }
        else if(local.equals("Acores")){
            if(prescricao)
                taxaTotal = 4;
            else
                taxaTotal = 23;
        }
        taxaTotal += calcularTaxaExtra();
        return taxaTotal;
    }
}
