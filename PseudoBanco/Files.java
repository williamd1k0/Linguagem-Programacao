import java.io.*;
import javax.swing.*;

class Files{

    String nome = "nome.cep";
    String idade = "idade.cep";
    String nick = "nick.cep";
    String email = "email.cep";

    public void writeFile(String path, String file, String texto){
        // Escreve no arquivo
        // Args:
        //      String path: caminho do arquivo
        //      String file: nome do arquivo
        //      String texto: texto do arquivo
        try{
            FileWriter fw = new FileWriter(path+file);
            fw.write(texto);
            fw.close();

        }catch(Exception e){
            System.out.println("Deu ruim!");
            System.out.println(e);
        }
        System.out.println("success");
    }

    public String readFile(String path, String file)throws Exception{
        // Lê o arquivo
        // Args:
        //      String path: caminho do arquivo
        //      String file: nome do arquivo
        // Retorno:
        //      String: todo o texto do arquivo

        FileReader fr = new FileReader(path+file);
        int i;
        String texto = "";
        while((i=fr.read())!=-1){
            texto += (char)i;
        }
        fr.close();
        return texto;
    }
    public void newUser(User newUser){
        String path = newUser.email+File.separator;
        newUser.nome = JOptionPane.showInputDialog("Digite seu nome:");
        newUser.idade = JOptionPane.showInputDialog("Digite sua idade:");
        newUser.nick = JOptionPane.showInputDialog("Digite seu apelido:");
        this.writeFile(path, this.email, newUser.email);
        this.writeFile(path, this.nome, newUser.nome);
        this.writeFile(path, this.idade, newUser.idade);
        this.writeFile(path, this.nick, newUser.nick);
    }
}
