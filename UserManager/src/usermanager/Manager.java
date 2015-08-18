package usermanager;

import java.io.File;
import javax.swing.JOptionPane;
import mainprogram.Configs;
import pseudodb.*;

/**
 *
 * @author tumeo
 */
public class Manager extends Configs{

    final Files DATA_BASE = new Files();
    final User DATA_FILES = new User("cep");
    final String specialChar = "ᅠ";
    private String path, mainFolder, backupFolder;
    boolean folderCreated;
    boolean backupEnabled = true;
    
    /**
     *
     * @param folder
     * @throws Exception
     */
    public Manager(String folder) throws Exception{
        this.mainFolder = folder+File.separator;
        this.backupFolder = this.mainFolder+File.separator+specialChar;
        File pasta = new File(this.mainFolder);
        if (!pasta.exists()) {
            pasta.mkdir();
            File backup = new File(this.backupFolder);
            backup.mkdir();
            DATA_BASE.writeFile(this.backupFolder+File.separator,"persistent", "1");
        }else{
            this.backupEnabled = this.checkPersistent();
        }
        folderCreated = true;
    }

    /**
     *
     */
    public Manager(){
        System.out.println("Pasta destino não definida!");
        folderCreated = false;
    }
    
    /**
     *
     * @param user
     * @return
     * @throws Exception
     */
    public boolean menu(User user) throws Exception {
        String opt;
        boolean exit = true;
        
        if(this.folderCreated){
            do {
                exit = false;
                opt = JOptionPane.showInputDialog("Menu:\n1. Criar usuário.\n2. Consultar.\n3. Alterar\n4. Deletar.\n5. Sair.");
            } while (!((opt.equals("1")) || (opt.equals("2")) || (opt.equals("3")) || (opt.equals("4")) || (opt.equals("5"))));

            switch (opt) {
                case "1":
                    this.createUser(user);
                    break;
                case "2":
                    this.showUser(this.getById("Digite o username: ", "O usuário não foi encontrado"));
                    break;
                case "3":
                    this.modifyUser();
                    break;
                case "4":
                    this.deleteUser();
                    break;
                case "5":
                    exit = true;
                    break;
            }
        }
        return exit;
    }

    /**
     *
     * @param newUser
     */
    public void createUser(User newUser) {
        boolean isNew = false;
        String check;
        newUser.setId(JOptionPane.showInputDialog("Digite um username:"));

        try {
            check = DATA_BASE.readFile(this.mainFolder + newUser.getId() + File.separator, DATA_FILES.getName());
        } catch (Exception erro) {
            isNew = true;
        }

        if (isNew) {
            File pasta = new File(this.mainFolder + newUser.getId());
            pasta.mkdir();
            this.newUser(newUser);
        } else {
            JOptionPane.showMessageDialog(null, "Este usuário já existe!");
        }
    }

    /**
     *
     * @param inputMessage
     * @param errorMessage
     * @return
     * @throws Exception
     */
    public User getById(String inputMessage, String errorMessage) throws Exception {
        String getId = "";
        boolean userExiste = true;
        try {
            getId = DATA_BASE.readFile(this.mainFolder+JOptionPane.showInputDialog(inputMessage) + File.separator, DATA_FILES.getId());
        } catch (Exception erro) {
            userExiste = false;
            JOptionPane.showMessageDialog(null, errorMessage);
        }
        if (userExiste) {
            return catchById(getId);
        } else {
            User noUser = new User();
            noUser.setId(specialChar);
            return noUser;
        }
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    public User catchById(String id) throws Exception {
        this.path = this.mainFolder+id+File.separator;
        User getUser = new User();

        getUser.setId(id);
        getUser.setName(DATA_BASE.readFile(this.path, DATA_FILES.getName()));
        getUser.setIdade(DATA_BASE.readFile(this.path, DATA_FILES.getIdade()));
        getUser.setFood(DATA_BASE.readFile(this.path, DATA_FILES.getFood()));
        return getUser;
    }

    /**
     *
     * @param u
     */
    public void showUser(User u) {
        if(!u.getId().equals(specialChar)){
            JOptionPane.showMessageDialog(null, "Usuário: " + u.getName() + "\nUsername: " + u.getId() + "\nIdade: " + u.getIdade() + "\nComida favorita: " + u.getFood());
        }
    }

    /**
     *
     * @throws Exception
     */
    public void modifyUser() throws Exception {
        User modUser = getById("Digite o username a ser alterado", "O usuário não foi encontrado");
        this.path = this.mainFolder + modUser.getId() + File.separator;
        if (!modUser.getId().equals(specialChar)){
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

    /**
     *
     * @throws Exception
     */
    public void deleteUser() throws Exception {
        User delUser = getById("Digite o username a ser deletado", "O usuário não foi encontrado");
        if (!delUser.getId().equals(specialChar)){
            File pasta = new File(this.mainFolder+delUser.getId());
            int opt = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir os dados de " + delUser.getName() + "?");
            if (opt == JOptionPane.YES_OPTION){
                if (backupEnabled) {
                    this.backupUser(delUser);
                }
                boolean hasDeleted = DATA_BASE.deleteFoler(pasta);
                if (hasDeleted){
                    JOptionPane.showMessageDialog(null, "O usuário foi deletado!");
                }else{
                    JOptionPane.showMessageDialog(null, "Algo inesperado aconteceu!");
                }
            }
        }
    }

    /**
     *
     * @param newUser
     */
    public void newUser(User newUser) {
        this.path = this.mainFolder + newUser.getId() + File.separator;
        newUser.setName(JOptionPane.showInputDialog("Digite seu nome:"));
        newUser.setIdade(JOptionPane.showInputDialog("Digite sua idade:"));
        newUser.setFood(JOptionPane.showInputDialog("Digite seu apelido:"));
        
        DATA_BASE.writeFile(this.path, DATA_FILES.getId(), newUser.getId());
        DATA_BASE.writeFile(this.path, DATA_FILES.getName(), newUser.getName());
        DATA_BASE.writeFile(this.path, DATA_FILES.getIdade(), newUser.getIdade());
        DATA_BASE.writeFile(this.path, DATA_FILES.getFood(), newUser.getFood());
    }

    /**
     *
     * @param backup
     */
    public void backupUser(User backup) {
        this.path = this.mainFolder + specialChar + File.separator + backup.getId() + File.separator;
        
        File pasta = new File(this.mainFolder + specialChar + File.separator + backup.getId());
        pasta.mkdir();
        
        DATA_BASE.writeFile(this.path, DATA_FILES.getId(), backup.getId());
        DATA_BASE.writeFile(this.path, DATA_FILES.getName(), backup.getName());
        DATA_BASE.writeFile(this.path, DATA_FILES.getIdade(), backup.getIdade());
        DATA_BASE.writeFile(this.path, DATA_FILES.getFood(), backup.getFood());
    }

    /**
     *
     * @return
     * @throws Exception
     */
    public boolean checkPersistent() throws Exception{
        String persist = DATA_BASE.readFile(this.backupFolder+File.separator, "persistent");
        return persist.equals("1");
    }
}