import java.io.*;
import java.util.ArrayList;

class Ficheiro {
    /**
     * Lista que armazena as linhas lidas do arquivo
     */
    ArrayList<String> linhas = new ArrayList<>();

    /**
     * Cria um objeto ficheiro através das linhas de um arquivo texto
     * @param nomeFicheiro nome do arquivo de texto
     */
    public Ficheiro(String nomeFicheiro) {
        File f = new File(nomeFicheiro);
        if (f.exists() && f.isFile()){
            try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
                String linha;
                while ((linha = reader.readLine()) != null) {
                    linhas.add(linha);
                }
            }catch (FileNotFoundException ex){
                System.out.println("Erro a abrir ficheiro de texto");
            } catch (IOException e) {
                System.out.println("Ocorreu um erro na leitura do ficheiro: ");
            }

        }else {
            System.out.println("Ficheiro não disponível");
        }
    }
}
