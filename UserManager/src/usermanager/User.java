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
    public User(){
        System.out.println("Usuário vazio.");
    }

    /** <h1>Método construtor 2</h1>
     * Cria uma instância para ser usada como base para os arquivos que serão criados.
     * @param ext(String): extensão dos arquivos que serão criados.
     */
    public User(String ext){
        ext = "."+ext;
        this.id += ext;
        this.nome += ext;
        this.idade += ext;
        this.food += ext;
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
