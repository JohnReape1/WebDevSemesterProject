
package controller;

import dao.SilentAuctionDAO;
import dao.SilentAuctionDAOImpl;
import java.util.ArrayList;
import java.util.Collections;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.ItemBean;
import model.LoginBean;
import model.SearchBean;
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
    private SearchBean theSearchModel;
    
    private UserController theUserController;
    private LoginController theLoginController;
    private ItemController theItemController; // for future implementation
    
    private ArrayList<UserBean> users;
    private ArrayList<String> categoryList;
    private ArrayList<String> permissionList;
    private ArrayList<String> stateList;
    private ArrayList<ItemBean> itemList;
    
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
        theItemModel = new ItemBean();
        theSearchModel = new SearchBean();
        
        theLoginController = new LoginController();
        theUserController = new UserController();
        theItemController = new ItemController();
        
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
        String redirect = theUserController.createUser(theUserModel);
        theLoginModel.setLoggedIn(true);
        theLoginModel.setPassword(theUserModel.getPassword());
        theLoginModel.setUsername(theUserModel.getUsername());
        theLoginModel.setQuestion(theUserModel.getQuestion());
        theLoginModel.setAnswer(theUserModel.getAnswer());
        return redirect;
    }

    public String createItem(){
        System.out.println("Primary");
        theItemModel.setAuthor(theLoginModel.getUsername());
        theItemModel.setMinBid(theItemModel.determineMinbid(theItemModel));
        theItemModel.setAngelPrice(theItemModel.determineAngelPrice(theItemModel));
        String redirect = theItemController.createItem(theItemModel);
        System.out.println("XBF" + redirect);
        /* going to need various redirects */
        return redirect;
    }
    /*
     * Verifies if it is a valid login by making sure user exists in Login DB
     */
    public String validateLogin() {   
        //logs user in and redirects as necessary
        String redirect = theLoginController.validateLogin(theLoginModel); 
        return redirect;
    }
    
    public ArrayList<UserBean> getUsers() {
        SilentAuctionDAO aUser = new SilentAuctionDAOImpl();
        this.users = aUser.findByUsername(theLoginModel.getUsername());
        return users;
    }
    /*
     *Searches the items did not make another controller for the search function
     *But did make a bean to keep track of the searched for term
    */
    public ArrayList<ItemBean> searchItems()
    {
        SilentAuctionDAO aItem = new SilentAuctionDAOImpl();
        this.itemList=aItem.searchAll(theSearchModel.getKeyword());
        return itemList;
    }

    public void setUsers(ArrayList<UserBean> users) {
        this.users = users;
    } 
    
    public LoginBean getTheLoginModel() {
        return theLoginModel;
    }
    
    public UserBean getTheUserModel() {
        return theUserModel;
    }

    public ItemBean getTheItemModel() {
        return theItemModel;
    }
    public SearchBean getTheSeacrhModel(){
        return theSearchModel;
    }

    public ArrayList<String> getCategoryList() {
        return categoryList;
    }

    public ArrayList<String> getPermissionList() {
        return permissionList;
    }

    public ArrayList<String> getStateList() {
        return stateList;
    }
    public ArrayList<ItemBean> getItemList(){
        return itemList;
    }
    public void serItemList(ArrayList<ItemBean> itemList){
        this.itemList=itemList;
    }
    public void setTheSearchModel(SearchBean model)
    {
        this.theSearchModel=model;
    }
}
