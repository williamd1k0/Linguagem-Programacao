package adminmanager;

import datamanager.User;

/**
 *
 * @author tumeo
 */
public class AdminUser extends User {

    private String password = "password";
    
    /**
     *
     */
    public AdminUser() {
    }

    /**
     *
     * @param id
     * @param nome
     * @param type
     */
    public AdminUser(String id, String nome, String type) {
        super(id, nome, type);
    }
    
    /**
     *
     * @param pass
     */
    public void setPass(String pass){
        this.password = pass;
    }

    /**
     *
     * @return
     */
    public String getPass(){
            return this.password;
    }
    
}
