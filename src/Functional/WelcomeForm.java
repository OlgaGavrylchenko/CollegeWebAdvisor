/*
 * Programming Assignment 1 - Advanced JAVA
 * Create an integrated graphical application for 
 * BOOLA BOOLA University. 
 *
 * @Created by Olga Gavrylchenko, 10/12/2017
 */

package Functional;

import UI.FormUI_Iface;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;


public class WelcomeForm implements FormUI_Iface{
    
        private HBox mainFormUI;
        
        //constructor
        public WelcomeForm(){
     
        this.mainFormUI = new HBox();   
        
        Label title = new Label("BOOLA BOOLA UNIVERSITY");
        title.setPrefHeight(70);
        title.setFont(TITLE_FONT_LARGE); 
        
        this.mainFormUI.getChildren().add(title);
        this.mainFormUI.setAlignment(Pos.CENTER);
        this.mainFormUI.setPrefHeight(SCREEN_HEIGHT);
        this.mainFormUI.setPrefWidth(SCREEN_WIDTH);
       }
       
       public HBox getMainForm(){
           return this.mainFormUI;
       }
}
