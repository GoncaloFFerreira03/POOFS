import java.io.Serializable;
import java.util.ArrayList;

public class Fatura  implements Serializable {
    private int numeroFatura;
    private Cliente cliente;
    private String data;
    private ArrayList<Produto> produtos;
    public Fatura(int numeroFatura, Cliente cliente, String data, ArrayList<Produto> produtos) {
        this.numeroFatura = numeroFatura;
        this.cliente = cliente;
        this.data = data;
        this.produtos = produtos;
    }

    public int getNumeroFatura() {
        return numeroFatura;
    }

    public void setNumeroFatura(int numeroFatura) {
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


    public double calcularTaxaImpostoTotal(){
        double taxaTotal=0;
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

    public double calcularPrecoComIva(){//preço com iva de todos os produtos somados
        double precoTotal=0;
        for (Produto produto : produtos) {
            double precoP = produto.calcularPrecoComIvaIndividual(cliente.getLocalizacao());//preço conm iva de cada produto em si
            precoTotal += precoP;
        }
        return precoTotal;
    }
}
