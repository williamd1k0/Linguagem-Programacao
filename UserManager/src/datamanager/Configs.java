package datamanager;
import java.util.Scanner;

/**
 *
 * @author tumeo
 */
public class Configs {
    
    /**
     *
     */
    protected final String CMD_MODE = "-cmd";
    private final Scanner read = new Scanner(System.in);
    
    /**
     *
     * @param args
     */
    @Deprecated
    public void exeArgs(String[] args){
        if (args[0].equals(this.CMD_MODE)){
            this.cmdMode();
        }else{
            this.cmdHelp();
        }
    }
    
    /**
     *
     */
    @Deprecated
    private void cmdMode(){
        System.out.println("================\n  User Manager\n================");
        System.out.print("\nDigite a senha do administrador: ");
    }
    
    /**
     *
     */
    @Deprecated
    private void cmdHelp(){
        System.out.println("");
    }
    
    @Deprecated
    public boolean isCmdMode(String args){
        return args.equals(this.CMD_MODE);
    }
    
}
