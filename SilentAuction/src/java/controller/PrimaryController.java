
package controller;

import dao.SilentAuctionDAO;
import dao.SilentAuctionDAOImpl;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.LoginBean;
import model.UserBean;

/**
 *
 * @author Corbin Sumner
 */
@ManagedBean
@SessionScoped
public class PrimaryController {

    // create the beans and pass them to the individual controllers
    private LoginBean theLoginModel;
    private UserBean theUserModel;
    
    private UserController theUserController;
    private LoginController theLoginController;
    //private ItemsController theItemController // for future implementation
    private ArrayList<UserBean> users;

    /*
     *  Primary Controller manages the other controllers that operate on the 
     *  different bean types in our site.
     *  By having a centralized "controller of controllers" we'll have an easier
     *  time not worrying about making the correct controller calls in our View.
     *  Essentially, think of it as a fancy interface that makes implementation 
     *  easier
     */
    public PrimaryController() {
        //System.out.println("Controller Created"); //for debug
        theLoginModel = new LoginBean();
        theUserModel = new UserBean();
        theLoginController = new LoginController();
        theUserController = new UserController();
    }



    /*
     * Verifies the information is valid and adds user to DB
     */
    public String createUser() {
        //user controller determines the redirect
        //return theUserController.createUser(theUserModel);
        String redirect = theUserController.createUser(theUserModel);
        //System.out.println("Go To: " + redirect);
        return redirect;
    }

    /*
     * Verifies if it is a valid login by making sure user exists in Login DB
     */
    public String validateLogin() {        
        //System.out.println("Primary: " + theLoginModel.getUsername());
        //System.out.println("Primary: " + theLoginModel.getPassword());
        String redirect = theLoginController.validateLogin(theLoginModel); 
        //System.out.println("Go To: " + redirect);
        return redirect;
    }
    
    public ArrayList<UserBean> getUsers() {
        SilentAuctionDAO aUser = new SilentAuctionDAOImpl();
        this.users = aUser.findByUsername(theLoginModel.getUsername());
        return users;
    }

    public void setUsers(ArrayList<UserBean> users) {
        this.users = users;
    } 
    
    public LoginBean getTheLoginModel() {
        return theLoginModel;
    }

    public void setTheLoginModel(LoginBean theLoginModel) {
        this.theLoginModel = theLoginModel;
    }
    
    public UserBean getTheUserModel() {
        return theUserModel;
    }
    
    public void setTheUserModel(UserBean theUserModel) {
        this.theUserModel = theUserModel;
    }
}
