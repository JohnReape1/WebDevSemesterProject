
package controller;

import dao.SilentAuctionDAO;
import dao.SilentAuctionDAOImpl;
import java.util.ArrayList;
import java.util.Collections;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.ItemBean;
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
    private ItemBean theItemModel;
    
    private UserController theUserController;
    private LoginController theLoginController;
    //private ItemsController theItemController // for future implementation
    private ArrayList<UserBean> users;
    
    private ArrayList<String> categoryList;
    private ArrayList<String> permissionList;
    private ArrayList<String> stateList;
    
    
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
        
        categoryList = new ArrayList<String>();
        permissionList = new ArrayList<String>();
        stateList = new ArrayList<String>();
        /* setting up prepopulated lists */
        String [] states = {"AL","AK","AZ","AR","CA","CO","CT","DE","FL","GA",
                            "HI","ID","IL","IN","IA","KS","KY","LA","ME","MD",
                            "MA","MI","MN","MS","MO","MT","NE","NV","NH","NJ",
                            "NM","NY","NC","ND","OH","OK","OR","PA","RI","SC",
                            "SD","TN","TX","UT","VT","VA","WA","WV","WI","WY"};
        Collections.addAll(stateList, states);
        
        String [] categories = {"Gingerbread", "Celebrity Place", 
                                "Jingle Bell Junction", "Opening Night", 
                                "Designer Item"};
        Collections.addAll(categoryList,categories);
        
        String [] permissions = {"Public", "Designer/Donor", 
                                 "Steering Committee", "Admin"};
        Collections.addAll(permissionList, permissions);
        
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

    public ItemBean getTheItemModel() {
        return theItemModel;
    }

    public ArrayList<String> getCategoryList() {
        return categoryList;
    }

    public ArrayList<String> getPermissionList() {
        return permissionList;
    }

    public UserController getTheUserController() {
        return theUserController;
    }

    public void setTheUserController(UserController theUserController) {
        this.theUserController = theUserController;
    }

    public LoginController getTheLoginController() {
        return theLoginController;
    }

    public void setTheLoginController(LoginController theLoginController) {
        this.theLoginController = theLoginController;
    }

    public ArrayList<String> getStateList() {
        return stateList;
    }

    public void setStateList(ArrayList<String> stateList) {
        this.stateList = stateList;
    }
    
    
    
}
