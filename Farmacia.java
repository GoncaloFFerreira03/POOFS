public class Farmacia extends Produto{
    private boolean prescricao;
    public Farmacia(String codigo, String nome, String descricao, double precoSemIva) {
        super(codigo,nome,descricao,precoSemIva);
    }
}
