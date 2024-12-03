import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
        lerFicheiroTexto(ficheiro, clientes, faturas, produtos,0);//0-> ler o ficheiro.txt, 1->ler as faturas extra

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
            System.out.print("= Escolha uma opção: ");

            int opcao;
            try {
                opcao = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Opção inválida! Por favor, escolha uma opção válida.");
                scanner.nextLine();
                continue;
            }


            switch (opcao) {
                case 1:
                    registar(clientes);
                    break;
                case 2:
                    editar(clientes, faturas, produtos);
                    break;
                case 3:
                    listarClientes(clientes);
                    break;
                case 4:
                    criarFaturas( clientes, faturas, produtos);
                    break;
                case 5:
                    editarFatura( clientes, faturas, produtos);
                    break;
                case 6:
                    listarFaturas(clientes,faturas);
                    break;
                case 7:
                    visualizarFatura(faturas);
                    break;
                case 8:
                    Ficheiro ficheiro1 = new Ficheiro("src/faturasExtra.txt");
                    lerFicheiroTexto(ficheiro1, clientes, faturas, produtos,1);
                    listarFaturas(clientes,faturas);
                    break;
                case 9:
                    criarFicheiroDeTexto(faturas);
                    break;
                case 10:
                    mostrarEstatisticas(faturas, produtos);
                    break;
                case 11://Sair
                    System.out.println("Saindo do programa...");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida! Por favor, escolha uma opção válida.");
            }
            System.out.println();
        }

        scanner.close();
    }
    private static void lerFicheiroTexto(Ficheiro ficheiro, ArrayList<Cliente> clientes, ArrayList<Fatura> faturas, ArrayList<Produto> produtos, int type) {
        // Processar as linhas lidas
        for (String linha : ficheiro.linhas) {
            String[] parts = linha.split("\\|"); // Dividir a linha pelo separador "|"
            if(type == 0) {
                switch (parts[0]) {
                    case "Cliente":
                        // Criar um cliente
                        clientes.add(new Cliente(parts[1], parts[2], parts[3]));
                        //FicheiroO.salvarObjetos("FicheiroObj", parts[1], parts[2], parts[3]);
                        break;

                    case "ProdutoF":
                    case "ProdutoA":
                        // Criar um produto farmacêutico ou alimentar
                        Produto produto;
                        if (parts[0].equals("ProdutoF")) {
                            produto = new Farmacia(parts[1], parts[2], parts[3], Double.parseDouble(parts[5]), Integer.parseInt(parts[4]), parts[6]);
                        } else {
                            ArrayList<String> certOuCat = new ArrayList<>();
                            for (int i = 7; i < parts.length; i++) {
                                certOuCat.add(parts[i]);
                            }
                            produto = new Alimentar(parts[1], parts[2], parts[3], Double.parseDouble(parts[5]), Integer.parseInt(parts[4]), Boolean.parseBoolean(parts[6]), certOuCat);
                        }
                        produtos.add(produto);
                        break;

                    case "Fatura":
                        // Criar uma fatura
                        ArrayList<Produto> produtoFatura = new ArrayList<>();
                        for (Produto produto1 : produtos) {
                            if (produto1.getCodigo().equals(parts[4])) {
                                produtoFatura.add(produto1);
                            }
                        }

                        Cliente cliente = null;
                        for (Cliente cliente1 : clientes) {
                            if (cliente1.getContribuinte().equals(parts[2])) {
                                cliente = cliente1;
                                break;
                            }
                        }
                        int id = Integer.parseInt(parts[1]);
                        faturas.add(new Fatura(id, cliente, parts[3], produtoFatura));
                        break;

                    default:
                        System.out.println("Linha inválida: " + linha);
                        break;
                }
            }
            else if(type == 1) {
                System.out.println();
                // Obter o número de contribuinte (cliente)
                String contribuinte = parts[0];
                Cliente cliente = null;

                // Encontrar o cliente correspondente pelo contribuinte
                for (Cliente cliente1 : clientes) {
                    if (cliente1.getContribuinte().equals(contribuinte)) {
                        cliente = cliente1;
                        break;
                    }
                }

                // Verificar se o cliente foi encontrado
                if (cliente == null) {
                    System.out.println("Cliente não encontrado para o contribuinte: " + contribuinte);
                    continue;
                }

                // Obter a data
                String data = parts[1];

                // Obter os códigos dos produtos a partir da terceira posição em diante
                ArrayList<Produto> produtoFatura = new ArrayList<>();
                for (int i = 2; i < parts.length; i++) {
                    String codigoProduto = parts[i];
                    for (Produto produto : produtos) {
                        if (produto.getCodigo().equals(codigoProduto)) {
                            produtoFatura.add(produto);
                            break;
                        }
                    }
                }

                // Verificar se todos os produtos foram encontrados
                if (produtoFatura.isEmpty()) {
                    System.out.println("Nenhum produto encontrado para a linha: " + linha);
                    continue;
                }

                // Determinar o próximo ID da fatura
                int numeroFatura=0;
                for(Fatura fatura : faturas) {
                    if(fatura.getNumeroFatura()>numeroFatura){
                        numeroFatura=fatura.getNumeroFatura();
                    }
                }
                numeroFatura++;
                // Criar a nova fatura e adicioná-la à lista de faturas
                Fatura novaFatura = new Fatura(numeroFatura, cliente, data, produtoFatura);
                faturas.add(novaFatura);

                // Opcional: Exibir um resumo da fatura criada
                System.out.println("= Fatura criada com sucesso: ID " + numeroFatura + ", Cliente " + cliente.getNome() + ", Data " + data);
            }
        }
    }

    private static void criarFicheiroDeTexto(ArrayList<Fatura> faturasss){
        Scanner scanner = new Scanner(System.in);

        String nomeFicheiro;
        while (true) {
            System.out.print("= Insira o nome do ficheiro (ex.: faturasExtra.txt): ");
            nomeFicheiro = scanner.nextLine();

            if (nomeFicheiro.endsWith(".txt")) {
                break;
            } else {
                System.out.println("Erro: O nome do ficheiro deve terminar com \".txt\". Tente novamente.");
            }
        }

        try {
            // Criar o objeto File
            File ficheiro = new File("src/"+nomeFicheiro);

            // Verificar se o ficheiro já existe
            if (ficheiro.createNewFile()) {
                System.out.println("Ficheiro criado: " + ficheiro.getName());
            } else {
                System.out.println("O ficheiro já existe.");
                return;
            }

            // Escrever no ficheiro
            FileWriter escritor = new FileWriter(ficheiro);

            // Escrever o conteúdo das faturas
            for (Fatura fatura : faturasss) {
                StringBuilder linha = new StringBuilder();
                linha.append(fatura.getCliente().getContribuinte()).append("|")
                        .append(fatura.getData()).append("|");

                // Adicionar os códigos dos produtos
                for (Produto produto : fatura.getProdutos()) {
                    linha.append(produto.getCodigo()).append("|");
                }

                // Remover o último "|" e adicionar uma nova linha
                if (linha.charAt(linha.length() - 1) == '|') {
                    linha.deleteCharAt(linha.length() - 1);
                }
                linha.append("\n");

                // Escrever a linha no ficheiro
                escritor.write(linha.toString());
            }

            escritor.close(); // Fechar o escritor
            System.out.println("Conteúdo gravado no ficheiro: " + nomeFicheiro);
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao criar o ficheiro.");
            e.printStackTrace();
        }
    }

    private static Cliente registar( ArrayList<Cliente> clientes) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n= Introduza o seu nome: ");
        String nome = scanner.nextLine();
        String contribuinte;
        while (true) {
            boolean flag=true;
            System.out.println("= Introduza o seu contribuinte: ");
            contribuinte = scanner.nextLine();
            if(contribuinte.length() == 9 ) {
                for(int i=0;i<contribuinte.length();i++)
                {
                    if(!Character.isDigit(contribuinte.charAt(i)))
                    {
                        flag = false;
                        break;
                    }

                }
                if (flag) {
                    for(Cliente c: clientes)
                    {
                        if(c.getContribuinte().equals(contribuinte))
                        {
                            flag=false;
                            break;
                        }
                    }
                }
                if(flag) {
                    break;
                }
            }
            else {
                System.out.println("Contribuinte inválido! Certifique-se de que tem exatamente 9 dígitos.");
            }
        }
        String localizacao;
        while (true) {
            System.out.println("= Introduza a sua localização (Acores, Madeira ou Portugal Continental): ");
            localizacao = scanner.nextLine();

            if (localizacao.equals("Acores") || localizacao.equals("Madeira") || localizacao.equals("Portugal Continental")) {
                break;
            }
            System.out.println("Localização invalida! Por favor, insira 'Acores', 'Madeira', ou 'Portugal Continental'.");
        }
        Cliente cliente = new Cliente(nome,contribuinte,localizacao);
        clientes.add(cliente);

        System.out.println("==== Cliente criado com sucesso! ====");

        return cliente;
    }
    private static void editar( ArrayList<Cliente> clientes, ArrayList<Fatura> faturas, ArrayList<Produto> produtos) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n= Introduza o seu nome: ");
        String nome = scanner.nextLine();
        System.out.println("= Introduza o seu contribuinte: ");
        String contribuinte = scanner.nextLine();
        Cliente fim = null;
        for (Cliente c : clientes) {
            if (c.getNome().equals(nome) && c.getContribuinte().equals(contribuinte) /*&& c.getLocalizacao().equals(localizacao)*/) {
                System.out.println("======== Editar Dados =======");
                System.out.println("= 1. Nome de Cliente        = ");
                System.out.println("= 2. Contribuinte de Cliente= ");
                System.out.println("= 3. Localizacao de Cliente = ");
                System.out.println("= 4. === MENU PRINCIPAL === = ");
                System.out.println("=============================");
                System.out.print("= Escolha uma opção: ");
                int opcao;
                try {
                    opcao = scanner.nextInt();
                    scanner.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("Opção inválida! Por favor, escolha uma opção válida.");
                    scanner.nextLine();
                    continue;
                }
                switch (opcao) {
                    case 1:
                        System.out.println("= Introduza o seu novo nome: ");
                        String novoNome = scanner.nextLine();
                        c.setNome(novoNome);
                        fim = c;
                        break;
                    case 2:
                        String novoContribuinte;
                        while (true) {
                            boolean flag=true;
                            System.out.println("= Introduza o seu novo contribuinte: ");
                            novoContribuinte = scanner.nextLine();
                            fim = c;
                            if(novoContribuinte.length() == 9) {
                                for (int i = 0; i < novoContribuinte.length(); i++) {
                                    if (!Character.isDigit(novoContribuinte.charAt(i))) {
                                        flag = false;
                                        break;
                                    }
                                }
                                if (flag) {
                                    for (Cliente cliente : clientes) {
                                        if (cliente.getContribuinte().equals(novoContribuinte)) {
                                            flag = false;
                                            break;
                                        }
                                    }
                                }
                                if (flag) {
                                    break;
                                }
                            } else {
                                System.out.println("Contribuinte inválido! Certifique-se de que tem exatamente 9 dígitos.");
                            }
                        }
                        c.setContribuinte(novoContribuinte);
                        break;
                    case 3:
                        String novaLoc;
                        while (true) {
                            System.out.println("= Introduza a sua nova localização (Acores, Madeira ou Portugal Continental): ");
                            novaLoc = scanner.nextLine();

                            if (novaLoc.equals("Acores") || novaLoc.equals("Madeira") || novaLoc.equals("Portugal Continental")) {
                                fim = c;
                                break;
                            }
                            System.out.println("Localização invalida! Por favor, insira 'Acores', 'Madeira', ou 'Portugal Continental'.");
                        }
                        c.setLocalizacao(novaLoc);
                        break;
                    case 4:
                        menuSistema(clientes, faturas, produtos);
                        break;

                }
            }
        }
        if(fim != null){
            System.out.println("==== Cliente editado com sucesso ====");
            System.out.println("= Dados Atualizados: Nome: "+ fim.getNome() +", contribuinte: "+ fim.getContribuinte()+", localizacao: "+ fim.getLocalizacao()+".");
        }
    }

    private static void listarClientes( ArrayList<Cliente> clientes) {
        System.out.println("==== Todos os clientes pertencentes ao sistema: ====");
        for (Cliente c : clientes) {
            System.out.println("= "+c.getNome() + ", " + c.getContribuinte() + ", " + c.getLocalizacao()+" =");

        }
    }

    private static void listarFaturas( ArrayList<Cliente> clientes,ArrayList<Fatura> faturas) {
        System.out.println("==== Os dados de todas as faturas: =====\n");
        for (Fatura fatura : faturas) {
            Cliente cliente = fatura.getCliente();


            // Obtém a lista de produtos da fatura
            ArrayList<Produto> produtosFatura = fatura.getProdutos();


            // Exibe as informações da fatura
            System.out.printf("= Fatura Número: %d, "
                            + "cliente: %s, "
                            + "localização: %s, "
                            + "número de Produtos: %d, "
                            + "valor Total Sem IVA: %.2f, "
                            + "valor Total com IVA: %.2f\n",
                    fatura.getNumeroFatura(),
                    cliente.getNome(),
                    cliente.getLocalizacao(),
                    produtosFatura.size(),
                    fatura.calcularPrecoSemIva(),
                    fatura.calcularPrecoComIva());
        }
    }
    private static void visualizarFatura( ArrayList<Fatura> faturas) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("==== Para visualizar a fatura de um cliente, precisa de inserir os dados do mesmo! ====");
        System.out.println("= Introduza o nome: ");
        String nome = scanner.nextLine();
        System.out.println("= Introduza o contribuinte: ");
        String contribuinte = scanner.nextLine();
        for(Fatura fatura : faturas){
            if(fatura.getCliente().getNome().equals(nome) && fatura.getCliente().getContribuinte().equals(contribuinte)){
                System.out.println("\n======== Visualizar Fatura =======");
                System.out.println("= Número da Fatura: " + fatura.getNumeroFatura());
                System.out.println("= Cliente: " + fatura.getCliente().toString());
                System.out.println("= Dados dos produtos presentes:");
                for(Produto produto : fatura.getProdutos()){
                    System.out.println("\n= -> Nome: " + produto.getNome());
                    System.out.println("= -> Quantidade: " + produto.getQuantidade());
                    System.out.printf("= -> Valor Total sem IVA: %.2f\n",produto.calcularPrecoTotalSemIva());
                    System.out.printf("= -> Taxa do IVA: %d%%\n", produto.calcularTaxa(fatura.getCliente().getLocalizacao()));
                    System.out.printf("= -> Valor do IVA(€): %.2f\n", produto.calcularPrecoTotalSemIva() * (produto.calcularTaxa(fatura.getCliente().getLocalizacao())/100));//acho que nao vale a pena criar uma função para isto
                    System.out.printf("= -> Valor Total com IVA: %.2f\n", produto.calcularPrecoComIvaIndividual(fatura.getCliente().getLocalizacao()));
                }
                System.out.println("= Preço Total sem IVA(€): "+fatura.calcularPrecoSemIva());
                System.out.println("= Valor Total do IVA(€): "+ (fatura.calcularPrecoComIva()-fatura.calcularPrecoSemIva()));
                System.out.println("= Preço Total com IVA(€): "+fatura.calcularPrecoComIva());
                System.out.println("=====================================");
            }
        }
    }
    private static void criarFaturas(ArrayList<Cliente> clientes, ArrayList<Fatura> faturas, ArrayList<Produto> produtos) {
        Scanner scanner = new Scanner(System.in);

        // Escolher ou criar um cliente
        System.out.println("\n= A que pessoa está associado?");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println((i + 1) + " -> " + clientes.get(i).getNome());
        }
        System.out.println("0 -> Criar novo cliente");

        int clienteIndex = -1;
        while (clienteIndex < 0 || clienteIndex > clientes.size()) {
            System.out.print("= Escolha o número correspondente ao cliente: ");
            clienteIndex = scanner.nextInt() - 1; // Converte de 1-based para 0-based
            if (clienteIndex >= -1 && clienteIndex <= clientes.size()) {
                break;
            }else
                System.out.println("Opção inválida. Tente novamente.");
        }

        Cliente clienteEscolhido;
        if (clienteIndex == -1) { // Criar novo cliente
            clienteEscolhido = registar(clientes); // Método para registrar um cliente
        } else {
            clienteEscolhido = clientes.get(clienteIndex);
        }

        // Adicionar produtos à fatura
        ArrayList<Produto> produtosEscolhidos = new ArrayList<>();
        boolean adicionarMaisProdutos = true;
        while (adicionarMaisProdutos) {
            System.out.println("\n= Escolha os produtos a adicionar:");
            for (int i = 0; i < produtos.size(); i++) {
                System.out.printf("%d -> %s (%.2f €/unid.)\n", i + 1, produtos.get(i).getNome(), produtos.get(i).calcularPrecoComIvaIndividual(clienteEscolhido.getLocalizacao()));
            }

            int produtoIndex = -1;
            while (produtoIndex < 0 || produtoIndex >= produtos.size()) {
                System.out.print("= Escolha o número correspondente ao produto: ");
                produtoIndex = scanner.nextInt() - 1; // Converte de 1-based para 0-based
                if (produtoIndex < 0 || produtoIndex >= produtos.size()) {
                    System.out.println("Opção inválida. Tente novamente.");
                }
            }
            produtosEscolhidos.add(produtos.get(produtoIndex));
            Produto produtoEscolhido = produtos.get(produtoIndex);
            System.out.println("= Quantidade: ");
            int quantidade = scanner.nextInt();
            produtoEscolhido.setQuantidade(quantidade);
            produtosEscolhidos.add(produtoEscolhido);

            // Perguntar se deseja adicionar mais produtos
            System.out.print("= Deseja adicionar mais produtos? (s/n): ");
            String resposta = scanner.next();
            adicionarMaisProdutos = resposta.equalsIgnoreCase("s");
        }

        // Criar a fatura
        int numeroFatura=0;
        for(Fatura fatura : faturas) {
            if(fatura.getNumeroFatura()>numeroFatura){
                numeroFatura=fatura.getNumeroFatura();
            }
        }
        numeroFatura++;

        LocalDate dataAtual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dataFatura = dataAtual.format(formatter);

        // Instancia a nova fatura e adiciona à lista de faturas
        Fatura novaFatura = new Fatura(numeroFatura, clienteEscolhido, dataFatura, produtosEscolhidos);
        faturas.add(novaFatura);

        System.out.println("==== Fatura criada com sucesso! ====");
        System.out.println("= Número da Fatura: " + numeroFatura);
        System.out.println("= Cliente: " + clienteEscolhido.getNome());
        System.out.println("= Data: " + dataFatura);
        System.out.printf("= Preço Total: %.2f€\n", novaFatura.calcularPrecoComIva());
        System.out.println("= Produtos na fatura:");
        for (Produto produto : produtosEscolhidos) {
            System.out.println("= -> " + produto.getNome()); // Assumindo que Produto tem o método getNome()
            System.out.println("= -> " + produto.getNome()+ " ("+ produto.getQuantidade() +")"); // Assumindo que Produto tem o método getNome()
        }
    }
    private static void editarFatura(ArrayList<Cliente> clientes, ArrayList<Fatura> faturas, ArrayList<Produto> produtos) {
        Scanner scanner = new Scanner(System.in);

        // Exibir as faturas existentes
        System.out.println("\n= Selecione a fatura que deseja editar:");
        for (int i = 0; i < faturas.size(); i++) {
            System.out.println((i + 1) + " -> Fatura Nº: " + faturas.get(i).getNumeroFatura() + " | Cliente: "
                    + faturas.get(i).getCliente().getNome() + " | Data: " + faturas.get(i).getData());
        }

        // Escolher uma fatura para editar
        int faturaIndex = -1;
        while (faturaIndex < 0 || faturaIndex >= faturas.size()) {
            System.out.print("= Escolha o número correspondente à fatura: ");
            faturaIndex = scanner.nextInt() - 1; // Converte de 1-based para 0-based
            if (faturaIndex < 0 || faturaIndex >= faturas.size()) {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }
        Fatura faturaEscolhida = faturas.get(faturaIndex);

        // Alterar o cliente
        System.out.println("\n= Cliente atual: " + faturaEscolhida.getCliente().getNome());
        System.out.println("= Deseja alterar o cliente desta fatura? (s/n): ");
        String alterarCliente = scanner.next();
        if (alterarCliente.equalsIgnoreCase("s")) {
            System.out.println("= Escolha o novo cliente ou crie um novo:");
            for (int i = 0; i < clientes.size(); i++) {
                System.out.println((i + 1) + " -> " + clientes.get(i).getNome());
            }
            System.out.println("0 -> Criar novo cliente");

            int clienteIndex = -1;
            while (clienteIndex < 0 || clienteIndex > clientes.size()) {
                System.out.print("= Escolha o número correspondente ao cliente: ");
                clienteIndex = scanner.nextInt() - 1; // Converte de 1-based para 0-based
                if (clienteIndex >= -1 && clienteIndex <= clientes.size()) {
                    break;
                }else
                    System.out.println("Opção inválida. Tente novamente.");
            }

            if (clienteIndex == -1) { // Opção para criar um novo cliente
                Cliente novoCliente = registar(clientes);
                faturaEscolhida.setCliente(novoCliente);
            } else {
                faturaEscolhida.setCliente(clientes.get(clienteIndex));
            }
        }

        // Alterar a data
        System.out.println("= Data atual: " + faturaEscolhida.getData());
        System.out.println("= Deseja alterar a data? (s/n): ");
        String alterarData = scanner.next();
        if (alterarData.equalsIgnoreCase("s")) {
            System.out.print("= Insira a nova data (yyyy-MM-dd): ");
            String novaData = scanner.next();
            faturaEscolhida.setData(novaData);
        }

        // Alterar os produtos
        System.out.println("\n= Produtos atuais na fatura:");
        for (Produto produto : faturaEscolhida.getProdutos()) {
            System.out.println("= -> " + produto.getNome());
        }
        System.out.println("\n= Deseja alterar os produtos?(s/n) Se quiser alterar, a lista de produtos atual é removida!");
        String alterarProdutos = scanner.next();
        if (alterarProdutos.equalsIgnoreCase("s")) {
            ArrayList<Produto> novosProdutos = new ArrayList<>();
            boolean adicionarMaisProdutos = true;

            while (adicionarMaisProdutos) {
                System.out.println("= Escolha os produtos a adicionar:");
                for (int i = 0; i < produtos.size(); i++) {
                    System.out.println((i + 1) + " -> " + produtos.get(i).getNome());
                }

                int produtoIndex = -1;
                while (produtoIndex < 0 || produtoIndex >= produtos.size()) {
                    System.out.print("= Escolha o número correspondente ao produto: ");
                    produtoIndex = scanner.nextInt() - 1;
                    if (produtoIndex < 0 || produtoIndex >= produtos.size()) {
                        System.out.println("Opção inválida. Tente novamente.");
                    }
                }
                novosProdutos.add(produtos.get(produtoIndex));

                // Perguntar se deseja adicionar mais produtos
                System.out.print("= Deseja adicionar mais produtos? (s/n): ");
                String resposta = scanner.next();
                adicionarMaisProdutos = resposta.equalsIgnoreCase("s");
            }

            faturaEscolhida.setProdutos(novosProdutos);
        }
        // Exibir resumo da edição
        System.out.println("==== Fatura editada com sucesso! ====");
        System.out.println("= Novos dados da fatura:");
        System.out.println("-> Número da Fatura: " + faturaEscolhida.getNumeroFatura());
        System.out.println("-> Cliente: " + faturaEscolhida.getCliente().getNome());
        System.out.println("-> Data: " + faturaEscolhida.getData());
        System.out.printf("-> Preço Total: %.2f€\n", faturaEscolhida.calcularPrecoComIva());
        System.out.println("-> Produtos na fatura:");
        for (Produto produto : faturaEscolhida.getProdutos()) {
            System.out.println("- " + produto.getNome());
        }
    }

    private static void mostrarEstatisticas(ArrayList<Fatura> faturas, ArrayList<Produto> produtos){
        int numFaturas = faturas.size();
        int numProdutos = produtos.size();
        double valorTotalSemIVA = 0.0;
        double valorTotalDoIVA = 0.0;
        double valorTotalComIVA = 0.0;
        for(Fatura fatura :faturas){
            valorTotalSemIVA += fatura.calcularPrecoSemIva();
            valorTotalComIVA += fatura.calcularPrecoComIva();
        }
        valorTotalDoIVA += (valorTotalComIVA - valorTotalSemIVA);
        System.out.println("===== Estatísticas das Faturas =====");
        System.out.println("= Total de faturas: " + numFaturas);
        System.out.println("= Total de produtos: " + numProdutos);
        System.out.printf("= Valor Total sem IVA(€): %.2f\n",valorTotalSemIVA);
        System.out.printf("= Valor Total do IVA(€): %.2f\n",valorTotalDoIVA);
        System.out.printf("= Valor Total com IVA(€): %.2f\n",valorTotalComIVA);
    }
}

