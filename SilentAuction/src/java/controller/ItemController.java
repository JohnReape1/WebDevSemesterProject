/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.SilentAuctionDAO;
import dao.SilentAuctionDAOImpl;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.ItemBean;

/**
 *
 * @author Corbin
 */
@ManagedBean
@SessionScoped
public class ItemController {
    
    public String createItem(ItemBean theItemModel){
        System.out.println("Controller");
        SilentAuctionDAO aItemDAO = new SilentAuctionDAOImpl();
        
        int status = aItemDAO.createItem(theItemModel);
        if(status==1){
            return "itemGood";
        }
        else{
            return "itemBad";
        }
    }
    
}
