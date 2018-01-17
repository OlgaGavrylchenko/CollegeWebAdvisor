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
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;


public class RegistrationFormUI implements FormUI_Iface{
    
    private final BorderPane mainBorderPane;
    private HBox formHBox, tableHBox, totalCostHBox; 
    
    private TableUI tableUI; //define a class 

    //define grid pane
    private GridPane formGridPane, totalCostFormGridPane; //for form panel
   
    //define and initialize buttons
    private Button bClear, bRegister, bSearch, bClearSearch; //search a student
    
    //define text fields
    protected TextField registerFormTextFields[];//for contact form pane

    //define lebels for contact form panel
    protected Label registerFormLabel[];
    
    private ToggleGroup tgDegreeType; //!!! way to switch between study models
    private RadioButton rbFullTime, rbPartTime, rbNonCredit;
    
    // constants representing text fields in REGISTER FORM panel GUI 
    public static final int SSN = 0, FIRSTNAME = 1, MIDDLENAME = 2, LASTNAME = 3;
    
    // constants representing LABEL fields in CONTACT FORM panel GUI 
    public static final int L_SSN = 0, L_FIRSTNAME = 1, L_MIDDLENAME = 2, L_LASTNAME = 3;
    
    protected final String registerFormPromptText[] = {"000-00-0000", "Bob", "J", "Smith"};
    protected final String registerFormLabelName[] = {"SSN", "First name", "MI", "Last name"};
    
    //define default constructor
    public RegistrationFormUI(int num){
      
        //inittialize HBox containers
         this.mainBorderPane = new BorderPane();
         this.tableUI = new TableUI(); //initialize table
         this.tableUI.createRegistrationTable();
         
         //initialize searching form
         initializeForm(num);
         initializeTotalCostForm();
         
         //Clear button
         this.bClearSearch.setOnAction((ActionEvent event)->{  clearForm(); });
         
         this.bClear.setOnAction((ActionEvent event)->{
             clearForm();
             this.tableUI.getTable().getItems().clear();
         });
         
         this.tableHBox.getChildren().add(this.tableUI.getTableHBox());
         
         //add all HBox containers to main container
         this.mainBorderPane.setTop(this.formHBox);
         this.mainBorderPane.setCenter(this.tableHBox);  //could use AnchorPane
         this.mainBorderPane.setBottom(this.totalCostHBox);
    }
    
    private void initializeForm(int studyModel){
         this.formHBox = new HBox(); 
         this.tableHBox = new HBox(); 
         this.totalCostHBox = new HBox();
    
         this.formGridPane = new GridPane();
         this.totalCostFormGridPane = new GridPane();
         
         //initialize labels
        this.registerFormLabel = createLabel(this.registerFormLabelName);
        //initialize text fields
        this.registerFormTextFields = createTextField(this.registerFormPromptText);
        
        //add each label and text field to a grid pane
        this.formGridPane.add(registerFormLabel[L_SSN], 0, 0); 
        this.formGridPane.add(registerFormTextFields[SSN], 1, 0, 2,1); //column, row
        
        this.formGridPane.add(registerFormLabel[L_FIRSTNAME], 0, 1); 
        this.formGridPane.add(registerFormTextFields[FIRSTNAME], 1, 1, 2, 1);
        this.registerFormTextFields[FIRSTNAME].setEditable(false);
        
        this.formGridPane.add(registerFormLabel[L_LASTNAME], 0, 2);
        this.formGridPane.add(registerFormTextFields[LASTNAME], 1, 2, 2, 2);
        this.registerFormTextFields[LASTNAME].setEditable(false);
        
        this.bSearch = new Button(" SEARCH ");
        this.formGridPane.add(bSearch, 8, 1);
        this.bClearSearch = new Button("RESET");
        this.formGridPane.add(bClearSearch, 8, 2); 

        //create title
        Label title = new Label("REGISTRATION");
        title.setPrefHeight(TITLE_HEIGHT);
        title.setFont(TITLE_FONT_MEDIUM); 
        
        this.tgDegreeType = new ToggleGroup();
        this.rbFullTime = new RadioButton("FULL-TIME");
        this.rbPartTime = new RadioButton("PART-TIME");
        this.rbNonCredit = new RadioButton("NON-CREDIT");
        this.rbFullTime.setUserData(1);
        this.rbPartTime.setUserData(2);
        this.rbNonCredit.setUserData(3);
        this.rbFullTime.setToggleGroup(tgDegreeType);
        this.rbPartTime.setToggleGroup(tgDegreeType);
        this.rbNonCredit.setToggleGroup(tgDegreeType);
        
        switch (studyModel) {
            case 1:
                //full time
                this.rbFullTime.setSelected(true);
                break;
            case 2:   
                //part time
                this.rbPartTime.setSelected(true);
                break;
            case 3:
                //non credit
                this.rbNonCredit.setSelected(true);
                break;
            default:
                break;
        }
        
        HBox degreeTypeHBox = new HBox();
        degreeTypeHBox.getChildren().addAll(this.rbFullTime, this.rbPartTime, this.rbNonCredit);
        degreeTypeHBox.setAlignment(Pos.CENTER);
        
        VBox titleHBox = new VBox(title, degreeTypeHBox, this.formGridPane);
        titleHBox.setPrefHeight(150);
        titleHBox.setAlignment(Pos.CENTER);

        registerFormStyle();
        
        this.formHBox.getChildren().addAll(titleHBox);//add form to a container
        
    }
    
