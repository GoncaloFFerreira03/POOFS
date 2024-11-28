import java.util.ArrayList;
import java.util.Scanner;

public class POOFS {
    public static void main(String[] args) {
        // Nome do arquivo que contém os dados
        String nomeFicheiro = "src/ficheiro.txt";

        // Ler o arquivo
        Ficheiro ficheiro = new Ficheiro(nomeFicheiro);

        // Listas para armazenar os objetos criados
        ArrayList<Cliente> clientes = new ArrayList<>();
        ArrayList<Fatura> faturas = new ArrayList<>();
        ArrayList<Produto> produtos = new ArrayList<>();

        // Processar o conteúdo do ficheiro
        lerFicheiroTexto(ficheiro, clientes, faturas, produtos);
        /*for (Produto produto : produtos) {
            if (produto instanceof Alimentar) {
                Alimentar alimentar = (Alimentar) produto;
                System.out.println("Código: " + alimentar.getCodigo());
                System.out.println("Nome: " + alimentar.getNome());
                System.out.println("Descrição: " + alimentar.getDescricao());
                System.out.println("Preço sem IVA: " + alimentar.getPrecoSemIVA());
                System.out.println("Quantidade: " + alimentar.getQuantidade());
                System.out.println("Biológico: " + (alimentar.isBiologico() ? "Sim" : "Não"));
                System.out.println("Certificados ou Categorias: ");

                for (String certOuCat : alimentar.getCertOuCat()) {
                    System.out.println("  - " + certOuCat);
                }
                System.out.println(); // Linha em branco para separar os produtos
            }
        }*/

        menuSistema(clientes, faturas, produtos);
    }
    private static void menuSistema( ArrayList<Cliente> clientes, ArrayList<Fatura> faturas, ArrayList<Produto> produtos) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;
        while (continuar) {
            System.out.println("=== MENU PRINCIPAL ===");
            System.out.println("1. Criar Cliente");
            System.out.println("2. Editar Cliente");
            System.out.println("3. Listar Clientes");
            System.out.println("4. Criar Faturas");
            System.out.println("5. Editar Faturas");
            System.out.println("6. Listar Faturas");
            System.out.println("7. Visualizar Fatura");
            System.out.println("8. Importar Faturas");
            System.out.println("9. Exportar Faturas");
            System.out.println("10. Estatísticas");
            System.out.println("11. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consome a quebra de linha após o número

            switch (opcao) {
                case 1:
                    registar(clientes);
                    break;
                case 2:
                    editar(clientes, faturas, produtos);
                    break;
                case 3://Listar Clientes
                    System.out.println("Opção escolhida: Criar ou Editar Faturas");
                    // Implementar método para criar ou editar faturas
                    break;
                case 4://Criar faturas
                    criarFaturas( clientes, faturas, produtos);
                    // Implementar método para listar faturas
                    break;
                case 5://Editar faturas
                    editarFatura( clientes, faturas, produtos);
                    // Implementar método para visualizar uma fatura
                    break;
                case 6://Listar faturas
                    System.out.println("Opção escolhida: Importar Faturas");
                    // Implementar método para importar faturas de ficheiro
                    break;
                case 7://Visualizar faturas
                    System.out.println("Opção escolhida: Exportar Faturas");
                    // Implementar método para exportar faturas para ficheiro
                    break;
                case 8://Importar Faturas
                    System.out.println("Opção escolhida: Estatísticas");
                    // Implementar método para mostrar estatísticas
                    break;
                case 9://Exportar Faturas
                    System.out.println("Opção escolhida: Estatísticas");
                    // Implementar método para mostrar estatísticas
                    break;
                case 10://Estatisticas
                    System.out.println("Opção escolhida: Estatísticas");
                    // Implementar método para mostrar estatísticas
                    break;
                case 11://Sair
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
                        produto = new Farmacia(parts[1], parts[2], parts[3], Double.parseDouble(parts[5]), Integer.parseInt(parts[4]), parts[6]);
                    } else {
                        ArrayList<String> certOuCat =new ArrayList<>();
                        for(int i=7;i< parts.length; i++){
                            certOuCat.add(parts[i]);
                        }
                        produto = new Alimentar(parts[1], parts[2], parts[3], Double.parseDouble(parts[5]), Integer.parseInt(parts[4]), Boolean.parseBoolean(parts[6]),certOuCat);
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
    public static Cliente registar( ArrayList<Cliente> clientes) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduza o seu nome: ");
        String nome = scanner.nextLine();
        System.out.println("Introduza o seu contribuinte: ");
        String contribuinte = scanner.nextLine();
        String localizacao;
        while (true) {
            System.out.println("Introduza a sua localização (Acores, Madeira ou Portugal Continental): ");
            localizacao = scanner.nextLine();

            if (localizacao.equals("Acores") || localizacao.equals("Madeira") || localizacao.equals("Portugal Continental")) {
                break;
            }
            System.out.println("Localização inválida! Por favor, insira 'Açores', 'Madeira', ou 'Portugal Continental'.");
        }
        Cliente cliente = new Cliente(nome,contribuinte,localizacao);
        clientes.add(cliente);
        for(Cliente c:clientes)
        {
            System.out.println("Nome: " + c.getNome() + ", Contribuinte: " + c.getContribuinte() + ", Localização: " + c.getLocalizacao());
        }
        return cliente;
    }
    public static void editar( ArrayList<Cliente> clientes, ArrayList<Fatura> faturas, ArrayList<Produto> produtos) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduza o seu nome: ");
        String nome = scanner.nextLine();
        System.out.println("Introduza o seu contribuinte: ");
        String contribuinte = scanner.nextLine();
        System.out.println("Introduza a sua localização (Acores, Madeira ou Continente): ");
        String localizacao = scanner.nextLine();
        for (Cliente c : clientes) {
            if (c.getNome().equals(nome) && c.getContribuinte().equals(contribuinte) && c.getLocalizacao().equals(localizacao)) {
                System.out.println("======== Editar Dados =======");
                System.out.println("= 1. Nome de Cliente        = ");
                System.out.println("= 2. Contribuinte de Cliente= ");
                System.out.println("= 3. Localizacao de Cliente = ");
                System.out.println("= 4. === MENU PRINCIPAL === = ");
                System.out.println("=============================");
                System.out.print("Escolha uma opção: ");
                int opcao = scanner.nextInt();
                scanner.nextLine();
                switch (opcao) {
                    case 1:
                        System.out.println("Introduza o seu novo nome: ");
                        String novoNome = scanner.nextLine();
                        c.setNome(novoNome);
                        break;
                    case 2:
                        System.out.println("Introduza o seu novo Contribuinte: ");
                        String novoContribuinte = scanner.nextLine();
                        c.setNome(novoContribuinte);
                        break;
                    case 3:
                        System.out.println("Introduza a sua nova Localizacao: ");
                        String novaLoc = scanner.nextLine();
                        c.setNome(novaLoc);
                        break;
                    case 4:
                        menuSistema(clientes, faturas, produtos);
                        break;

                }
            }
        }
        for(Cliente c:clientes)
        {
            System.out.println("Nome: " + c.getNome() + ", Contribuinte: " + c.getContribuinte() + ", Localização: " + c.getLocalizacao());
        }
    }

    public static void criarFaturas(ArrayList<Cliente> clientes, ArrayList<Fatura> faturas, ArrayList<Produto> produtos) {
        Scanner scanner = new Scanner(System.in);

        // Escolha do cliente
        System.out.println("A que pessoa está associado?");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println((i + 1) + " -> " + clientes.get(i).getNome());
        }

        int clienteIndex = -1;
        while (clienteIndex < 0 || clienteIndex >= clientes.size()) {
            System.out.print("Escolha o número correspondente ao cliente: ");
            clienteIndex = scanner.nextInt() - 1; // Converte de 1-based para 0-based
            if (clienteIndex < 0 || clienteIndex >= clientes.size()) {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }
        Cliente clienteEscolhido = clientes.get(clienteIndex);

        // Adicionar produtos à fatura
        ArrayList<Produto> produtosEscolhidos = new ArrayList<>();
        boolean adicionarMaisProdutos = true;
        while (adicionarMaisProdutos) {
            System.out.println("Escolha os produtos a adicionar:");
            for (int i = 0; i < produtos.size(); i++) {
                System.out.println((i + 1) + " -> " + produtos.get(i).getNome()); // Assumindo que Produto tem o método getNome()
            }

            int produtoIndex = -1;
            while (produtoIndex < 0 || produtoIndex >= produtos.size()) {
                System.out.print("Escolha o número correspondente ao produto: ");
                produtoIndex = scanner.nextInt() - 1; // Converte de 1-based para 0-based
                if (produtoIndex < 0 || produtoIndex >= produtos.size()) {
                    System.out.println("Opção inválida. Tente novamente.");
                }
            }
            produtosEscolhidos.add(produtos.get(produtoIndex));

            // Perguntar se deseja adicionar mais produtos
            System.out.print("Deseja adicionar mais produtos? (s/n): ");
            String resposta = scanner.next();
            adicionarMaisProdutos = resposta.equalsIgnoreCase("s");
        }

        // Criar a fatura
        System.out.print("Insira o número da fatura: ");
        String numeroFatura = scanner.next();

        System.out.print("Insira a data da fatura (yyyy-MM-dd): ");
        String dataFatura = scanner.next();

        // Instancia a nova fatura e adiciona à lista de faturas
        Fatura novaFatura = new Fatura(numeroFatura, clienteEscolhido, dataFatura, produtosEscolhidos);
        faturas.add(novaFatura);

        System.out.println("Fatura criada com sucesso!");
        System.out.println("Número da Fatura: " + numeroFatura);
        System.out.println("Cliente: " + clienteEscolhido.getNome());
        System.out.println("Data: " + dataFatura);
        System.out.println("Produtos na fatura:");
        for (Produto produto : produtosEscolhidos) {
            System.out.println("- " + produto.getNome()); // Assumindo que Produto tem o método getNome()
        }
    }
    public static void editarFatura(ArrayList<Cliente> clientes, ArrayList<Fatura> faturas, ArrayList<Produto> produtos) {
        Scanner scanner = new Scanner(System.in);

        // Exibir as faturas existentes
        System.out.println("Selecione a fatura que deseja editar:");
        for (int i = 0; i < faturas.size(); i++) {
            System.out.println((i + 1) + " -> Fatura Nº: " + faturas.get(i).getNumeroFatura() + " | Cliente: "
                    + faturas.get(i).getCliente().getNome() + " | Data: " + faturas.get(i).getData());
        }

        // Escolher uma fatura para editar
        int faturaIndex = -1;
        while (faturaIndex < 0 || faturaIndex >= faturas.size()) {
            System.out.print("Escolha o número correspondente à fatura: ");
            faturaIndex = scanner.nextInt() - 1; // Converte de 1-based para 0-based
            if (faturaIndex < 0 || faturaIndex >= faturas.size()) {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }
        Fatura faturaEscolhida = faturas.get(faturaIndex);

        // Alterar o cliente
        System.out.println("Cliente atual: " + faturaEscolhida.getCliente().getNome());
        System.out.println("Deseja alterar o cliente? (s/n): ");
        String alterarCliente = scanner.next();
        if (alterarCliente.equalsIgnoreCase("s")) {
            System.out.println("Escolha o novo cliente ou crie um novo:");
            for (int i = 0; i < clientes.size(); i++) {
                System.out.println((i + 1) + " -> " + clientes.get(i).getNome());
            }
            System.out.println("0 -> Criar novo cliente");

            int clienteIndex = -1;
            while (clienteIndex < 0 || clienteIndex > clientes.size()) {
                System.out.print("Escolha o número correspondente ao cliente: ");
                clienteIndex = scanner.nextInt() - 1; // Converte de 1-based para 0-based
                System.out.println("O NUMERO QUE ESCOLHI "+ clienteIndex+"\n");
                if (clienteIndex >= -1 || clienteIndex <= clientes.size()) {
                    break;
                }else
                    System.out.println("Opção inválida. Tente novamente.");
            }

            if (clienteIndex == -1) { // Opção para criar um novo cliente
                System.out.println("VOU CRIAR UM NOVO\n");
                Cliente novoCliente = registar(clientes);
                faturaEscolhida.setCliente(novoCliente);
            } else {
                faturaEscolhida.setCliente(clientes.get(clienteIndex));
            }
        }

        // Alterar a data
        System.out.println("Data atual: " + faturaEscolhida.getData());
        System.out.println("Deseja alterar a data? (s/n): ");
        String alterarData = scanner.next();
        if (alterarData.equalsIgnoreCase("s")) {
            System.out.print("Insira a nova data (yyyy-MM-dd): ");
            String novaData = scanner.next();
            faturaEscolhida.setData(novaData);
        }

        // Alterar os produtos
        System.out.println("Produtos atuais na fatura:");
        for (Produto produto : faturaEscolhida.getProdutos()) {
            System.out.println("- " + produto.getNome());
        }
        System.out.println("Deseja alterar os produtos? (s/n): ");
        String alterarProdutos = scanner.next();
        if (alterarProdutos.equalsIgnoreCase("s")) {
            ArrayList<Produto> novosProdutos = new ArrayList<>();
            boolean adicionarMaisProdutos = true;

            while (adicionarMaisProdutos) {
                System.out.println("Escolha os produtos a adicionar:");
                for (int i = 0; i < produtos.size(); i++) {
                    System.out.println((i + 1) + " -> " + produtos.get(i).getNome());
                }

                int produtoIndex = -1;
                while (produtoIndex < 0 || produtoIndex >= produtos.size()) {
                    System.out.print("Escolha o número correspondente ao produto: ");
                    produtoIndex = scanner.nextInt() - 1;
                    if (produtoIndex < 0 || produtoIndex >= produtos.size()) {
                        System.out.println("Opção inválida. Tente novamente.");
                    }
                }
                novosProdutos.add(produtos.get(produtoIndex));

                // Perguntar se deseja adicionar mais produtos
                System.out.print("Deseja adicionar mais produtos? (s/n): ");
                String resposta = scanner.next();
                adicionarMaisProdutos = resposta.equalsIgnoreCase("s");
            }

            faturaEscolhida.setProdutos(novosProdutos);
        }

        // Exibir resumo da edição
        System.out.println("Fatura editada com sucesso!");
        System.out.println("Número da Fatura: " + faturaEscolhida.getNumeroFatura());
        System.out.println("Cliente: " + faturaEscolhida.getCliente().getNome());
        System.out.println("Data: " + faturaEscolhida.getData());
        System.out.println("Produtos na fatura:");
        for (Produto produto : faturaEscolhida.getProdutos()) {
            System.out.println("- " + produto.getNome());
        }
    }
}


