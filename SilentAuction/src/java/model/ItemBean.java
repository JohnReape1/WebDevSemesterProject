package model;

/**
 * @author Corbin Sumner
 */
public class ItemBean {
    private String  itemName, // item's name 
                    idNumber, // item's identifying number
                    listPrice, // item's list price
                    highestBid, // the highest bid
                    highestBidder, // highest bidder's username
                    donor, // designer or person who donated
                    approxValue, // value of item
                    minBid, // minimum acceptable bid
                    angelPrice, // "angel" price
                    category,
                    paymentStatus, // whether it is paid for or not
                    soldStatus; // whether it is sold or not
    
    public ItemBean(){
        this.itemName = null;
        this.idNumber = null;
        this.listPrice = null;
        this.highestBid = null;
        this.donor = null;
        this.approxValue = null;
        this.minBid = null;
        this.angelPrice = null;
        this.paymentStatus = null;
        this.soldStatus = null;
    }
    
    public ItemBean(String itemName, String itemIDNumber, String listPrice, 
        String highestBid, String donor, String approxValue, String minBid, String angelPrice, 
        String paymentStatus, String soldStatus){
        this.itemName = itemName;
        this.idNumber = itemIDNumber;
        this.listPrice = listPrice;
        this.highestBid = highestBid;
        this.donor = donor;
        this.approxValue = approxValue;
        this.minBid = minBid;
        this.angelPrice = angelPrice;  
        this.paymentStatus = "Unpaid";
        this.soldStatus = "Unsold";
    }
    
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
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

    public String getHighestBidder() {
        return highestBidder;
    }

    public void setHighestBidder(String highestBidder) {
        this.highestBidder = highestBidder;
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

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getSoldStatus() {
        return soldStatus;
    }

    public void setSoldStatus(String soldStatus) {
        this.soldStatus = soldStatus;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