    private void registerFormStyle(){
        
        this.mainBorderPane.setMinSize(HBOX_WIDTH, HBOX_HEIGHT);
        this.mainBorderPane.setMaxSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.mainBorderPane.setStyle(" -fx-background-color: #fff;");
        
        this.formHBox.setMinSize(SCREEN_WIDTH, 250);
        this.formHBox.setMaxSize(SCREEN_WIDTH, 250);
        
        this.formGridPane.setStyle(" -fx-background-color: #f0f0f5;");
        this.formGridPane.setVgap(15);
        this.formGridPane.setHgap(20);
        this.formGridPane.setPadding(new Insets(10, 10, 10, 40));
        this.formGridPane.setMaxSize(SCREEN_WIDTH, 150);
        this.formGridPane.setMinSize(SCREEN_WIDTH, 150);
        this.formGridPane.setAlignment(Pos.CENTER_LEFT);
        
        //initialize text field width
        this.registerFormTextFields[SSN].setMinWidth(TEXTFIELD_WIDTH);
        this.registerFormTextFields[FIRSTNAME].setMinWidth(TEXTFIELD_WIDTH);
        this.registerFormTextFields[MIDDLENAME].setMinWidth(TEXTFIELD_WIDTH/3);
        this.registerFormTextFields[LASTNAME].setMinWidth(TEXTFIELD_WIDTH);
        
        this.registerFormTextFields[SSN].setMaxWidth(TEXTFIELD_WIDTH);
        this.registerFormTextFields[FIRSTNAME].setMaxWidth(TEXTFIELD_WIDTH);
        this.registerFormTextFields[LASTNAME].setMaxWidth(TEXTFIELD_WIDTH);
        this.registerFormTextFields[MIDDLENAME].setMaxWidth(TEXTFIELD_WIDTH/3);  
       
        //style buttons
        this.bSearch.setFont(MAIN_FONT_SMALL);
        this.bClearSearch.setFont(MAIN_FONT_SMALL);
        
        //set buttons width size
        this.bSearch.setMinWidth(BUTTON_WIDTH);
        this.bSearch.setMaxWidth(BUTTON_WIDTH); 
        this.bClearSearch.setMinWidth(BUTTON_WIDTH);
        this.bClearSearch.setMaxWidth(BUTTON_WIDTH); 
        
        //set buttons height size
        this.bSearch.setMinHeight(BUTTON_HEIGHT);
        this.bSearch.setMaxHeight(BUTTON_HEIGHT);   
        this.bClearSearch.setMinHeight(BUTTON_HEIGHT);
        this.bClearSearch.setMaxHeight(BUTTON_HEIGHT);   
        
        this.rbFullTime.setFont(CHECKBOX_FONT_MEDIUM);
        this.rbPartTime.setFont(CHECKBOX_FONT_MEDIUM);
        this.rbNonCredit.setFont(CHECKBOX_FONT_MEDIUM);
        this.rbFullTime.setPadding(new Insets(15));
        this.rbPartTime.setPadding(new Insets(15));
        this.rbNonCredit.setPadding(new Insets(15));
    }
    
    private void initializeTotalCostForm(){
        
        this.bClear = new Button("RESET");
        this.bRegister = new Button("SUBMIT");
        
        totalCostFormStyle();
        
        this.totalCostHBox.getChildren().addAll(bClear, bRegister);//add form to a container
       
    }
    
    private void totalCostFormStyle(){

        this.totalCostHBox.setStyle(" -fx-background-color: #f0f0f5;");
        this.totalCostHBox.setPadding(new Insets(30));
        this.totalCostHBox.setMaxSize(SCREEN_WIDTH, 120);
        this.totalCostHBox.setMinSize(SCREEN_WIDTH, 120);
        this.totalCostHBox.setAlignment(Pos.CENTER_RIGHT);
        this.totalCostHBox.setSpacing(15);
        //style buttons
        this.bRegister.setFont(MAIN_FONT_SMALL);
        this.bClear.setFont(MAIN_FONT_SMALL);
        
        //set buttons width size
        this.bRegister.setMinWidth(BUTTON_WIDTH);
        this.bRegister.setMaxWidth(BUTTON_WIDTH);      
        this.bClear.setMinWidth(BUTTON_WIDTH);
        this.bClear.setMaxWidth(BUTTON_WIDTH);
        
        //set buttons height size
        this.bRegister.setMinHeight(BUTTON_HEIGHT);
        this.bRegister.setMaxHeight(BUTTON_HEIGHT);
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
    
     public BorderPane getMainHBox(){
        return this.mainBorderPane;
    }
    
    public TextField[] getRegisterFormTextFields(){
        return this.registerFormTextFields;
    }
    
    public Label[] getRegisterFormLabel(){
        return this.registerFormLabel;
    }
    
    public Button getRegisterButton(){
        return this.bRegister;
    }
    
    public Button getSearchButton(){
        return this.bSearch;
    }
    
    public TableUI getTableUI(){
        return this.tableUI;
    }
    
    public ToggleGroup getModelStudyGroup(){
        return this.tgDegreeType;
    }
    
    public void clearForm(){
             this.registerFormTextFields[SSN].clear();
             this.registerFormTextFields[SSN].setEditable(true);
             this.registerFormTextFields[FIRSTNAME].clear();
             this.registerFormTextFields[LASTNAME].clear();
    }
    
}//RegistrationFormUI
