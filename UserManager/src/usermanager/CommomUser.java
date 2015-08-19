package usermanager;
import datamanager.User;

/** <h1>Classe CommomUser</h1>
 * Sub-classe de {@link datamanager.User}, usada especificamente para armazenar os dados de um usuário comum.
 * @author tumeo
 */
public class CommomUser extends User{
    private String food = "food", idade = "idade";
    
    /** <h1>Método construtor 1</h1>
     * Sem argumentos.
     */
    public CommomUser(){}
 
    /**
     *
     * @param num
     */
    public void setIdade(String num){
        this.idade = num;
    }

    /**
     *
     * @param num
     */
    public void setIdade(int num){
        this.idade = String.valueOf(num);
    }

    /**
     *
     * @return
     */
    public String getIdade(){
            return this.idade;
    }
    
    /**
     *
     * @param food
     */
    public void setFood(String food){
        this.food = food;
    }

    /**
     *
     * @return
     */
    public String getFood(){
            return this.food;
    }
}
