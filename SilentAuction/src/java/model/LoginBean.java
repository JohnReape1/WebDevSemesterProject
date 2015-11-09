
package model;

/**
 * @author Corbin Sumner
 */
public class LoginBean {
    private String  username, // username
                    password; // user's password
    // determines if a user is currently logged in
    private boolean loggedIn;

    public LoginBean(){
        this.username = null;
        this.password = null;
        this.loggedIn = false;
    }
    
    public LoginBean(String username, String password){
        this.username = username;
        this.password = password;
        this.loggedIn = false;
    }
    
    public void loggedIn(){
        loggedIn = true;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
    
    public boolean getLoggedIn() {
        return loggedIn;
    }
    
}
