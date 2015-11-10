
package dao;

import java.util.ArrayList;
import model.UserBean;

/**
 * @author Corbin
 */
public interface SilentAuctionDAO {
    public int createUser(UserBean aUser);
    public ArrayList findAll();
    // either get one back or several if multiple same name allowed
    public ArrayList findByUsername(String username);  
    public ArrayList authorizeUser(String username, String password);
}
