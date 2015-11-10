package model;

import java.util.ArrayList;
/**
 * @author Corbin Sumner
 */
public class UserBean {
    private String  username, // username
                    firstName, // user's first name
                    lastName, // user's last name
                    state, // user's address
                    city, // user's city
                    zip, // user's zip
                    street, // user's street
                    homePhone, // user's home phone
                    cellPhone, // user's cell phone
                    permissionLevel, // user's permission level
                    email, // user's email address
                    password, // user's password
                    confirmPassword, // confirm password
                    question, // security question
                    answer; // security answer
    
    // lists items that the user is highest bidder
    private ArrayList<String> itemList; 
    public UserBean(){};
    public UserBean(String firstName, String lastName, String username, 
        String street, String city, String state, String zip,
        String homePhone, String cellPhone, String email, String password, 
        String question, String answer, String permissionLevel){
        
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.homePhone = homePhone;
        this.cellPhone = cellPhone;
        this.password = password;
        this.question = question;
        this.answer = answer;
        this.permissionLevel = permissionLevel; 
    }
    
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(String permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }
    
    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<String> getItemList() {
        return itemList;
    }

    public void setItemList(ArrayList<String> itemList) {
        this.itemList = itemList;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
