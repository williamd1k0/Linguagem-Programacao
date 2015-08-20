package adminmanager;

import datamanager.*;
import java.io.File;
import javax.swing.*;

/**
 *
 * @author tumeo
 */
public class AdminManager extends Manager {
    
    private boolean loggedIn = false;
    private int tentativas = 0;
    
    /**
     *
     */
    public final AdminUser DATA_FILES = new AdminUser();
    
    /**
     *
     * @param folder
     * @param ext
     * @throws Exception
     */
    public AdminManager(String folder, String ext) throws Exception{
        this.MAIN_FOLDER = folder+File.separator;
        SPECIAL_CHAR = "ᅠ";
        
        DATA_FILES.setId(DATA_FILES.getId()+"."+ext);
        DATA_FILES.setName(DATA_FILES.getName()+"."+ext);
        DATA_FILES.setPass(DATA_FILES.getPass()+"."+ext);

        this.BACKUP_FOLDER = this.MAIN_FOLDER+File.separator+SPECIAL_CHAR;
        File pasta = new File(this.MAIN_FOLDER);
        if (!pasta.exists()) {
            pasta.mkdir();
            File backup = new File(this.BACKUP_FOLDER);
            backup.mkdir();
            DATA_BASE.writeFile(this.BACKUP_FOLDER+File.separator,"persistent", "1");
            DATA_BASE.writeFile(this.BACKUP_FOLDER+File.separator,"admin", "0");
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
    public AdminManager(String folder, String ext, String backfolder) throws Exception{
        this.MAIN_FOLDER = folder+File.separator;
        SPECIAL_CHAR = backfolder;
        DATA_FILES.setId(DATA_FILES.getId()+"."+ext);
        DATA_FILES.setName(DATA_FILES.getName()+"."+ext);
        DATA_FILES.setPass(DATA_FILES.getPass()+"."+ext);

        this.BACKUP_FOLDER = this.MAIN_FOLDER+File.separator+SPECIAL_CHAR;
        File pasta = new File(this.MAIN_FOLDER);
        if (!pasta.exists()) {
            pasta.mkdir();
            File backup = new File(this.BACKUP_FOLDER);
            backup.mkdir();
            DATA_BASE.writeFile(this.BACKUP_FOLDER+File.separator,"persistent", "1");
            DATA_BASE.writeFile(this.BACKUP_FOLDER+File.separator,"admin", "0");
        }else{
            this.backupEnabled = this.checkPersistent();
        }
        this.folderCreated = true;
    }
    
    private boolean loginMenu() throws Exception{
        AdminUser admin = new AdminUser();
        try{
            admin.setId(this.enterLogin());
            admin.setPass(this.enterPassword("Digite a senha."));
            
            AdminUser checkAdmin = new AdminUser();
            checkAdmin.setId(admin.getId());
            checkAdmin.setPass(this.checkPass(admin.getId()));
            
            if (!admin.getPass().equals(checkAdmin.getPass())) {
               JOptionPane.showMessageDialog(null, "Senha incorreta!"); 
            }
            return admin.getPass().equals(checkAdmin.getPass());
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Este usuário não existe!");
            return false;
        }

    }
    
    private String checkPass(String user) throws Exception{
        return this.catchById(user).getPass();
    }
    
    private String enterLogin() throws Exception{
        String user = JOptionPane.showInputDialog("Digite o username do Administrador.");

        this.tentativas++;
        if(this.tentativas > 3){
            System.exit(1);
        }

        return this.catchById(user).getId();
    }
    
    private String enterPassword(String passMessage){
        JPasswordField enterPass = new JPasswordField();
        String password = "";
        int okPressed = JOptionPane.showConfirmDialog(null, enterPass, passMessage, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (okPressed == JOptionPane.OK_OPTION) {
            password = new String(enterPass.getPassword());
        }
        return password;
    }
    
    /**
     *
     * @return
     * @throws Exception
     */
    public String mainMenu() throws Exception{
        String opt, menuNum = "0";
        
        if(this.hasAdmin()){
            
            if(!this.loggedIn){
                this.loggedIn = this.loginMenu();
            }else{
                do {
                    opt = JOptionPane.showInputDialog("Menu Principal"+
                                                      "\n1. Acessar menu de administradores."+
                                                      "\n2. Acessar menu de Usuários."+
                                                      "\n3. Configurações"+
                                                      "\n4. Sair.");
                } while (!((opt.equals("1")) || (opt.equals("2")) || (opt.equals("3")) || (opt.equals("4"))));

                switch (opt) {
                    case "1":
                        menuNum = "1";
                        break;
                    case "2":
                        menuNum = "2";
                        break;
                    case "3":
                        menuNum = "3";
                        break;
                    case "4":
                        menuNum = "4";
                        break;
                }
            }
            
        }else{
            JOptionPane.showMessageDialog(null, "Primeira Inicialização:\n"+
                                                "Clique em Ok para criar um administrador.");
            this.createUser("Digite um username:","Este usuário já existe!");
            DATA_BASE.writeFile(this.BACKUP_FOLDER+File.separator,"admin", "1");
            this.loggedIn = true;
        }
        
        return menuNum;
    }
    
    @Override
    public boolean menu() throws Exception {
        String opt;
        boolean exit = true;
        
        if(this.folderCreated){
            do {
                exit = false;
                opt = JOptionPane.showInputDialog("Menu de Administradores"+
                                                  "\n1. Criar novo administrador."+
                                                  "\n2. Consultar administradores."+
                                                  "\n3. Alterar administradores."+
                                                  "\n4. Deletar administradores."+
                                                  "\n5. Voltar.");
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
                                    "O username não foi encontrado",
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
    public void createUser(String message, String error) {
        AdminUser newUser = new AdminUser();
        boolean isNew = false;
        String check;
        newUser.setId(JOptionPane.showInputDialog(message));

        try {
            check = DATA_BASE.readFile(this.MAIN_FOLDER + newUser.getId() + File.separator, DATA_FILES.getName());
        } catch (Exception erro) {
            isNew = true;
        }

        if (isNew) {
            File pasta = new File(this.MAIN_FOLDER + newUser.getId());
            pasta.mkdir();
            this.newUser(newUser);
        } else {
            JOptionPane.showMessageDialog(null, error);
        }
    }
    
    /**
     *
     * @param newUser
     */
    public void newUser(AdminUser newUser) {
        this.path = this.MAIN_FOLDER + newUser.getId() + File.separator;
        
        newUser.setName(JOptionPane.showInputDialog("Digite seu nome:"));
        newUser.setPass(JOptionPane.showInputDialog("Digite sua senha:"));
        
        DATA_BASE.writeFile(this.path, DATA_FILES.getId(), newUser.getId());
        DATA_BASE.writeFile(this.path, DATA_FILES.getName(), newUser.getName());
        DATA_BASE.writeFile(this.path, DATA_FILES.getPass(), DATA_BASE.encodePass(newUser.getPass()));
    }
    
    @Override
    public AdminUser catchById(String id) throws Exception {
        this.path = this.MAIN_FOLDER+id+File.separator;
        AdminUser getUser = new AdminUser();

        getUser.setId(id);
        getUser.setName(DATA_BASE.readFile(this.path, DATA_FILES.getName()));
        getUser.setPass(DATA_BASE.decodePass(DATA_BASE.readFile(this.path, DATA_FILES.getPass())));
        
        return getUser;
    }
    
    @Override
    public AdminUser getById(String inputMessage, String errorMessage) throws Exception {
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
            AdminUser noUser = new AdminUser();
            noUser.setId(SPECIAL_CHAR);
            return noUser;
        }
    }
    
    /**
     *
     * @param u
     */
    public void showUser(AdminUser u) {
        if(!u.getId().equals(SPECIAL_CHAR)){
            JOptionPane.showMessageDialog(null, "Administrador: " + u.getName() + 
                                                "\nUsername: " + u.getId());
        }
    }
    
    @Override
    public void modifyUser() throws Exception {
        AdminUser modUser = getById("Digite o username a ser alterado", "O usuário não foi encontrado");
        this.path = this.MAIN_FOLDER + modUser.getId() + File.separator;
        if (!modUser.getId().equals(SPECIAL_CHAR)){
            String opt;
            do {
                opt = "0";
                opt = JOptionPane.showInputDialog("Qual valor deseja alterar de " + modUser.getId() + "?\n"+
                                                  "1. Nome [" + modUser.getName() + "]\n"+
                                                  "2. Senha [ indisponível ]\n"+
                                                  "3. Voltar.");

                switch (opt) {
                    case "1":
                        modUser.setName(JOptionPane.showInputDialog("Digite o novo nome:"));
                        DATA_BASE.writeFile(this.path, DATA_FILES.getName(), modUser.getName());
                        break;
                    case "2": break;
                    case "3":
                        break;
                    default:
                        opt = "1";
                }
            } while (((opt.equals("1")) || (opt.equals("2"))));

        }
    }
    
    @Override
    public void deleteUser(String queryMessage, String error, String yesNO, String deleted, String error2) throws Exception {
        AdminUser delUser = getById(queryMessage, error);
        if (!delUser.getId().equals(SPECIAL_CHAR)){
            File pasta = new File(this.MAIN_FOLDER+delUser.getId());
            int opt = JOptionPane.showConfirmDialog(null, yesNO + delUser.getName() + "?");
            if (opt == JOptionPane.YES_OPTION){
                if (backupEnabled) {
                    this.backupUser(delUser);
                }
                boolean hasDeleted = DATA_BASE.deleteFoler(pasta);
                if (hasDeleted){
                    JOptionPane.showMessageDialog(null, deleted);
                }else{
                    JOptionPane.showMessageDialog(null, error2);
                }
            }
        }
    }
    
    /**
     *
     * @param backup
     */
    public void backupUser(AdminUser backup) {
        this.path = this.MAIN_FOLDER + SPECIAL_CHAR + File.separator + backup.getId() + File.separator;
        
        File pasta = new File(this.MAIN_FOLDER + SPECIAL_CHAR + File.separator + backup.getId());
        pasta.mkdir();
        
        DATA_BASE.writeFile(this.path, DATA_FILES.getId(), backup.getId());
        DATA_BASE.writeFile(this.path, DATA_FILES.getName(), backup.getName());
        DATA_BASE.writeFile(this.path, DATA_FILES.getPass(), DATA_BASE.encodePass(backup.getPass()));
        
    }
    
    private boolean hasAdmin() throws Exception{
        boolean isNew = false;
        String check;
        
        check = DATA_BASE.readFile(this.BACKUP_FOLDER+File.separator, "admin");
        
        return check.equals("1");
        
    }
    
}
