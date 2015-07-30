import javax.swing.*;
import java.io.*;

public class Program{
    public static void main(String[] args) throws Exception{

        // Instância da classe File (File.java)
        Files file = new Files();
        User user = new User();

        boolean isNew = false;
        String check;

        user.email = JOptionPane.showInputDialog("Digite seu email:");

        try{
            check = file.readFile(user.email+File.separator, file.nome);
        }catch(Exception erro){

        }finally{
            isNew = true;
        }

        if(isNew){
            File pasta = new File(user.email);
            pasta.mkdir();
            file.newUser(user);
        }else{
            JOptionPane.showMessageDialog(null,"Este usuário ja existe!");
        }

        // Lê o arquivo e joga no prompt
        //System.out.println(file.readFile(caminho, arquivo));

    }

}
