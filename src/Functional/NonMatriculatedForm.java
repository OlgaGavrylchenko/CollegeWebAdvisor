/*
 * Programming Assignment 1 - Advanced JAVA
 * Create an integrated graphical application for 
 * BOOLA BOOLA University. 
 *
 * @Created by Olga Gavrylchenko, 10/12/2017
 */
package Functional;

import UI.MatriculatedFormUI;

import javafx.event.ActionEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import java.sql.*;

public class NonMatriculatedForm {
    
    private Connection connection = null;
    private MatriculatedFormUI mainFormUI;
    private ValidationUtils validationUtils;
    private AlertBox alertBox;
    
    private boolean result = false;
    
    public NonMatriculatedForm(Connection connection){
        
        this.connection = connection;
        this.validationUtils = new ValidationUtils();
        this.alertBox = new AlertBox();
        
        this.mainFormUI = new MatriculatedFormUI();
        this.mainFormUI.setDisableFields(true); //difference between matriculated and non-matriculated UI
        this.mainFormUI.setTitle("   NON-MATRICULATED");
        
        //SUBMIT button
        this.mainFormUI.getRegisterButton().setOnAction((ActionEvent event) -> {
            registerStudent();
            if(isRegister()){
                this.alertBox.informationDialog("Registered Successfully!");
                this.mainFormUI.cleanFields();
            }           
        });
        
        //get main sub root 
        BorderPane mainPane = WebAdvisor.getSubRoot();
        mainPane.getChildren().removeAll();
        mainPane.setCenter(this.mainFormUI.getMainHBox());
    }//constructor
    
