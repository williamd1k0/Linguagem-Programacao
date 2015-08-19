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
        
        UserManager mainProg = new UserManager("data", "cep");
        
        boolean exit, hasArgs = true;
        boolean cmdMode = false;
        
        try{
            String misc = args[0];  
        }catch(Exception e){
            hasArgs = false;
        }
        
        if(hasArgs && mainProg.isCmdMode(args[0])){  
            mainProg.exeArgs(args);
            cmdMode = true;
        }else if(hasArgs && !mainProg.isCmdMode(args[0])){
            mainProg.exeArgs(args);   
        }
        
        if(!cmdMode){
            do{
                exit = mainProg.menu();
            }while(!exit);
        }
    }
}
