/*
 * Programming Assignment 1 - Advanced JAVA
 * Create an integrated graphical application for 
 * BOOLA BOOLA University. 
 *
 * @Created by Olga Gavrylchenko, 10/12/2017
 */
package Functional;

import UI.RegistrationFormUI;
import UI.CourseCost_Iface;
import UI.CourseInfo;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.sql.*;

public class FullTimeForm implements CourseCost_Iface{
    
    private Connection connection = null;
    private RegistrationFormUI mainFormUI;
    private AlertBox alertBox;
    private ValidationUtils validationUtils;
    private TableView<CourseInfo> table;
    private ObservableList<CourseInfo> courseData; //array of data
    private CourseInfo[] selectedData;
     
    private String studentFirstName, studentLastName, studyModel, immunRecord;
    private boolean result = false;
    
    public FullTimeForm(Connection connection, int studyModel){
        
        this.connection = connection;
        this.alertBox = new AlertBox();
        this.validationUtils = new ValidationUtils();
        this.mainFormUI = new RegistrationFormUI(studyModel);
        //inittialize table view
        this.table = this.mainFormUI.getTableUI().getTable();
        //initialize observable list
        this.courseData = FXCollections.observableArrayList();
        
        
        //SEARCH  button - get student information from a db
        this.mainFormUI.getSearchButton().setOnAction((ActionEvent event) -> {
               
               String ssn = this.mainFormUI.getRegisterFormTextFields()[RegistrationFormUI.SSN].getText().trim();
               if(isStudentExist(ssn)){
                   
                   this.mainFormUI.getRegisterFormTextFields()[RegistrationFormUI.SSN].setEditable(false);
                   this.mainFormUI.getRegisterFormTextFields()[RegistrationFormUI.FIRSTNAME].setText(this.studentFirstName);
                   this.mainFormUI.getRegisterFormTextFields()[RegistrationFormUI.LASTNAME].setText(this.studentLastName);
                   
                   int x = (Integer)this.mainFormUI.getModelStudyGroup().getSelectedToggle().getUserData();
                   if(x == 1 || x == 2){ //full time
                             getClassesFromDB("CR"); //classes schedule from db
                             
                    }else{ //non credit
                            getClassesFromDB("NC"); //classes schedule from db
                    }
                   
               }else{
                   this.alertBox.errorDialog("Student does not exist!");
               }
         });
        
        //insert data to a table view
        this.table.itemsProperty().setValue(this.courseData);
        
        //SUBMIT button
        this.mainFormUI.getRegisterButton().setOnAction((ActionEvent event)->{
            ArrayList<String> data = getSelectedClasses(); //get list of selected classes
            String ssn = this.mainFormUI.getRegisterFormTextFields()[RegistrationFormUI.SSN].getText().trim(); //get student SSN
            HashMap<String, Double> result;
            
           if(data.isEmpty()){
               this.alertBox.errorDialog("Classes do not selected! ");
               
            }else{ // 3 classes are 9 credits
               int x = (Integer)this.mainFormUI.getModelStudyGroup().getSelectedToggle().getUserData();
               
               if(x == 1){ //full time
                   
                    if(data.size() > 2){ //register for more than 6 credits
                        if(this.studyModel.equals("NM") && this.immunRecord.equals("n")){
                            
                             this.alertBox.errorDialog("Registration is not allowed!\n You need to submit IMMUNIZATION RECORD before register to classes!");
                        }else{
                             result = getFullTimeCoursesCost(data); 
                             insertSelectedClasses(result, ssn);
                            //add alert boxto review an information!!!
                            if(isRegister()){
                                this.alertBox.informationDialog("Registration was successfull!");
                                this.mainFormUI.clearForm();
                                this.table.getItems().clear();
                            }
                        }
                    }else{
                        this.alertBox.errorDialog("Select PART-TIME option to register for classes!");
                    }
                    
               }else if(x == 2){ //part time
       
                    if(data.size() <= 2){ //register for 6 credits and less
                        result = getPartTimeCoursesCost(data); 
                        insertSelectedClasses(result, ssn);
                        //add alert boxto review an information!!!
                     if(isRegister()){
                         this.alertBox.informationDialog("Registration was successfull!");
                         this.mainFormUI.clearForm();
                         this.table.getItems().clear();
                }
                    }else{ 
                        this.alertBox.errorDialog("Select FULL-TIME option to register for classes!");
                    }
            
               }else if(x == 3){ //non credit
                      result = getNonCreditCoursesCost(data);  //does not matter have many credits
                      insertSelectedClasses(result, ssn);
                      //add alert boxto review an information!!!
                      if(isRegister()){
                         this.alertBox.informationDialog("Registration was successfull!");
                         this.mainFormUI.clearForm();
                         this.table.getItems().clear();
                      }
               }
            }//else
        });   
        
        //get main sub root 
        BorderPane mainPane = WebAdvisor.getSubRoot();
        mainPane.getChildren().removeAll();
        mainPane.setCenter(mainFormUI.getMainHBox());
    }
    
