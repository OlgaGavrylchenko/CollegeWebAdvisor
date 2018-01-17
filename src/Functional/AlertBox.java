/*
 * Programming Assignment 1 - Advanced JAVA
 * Create an integrated graphical application for 
 * BOOLA BOOLA University. 
 *
 * @Created by Olga Gavrylchenko, 10/12/2017
 */
package Functional;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;

import java.util.Optional;

public class AlertBox {

    public void informationDialog(String content){
    
        Alert alert = new Alert(Alert.AlertType.INFORMATION, content);
        alert.setHeaderText("NOTICE: ");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(null);
        alert.showAndWait(); //thread 
    }
    
    public void errorDialog(String content){
        Alert alert = new Alert(Alert.AlertType.ERROR, content);
        alert.setHeaderText("ERROR: ");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(null);
        alert.showAndWait(); //thread 
    }
    
    public void warningDialog(String content){
        Alert alert = new Alert(Alert.AlertType.WARNING, content);
        alert.setHeaderText("WARNING: ");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(null);
        alert.showAndWait(); //thread 
    }
    
    public void confirmationDialog(String content){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, content);
        alert.setHeaderText("CONFIRMATION: ");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(null);
        alert.showAndWait(); //thread 
    }
    
    public void quitDialog(){
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "ARE YOU SURE TO QUIT A PROGRAM?");
        alert.setHeaderText("Hello!");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(null);
        
        //create buttons
        ButtonType btYes = new ButtonType("Yes");
        ButtonType btNo = new ButtonType("No");
        alert.getButtonTypes().setAll(btNo, btYes);
        
        Optional<ButtonType> result = alert.showAndWait(); //thread 
        
        if(result.get() == btYes){

             Platform.exit();
            
        }else{  
            alert.close(); //close dialog window
        } 
    }//quitDialog

}
