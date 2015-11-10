/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * @author Corbin Sumner
 */

/*
 * This class writes emails upon successful signup
 */
public class EmailWriter {
    /*
     * Writes/Sends an Email  
     */
    public void writeConfirmationEmail(String firstName, String lastName, String username, String password, String email) {
        String to = email;
        String from = "cssumne@ilstu.edu";
        String host = "smtp.ilstu.edu";
        Properties properties = System.getProperties();

        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.user", "cssumne"); // if needed
        properties.setProperty("mail.password", "stuC0rb!"); // if needed                    
        Session session = Session.getDefaultInstance(properties);

        try {
            MimeMessage message = new MimeMessage(session);
            MimeMultipart multipart = new MimeMultipart("related");
            BodyPart messageBodyPart = new MimeBodyPart();
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Profile Created!");

            String htmlText = "<h1>Congratulations, " + firstName + " " + lastName + "! Your Profile has been created!</h1>" + "<img src=\"cid:image\">"
                    + "<p>Username: " + username + "</p>"
                    + "<p>Email: " + email + "</p>"
                    + "<p>Password: " + password + "</p>"
                    + "<p>Thank You for Joining!</p>";

            messageBodyPart.setContent(htmlText, "text/html");
            multipart.addBodyPart(messageBodyPart);
            messageBodyPart = new MimeBodyPart();
            DataSource fds;
            fds = new FileDataSource("H:/NetBeansApps/SilentAuction/web/resources/bf_logo.png");
            messageBodyPart.setDataHandler(new DataHandler(fds));
            messageBodyPart.setHeader("Content-ID", "<image>");
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);

            Transport.send(message);

            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
    
    public void writeReceipt(){
        
    }
    
    public void writeInvoice(){
        
    }
}
