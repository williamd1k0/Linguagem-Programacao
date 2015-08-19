package datamanager;

import pseudodb.*;

/** <h1>Classe Manager</h1>
 * Tem a função de gerar sub-classes para o gerenciamento individual de dados.
 * @author tumeo
 */
public class Manager extends Configs{

    /**
     *
     */
    public final Files DATA_BASE = new Files();

    /**
     *
     */
    public String BACKUP_FOLDER,

    /**
     *
     */
    MAIN_FOLDER,

    /**
     *
     */
    SPECIAL_CHAR;
    
    protected String path;

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
    public boolean menu() throws Exception{
        return false;
    } 

    /**
     *
     * 
     * @param message
     * @param error
     */
    public void createUser(String message, String error){}

    /**
     *
     * @param inputMessage
     * @param errorMessage
     * @return
     * @throws Exception
     */
    public User getById(String inputMessage, String errorMessage) throws Exception{
        return null;
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    public User catchById(String id) throws Exception{
        return null;
    }

    /**
     *
     * @param u
     */
    public void showUser(User u){}

    /**
     *
     * @throws Exception
     */
    public void modifyUser() throws Exception{}

    /**
     *
     * @param queryMessage
     * @param error
     * @param yesNO
     * @param deleted
     * @param error2
     * @throws Exception
     */
    public void deleteUser(String queryMessage, String error, String yesNO, String deleted, String error2) throws Exception{}

    /**
     *
     * @param newUser
     */
    public void newUser(User newUser){}

    /**
     *
     * @param backup
     */
    public void backupUser(User backup){}

    /**
     *
     * @return
     * @throws Exception
     */
    public boolean checkPersistent() throws Exception{
        return false;
    }

}