/*import java.io.Serializable;
import java.io.*;
import java.util.ArrayList;

public class FicheiroO implements Serializable {
    public static void salvarObjetos(String nomeFicheiro, ArrayList<Cliente> clientes, ArrayList<Fatura> faturas, ArrayList<Produto> produtos) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nomeFicheiro))) {
            // Escrever os objetos no arquivo
            out.writeObject(clientes);
            out.writeObject(faturas);
            out.writeObject(produtos);
            System.out.println("Objetos salvos com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void carregarObjetos(String nomeFicheiro, ArrayList<Cliente> clientes, ArrayList<Fatura> faturas, ArrayList<Produto> produtos) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(nomeFicheiro))) {
            // Ler os objetos do arquivo e atribuir às listas
            clientes.addAll((ArrayList<Cliente>) in.readObject());
            faturas.addAll((ArrayList<Fatura>) in.readObject());
            produtos.addAll((ArrayList<Produto>) in.readObject());
            System.out.println("Objetos carregados com sucesso.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}*/
