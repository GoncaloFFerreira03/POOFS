public class Produto {
    private String codigo;
    private int quantidade;
    private String nome;
    private String descricao;
    private double precoSemIVA;
    public Produto(String codigo, String nome, String descricao, double precoSemIva, int quantidade) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.precoSemIVA = precoSemIva;
    }


}
