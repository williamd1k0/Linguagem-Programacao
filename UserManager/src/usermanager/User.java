package usermanager;

/**
 *
 * @author tumeo
 */
public class User{
    private String nome = "nome", id = "id", food = "food", idade = "idade";
    
    /** <h1>Método construtor 1</h1>
     * Sem argumentos.
     */
    public User(){}

    /** <h1>Método construtor 2</h1>
     * Cria uma instância para ser usada como base para os arquivos que serão criados.
     * @param id
     * @param nome
     * @param idade
     * @param food
     */
    public User(String id, String nome, String idade, String food){      
        this.id = id;
        this.nome = nome;
        this.idade += idade;
        this.food += food;
    }
    
    /** <h1>Alterar ID</h1>
     * Altera o valor de {@link #id}
     * @param id(String): Valor para definir o novo ID.
     */
    public void setId(String id){
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getId(){
            return this.id;
    }
    
    /**
     *
     * @param name
     */
    public void setName(String name){
        this.nome = name;
    }

    /**
     *
     * @return
     */
    public String getName(){
            return this.nome;
    }
    
}
