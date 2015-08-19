package mainprogram;
import usermanager.User;
/**
 *
 * @author tumeo
 */
public class CommomUser extends User{
    private String nome = "nome", id = "id", food = "food", idade = "idade";
    
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