    private void registerStudent(){
        
        //define a statement
        Statement statement = null;
        //get text fields
        TextField textFields[] = this.mainFormUI.getRegisterFormTextFields();
   
        //define temp variable
        String query = "";
        String ssn="", firstName="", lastName="", middleName="", streetName="", 
                city="", zipCode="", state="", year="NM", degree="NN", diploma = "n", immunForm = "";
        
        java.sql.Date currentDate;  //sql.Date value - holds just Date
        
        try{
            //get data from fields
            ssn = textFields[MatriculatedFormUI.SSN].getText().trim();
            firstName = textFields[MatriculatedFormUI.FIRSTNAME].getText().trim();
            lastName = textFields[MatriculatedFormUI.LASTNAME].getText().trim();
            middleName = textFields[MatriculatedFormUI.MIDDLENAME].getText().trim();
            streetName = textFields[MatriculatedFormUI.STREET].getText().trim();
            city = textFields[MatriculatedFormUI.CITY].getText().trim();
            zipCode =  textFields[MatriculatedFormUI.ZIPCODE].getText().trim();
            
            //get date of a registration
            currentDate = java.sql.Date.valueOf(this.mainFormUI.getCurrentDate().getValue()); //localDate
            
            if(this.mainFormUI.getStateBox().getSelectionModel().getSelectedItem() != null &&
               inputValidation(ssn, firstName, lastName, middleName, streetName, city, zipCode)){
                
              if(isStudentExist(ssn) == false){
                    state = String.valueOf(this.mainFormUI.getStateBox().getSelectionModel().getSelectedItem().trim());
                
                    if( this.mainFormUI.getImmunForm().isSelected()){
                        immunForm = "y";
                    }else{
                         immunForm = "n";
                    }
                
                        //create statement;
                        statement = this.connection.createStatement();

                        //create a query statement
                        query = "INSERT INTO student_info (SSN, FirstName, MiddleName, LastName,"+
                                "Street, City, ZipCode, State, Class, Degree, Diploma, ImmunRecord, RegistrationDate)"
                                + "VALUES " + "('"+ssn +"','"+firstName.toUpperCase() +"','"+ middleName.toUpperCase() +"','"+ 
                                 lastName.toUpperCase() +"','"+ streetName.toUpperCase() +"','"+ city.toUpperCase()+"','"+zipCode+"','"+
                                 state +"','"+year +"','"+degree+"','"+diploma+"','"+immunForm+"','"+currentDate+"')";
             
                        statement.executeUpdate(query);
              
                         //Clean-up environment
                        statement.close();
                        setRegister(true);
                }else{
                    alertBox.errorDialog("Registration is not Allowed!\nStudent is already exist!\n"); //error message  
               }
              
            }else{
                alertBox.errorDialog("Registration is not Allowed! \nInput is incorrect or empty field value(s)\n"); //error message
            }//else 
            
        }catch(SQLException e){
            e.printStackTrace();               
        }catch(Exception e){
            e.printStackTrace(); 
        }finally{
            if(statement != null){
                try{
                    statement.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }    
    }//registerStudent
    
    private boolean inputValidation(String ssn, String firstName, String lastName,
                                    String middleName, String streetName, String city, 
                                    String zipCode){
   
        //get label
        Label label[] = this.mainFormUI.getRegisterFormLabel();
        
        boolean result = false;
        boolean isSSN = true, isFirstName, isLastName, isMiddleName, isStreetName, isCity, isZipCode;
        
        //input validation      
        isSSN = this.validationUtils.checkSSN(ssn);
        System.out.println(isSSN);
        isFirstName = this.validationUtils.checkName(firstName);
        isLastName = this.validationUtils.checkName(lastName);

        if(middleName.isEmpty()){
            isMiddleName = true;
        }else{
            isMiddleName = this.validationUtils.checkMiddleName(middleName);
        }
        
        isStreetName = this.validationUtils.checkStreetName(streetName);
        isCity = this.validationUtils.checkName(city);
        isZipCode = this.validationUtils.checkNumber(zipCode);

        if(!isSSN){
            label[MatriculatedFormUI.L_SSN].textProperty().set("   *SSN    ");
            label[MatriculatedFormUI.L_SSN].setTextFill(Color.RED);
        }
        
        if(!isFirstName){
            label[MatriculatedFormUI.L_FIRSTNAME].textProperty().set("   *First Name    ");
            label[MatriculatedFormUI.L_FIRSTNAME].setTextFill(Color.RED);
        }
            
        if(!isLastName){
            label[MatriculatedFormUI.L_LASTNAME].textProperty().set("   *Last Name    ");
            label[MatriculatedFormUI.L_LASTNAME].setTextFill(Color.RED);
        }
        
        if(!isMiddleName){
            label[MatriculatedFormUI.L_MIDDLENAME].textProperty().set("   *MI    ");
            label[MatriculatedFormUI.L_MIDDLENAME].setTextFill(Color.RED);
        }

        if(!isStreetName || !isCity || !isZipCode){
            label[MatriculatedFormUI.L_ADDRESS].textProperty().set("   *Address    ");
            label[MatriculatedFormUI.L_ADDRESS].setTextFill(Color.RED);
        }
            
        if(isSSN && isFirstName && isLastName && isMiddleName && isStreetName
                && isCity && isZipCode){
            
            result = true;
        }   
        return result;
    }//inputValidation
    
    private boolean isStudentExist(String ssn){ //check if student is exist
        boolean result = false;
        
        PreparedStatement preparedSt = null;
        ResultSet resultSet = null;
        String query;
        
        if(this.validationUtils.checkSSN(ssn)){
        
            try{
            query = "SELECT SSN FROM webadvisor.student_info WHERE SSN = ?";
            
            preparedSt = this.connection.prepareStatement(query);
            preparedSt.setString(1, ssn);
            
            //create a statement
            resultSet = preparedSt.executeQuery();
            
            result = resultSet.next();
            
            //Clean-up environment
            resultSet.close();
            preparedSt.close();
        
        }catch(SQLException e){
            System.err.println("SQL error: " + e.toString());
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(preparedSt != null){
                try{
                    preparedSt.close();
                }catch(SQLException e){
                    System.err.println("SQL error: " + e.toString());
                }
            }
            if(resultSet != null){
                try{
                    resultSet.close();
                }catch(SQLException e){
                    System.err.println("SQL error: " + e.toString());
                }
            }
        }
        
        }else{
            result = false;
        }
        
        return result;
    }
    
    private boolean isRegister(){
        return this.result;
    }
    
    private void setRegister(boolean value){
        this.result = value;
    }
}
