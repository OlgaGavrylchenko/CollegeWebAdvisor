/*
 * Programming Assignment 1 - Advanced JAVA
 * Create an integrated graphical application for 
 * BOOLA BOOLA University. 
 *
 * @Created by Olga Gavrylchenko, 10/12/2017
 */

package UI;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class CourseInfo {

    private SimpleIntegerProperty totalNumber;
    private SimpleStringProperty courseId;
    private SimpleStringProperty courseName;
    private SimpleIntegerProperty courseCredits;
    private SimpleDoubleProperty courseCost;
    private SimpleDoubleProperty courseFee;
    private SimpleDoubleProperty totalCost;
    private SimpleStringProperty courseMeetTime;
    private SimpleStringProperty courseMeetDate;
    private SimpleStringProperty courseMeetPlace;
    private SimpleStringProperty courseType;
    
    public CourseInfo(){ //define default constructor
        
        this.totalNumber = new SimpleIntegerProperty(0);
        this.courseId = new SimpleStringProperty("");
        this.courseName = new SimpleStringProperty("");
        this.courseCredits = new SimpleIntegerProperty(0);
        this.courseCost = new SimpleDoubleProperty(0.0);
        this.courseFee = new SimpleDoubleProperty(0.0);
        this.totalCost = new SimpleDoubleProperty(0.0);
        this.courseMeetTime = new SimpleStringProperty("");
        this.courseMeetDate = new SimpleStringProperty("");
        this.courseMeetPlace = new SimpleStringProperty("");
        this.courseType = new SimpleStringProperty("");
    }
    
    public CourseInfo(String courseId, String name, int courseCredits, String meetTime, String meetDate, String meetPlace){
    
        this.courseId = new SimpleStringProperty(courseId);
        this.courseName = new SimpleStringProperty(name);
        this.courseCredits = new SimpleIntegerProperty(courseCredits);
        this.courseMeetTime = new SimpleStringProperty(meetTime);
        this.courseMeetDate = new SimpleStringProperty(meetDate);
        this.courseMeetPlace = new SimpleStringProperty(meetPlace);
    }
    
    public CourseInfo(String courseId, String name, String meetTime, String meetDate, String meetPlace){
    
        this.courseId = new SimpleStringProperty(courseId);
        this.courseName = new SimpleStringProperty(name);
        this.courseMeetTime = new SimpleStringProperty(meetTime);
        this.courseMeetDate = new SimpleStringProperty(meetDate);
        this.courseMeetPlace = new SimpleStringProperty(meetPlace);
    }
    
     public CourseInfo(String courseId, String name, String type, int credits, double  price){
    
        this.courseId = new SimpleStringProperty(courseId);
        this.courseName = new SimpleStringProperty(name);
        this.courseType = new SimpleStringProperty(type);    
        this.courseCredits = new SimpleIntegerProperty(credits);
        this.courseCost = new SimpleDoubleProperty(price);    
    }
     
    public CourseInfo(int num, String courseId, String name, String type, int credits, double tuition, double  fee, double total){
    
        this.totalNumber = new SimpleIntegerProperty(num);
        this.courseId = new SimpleStringProperty(courseId);
        this.courseName = new SimpleStringProperty(name);
        this.courseType = new SimpleStringProperty(type);  
        this.courseCredits = new SimpleIntegerProperty(credits);
        this.courseCost = new SimpleDoubleProperty(tuition);   
        this.courseFee = new SimpleDoubleProperty(fee);
        this.totalCost = new SimpleDoubleProperty(total);
    }
    
    //define mutator functions
    public void setTotalNumber(int num){
        this.totalNumber.set(num);
    }
    
    public void setCourseId(String id){
        this.courseId.set(id);
    }
    
    public void setCourseName(String name){
        this.courseName.set(name);
    }
    
    public void setCourseCredits(int credits){
        this.courseCredits.set(credits);
    }
    
    public void setCourseCost(double cost){
        this.courseCost.set(cost);
    }
    
    public void setCourseFee(double cost){
        this.courseFee.set(cost);
    }
    
    public void setTotalCost(double cost){
        this.totalCost.set(cost);
    }
    
    public void setCourseMeetTime(String time){
        this.courseMeetTime.set(time);
    }
    
    public void setCourseMeetDate(String date){
        this.courseMeetDate.set(date);
    }
    
    public void setCourseMeetPlace(String place){
        this.courseMeetPlace.set(place);
    }
    
    public void setCourseType(String type){
        this.courseType.set(type);
    }
    
    //define accessor functions
     public int getTotalNumber(){
        return this.totalNumber.get();
    }
     
    public String getCourseId(){
        return this.courseId.get();
    }
    
    public String getCourseName(){
        return this.courseName.get();
    }
    
    public int getCourseCredits(){
        return this.courseCredits.get();
    }
    
    public double getCourseCost(){
        return this.courseCost.get();
    }
    
    public double getCourseFee(){
        return this.courseFee.get();
    }
    
    public double getTotalCost(){
        return this.totalCost.get();
    }
    
    public String getCourseMeetTime(){
        return this.courseMeetTime.get();
    }
    
    public String getCourseMeetDate(){
        return this.courseMeetDate.get();
    }
    
    public String getCourseMeetPlace(){
        return this.courseMeetPlace.get();
    }
    
    public String getCourseType(){
        return this.courseType.get();
    }
    
    @Override
    public String toString(){
        return ( this.courseId.get() + "\n"+
            this.courseName.get() + "\n"+
            this.courseCredits.get() + "\n"+
            this.courseMeetTime.get() + "\n"+
            this.courseMeetDate.get() + "\n"+
            this.courseMeetPlace.get() + "\n");
    }
    
}//CourseInfo
