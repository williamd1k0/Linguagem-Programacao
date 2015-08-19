package usermanager;

import java.io.File;
import javax.swing.JOptionPane;
import mainprogram.Configs;
import pseudodb.*;

/**
 *
 * @author tumeo
 */
public abstract class Manager extends Configs{

    /**
     *
     */
    public final Files DATA_BASE = new Files();

    /**
     *
     */
    public User DATA_FILES;
    public String BACKUP_FOLDER,

    /**
     *
     */
    MAIN_FOLDER,

    /**
     *
     */
    SPECIAL_CHAR;
    public String path,

    /**
     *
     */
    password = "root";

    /**
     *
     */
    public boolean folderCreated;

    /**
     *
     */
    public boolean backupEnabled = true;
    
    
    
    /**
     * 
     * @return
     * @throws Exception
     */
    abstract public boolean menu() throws Exception; 

    /**
     *
     * 
     * @param message
     * @param error
     */
    public void createUser(String message, String error) {
        User newUser = new User();
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
     * @param inputMessage
     * @param errorMessage
     * @return
     * @throws Exception
     */
    public User getById(String inputMessage, String errorMessage) throws Exception {
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
            User noUser = new User();
            noUser.setId(SPECIAL_CHAR);
            return noUser;
        }
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    abstract public User catchById(String id) throws Exception;

    /**
     *
     * @param u
     */
    abstract public void showUser(User u);

    /**
     *
     * @throws Exception
     */
    abstract public void modifyUser() throws Exception;

    /**
     *
     * @param queryMessage
     * @param error
     * @param yesNO
     * @param deleted
     * @param error2
     * @throws Exception
     */
    public void deleteUser(String queryMessage, String error, String yesNO, String deleted, String error2) throws Exception {
        User delUser = getById(queryMessage, error);
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
     * @param newUser
     */
    abstract public void newUser(User newUser);

    /**
     *
     * @param backup
     */
    abstract public void backupUser(User backup);

    /**
     *
     * @return
     * @throws Exception
     */
    public final boolean checkPersistent() throws Exception{
        String persist = DATA_BASE.readFile(this.BACKUP_FOLDER+File.separator, "persistent");
        return persist.equals("1");
    }

}