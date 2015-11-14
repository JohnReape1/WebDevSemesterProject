package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.ItemBean;
import model.LoginBean;
import model.UserBean;

/**
 * @author Corbin
 */
public class SilentAuctionDAOImpl implements SilentAuctionDAO {

    public int createUser(UserBean aUser) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        
        int rowCount = 0;
        
        try {
            String myDB = "jdbc:derby://localhost:1527/SilentAuction";
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");
            String temp = "";

            String insertString;
            Statement stmt = DBConn.createStatement();
            insertString = "INSERT INTO SilentAuction.Users(First_Name,Last_Name,Username,"
                    + "Password,Email,Street,City,State_,Zip,Home_Phone,Cell_Phone,Permission_Level,"
                    + "Question,Answer) VALUES ('"
                    + aUser.getFirstName() + "','"
                    + aUser.getLastName() + "','"
                    + aUser.getUsername() + "','"
                    + aUser.getPassword() + "','"
                    + aUser.getEmail() + "','"
                    + aUser.getStreet() + "','"
                    + aUser.getCity() + "','"
                    + aUser.getState() + "','"
                    + aUser.getZip() + "','"
                    + aUser.getHomePhone() + "','"
                    + aUser.getCellPhone() + "','"
                    + aUser.getPermissionLevel()  + "','"
                    + aUser.getQuestion()+ "','"
                    + aUser.getAnswer()
                    + "')";
            System.out.println("insert string =" + insertString);
            rowCount = stmt.executeUpdate(insertString);
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        // if insert is successful, rowCount will be set to 1 (1 row inserted successfully). Else, insert failed.
        if (rowCount == 1) {
            return 1;
        } else {
            return 0;
        }
    }

       
    public int createItem(ItemBean aItem) {
        System.out.println("DAO");
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        
        int rowCount = 0;

        try {
            String myDB = "jdbc:derby://localhost:1527/SilentAuction";
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");
            String temp = "";

            String insertString;
            Statement stmt = DBConn.createStatement();
            insertString = "INSERT INTO SilentAuction.Items(Item_Name,List_Price,Category,Highest_Bidder,Highest_Bid,"
                    + "Minimum_Bid,Approx_Value,Donor,Author,Angel_Price) VALUES ('"
                    + aItem.getItemName() + "','"
                    + aItem.getListPrice() + "','"
                    +aItem.getCategory()+"','"
                    + aItem.getHighestBid() + "','"
                    + aItem.getHighestBidder() + "','"
                    + aItem.getMinBid() + "','"
                    + aItem.getApproxValue() + "','"
                    + aItem.getDonor() + "','"
                    + aItem.getAuthor()+"','"
                    + aItem.getAngelPrice()
                    + "')";
            System.out.println("insert string =" + insertString);
            rowCount = stmt.executeUpdate(insertString);
            String a = aItem.getCategory().substring(0, 1);

            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        // if insert is successful, rowCount will be set to 1 (1 row inserted successfully). Else, insert failed.
        if (rowCount == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    /* gets user name/password for query */
    private ArrayList verifyLogin(String query) {
        
        ArrayList aLoginCollection = new ArrayList();
        Connection DBConn = null;
        
        try {
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            String myDB = "jdbc:derby://localhost:1527/SilentAuction";
            DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String username, password;
            LoginBean loginBean;
            while (rs.next()) {
                username = rs.getString("Username");
                password = rs.getString("Password");
                loginBean = new LoginBean(username, password);
                aLoginCollection.add(loginBean);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println("ERROR: Problems with SQL select");
            e.printStackTrace();
        }
        try {
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return aLoginCollection;
    }

    /* gets all of all information on a spefic user */
    private ArrayList selectUsersFromDB(String query) {
        ArrayList aUserCollection = new ArrayList();
        Connection DBConn = null;
        try {
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            String myDB = "jdbc:derby://localhost:1527/SilentAuction";
            DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");

            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String first_name, last_name, username,
                    password, email, question, answer,
                    street, city, state, zip,
                    homePhone, cellPhone, permission;
            UserBean silentAuctionBean;
            while (rs.next()) {
                first_name = rs.getString("First_Name");
                last_name = rs.getString("Last_Name");
                username = rs.getString("Username");
                password = rs.getString("Password");
                email = rs.getString("Email");
                street = rs.getString("Street");
                city = rs.getString("City");
                state = rs.getString("State");
                zip = rs.getString("Zip");
                homePhone = rs.getString("Home_Phone");
                cellPhone = rs.getString("Cell_Phone");
                question = rs.getString("Question");
                answer = rs.getString("Answer");
                permission = rs.getString("Permission_Level");

                silentAuctionBean = new UserBean(first_name, last_name, username, street, city, state, zip,
                        homePhone, cellPhone, email, password, question, answer, permission);
                
                // add the newly created object to the collection
                aUserCollection.add(silentAuctionBean);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println("ERROR: Problems with SQL select");
            e.printStackTrace();
        }
        try {
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return aUserCollection;
    }

    /* query user table for a username */
    public ArrayList findByUsername(String username) {
        String query = "SELECT * FROM SilentAuction.Users ";
        query += "WHERE Username = '" + username + "'";

        ArrayList aUserCollection = selectUsersFromDB(query);
        return aUserCollection;
    }

    /* query user table for a username and password combo */
    public ArrayList authorizeUser(String username, String password) {
        String query = "SELECT * FROM SilentAuction.Users ";
        query += "WHERE Username = '" + username + "'"
                + "AND Password = '" + password + "'";
        ArrayList authorizedUser = verifyLogin(query);
        return authorizedUser;
    }
    
    /* gets a user's question and answer to populate login bean */
    public ArrayList getQandA(String username, String password) {
        String query = "SELECT * FROM SilentAuction.Users ";
        query += "WHERE Username = '" + username + "'"
                + "AND Password = '" + password + "'";
        
          ArrayList<String> QandA = new ArrayList<String>();
          Connection DBConn = null;
        
        try {
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            String myDB = "jdbc:derby://localhost:1527/SilentAuction";
            DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            String question, answer;
            
            while (rs.next()) {
                question = rs.getString("Question");
                answer = rs.getString("Answer");
                QandA.add(question);
                QandA.add(answer);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println("ERROR: Problems with SQL select");
            e.printStackTrace();
        }
        try {
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }  
        return QandA;
    }
    private ArrayList<ItemBean> searchItemsByCategory(String query,String keyword) {
          ArrayList<ItemBean> items = new ArrayList<ItemBean>();
          Connection DBConn = null;
        
        try {
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            String myDB = "jdbc:derby://localhost:1527/SilentAuction";
            DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");
            PreparedStatement stmt =DBConn.prepareStatement(query);
            stmt.setString(1, keyword.substring(0, 1)+"%");
            ResultSet rs = stmt.executeQuery();
            ItemBean temp;
            String itemNum,name,donor,highestBid,highestBidder,listPrice,approx_value,
                    minBid,payment_status,sold_status,angelPrice,author;
            
            while (rs.next()) {
                itemNum=rs.getString("Item_ID");   
                name=rs.getString("Item_Name");
                listPrice=rs.getString("List_Price");
                highestBidder=rs.getString("Highest_Bidder");
                highestBid=rs.getString("Highest_Bid");
                minBid=rs.getString("Minimum_Bid");
                approx_value=rs.getString("Approx_Value");
                donor=rs.getString("Donor");
                author=rs.getString("Author");
                angelPrice=rs.getString("Angel_Price");
                payment_status=rs.getString("Payment_Status");
                sold_status=rs.getString("sold_status");
                temp=new ItemBean(name,itemNum,listPrice,highestBid,donor,author,approx_value,
                minBid,angelPrice,payment_status,sold_status);
                items.add(temp);
             }
            rs.close();
            stmt.close();
        } catch (Exception e) {
           System.err.println(e.getMessage());
        }
        try {
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }  
        return items;
    }
     private ArrayList<ItemBean> searchItemsByNumber(String query,String keyword) {
          ArrayList<ItemBean> items = new ArrayList<ItemBean>();
          Connection DBConn = null;
        
        try {
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            String myDB = "jdbc:derby://localhost:1527/SilentAuction";
            DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");
            PreparedStatement stmt =DBConn.prepareStatement(query);
            stmt.setInt(1, Integer.parseInt(keyword));
            ResultSet rs = stmt.executeQuery();
            ItemBean temp;
            int itemNum1;
            String itemNum,name,donor,highestBid,highestBidder,listPrice,approx_value,
                    minBid,payment_status,sold_status,angelPrice,author;
            
            while (rs.next()) {
                itemNum1=rs.getInt("Item_ID");   
                name=rs.getString("Item_Name");
                listPrice=rs.getString("List_Price");
                highestBidder=rs.getString("Highest_Bidder");
                highestBid=rs.getString("Highest_Bid");
                minBid=rs.getString("Minimum_Bid");
                approx_value=rs.getString("Approx_Value");
                donor=rs.getString("Donor");
                author=rs.getString("Author");
                angelPrice=rs.getString("Angel_Price");
                payment_status=rs.getString("Payment_Status");
                sold_status=rs.getString("sold_status");
                temp=new ItemBean(name,String.valueOf(itemNum1),listPrice,highestBid,donor,author,approx_value,
                minBid,angelPrice,payment_status,sold_status);
                items.add(temp);
             }
            rs.close();
            stmt.close();
        } catch (Exception e) {
           System.err.println(e.getMessage());
        }
        try {
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }  
        return items;
    }
    /* gets all of the users */
    public ArrayList findAll() {
        String query = "SELECT * FROM SilentAuction.Users";
        ArrayList aUserCollection = selectUsersFromDB(query);
        return aUserCollection;
    }
    @Override
    public ArrayList<ItemBean> searchAll(String keyword)
    {
        
    
        String query= "SELECT * FROM SilentAuction.Items WHERE Item_ID =?";
        String query1="SELECT * FROM SilentAuction.Items WHERE Category LIKE?";
        ArrayList<ItemBean> resultsCat = searchItemsByCategory(query1,keyword);
        ArrayList<ItemBean> resultsNum= searchItemsByNumber(query,keyword);
        resultsCat.addAll(resultsNum);
        return resultsCat;
    }
}
