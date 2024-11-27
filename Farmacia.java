public class Farmacia extends Produto{
    private boolean prescricao;

    public Farmacia(String codigo, String nome, String descricao, double precoSemIva, int quantidade, boolean prescricao) {
        super(codigo, nome, descricao, precoSemIva, quantidade);
        this.prescricao = prescricao;
    }

    public boolean isPrescricao() {
        return prescricao;
    }

    public void setPrescricao(boolean prescricao) {
        this.prescricao = prescricao;
    }
}
