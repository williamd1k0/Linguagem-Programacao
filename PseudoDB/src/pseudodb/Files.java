package pseudodb;

import java.io.*;

public class Files {

    public void writeFile(String path, String file, String texto) {
        // Escreve no arquivo
        // Args:
        //      String path: caminho do arquivo
        //      String file: nome do arquivo
        //      String texto: texto do arquivo
        try {
            FileWriter fw = new FileWriter(path + file);
            fw.write(texto);
            fw.close();

        } catch (Exception e) {
            System.out.println("Deu ruim!");
            System.out.println(e);
        }
        System.out.println("success");
    }

    public String readFile(String path, String file) throws Exception {
        // Lê o arquivo
        // Args:
        //      String path: caminho do arquivo
        //      String file: nome do arquivo
        // Retorno:
        //      String: todo o texto do arquivo

        FileReader fr = new FileReader(path + file);
        int i;
        String texto = "";
        while ((i = fr.read()) != -1) {
            texto += (char) i;
        }
        fr.close();
        return texto;
    }

    public boolean deleteFoler(File directory) {
        if (directory.exists()){
            File[] files = directory.listFiles();
            if (null != files) {
                for (int i = 0; i < files.length; i++) {
                    files[i].delete();
                }
            }
        }
        return directory.delete();
    }
}
