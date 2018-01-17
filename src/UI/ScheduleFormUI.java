/*
 * Programming Assignment 1 - Advanced JAVA
 * Create an integrated graphical application for 
 * BOOLA BOOLA University. 
 *
 * @Created by Olga Gavrylchenko, 10/12/2017
 */

package UI;


import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Separator;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.geometry.HPos;

public class ScheduleFormUI implements FormUI_Iface{
    
    private final BorderPane mainBorderPane;
    private HBox formHBox, tableHBox; 
    
   // private TableScheduleUI tableUI; //define a class 
    private TableUI tableUI;
    
    //define grid pane
    private GridPane formGridPane; //for form panel
   
    //define and initialize buttons
    private Button bClear, bSearch; //search a student
    
    //define text fields
    protected TextField registerFormTextFields[];//for contact form panel
   
    //define lebels for contact form panel
    protected Label registerFormLabel[];
    
    // constants representing text fields in REGISTER FORM panel GUI 
    public static final int SSN = 0, FIRSTNAME = 1,  LASTNAME = 2, CLASS = 3;
    
    // constants representing LABEL fields in CONTACT FORM panel GUI 
    public static final int L_SSN = 0, L_FIRSTNAME = 1,  L_LASTNAME = 2, L_CLASS = 3;
    
    protected final String registerFormPromptText[] = {"000-00-0000", "Bob", "Smith", "Class"};
    
    protected final String registerFormLabelName[] = {"SSN", "First name", "Last name", "Class"};
    
    //define default constructor
    public ScheduleFormUI() {
         //inittialize HBox containers
         this.mainBorderPane = new BorderPane();
         
         this.formHBox = new HBox(); 
         this.tableHBox = new HBox(); 
    
         this.formGridPane = new GridPane();
                
         //initialize searching form
         initializeForm();
         
         this.tableUI = new TableUI(); //initialize table
         this.tableUI.createScheduleTable();
         this.tableHBox.getChildren().add(tableUI.getTableHBox());
         
         this.bClear.setOnAction((ActionEvent event) -> {
                    cleanFields(registerFormPromptText);
                    this.tableUI.getTable().getItems().clear();
         });
         
         //add all HBox containers to main container
         this.mainBorderPane.setTop(this.formHBox);
         this.mainBorderPane.setCenter(this.tableHBox);  //could use AnchorPane
    }
    
    private void initializeForm(){

         //initialize labels
        this.registerFormLabel = createLabel(registerFormLabelName);
        
        //initialize text fields
        this.registerFormTextFields = createTextField(registerFormPromptText);
        
        //add each label and text field to a grid pane
        this.formGridPane.add(registerFormLabel[L_SSN], 0, 0); 
        this.formGridPane.add(registerFormTextFields[SSN], 1, 0, 2,1); //column, row
        this.registerFormTextFields[SSN].setEditable(true);
        
        Separator firstS = new Separator(Orientation.HORIZONTAL);
        firstS.setStyle("-fx-border-width: 2px; -fx-background-color: #e6e6e6;");
        formGridPane.add(firstS, 0, 1);
        GridPane.setConstraints(firstS, 0, 1, 22, 1, HPos.CENTER, VPos.CENTER);
        
        this.formGridPane.add(registerFormLabel[L_CLASS], 0, 2); 
        this.formGridPane.add(registerFormTextFields[CLASS], 1, 2, 2, 1);
        this.registerFormTextFields[CLASS].setEditable(false);
        
        this.formGridPane.add(registerFormLabel[L_FIRSTNAME], 0, 3); 
        this.formGridPane.add(registerFormTextFields[FIRSTNAME], 1, 3, 2, 1);
        this.registerFormTextFields[FIRSTNAME].setEditable(false);
        
        this.formGridPane.add(registerFormLabel[L_LASTNAME], 0, 4);
        this.formGridPane.add(registerFormTextFields[LASTNAME], 1, 4, 2, 2);
        this.registerFormTextFields[LASTNAME].setEditable(false);
       
        this.bSearch = new Button(" SEARCH ");
        this.formGridPane.add(bSearch, 8, 4);
        
        this.bClear = new Button("RESET");
        this.formGridPane.add(bClear, 9, 4);
       
        
        //create title
        Label title = new Label("PLAN & SCHEDULE");
        title.setPrefHeight(TITLE_HEIGHT);
        title.setFont(TITLE_FONT_MEDIUM); 
        
        VBox titleHBox = new VBox(title, formGridPane);
        titleHBox.setAlignment(Pos.CENTER);

        registerFormStyle();
        
        this.formHBox.getChildren().addAll(titleHBox);//add form to a container
        
    }
    
