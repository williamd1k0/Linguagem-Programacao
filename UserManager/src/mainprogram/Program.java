package mainprogram;
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

        Manager mainProgram = new Manager("data");
        User user = new User();
        
        boolean exit, hasArgs = true;
        
        try{
            String misc = args[0];  
        }catch(Exception e){
            hasArgs = false;
        }
        
        if(hasArgs){
            mainProgram.exeArgs(args);
        }else{
            do{
                exit = mainProgram.menu(user);
            }while(!exit);
        }
    }
}
