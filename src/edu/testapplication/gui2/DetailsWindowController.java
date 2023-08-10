/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.testapplication.gui2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class DetailsWindowController implements Initializable {

    @FXML
    private TextField tfID;
    @FXML
    private TextField tfNOM;
    @FXML
    private TextField tfPRENOM;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  

    public void setTextID(String message) {
        this.tfID.setText(message);
    }
    public void setTextNOM(String message) {
        this.tfNOM.setText(message);
    }
    public void setTextPRENOM(String message) {
        this.tfPRENOM.setText(message);
    }



    
    
    
}
