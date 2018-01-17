/*
 * Programming Assignment 1 - Advanced JAVA
 * Create an integrated graphical application for 
 * BOOLA BOOLA University. 
 *
 * @Created by Olga Gavrylchenko, 10/12/2017
 */

package UI;

import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;

public class TableUI implements FormUI_Iface{
    
     private final HBox tableHBox;
     private final TableView<CourseInfo> table;//create table

    //define default constructor
    public TableUI(){
 
        this.tableHBox = new HBox();
        this.table = new TableView();
 
        //add table view to a HBox
        this.tableHBox.getChildren().add(this.table); 
    }//constructor TableUI
    
    public void createRegistrationTable(){
        //set multiple selection
        this.table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        TableColumn<CourseInfo, String> cCourseNumber= new TableColumn<>("Course Id");
        TableColumn<CourseInfo, String> cCourseName = new TableColumn<>("Course Name");
        TableColumn<CourseInfo, Integer> cCourseCredits = new TableColumn<>("Credits");
        TableColumn<CourseInfo, String> cMeetInfo = new TableColumn<>("Meeting information");
        TableColumn<CourseInfo, String> cMeetTime = new TableColumn<>("Time");
        TableColumn<CourseInfo, String> cMeetDate= new TableColumn<>("Dates");
        TableColumn<CourseInfo, String> cMeetPlace = new TableColumn<>("Location");
        cMeetInfo.getColumns().addAll(cMeetTime, cMeetDate, cMeetPlace);
        
        
         //initialize setCellValueFactory for each column
        cCourseNumber.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        cCourseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        cCourseCredits.setCellValueFactory(new PropertyValueFactory<>("courseCredits"));
        cMeetTime.setCellValueFactory(new PropertyValueFactory<>("courseMeetTime"));
        cMeetDate.setCellValueFactory(new PropertyValueFactory<>("courseMeetDate"));
        cMeetPlace.setCellValueFactory(new PropertyValueFactory<>("courseMeetPlace"));

        //restrict each field to resize
        cCourseNumber.setResizable(false);
        cCourseName.setResizable(false);
        cCourseCredits.setResizable(false);
        cMeetTime.setResizable(false);
        cMeetDate.setResizable(false);
        cMeetPlace.setResizable(false);
               
        //add each column to a table view
        this.table.getColumns().addAll(cCourseNumber, cCourseName, cCourseCredits, cMeetInfo);
         
        //initialize size of each column
        cCourseNumber.setPrefWidth(COLUMN_WIDTH_MID);
        cCourseName.setPrefWidth(COLUMN_WIDTH);
        cCourseCredits.setPrefWidth(COLUMN_WIDTH_MID);
        cMeetTime.setPrefWidth(COLUMN_WIDTH);
        cMeetDate.setPrefWidth(COLUMN_WIDTH);
        cMeetPlace.setPrefWidth(COLUMN_WIDTH);

  
         //initialize size of a table
         this.table.setMinSize(TABLE_WIDTH, 400);
         this.table.setMaxSize(TABLE_WIDTH, 400);
         
         //set size of HBox
         this.tableHBox.setMaxSize(SCREEN_WIDTH, 450);
         this.tableHBox.setMinSize(SCREEN_WIDTH, 450);
         this.tableHBox.setAlignment(Pos.CENTER);
    }
    
