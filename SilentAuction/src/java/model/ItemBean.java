package model;

/**
 * @author Corbin Sumner
 */
public class ItemBean {
    private String  itemName, // item's name 
                    itemIDNumber, // item's identifying number
                    listPrice, // item's list price
                    highestBid, // the highest bid
                    userName, // highest bidder's username
                    donor, // designer or person who donated
                    approxValue, // value of item
                    minBid, // minimum acceptable bid
                    angelPrice; // "angel" price
    
    public ItemBean(){
        this.itemName = null;
        this.itemIDNumber = null;
        this.listPrice = null;
        this.highestBid = null;
        this.donor = null;
        this.approxValue = null;
        this.minBid = null;
        this.angelPrice = null;
    }
    
    public ItemBean(String itemName, String itemIDNumber, String listPrice, 
        String highestBid, String donor, String approxValue, String minBid, String angelPrice){
        this.itemName = itemName;
        this.itemIDNumber = itemIDNumber;
        this.listPrice = listPrice;
        this.highestBid = highestBid;
        this.donor = donor;
        this.approxValue = approxValue;
        this.minBid = minBid;
        this.angelPrice = angelPrice;  
    }
    
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemIDNumber() {
        return itemIDNumber;
    }

    public void setItemIDNumber(String itemNumber) {
        this.itemIDNumber = itemNumber;
    }

    public String getListPrice() {
        return listPrice;
    }

    public void setListPrice(String listPrice) {
        this.listPrice = listPrice;
    }

    public String getHighestBid() {
        return highestBid;
    }

    public void setHighestBid(String highestBid) {
        this.highestBid = highestBid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDonor() {
        return donor;
    }

    public void setDonor(String donor) {
        this.donor = donor;
    }

    public String getApproxValue() {
        return approxValue;
    }

    public void setApproxValue(String approxValue) {
        this.approxValue = approxValue;
    }

    public String getMinBid() {
        return minBid;
    }

    public void setMinBid(String minBid) {
        this.minBid = minBid;
    }

    public String getAngelPrice() {
        return angelPrice;
    }

    public void setAngelPrice(String angelPrice) {
        this.angelPrice = angelPrice;
    }
}
