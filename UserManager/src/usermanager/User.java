package usermanager;

public class User{
    private String nome = "nome", id = "id", food = "food", idade = "idade";
    
    public User(){
        System.out.println("Usuário vazio.");
    }
    public User(String ext){
        ext = "."+ext;
        this.id += ext;
        this.nome += ext;
        this.idade += ext;
        this.food += ext;
    }
    
    public void setId(String id){
        this.id = id;
    }
    public String getId(){
            return this.id;
    }
    
    public void setName(String name){
        this.nome = name;
    }
    public String getName(){
            return this.nome;
    }
    
    public void setIdade(String num){
        this.idade = num;
    }
    public void setIdade(int num){
        this.idade = String.valueOf(num);
    }
    public String getIdade(){
            return this.idade;
    }
    
    public void setFood(String food){
        this.food = food;
    }
    public String getFood(){
            return this.food;
    }
}
