public class Alimentar extends Produto {
    private boolean biologico;
    public Alimentar(String codigo, String nome, String descricao, double precoSemIva, int quantidade) {
        super(codigo,nome,descricao,precoSemIva,quantidade);
    }
}
