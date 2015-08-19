package mainprogram;
import java.io.File;
import javax.swing.JOptionPane;
import usermanager.*;
/**
 *
 * @author tumeo
 */

public class UserManager extends Manager{
    
    public final CommomUser DATA_FILES = new CommomUser();
    
    public UserManager(String folder, String ext) throws Exception{
        this.MAIN_FOLDER = folder+File.separator;
        SPECIAL_CHAR = "ᅠ";
        
        DATA_FILES.setId(DATA_FILES.getId()+"."+ext);
        DATA_FILES.setName(DATA_FILES.getName()+"."+ext);
        DATA_FILES.setIdade(DATA_FILES.getIdade()+"."+ext);
        DATA_FILES.setFood(DATA_FILES.getFood()+"."+ext);

        this.BACKUP_FOLDER = this.MAIN_FOLDER+File.separator+SPECIAL_CHAR;
        File pasta = new File(this.MAIN_FOLDER);
        if (!pasta.exists()) {
            pasta.mkdir();
            File backup = new File(this.BACKUP_FOLDER);
            backup.mkdir();
            DATA_BASE.writeFile(this.BACKUP_FOLDER+File.separator,"persistent", "1");
        }else{
            this.backupEnabled = this.checkPersistent();
        }
        this.folderCreated = true;
    }
    
    /**
     *
     * @param folder
     * @param ext
     * @param backfolder
     * @throws Exception
     */
    public UserManager(String folder, String ext, String backfolder) throws Exception{
        this.MAIN_FOLDER = folder+File.separator;
        SPECIAL_CHAR = backfolder;
        DATA_FILES.setId(DATA_FILES.getId()+"."+ext);
        DATA_FILES.setName(DATA_FILES.getName()+"."+ext);
        DATA_FILES.setIdade(DATA_FILES.getIdade()+"."+ext);
        DATA_FILES.setFood(DATA_FILES.getFood()+"."+ext);

        this.BACKUP_FOLDER = this.MAIN_FOLDER+File.separator+SPECIAL_CHAR;
        File pasta = new File(this.MAIN_FOLDER);
        if (!pasta.exists()) {
            pasta.mkdir();
            File backup = new File(this.BACKUP_FOLDER);
            backup.mkdir();
            DATA_BASE.writeFile(this.BACKUP_FOLDER+File.separator,"persistent", "1");
        }else{
            this.backupEnabled = this.checkPersistent();
        }
        this.folderCreated = true;
    }
    
    @Override
    public boolean menu() throws Exception {
        String opt;
        boolean exit = true;
        
        if(this.folderCreated){
            do {
                exit = false;
                opt = JOptionPane.showInputDialog("Menu:\n1. Criar usuário.\n2. Consultar.\n3. Alterar\n4. Deletar.\n5. Sair.");
            } while (!((opt.equals("1")) || (opt.equals("2")) || (opt.equals("3")) || (opt.equals("4")) || (opt.equals("5"))));

            switch (opt) {
                case "1":
                    this.createUser("Digite um username:","Este usuário já existe!");
                    break;
                case "2":
                    this.showUser(this.getById("Digite o username: ", "O usuário não foi encontrado"));
                    break;
                case "3":
                    this.modifyUser();
                    break;
                case "4":
                    this.deleteUser("Digite o username a ser deletado",
                                    "O usuário não foi encontrado",
                                    "Tem certeza que deseja excluir os dados de ",
                                    "O usuário foi deletado!",
                                    "Algo inesperado aconteceu!");
                    break;
                case "5":
                    exit = true;
                    break;
            }
        }
        return exit;
    }
    
    @Override
    public CommomUser catchById(String id) throws Exception {
        this.path = this.MAIN_FOLDER+id+File.separator;
        CommomUser getUser = new CommomUser();

        getUser.setId(id);
        getUser.setName(DATA_BASE.readFile(this.path, DATA_FILES.getName()));
        getUser.setIdade(DATA_BASE.readFile(this.path, DATA_FILES.getIdade()));
        getUser.setFood(DATA_BASE.readFile(this.path, DATA_FILES.getFood()));
        return getUser;
    }
    
