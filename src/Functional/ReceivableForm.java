/*
 * Programming Assignment 1 - Advanced JAVA
 * Create an integrated graphical application for 
 * BOOLA BOOLA University. 
 *
 * @Created by Olga Gavrylchenko, 10/12/2017
 */
package Functional;

import UI.CourseInfo;
import UI.ReceivableFormUI;
import UI.CourseCost_Iface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import java.sql.*;

public class ReceivableForm implements CourseCost_Iface{
    private Connection connection = null;
    
    private ReceivableFormUI mainFormUI;
    private ValidationUtils validationUtils;
    private AlertBox alertBox;
    
    private TableView<CourseInfo> mainTable;
    private ObservableList<CourseInfo> courseData; //array of data
 
    private String studentFirstName, studentLastName, studentStudyModel;
    
    
    public ReceivableForm(Connection connection){
        this.connection = connection;
        
        this.validationUtils = new ValidationUtils();
        this.alertBox = new AlertBox();
        this.courseData =  FXCollections.observableArrayList(); //list of courses
        this.mainFormUI = new ReceivableFormUI();
        this.mainTable = this.mainFormUI.getTableUI().getTable();
        
        this.mainFormUI.getSearchButton().setOnAction((ActionEvent event) -> {
               
               String ssn = this.mainFormUI.getRegisterFormTextFields()[ReceivableFormUI.SSN].getText().trim();
               if(isStudentExist(ssn)){
                   this.mainFormUI.getRegisterFormTextFields()[ReceivableFormUI.SSN].setEditable(false);
                   this.mainFormUI.getRegisterFormTextFields()[ReceivableFormUI.FIRSTNAME].setText(this.studentFirstName);
                   this.mainFormUI.getRegisterFormTextFields()[ReceivableFormUI.LASTNAME].setText(this.studentLastName);
                   this.mainFormUI.getRegisterFormTextFields()[ReceivableFormUI.CLASS].setText(this.studentStudyModel);
                   
                   getDataFromToDB(ssn);
               }else{
                   this.alertBox.errorDialog("Student does not exist!");
               }
         });
        
        //insert data to a table
        this.mainTable.itemsProperty().setValue(this.courseData);
        
        
        //get main sub root 
        BorderPane mainPane = WebAdvisor.getSubRoot();
        mainPane.getChildren().removeAll();
        mainPane.setCenter(mainFormUI.getMainHBox());
    }
    
     private boolean isStudentExist(String ssn){ //check if student is exist
        boolean result = false;
        
        PreparedStatement preparedSt = null;
        ResultSet resultSet = null;
        String query;
        
        if(this.validationUtils.checkSSN(ssn)){
        
            try{
            query = "SELECT FirstName, LastName, Class FROM webadvisor.student_info WHERE SSN = ?";
            
            preparedSt = this.connection.prepareStatement(query);
            preparedSt.setString(1, ssn);
            
            //create a statement
            resultSet = preparedSt.executeQuery();
            
            result = resultSet.next();
            
            //get student value from db
            if(result){
                this.studentFirstName = resultSet.getString("FirstName");
                this.studentLastName = resultSet.getString("LastName");
                this.studentStudyModel = resultSet.getString("Class");
            }
            
            //Clean-up environment
            resultSet.close();
            preparedSt.close();
        
        }catch(SQLException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(preparedSt != null){
                try{
                    preparedSt.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
            if(resultSet != null){
                try{
                    resultSet.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
        
        }else{
            result = false;
        }
        
        return result;
    }
    
     private void getDataFromToDB(String ssn){
    
        PreparedStatement preparedSt = null;
        ResultSet resultSet = null;
        
        //define temp variable
        String query, courseId, name, type;
        int credits;
        double tuition;
        double fee = CourseCost_Iface.COURSE_FEE;
        double grandTotal = 0;
        int totalNumber = 1;
        
        try{
            query = "SELECT c.course_id, c.course_name, c.course_credits, "
                    + "c.course_type, r.price FROM class_schedule c, registration r "
                    + "WHERE c.course_id = r.course_id AND r.SSN = ? ORDER BY c.course_type ASC";
            
            preparedSt = this.connection.prepareStatement(query);
            preparedSt.setString(1, ssn);
            //create a statement
            resultSet = preparedSt.executeQuery();


            while(resultSet.next()){//iterate through rows
               
                //Retrieve by column name
               courseId = resultSet.getString("course_id");
               name = resultSet.getString("course_name");
               credits = resultSet.getInt("course_credits");
               type = resultSet.getString("course_type");
               tuition = resultSet.getDouble("price");
               
               //calculate grand total
               grandTotal += (tuition + fee);
                //instansiate an object
               CourseInfo newCourse = new CourseInfo(totalNumber, courseId, name, type, credits, tuition, fee, grandTotal);
               this.courseData.add(newCourse); //add object to observable list
               
               //increment number of courses
               totalNumber++;
            }//while loop
           
            
            //Clean-up environment
            resultSet.close();
            preparedSt.close();
        
        }catch(SQLException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(preparedSt != null){
                try{
                    preparedSt.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
            if(resultSet != null){
                try{
                    resultSet.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }//setDataToAddressBook
}
