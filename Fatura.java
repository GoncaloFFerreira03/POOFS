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



}
