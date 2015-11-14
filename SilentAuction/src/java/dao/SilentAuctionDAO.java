
package dao;

import java.util.ArrayList;
import model.ItemBean;
import model.UserBean;

/**
 * @author Corbin
 */
public interface SilentAuctionDAO {
    public int createUser(UserBean aUser);
    public int createItem(ItemBean aItem);
    public ArrayList findAll();
    // either get one back or several if multiple same name allowed
    public ArrayList findByUsername(String username);  
    public ArrayList authorizeUser(String username, String password);
    public ArrayList getQandA(String username, String password);
    public ArrayList<ItemBean> searchAll(String keyword);
    
}
