package adminmanager;

import datamanager.User;


public class AdminUser extends User {

    private String password = "password";
    
    public AdminUser() {
    }

    public AdminUser(String id, String nome, String type) {
        super(id, nome, type);
    }
    
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
