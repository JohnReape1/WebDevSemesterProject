package dao;

import java.sql.Connection;
import java.sql.DriverManager;
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
            insertString = "INSERT INTO Users VALUES ('"
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
            insertString = "INSERT INTO Items VALUES ('"
                    + aItem.getItemName() + "','"
                    + aItem.getIdNumber() + "','"
                    + aItem.getListPrice() + "','"
                    + aItem.getHighestBid() + "','"
                    + aItem.getHighestBidder() + "','"
                    + aItem.getMinBid() + "','"
                    + aItem.getApproxValue() + "','"
                    + aItem.getDonor() + "','"
                    + aItem.getAngelPrice() + "','"
                    + aItem.getPaymentStatus() + "','"
                    + aItem.getSoldStatus()
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
        String query = "SELECT * FROM Users ";
        query += "WHERE Username = '" + username + "'";

        ArrayList aUserCollection = selectUsersFromDB(query);
        return aUserCollection;
    }

    /* query user table for a username and password combo */
    public ArrayList authorizeUser(String username, String password) {
        String query = "SELECT * FROM Users ";
        query += "WHERE Username = '" + username + "'"
                + "AND Password = '" + password + "'";
        ArrayList authorizedUser = verifyLogin(query);
        return authorizedUser;
    }
    
    /* gets a user's question and answer to populate login bean */
    public ArrayList getQandA(String username, String password) {
        String query = "SELECT * FROM Users ";
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
    
    /* gets all of the users */
    public ArrayList findAll() {
        String query = "SELECT * FROM Users";
        ArrayList aUserCollection = selectUsersFromDB(query);
        return aUserCollection;
    }
}
