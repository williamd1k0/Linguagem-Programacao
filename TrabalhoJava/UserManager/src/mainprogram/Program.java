package mainprogram;
import adminmanager.*;
import usermanager.*;

/**
 * @author William Tumeo
 */
public class Program{
    /**
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        
        AdminManager mainAdm = new AdminManager("root", "adm");
        UserManager mainProg = new UserManager("data", "cep");
        
        boolean exit, exit2;
        
        exit = false;
        
        do{
            String mainLoop = mainAdm.mainMenu();
            
            switch(mainLoop){
                case "1": 
                    do{
                        exit = mainAdm.menu();
                    }while(!exit);
                    exit = false;
                break;

                case "2":
                    do{
                        exit2 = mainProg.menu();
                    }while(!exit2);
                break;
                
                case "3":System.out.println("Sem opções ainda");
                break;
                case "4": exit = true;

            }
        }while(!exit);
    }
}
