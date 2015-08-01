import javax.swing.*;
import java.io.*;

public class Program{
    public static void main(String[] args) throws Exception{

        // Instância da classe File (File.java)
        Files file = new Files();
        User user = new User();

        boolean isNew = false, exit = false;
        String check;

        do {
            exit = file.menu(user);
        } while (!exit);

    }

}
