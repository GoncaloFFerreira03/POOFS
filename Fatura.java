import java.util.ArrayList;

public class Fatura {
    private String numeroFatura;
    private Cliente cliente;
    private String data;
    private ArrayList<Produto> produtos;
    public Fatura(String numeroFatura, Cliente cliente, String data, ArrayList<Produto> produtos) {
        this.numeroFatura = numeroFatura;
        this.cliente = cliente;
        this.data = data;
        this.produtos = produtos;
    }

    public String getNumeroFatura() {
        return numeroFatura;
    }

    public void setNumeroFatura(String numeroFatura) {
        this.numeroFatura = numeroFatura;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }


    public int calcularTaxaImpostoTotal(){
        int taxaTotal=0;
        for (Produto produto : produtos) {
            taxaTotal += produto.calcularTaxa(cliente.getLocalizacao());
        }
        return taxaTotal;
    }
    public double calcularPrecoSemIva(){
        double precoTotal=0;
        for (Produto produto : produtos) {
            precoTotal+= produto.calcularPrecoTotalSemIva();
        }
        return precoTotal;
    }

}