    private void getClassesFromDB(String classType){
    
        PreparedStatement preparedSt = null;
        ResultSet resultSet = null;
        
        //define temp variable
        String query, courseId, name, meetTime, meetDate, location, type;
        int credits;
        
        try{
            query = "SELECT * FROM class_schedule WHERE course_type = ?";
            
            preparedSt = this.connection.prepareStatement(query);
            preparedSt.setString(1, classType);
            //create a statement
            resultSet = preparedSt.executeQuery();
            
            //Ensure we start with first row
            resultSet.beforeFirst(); //think if i need it

            while(resultSet.next()){//iterate through rows

                //Retrieve by column name
                courseId = resultSet.getString("course_id");
                name = resultSet.getString("course_name");
                credits = resultSet.getInt("course_credits");
                meetTime = resultSet.getString("meet_time");
                meetDate = resultSet.getString("meet_date");
                location = resultSet.getString("meet_location");
                
                //instansiate an object
               CourseInfo newCourse = new CourseInfo(courseId, name, credits, meetTime, meetDate, location);
               this.courseData.add(newCourse); //add object to observable list

            }//while loop

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
    }//setDataToAddressBook
    
    
    private ArrayList<String> getSelectedClasses(){
        //define and inittialize array list tot hold selected classes
        ArrayList<String> arrCourseID = new ArrayList<>();
        
        //get selected rows from a tableview
        ObservableList<CourseInfo> data  = this.table.getSelectionModel().getSelectedItems();
        
        //iterate though an observable list
        for(CourseInfo el: data){
            arrCourseID.add(el.getCourseId()); //add class id to an array list
        }
        
        return arrCourseID;
    }//getSelectedClasses
    
    private void insertSelectedClasses(HashMap<String, Double> arr, String ssn){
    
        //define a statement
        Statement statement = null;
        String query;
        try{
             //create statement;
            statement = this.connection.createStatement();
          
            for(Map.Entry data: arr.entrySet()){
                //create a query statement
                query = "INSERT INTO registration (SSN, course_id, price) VALUES " + "('"+ ssn +"','"+ data.getKey() +"','"+ data.getValue() +"')";  
                statement.executeUpdate(query);         
            }    
            //Clean-up environment
            statement.close();
            setRegister(true);
            
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
    }
    
    private boolean isStudentExist(String ssn){ //check if student is exist
        boolean result = false;
        
        PreparedStatement preparedSt = null;
        ResultSet resultSet = null;
        String query;
        
        if(this.validationUtils.checkSSN(ssn)){
        
            try{
            query = "SELECT FirstName, LastName, Class, ImmunRecord FROM webadvisor.student_info WHERE SSN = ?";
            
            preparedSt = this.connection.prepareStatement(query);
            preparedSt.setString(1, ssn);
            
            //create a statement
            resultSet = preparedSt.executeQuery();
            
            result = resultSet.next();
            
            //get student value from db
            if(result){
                this.studentFirstName = resultSet.getString("FirstName");
                this.studentLastName = resultSet.getString("LastName");
                this.studyModel = resultSet.getString("Class");
                this.immunRecord = resultSet.getString("ImmunRecord");
            }
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
    
    private HashMap<String, Double> getFullTimeCoursesCost(ArrayList<String> selectedCourses){
        
        HashMap<String, Double> calcResult = new HashMap<String, Double>();
        int credit= 0;
        int total = 0;
        
        for(String data: selectedCourses){
            
            credit += 3;
            
            if(credit <= 9){
                
               calcResult.put(data, CourseCost_Iface.THREE_CREDIT);
               
            }else{ // more than 9 credits
                
               calcResult.put(data, CourseCost_Iface.NINE_CREDIT);
            }
        }
        
        return calcResult;
    }
    
    private HashMap<String, Double> getPartTimeCoursesCost(ArrayList<String> selectedCourses){
        
        HashMap<String, Double> calcResult = new HashMap<String, Double>();
        
        for(String data: selectedCourses){

            calcResult.put(data, CourseCost_Iface.SIX_CREDIT);
        } 
        return calcResult;
    }
    
    private HashMap<String, Double> getNonCreditCoursesCost(ArrayList<String> selectedCourses){
        
        HashMap<String, Double> calcResult = new HashMap<String, Double>();
        
        for(String data: selectedCourses){

            calcResult.put(data, CourseCost_Iface.NONCREDIT_FEE);
        } 
        return calcResult;
    }
    
       private boolean isRegister(){
        return this.result;
    }
    
    private void setRegister(boolean value){
        this.result = value;
    }  
}//FullTimeForm


