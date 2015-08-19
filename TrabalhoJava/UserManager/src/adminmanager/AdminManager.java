package adminmanager;

import datamanager.*;
import java.io.File;
import javax.swing.JOptionPane;

public class AdminManager extends Manager {
    
    public final AdminUser DATA_FILES = new AdminUser();
    
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
    
    public String mainMenu() throws Exception{
        String opt, menuNum = "0";
        
        if(this.hasAdmin()){
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
        }else{
            JOptionPane.showMessageDialog(null, "Primeira Inicialização:\n"+
                                                "Clique em Ok para criar um administrador.");
            this.createUser("Digite um username:","Este usuário já existe!");
            DATA_BASE.writeFile(this.BACKUP_FOLDER+File.separator,"admin", "1");
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
    
    public void newUser(AdminUser newUser) {
        this.path = this.MAIN_FOLDER + newUser.getId() + File.separator;
        
        newUser.setName(JOptionPane.showInputDialog("Digite seu nome:"));
        newUser.setPass(JOptionPane.showInputDialog("Digite sua senha:"));
        
        DATA_BASE.writeFile(this.path, DATA_FILES.getId(), newUser.getId());
        DATA_BASE.writeFile(this.path, DATA_FILES.getName(), newUser.getName());
        DATA_BASE.writeFile(this.path, DATA_FILES.getPass(), DATA_BASE.encodePass(newUser.getPass()));
    }
    
    private boolean hasAdmin() throws Exception{
        boolean isNew = false;
        String check;
        
        check = DATA_BASE.readFile(this.BACKUP_FOLDER+File.separator, "admin");
        
        return check.equals("1");
        
    }
    
}
