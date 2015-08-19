package datamanager;

/** <h1>Classe User</h1>
 * Tem a função de gerar uma sub-classe contendo no mínimo um ID, um nome e um tipo.
 * @author tumeo
 */
public class User{
    
    private final String[] TYPES = {"comum","root"};
    private String nome = "nome", id = "id", type = TYPES[0];

    
    /** <h1>Método construtor 1</h1>
     * Sem argumentos.
     */
    public User(){}

    /** <h1>Método construtor 2</h1>
     * Cria uma instância para ser usada como base para os arquivos que serão criados.
     * @param id(String) Pré-setar um {@link id}.
     * @param nome(String) Pré-setar um {@link nome}.
     * @param type(String) Pré-setar um {@link type}.
     */
    public User(String id, String nome, String type){      
        this.id = id;
        this.nome = nome;
        this.type = type;
    }
    
    /** <h1>Alterar ID</h1>
     * Altera o valor de {@link #id}
     * @param id(String): Valor para definir o novo ID.
     */
    public void setId(String id){
        this.id = id;
    }

    /** <h1>Obter ID</h1>
     * Obtem o valor de {@link #id}
     * @return(String) {@link #id}
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
    
    public void setType(String type){
        this.type = type;
    }
    
    public String getType(){
            return this.type;
    }
    
}
