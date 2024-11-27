public class Farmacia extends Produto {
    private boolean prescricao;
    private String medOuCat;

    public Farmacia(String codigo, String nome, String descricao, double precoSemIva, int quantidade, String medOuCat) {
        super(codigo, nome, descricao, precoSemIva, quantidade);
        this.prescricao = prescricao;
        this.medOucat = medOuCat;
    }

    public boolean getPrescricao() {
        return prescricao;
    }

    public String getMedOuCat() {
        return medOucat;
    }

    private void setPrescricao() {
        String[] categorias = {"beleza", "bem-estar", "bebés", "animais", "outro"};
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
    }

    public int calcularTaxa(local){
        int taxaTotal = 0;
        if(local.equals("Portugal Continental"))   {
            if(pescricao)
                taxaTotal = 6:
            else
                taxaTotal = 23;
        }
        else if(local.equals("Madeira")){
            if(pescricao)
                taxaTotal = 5:
            else
                taxaTotal = 23;
        }
        else if(local.equals("Acores")){
            if(pescricao)
                taxaTotal = 4:
            else
                taxaTotal = 23;
        }
        taxaTotal += calcularTaxaExtra();
        return taxaTotal;
    }
}
