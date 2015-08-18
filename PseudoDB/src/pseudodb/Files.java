package pseudodb;

import java.io.*;

/**
 *
 * @author tumeo
 */
public class Files {

    /**
     *
     * @param path
     * @param file
     * @param texto
     */
    public void writeFile(String path, String file, String texto) {
        
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

    /**
     *
     * @param path
     * @param file
     * @return
     * @throws Exception
     */
    public String readFile(String path, String file) throws Exception {

        FileReader fr = new FileReader(path + file);
        int i;
        String texto = "";
        while ((i = fr.read()) != -1) {
            texto += (char) i;
        }
        fr.close();
        return texto;
    }

    /**
     *
     * @param directory
     * @return
     */
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
