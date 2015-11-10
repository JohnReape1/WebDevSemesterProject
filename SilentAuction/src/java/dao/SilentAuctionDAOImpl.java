
package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.LoginBean;
import model.UserBean;

/**
 * @author Corbin
 */
public class SilentAuctionDAOImpl implements SilentAuctionDAO{
    
    public int createUser(UserBean aUser){
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        int rowCountUser = 0;
        int rowCountLogin = 0;
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
                + aUser.getStreet() + "','"
                + aUser.getHomePhone() + "','"
                + aUser.getCellPhone() + "','"
                + aUser.getPermissionLevel() + "','"
                + "')";
            System.out.println("insert string =" + insertString);
            rowCountUser = stmt.executeUpdate(insertString);
            
            
            stmt = DBConn.createStatement();
            // add an entry to the login DB
            insertString = "INSERT INTO LoginInfo VALUES('"
                + aUser.getUsername() + "','"
                + aUser.getPassword()
                + "')";
            System.out.println("insert string =" + insertString);
            rowCountLogin = stmt.executeUpdate(insertString);
                    
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        // if insert is successful, rowCount will be set to 1 (1 row inserted successfully). Else, insert failed.
        if(rowCountLogin == 1 && rowCountUser ==1)
        {
            return 1;
        }
        else
            return 0;
    }
    
    private ArrayList verifyLogin(String query){
        ArrayList aLoginCollection = new ArrayList();
        Connection DBConn = null;
        try{
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
                loginBean = new LoginBean(username,password);
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
    
     private ArrayList selectUsersFromDB(String query) {
        ArrayList aWebAppBeanCollection = new ArrayList();
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
                    homePhone, cellPhone , permission;
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
                aWebAppBeanCollection.add(silentAuctionBean);
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
        return aWebAppBeanCollection;
    }
     
    public ArrayList findByUsername(String username) {
        String query = "SELECT * FROM Users ";
        query += "WHERE Username = '" + username + "'"; 

        ArrayList aUserCollection = selectUsersFromDB(query);
        return aUserCollection;
    }
    
    public ArrayList authorizeUser(String username, String password){
        String query = "SELECT * FROM LoginInfo ";
        query += "WHERE Username = '" + username + "'"
                + "AND Password = '" + password + "'";
        ArrayList authorizedUser = verifyLogin(query);
        return authorizedUser;
    }
   
    public ArrayList findAll(){
        String query = "SELECT * FROM Users";
        ArrayList aUserCollection = selectUsersFromDB(query);
        return aUserCollection;
    }
}
