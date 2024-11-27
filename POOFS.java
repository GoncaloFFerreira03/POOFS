import java.util.ArrayList;
import java.util.Scanner;

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
        menuSistema();
    }
    private static void menuSistema() {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;
        while (continuar) {
            System.out.println("=== MENU PRINCIPAL ===");
            System.out.println("1. Criar ou Editar Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Criar ou Editar Faturas");
            System.out.println("4. Listar Faturas");
            System.out.println("5. Visualizar Fatura");
            System.out.println("6. Importar Faturas");
            System.out.println("7. Exportar Faturas");
            System.out.println("8. Estatísticas");
            System.out.println("9. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consome a quebra de linha após o número

            switch (opcao) {
                case 1:
                    System.out.println("Opção escolhida: Criar ou Editar Cliente");
                    // Implementar método para criar ou editar cliente
                    break;
                case 2:
                    System.out.println("Opção escolhida: Listar Clientes");
                    // Implementar método para listar clientes
                    break;
                case 3:
                    System.out.println("Opção escolhida: Criar ou Editar Faturas");
                    // Implementar método para criar ou editar faturas
                    break;
                case 4:
                    System.out.println("Opção escolhida: Listar Faturas");
                    // Implementar método para listar faturas
                    break;
                case 5:
                    System.out.println("Opção escolhida: Visualizar Fatura");
                    // Implementar método para visualizar uma fatura
                    break;
                case 6:
                    System.out.println("Opção escolhida: Importar Faturas");
                    // Implementar método para importar faturas de ficheiro
                    break;
                case 7:
                    System.out.println("Opção escolhida: Exportar Faturas");
                    // Implementar método para exportar faturas para ficheiro
                    break;
                case 8:
                    System.out.println("Opção escolhida: Estatísticas");
                    // Implementar método para mostrar estatísticas
                    break;
                case 9:
                    System.out.println("Saindo do programa...");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida! Por favor, escolha uma opção válida.");
            }
            System.out.println(); // Espaço entre menus
        }

        scanner.close();
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
