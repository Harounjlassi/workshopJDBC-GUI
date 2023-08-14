/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.testapplication.gui2;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.embed.swing.SwingFXUtils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class SendemailController implements Initializable {

    @FXML
    private Button testmail;

    @FXML
    private Button bsend;
    @FXML
    private Button addnew;
    @FXML
    private TextField mSubject;
    @FXML
    private TextField mMails;
    @FXML
    private TextField mMailpass;
    @FXML
    private TextField mto;
    @FXML
    private TextField mBody;
    @FXML
    private Button addfile;

    /**
     * Initializes the controller class.
     */
    String filePath="";
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        String path = System.getProperty("user.home");

        //Creating a graphic (image)
        BufferedImage img = null;
        BufferedImage img2 = null;

        try {
            img = ImageIO.read(new File(path + "\\Documents\\NetBeansProjects\\workshopJDBC\\src\\icons\\send.png"));
            img2 = ImageIO.read(new File(path + "\\Documents\\NetBeansProjects\\workshopJDBC\\src\\icons\\cancel.png"));

        } catch (IOException ex) {
        }
        ImageView view = null;
        ImageView view2 = null;

        if (img != null) {
            WritableImage image = SwingFXUtils.toFXImage(img, null); // Convert BufferedImage to Image
            view = new ImageView(image);
            WritableImage image2 = SwingFXUtils.toFXImage(img2, null); // Convert BufferedImage to Image
            view2 = new ImageView(image2);
            // Further processing with the ImageView
        }
        view.setFitHeight(45);
        view.setPreserveRatio(true);
        //Creating a Button

        //Setting the location of the button
        //bsend.setTranslateX(25);
        //bsend.setTranslateY(25);
        //Setting the size of the button
        testmail.setPrefSize(45, 45);
        //Setting a graphic to the button
        testmail.setGraphic(view);

        view2.setFitHeight(45);
        view2.setPreserveRatio(true);
        //Creating a Button

        //Setting the location of the button
        //bsend.setTranslateX(25);
        //bsend.setTranslateY(25);
        //Setting the size of the button
        bsend.setPrefSize(45, 45);
        //Setting a graphic to the button
        bsend.setGraphic(view2);

    }

    @FXML
    private void testmails(ActionEvent event) {
        String mSubjecttext = mSubject.getText();
        String mailtext = mMails.getText();
        String mMailpasstext = mMailpass.getText();
        String mtotext = mto.getText();

        String mbodyext = mBody.getText();
//String username = "yhhhgtub@gmail.com";
//        String password = "jivbqbklkkkwqvpk";
//        String fromEmail = "yhhhgtub@gmail.com";
//        String toEmail = "jlsshrn8621@gmail.com";

//System.out.println(filePath);

        mailsend(mailtext,mMailpasstext, mailtext,mtotext, mSubjecttext,mbodyext,filePath);
    }

    void mailsend(String username, String password, String fromEmail, String toEmail, String subject,String mbodyext,String filep) {

        //authentication info
        

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session;
        session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        //Start our mail message
        MimeMessage msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(fromEmail));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            msg.setSubject(subject);

            Multipart emailContent = new MimeMultipart();

            //Text body part
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText(mbodyext);

            //Attachment body part.
            MimeBodyPart pdfAttachment = new MimeBodyPart();
            try {
                pdfAttachment.attachFile(filep);
            } catch (IOException ex) {
                Logger.getLogger(SendemailController.class.getName()).log(Level.SEVERE, null, ex);
            }

            //Attach body parts
            emailContent.addBodyPart(textBodyPart);
            emailContent.addBodyPart(pdfAttachment);

            //Attach multipart to message
            msg.setContent(emailContent);

            Transport.send(msg);
            System.out.println("Sent message");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void addfileaction(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("C:\\")); // Set the initial directory

        // Show the file dialog and get the selected file
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
             filePath = selectedFile.getAbsolutePath();
            System.out.println("Chosen File: " + filePath);
            
            // Now you can use the filePath for further processing
        } else {
            System.out.println("No file selected.");
        }
   
    }

    }

