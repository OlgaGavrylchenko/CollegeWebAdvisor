/*
 * Programming Assignment 1 - Advanced JAVA
 * Create an integrated graphical application for 
 * BOOLA BOOLA University. 
 *
 * @Created by Olga Gavrylchenko, 10/12/2017
 */

package Functional;
import UI.*; 

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.geometry.Pos;

import java.sql.*;

public class WebAdvisor extends Application implements FormUI_Iface{
    
    private final BorderPane root = new BorderPane();
    
    private MenuBarUI menuBarUI;
    private AlertBox alertBox;
    private static BorderPane subRoot = new BorderPane();
    
    private Connection connection = null;
    
    //JDBC driver name and database URL
    static final String DRIVER = "com.mysql.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost/webadvisor";
    
    @Override
    public void start(Stage primaryStage) {
        
        WelcomeForm welcome = new WelcomeForm();
        Label title = new Label("@Created by Olga Gavrylchenko, 10/12/2017");
        title.setFont(MAIN_FONT_SMALL); 
        title.setPrefHeight(30);
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().add(title);
        
        //instantiate an object
        alertBox = new AlertBox();
        menuBarUI = new MenuBarUI();
        
        initializeDB(); //make db connection
        setMenuItemAction();
        
        root.setTop(menuBarUI.getMenuBar());
        root.setStyle(" -fx-background-color: #fff;");
        root.setCenter(subRoot);
        root.setBottom(hbox);
        
        subRoot.setCenter(welcome.getMainForm());
        
        Scene scene = new Scene(root);
        
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(SCREEN_WIDTH);
        primaryStage.setMinHeight(SCREEN_HEIGHT);
        primaryStage.setMaxWidth(SCREEN_WIDTH);
        primaryStage.setMaxHeight(SCREEN_HEIGHT);
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setTitle("BOOLA BOOLA UNIVERSITY - College Web Advisor");
        primaryStage.show();
    }

    @Override //application method stop()
    public void stop(){ //This method is called when the application should stop, 
                        //and provides a convenient place to prepare for 
                        //application exit and destroy resources.
        try{
            if(connection != null){
                connection.close();
                System.out.println("Connection is closed!");
            }
                
        }catch(SQLException e){
            System.out.println("SQL error: " + e.toString());
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    //method which connect javaFX app to database
    private void initializeDB(){
        try{
        //load the JDBC driver
        Class.forName(DRIVER);
        
        //establish the connection - connect to database
        connection = DriverManager.getConnection(DATABASE_URL, "olga", "gavrylc");
       
        }catch(ClassNotFoundException e){
            e.printStackTrace();          
        }catch(SQLException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    
    }//initializeDB
    
    public static BorderPane getSubRoot(){
        return subRoot;
    }
    
    
    private void setMenuItemAction(){
       
       menuBarUI.getAdmission()[MenuBarUI.MATRICULATED].setOnAction((ActionEvent event)->{
            new MatriculatedForm(this.connection);
       });
       
       menuBarUI.getAdmission()[MenuBarUI.NONMATRICULATED].setOnAction((ActionEvent event)->{
          new NonMatriculatedForm(this.connection);
       });
       
       menuBarUI.getAdmission()[MenuBarUI.QUIT].setOnAction((ActionEvent event)->{
            //stop and close data base
             alertBox.quitDialog();       
       });
       
       menuBarUI.getRegistration()[MenuBarUI.FULLTIME].setOnAction((ActionEvent event)->{
          new FullTimeForm(this.connection, 1);
       });
       
       menuBarUI.getRegistration()[MenuBarUI.PARTTIME].setOnAction((ActionEvent event)->{
          new FullTimeForm(this.connection, 2);
       });
       
       menuBarUI.getRegistration()[MenuBarUI.NONCREDIT].setOnAction((ActionEvent event)->{
          new FullTimeForm(this.connection, 3);
       });
       
       menuBarUI.getReports()[MenuBarUI.SCHEDULE].setOnAction((ActionEvent event)->{
           new ClassScheduleForm(this.connection);
       });
       
       menuBarUI.getReports()[MenuBarUI.RECEIVABLES].setOnAction((ActionEvent event)->{
           new ReceivableForm(this.connection);
       });
       
   }//setMenuItemAction
   
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
}//WebAdvisor
