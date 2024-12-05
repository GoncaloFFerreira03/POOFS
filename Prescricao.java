/**
 * Classe que representa a Prescricao
 */
public class Prescricao extends Farmacia{
    /**
     * nome do médico que preescreve o medicamento
     */
    private String medico;

    /**
     * Construtor da classe Prescricao que me vai armazenar os dados de cada prescricao
     * @param codigo
     * @param nome
     * @param descricao
     * @param precoSemIva
     * @param quantidade
     * @param medico
     */
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

    /**
     * método que faz o cálculo da taxa a pagar tendo em consideração apenas as taxas associdas ao produto
     * @param local
     * @return taxaTotal
     */
    public double calcularTaxa(String local){
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
