
package controller;

import dao.SilentAuctionDAO;
import dao.SilentAuctionDAOImpl;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.UserBean;

/**
 * @author Corbin
 */
@ManagedBean
@SessionScoped
public class UserController {
    private EmailWriter eWriter;
    
    public UserController(){
        eWriter = new EmailWriter();
    }
    
    public String createUser(UserBean theUserModel) {
        // if the password and confirm password match
        if (theUserModel.getPassword().equals(theUserModel.getConfirmPassword())) {
            // creates data access object for DB operations
            SilentAuctionDAO aUserDAO = new SilentAuctionDAOImpl();
            // if there are no users with the same name in the DB
            if (aUserDAO.findByUsername(theUserModel.getUsername()).isEmpty()) {
                // adds user to DB
                int status = aUserDAO.createUser(theUserModel);
                // if a user is successfully created
                if (status == 1) {
                    
                    // write confirmation email
                    eWriter.writeEmail(theUserModel.getFirstName(),
                        theUserModel.getLastName(), theUserModel.getUsername(),
                        theUserModel.getPassword(), theUserModel.getEmail());
                    
                    // return confirmation page
                    return "echo";
                } else {
                    // redirect to information entry error page
                    return "entry-error";
                }
            } else {
                // redirection to info already exists in the DB error
                return "duplicate-error";
            }
        } else {
            // redirect to the password doesn't match error
            return "password-error";
        }
    }
}