    private void registerFormStyle(){
        
        this.mainBorderPane.setMinSize(HBOX_WIDTH, HBOX_HEIGHT);
        this.mainBorderPane.setMaxSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.mainBorderPane.setStyle(" -fx-background-color: #fff;");
        
        this.formHBox.setMinSize(SCREEN_WIDTH, 350);
        this.formHBox.setMaxSize(SCREEN_WIDTH, 350);
        
        this.formGridPane.setStyle(" -fx-background-color: #f0f0f5;");
        this.formGridPane.setVgap(15);
        this.formGridPane.setHgap(20);
        this.formGridPane.setPadding(new Insets(10, 10, 10, 40));
        this.formGridPane.setMaxSize(SCREEN_WIDTH, 250);
        this.formGridPane.setMinSize(SCREEN_WIDTH, 250);
        this.formGridPane.setAlignment(Pos.CENTER_LEFT);
        
        //initialize text field width
        this.registerFormTextFields[SSN].setMinWidth(TEXTFIELD_WIDTH);
        this.registerFormTextFields[FIRSTNAME].setMinWidth(TEXTFIELD_WIDTH);
        this.registerFormTextFields[LASTNAME].setMinWidth(TEXTFIELD_WIDTH);
        
        this.registerFormTextFields[SSN].setMaxWidth(TEXTFIELD_WIDTH);
        this.registerFormTextFields[FIRSTNAME].setMaxWidth(TEXTFIELD_WIDTH);
        this.registerFormTextFields[LASTNAME].setMaxWidth(TEXTFIELD_WIDTH); 
       
        //style buttons
        this.bSearch.setFont(MAIN_FONT_SMALL);
        this.bClear.setFont(MAIN_FONT_SMALL);
        
        //set buttons width size
        this.bSearch.setMinWidth(BUTTON_WIDTH);
        this.bSearch.setMaxWidth(BUTTON_WIDTH); 
        this.bClear.setMinWidth(BUTTON_WIDTH);
        this.bClear.setMaxWidth(BUTTON_WIDTH); 
        
        //set buttons height size
        this.bSearch.setMinHeight(BUTTON_HEIGHT);
        this.bSearch.setMaxHeight(BUTTON_HEIGHT);   
        this.bClear.setMinHeight(BUTTON_HEIGHT);
        this.bClear.setMaxHeight(BUTTON_HEIGHT);   
    }
    
     private Label[] createLabel(String arrName[]){
    
        //initialize labels
       Label[] label = new Label[arrName.length];
        for(int i=0; i<arrName.length; i++){
            Label newLabel = new Label(arrName[i]);
            newLabel.setFont(MAIN_FONT_SMALL);
            label[i] = newLabel;
        }
        return label;
    }
    
    private TextField[] createTextField(String arrName[]){
    
        //initializetext fields
       TextField[] textField = new TextField[arrName.length];
        for(int i=0; i<arrName.length; i++){
            TextField newTextField = new TextField(); //create text field
            newTextField.setPromptText(arrName[i]); //set prompt text
            textField[i] = newTextField; //add text field to ana arrays
        }
        return textField;
    }
    
    public void cleanFields(String[] arr){
        
        for(int i=0; i<arr.length; i++){
           this.registerFormTextFields[i].clear();
        }
        this.registerFormTextFields[SSN].setEditable(true);
    }//cleanFields
    
     public BorderPane getMainHBox(){
        return this.mainBorderPane;
    }
    
    public TextField[] getRegisterFormTextFields(){
        return this.registerFormTextFields;
    }
    
    public Label[] getRegisterFormLabel(){
        return this.registerFormLabel;
    }
    
    public Button getSearchButton(){
        return this.bSearch;
    }
    
    public TableUI getTableUI(){
        return this.tableUI;
    }
}//ScheduleFormUI
