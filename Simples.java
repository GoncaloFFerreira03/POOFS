public class Simples extends Farmacia{
    private String categoria;//beleza, bem-estar, bebés, animais, ...

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

    public double calcularTaxaExtra(){
        double taxa=0;
        if (categoria.toLowerCase().equals("animais")) {
            taxa -= 1;
        }
        return taxa;
    }

    public double calcularTaxaProduto(String local) {//taxa é igual para todos
        double taxaTotal = 23;
        return taxaTotal;
    }
}
