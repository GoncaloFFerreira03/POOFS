public class Produto {
    private String codigo;
    private int quantidade;
    private String nome;
    private String descricao;
    private double precoSemIVA;
    private int taxaTotal;//o valor só via ser atribuído na classe das faturas

    public Produto(String codigo, String nome, String descricao, double precoSemIva, int quantidade) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.precoSemIVA = precoSemIva;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPrecoSemIVA() {
        return precoSemIVA;
    }

    public void setPrecoSemIVA(double precoSemIVA) {
        this.precoSemIVA = precoSemIVA;
    }


    @Override//implementar esta função em cada um dos tipo de produtos para saber o total
    public int calcularTaxa(local);
}
