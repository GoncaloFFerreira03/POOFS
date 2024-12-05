/**
 * Classe Simples que representa os produtos de Farmacia que não têm prescrição
 */
public class Simples extends Farmacia{
    /**
     * Variável que me indica a categoria do produto
     */
    private String categoria;//beleza, bem-estar, bebés, animais, ...

    /**
     * Construtor da classe Simples que me permite guardar toda a informção sobre um produto de farmacia simples
     * @param codigo
     * @param nome
     * @param descricao
     * @param precoSemIva
     * @param quantidade
     * @param categoria
     */
    public Simples(String codigo, String nome, String descricao, double precoSemIva, int quantidade, String categoria) {
        super(codigo, nome, descricao, precoSemIva, quantidade);
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * método que me verifica se a categoria é animais porque se for tem um redução na taxa
     * @return taxa
     */
    public double calcularTaxaExtra(){
        double taxa=0;
        if (categoria.toLowerCase().equals("animais")) {
            taxa -= 1;
        }
        return taxa;
    }

    /**
     * método que retorna a taxa dos prudotos farmceuticos com prescição
     * @param local
     * @return taxaTotal
     */
    public double calcularTaxa(String local){
        double taxaTotal = 23;
        double taxaProduto = taxaTotal + calcularTaxaExtra();

        return taxaProduto;
    }
}
