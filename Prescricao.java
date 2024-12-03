public class Prescricao extends Farmacia{
    private String medico;

    public Prescricao(String codigo, String nome, String descricao, double precoSemIva, int quantidade, String medico) {
        super(codigo, nome, descricao, precoSemIva, quantidade);
        this.medico = medico;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public double calcularTaxaExtra(){
        return 0;
    }

    public double calcularTaxaProduto(String local) {//vai ser abstrato
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
