import java.util.ArrayList;

public class POOFS {
    public static void main(String[] args) {
        // Nome do arquivo que contém os dados
        String nomeFicheiro = "ficheiro.txt";

        // Ler o arquivo
        Ficheiro ficheiro = new Ficheiro(nomeFicheiro);

        // Listas para armazenar os objetos criados
        ArrayList<Cliente> clientes = new ArrayList<>();
        ArrayList<Fatura> faturas = new ArrayList<>();
        ArrayList<Produto> produtos = new ArrayList<>();

        // Processar o conteúdo do ficheiro
        lerFicheiroTexto(ficheiro, clientes, faturas, produtos);

        // Mostrar os dados processados
        System.out.println("Clientes:");
        for (Cliente cliente : clientes) {
            System.out.println("Nome: " + cliente.getName() +
                    ", Contribuinte: " + cliente.getContribuinte() +
                    ", Localização: " + cliente.getLocalizacao());
        }

        System.out.println("\nFaturas:");
        for (Fatura fatura : faturas) {
            System.out.println("Número: " + fatura.getNumeroFatura() +
                    ", Cliente: " + fatura.getCliente().getName() +
                    ", Data: " + fatura.getData() +
                    ", Produtos: " + fatura.getProdutos()); // Presume que getProdutos() retorna uma lista legível
        }

        System.out.println("\nProdutos:");
        for (Produto produto : produtos) {
            if (produto instanceof Farmacia) {
                Farmacia farmacia = (Farmacia) produto;
                System.out.println("Tipo: Farmácia, Código: " + farmacia.getCodigo() +
                        ", Nome: " + farmacia.getNome() +
                        ", Descrição: " + farmacia.getDescricao() +
                        ", Quantidade: " + farmacia.getQuantidade() +
                        ", Preço: " + farmacia.getPrecoSemIVA() +
                        ", Prescrição Obrigatória: " + farmacia.isPrescricao());
            } else if (produto instanceof Alimentar) {
                Alimentar alimentar = (Alimentar) produto;
                System.out.println("Tipo: Alimentar, Código: " + alimentar.getCodigo() +
                        ", Nome: " + alimentar.getNome() +
                        ", Descrição: " + alimentar.getDescricao() +
                        ", Quantidade: " + alimentar.getQuantidade() +
                        ", Preço: " + alimentar.getPrecoSemIVA() +
                        ", Biológico: " + alimentar.isBiologico());
            }
        }

    }

    public static void lerFicheiroTexto(Ficheiro ficheiro, ArrayList<Cliente> clientes, ArrayList<Fatura> faturas, ArrayList<Produto> produtos) {
        // Processar as linhas lidas
        for (String linha : ficheiro.linhas) {
            String[] parts = linha.split("\\|"); // Dividir a linha pelo separador "|"

            switch (parts[0]) {
                case "Cliente":
                    // Criar um cliente
                    clientes.add(new Cliente(parts[1], parts[2], parts[3]));
                    break;

                case "ProdutoF":
                case "ProdutoA":
                    // Criar um produto farmacêutico ou alimentar
                    Produto produto;
                    if (parts[0].equals("ProdutoF")) {
                        produto = new Farmacia(parts[1], parts[2], parts[3], Double.parseDouble(parts[5]), Integer.parseInt(parts[4]), Boolean.parseBoolean(parts[6]));
                    } else {
                        produto = new Alimentar(parts[1], parts[2], parts[3], Double.parseDouble(parts[5]), Integer.parseInt(parts[4]), Boolean.parseBoolean(parts[6]));
                    }
                    produtos.add(produto);
                    break;

                case "Fatura":
                    // Criar uma fatura
                    ArrayList<Produto> produtoFatura =  new ArrayList<>();
                    for(Produto produto1: produtos){
                        if(produto1.getCodigo().equals(parts[4])){
                            produtoFatura.add(produto1);
                        }
                    }

                    Cliente cliente= null;
                    for(Cliente cliente1: clientes){
                        if(cliente1.getContribuinte().equals(parts[2])){
                            cliente = cliente1;
                            break;
                        }
                    }

                    faturas.add(new Fatura(parts[1], cliente, parts[3], produtoFatura));
                    break;

                default:
                    System.out.println("Linha inválida: " + linha);
                    break;
            }
        }
    }
}