    public void createScheduleTable(){
        TableColumn<CourseInfo, String> cCourseNumber= new TableColumn<>("Course Id");
        TableColumn<CourseInfo, String> cCourseName = new TableColumn<>("Course name");
        TableColumn<CourseInfo, String> cMeetInfo = new TableColumn<>("Meeting information");
        TableColumn<CourseInfo, String> cMeetTime = new TableColumn<>("Time");
        TableColumn<CourseInfo, String> cMeetDate= new TableColumn<>("Dates");
        TableColumn<CourseInfo, String> cMeetPlace = new TableColumn<>("Location");
        
        cMeetInfo.getColumns().addAll(cMeetTime, cMeetDate, cMeetPlace);
         
        //initialize setCellValueFactory for each column
        cCourseNumber.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        cCourseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        cMeetTime.setCellValueFactory(new PropertyValueFactory<>("courseMeetTime"));
        cMeetDate.setCellValueFactory(new PropertyValueFactory<>("courseMeetDate"));
        cMeetPlace.setCellValueFactory(new PropertyValueFactory<>("courseMeetPlace"));

        //restrict each field to resize
        cCourseNumber.setResizable(false);
        //cCourseName.setResizable(false);
        cMeetTime.setResizable(false);
        cMeetDate.setResizable(false);
        cMeetPlace.setResizable(false);

        //add each column to a table view
        this.table.getColumns().addAll(cCourseNumber, cCourseName, cMeetInfo);
         
         //initialize size of each column
         cCourseNumber.setPrefWidth(COLUMN_WIDTH);
         cCourseName.setPrefWidth(COLUMN_WIDTH);
         cMeetTime.setPrefWidth(COLUMN_WIDTH);
         cMeetDate.setPrefWidth(COLUMN_WIDTH);
         cMeetPlace.setPrefWidth(COLUMN_WIDTH);
         
         //initialize size of a table
         this.table.setMinSize(TABLE_WIDTH, 400);
         this.table.setMaxSize(TABLE_WIDTH, 400);

         //set size of HBox
         this.tableHBox.setMaxSize(SCREEN_WIDTH, 450);
         this.tableHBox.setMinSize(SCREEN_WIDTH, 450);
         this.tableHBox.setAlignment(Pos.CENTER);
    }
    
    public void createReportTable(){
        
        TableColumn<CourseInfo, Integer> cTotalNumber= new TableColumn<>(" # ");
        TableColumn<CourseInfo, String> cCourseType= new TableColumn<>("Course Type");
        TableColumn<CourseInfo, String> cCourseNumber= new TableColumn<>("Course Id");
        TableColumn<CourseInfo, String> cCourseName = new TableColumn<>("Course Name");
        TableColumn<CourseInfo, Integer> cCourseCredits = new TableColumn<>("Credits");
        TableColumn<CourseInfo, Double> cCourseCosts = new TableColumn<>("Tuition");
        TableColumn<CourseInfo, Double> cCourseFee = new TableColumn<>("Registration Fee");
        TableColumn<CourseInfo, Double> cTotalCost = new TableColumn<>("Grand Total");

        //initialize setCellValueFactory for each column
        cTotalNumber.setCellValueFactory(new PropertyValueFactory<>("totalNumber"));
        cCourseType.setCellValueFactory(new PropertyValueFactory<>("courseType"));
        cCourseNumber.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        cCourseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        cCourseCredits.setCellValueFactory(new PropertyValueFactory<>("courseCredits"));
        cCourseCosts.setCellValueFactory(new PropertyValueFactory<>("courseCost"));
        cCourseFee.setCellValueFactory(new PropertyValueFactory<>("courseFee"));
        cTotalCost.setCellValueFactory(new PropertyValueFactory<>("totalCost"));
        
        //restrict each field to resize
        cTotalNumber.setResizable(false);
        cCourseNumber.setResizable(false);
        cCourseName.setResizable(false);
        cCourseType.setResizable(false);
        cCourseCredits.setResizable(false);
        cCourseCosts.setResizable(false);
        cCourseFee.setResizable(false);
        cTotalCost.setResizable(false);
         
        //add each column to a table view
        this.table.getColumns().addAll(cTotalNumber, cCourseType, cCourseNumber, cCourseName, cCourseCredits, cCourseCosts, cCourseFee, cTotalCost);
         
         //initialize size of each column
         cTotalNumber.setPrefWidth(COLUMN_WIDTH_MIN);
         cCourseType.setPrefWidth(COLUMN_WIDTH_MID);
         cCourseNumber.setPrefWidth(COLUMN_WIDTH_MID);
         cCourseName.setPrefWidth(COLUMN_WIDTH);
         cCourseCredits.setPrefWidth(COLUMN_WIDTH_MID);
         cCourseCosts.setPrefWidth(COLUMN_WIDTH_MID);
         cCourseFee.setPrefWidth(COLUMN_WIDTH-35);
         cTotalCost.setPrefWidth(COLUMN_WIDTH);
      
         //initialize size of a table
         this.table.setMinSize(TABLE_WIDTH, 400);
         this.table.setMaxSize(TABLE_WIDTH, 400);
         //set size of HBox
         this.tableHBox.setMaxSize(SCREEN_WIDTH, 450);
         this.tableHBox.setMinSize(SCREEN_WIDTH, 450);
         this.tableHBox.setAlignment(Pos.CENTER);
    }
    
    public TableView<CourseInfo> getTable(){
        return this.table;
    }
    
    public HBox getTableHBox(){
        return this.tableHBox;
    }
    
}//TableUI
