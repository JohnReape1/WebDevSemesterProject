
package controller;

import dao.SilentAuctionDAO;
import dao.SilentAuctionDAOImpl;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.LoginBean;

/**
 * @author Corbin
 */
@ManagedBean
@SessionScoped
public class LoginController {
    
    // number of login attempts
    private int attempts = 0;

    //increase the number of attempts
    public String validateLogin(LoginBean theLoginModel) {        
        // ensures the number of login attempts are less than 3
        if (attempts < 3) {
            // creates DAO for DB operations
            SilentAuctionDAO aLoginDAO = new SilentAuctionDAOImpl();
            // if no DB entries match the username and password 
            // increase the number of login attempts and
            // redirect to login error
            if (aLoginDAO.authorizeUser(theLoginModel.getUsername(), 
                                    theLoginModel.getPassword()).isEmpty()) {
                attempts++;
                return "LoginBad";
            } 
            // login successful
            else {
                // resets attempts to 0
                attempts = 0;
                // sets login variable to true
                theLoginModel.setLoggedIn(true);
                // query the table for the Question and Answer from the User Table
                ArrayList<String>QandA = aLoginDAO.getQandA(theLoginModel.getUsername(), 
                                                            theLoginModel.getPassword());
                
                // set the question and answer in the bean
                theLoginModel.setQuestion(QandA.get(0));
                theLoginModel.setAnswer(QandA.get(1));                
                return "LoginGood";
            }
        } else {
            // max attempts error page redirect
            return "max-attempt";
        }
    }
}