    public void showUser(CommomUser u) {
        if(!u.getId().equals(SPECIAL_CHAR)){
            JOptionPane.showMessageDialog(null, "Usuário: " + u.getName() + "\nUsername: " + u.getId() + "\nIdade: " + u.getIdade() + "\nComida favorita: " + u.getFood());
        }
    }
    
    @Override
    public void modifyUser() throws Exception {
        CommomUser modUser = getById("Digite o username a ser alterado", "O usuário não foi encontrado");
        this.path = this.MAIN_FOLDER + modUser.getId() + File.separator;
        if (!modUser.getId().equals(SPECIAL_CHAR)){
            String opt;
            do {
                opt = "0";
                opt = JOptionPane.showInputDialog("Qual valor deseja alterar de " + modUser.getId() + "?\n1. Nome [" + modUser.getName() + "]\n2. Idade [" + modUser.getIdade() + "]\n3. Comida favorita [" + modUser.getFood() + "]\n4. Voltar.");

                switch (opt) {
                    case "1":
                        modUser.setName(JOptionPane.showInputDialog("Digite o novo nome:"));
                        DATA_BASE.writeFile(this.path, DATA_FILES.getName(), modUser.getName());
                        break;
                    case "2":
                        modUser.setIdade(JOptionPane.showInputDialog("Digite a nova idade:"));
                        DATA_BASE.writeFile(this.path, DATA_FILES.getIdade(), modUser.getIdade());
                        break;
                    case "3":
                        modUser.setFood(JOptionPane.showInputDialog("Digite a nova comida favorita:"));
                        DATA_BASE.writeFile(this.path, DATA_FILES.getFood(), modUser.getFood());
                        break;
                    case "4":
                        break;
                    default:
                        opt = "1";
                }
            } while (((opt.equals("1")) || (opt.equals("2")) || (opt.equals("3"))));

        }
    }
    
    @Override
    public CommomUser getById(String inputMessage, String errorMessage) throws Exception {
        String getId = "";
        boolean userExiste = true;
        try {
            getId = DATA_BASE.readFile(this.MAIN_FOLDER+JOptionPane.showInputDialog(inputMessage) + File.separator, DATA_FILES.getId());
        } catch (Exception erro) {
            userExiste = false;
            JOptionPane.showMessageDialog(null, errorMessage);
        }
        if (userExiste) {
            return catchById(getId);
        } else {
            CommomUser noUser = new CommomUser();
            noUser.setId(SPECIAL_CHAR);
            return noUser;
        }
    }
    
    public void newUser(CommomUser newUser) {
        this.path = this.MAIN_FOLDER + newUser.getId() + File.separator;
        
        newUser.setName(JOptionPane.showInputDialog("Digite seu nome:"));
        newUser.setIdade(JOptionPane.showInputDialog("Digite sua idade:"));
        newUser.setFood(JOptionPane.showInputDialog("Digite seu apelido:"));
        
        DATA_BASE.writeFile(this.path, DATA_FILES.getId(), newUser.getId());
        DATA_BASE.writeFile(this.path, DATA_FILES.getName(), newUser.getName());
        DATA_BASE.writeFile(this.path, DATA_FILES.getIdade(), newUser.getIdade());
        DATA_BASE.writeFile(this.path, DATA_FILES.getFood(), newUser.getFood());
    }
    
    public void backupUser(CommomUser backup) {
        this.path = this.MAIN_FOLDER + SPECIAL_CHAR + File.separator + backup.getId() + File.separator;
        
        File pasta = new File(this.MAIN_FOLDER + SPECIAL_CHAR + File.separator + backup.getId());
        pasta.mkdir();
        
        DATA_BASE.writeFile(this.path, DATA_FILES.getId(), backup.getId());
        DATA_BASE.writeFile(this.path, DATA_FILES.getName(), backup.getName());
        DATA_BASE.writeFile(this.path, DATA_FILES.getIdade(), backup.getIdade());
        DATA_BASE.writeFile(this.path, DATA_FILES.getFood(), backup.getFood());
    }

    @Override
    public void showUser(User u) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void newUser(User newUser) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void backupUser(User backup) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
