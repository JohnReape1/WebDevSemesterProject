
package model;

/**
 * @author Corbin Sumner
 */
public class LoginBean {
    private String  username, // username
                    password, // user's password
                    question, // question
                    answer; // answer
    // determines if a user is currently logged in
    private boolean loggedIn;

    public LoginBean(){
        this.username = null;
        this.password = null;
        this.question = null;
        this.answer = null;
        this.loggedIn = false;
    }
    
    public LoginBean(String username, String password){
        this.username = username;
        this.password = password;
        this.question = null;
        this.answer = null;
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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
