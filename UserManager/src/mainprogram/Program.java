package mainprogram;
import usermanager.*;

public class Program{
    public static void main(String[] args) throws Exception{

        // Instância da classe File (File.java)
        Manager mainProgram = new Manager("data");
        User user = new User();

        boolean exit = false;
        String check;
        
        do {
            exit = mainProgram.menu(user);
        } while (!exit);
    }
}
