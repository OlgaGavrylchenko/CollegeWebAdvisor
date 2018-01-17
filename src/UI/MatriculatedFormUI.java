/*
 * Programming Assignment 1 - Advanced JAVA
 * Create an integrated graphical application for 
 * BOOLA BOOLA University. 
 *
 * @Created by Olga Gavrylchenko, 10/12/2017
 */

package UI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.control.Separator;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.geometry.Orientation;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.time.LocalDate;

public class MatriculatedFormUI implements FormUI_Iface{
    
    private final static int TEXT_FIELD_SIZE = 7;
    
    private final BorderPane mainBorderPane; //for form panel
    private HBox titleHBox;
    
    //define grid pane
    private GridPane mainGridPane; //for form panel
    
    //define and initialize buttons
    private Button bClear, bRegister;
    
    //define text fields
    protected TextField registerFormTextFields[];//for contact form panel

    //define lebels for contact form panel
    protected Label registerFormLabel[], title2;
    
    // constants representing text fields in REGISTER FORM panel GUI 
    public static final int SSN = 0, FIRSTNAME = 1, MIDDLENAME = 2, LASTNAME = 3, 
                                STREET = 4, CITY = 5, ZIPCODE = 6;
    
    // constants representing LABEL fields in CONTACT FORM panel GUI 
    public static final int L_SSN = 0, L_FIRSTNAME = 1, L_MIDDLENAME = 2, L_LASTNAME = 3, 
                                L_ADDRESS = 4, L_YEAR = 5, L_DEGREE = 6, L_PREREQUISITES = 7, L_CURRENT_DATE = 8;
    
    private ComboBox<String> cbState;
    private CheckBox chbDiploma, chbImmunForm;
    private ToggleGroup tgYear, tgDegree, tgDegreeType;
    private RadioButton rbFreshman, rbSophomore, rbJunior, rbSenior, rbAssocScience, rbAssocArt;
    private DatePicker currentDate;
    
    protected final String registerFormPromptText[] = {"000-00-0000", "Bob", "J", "Smith",
                                                                            "10 Boylston street", "Boston", "02134"};
    
    protected final String registerFormLabelName[] = {"    SSN    ", "    First Name    ", "    MI    ", "    Last Name    ", "    Address    ", 
                                                                        "    Year of Matriculation    ", "    Degree    ", "    Prerequisites    ", "    Today    "};
    
    protected final String[] listOfState = {"AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI",
                                            "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI",
                                            "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC",
                                            "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT",
                                            "VT", "VA", "WA", "WV", "WI", "WY"};
    
    
    //define default constructor
    public MatriculatedFormUI(){
         
         //initialize HBox and GridPane
        mainBorderPane = new BorderPane();
        
        initializeRegisterFormPane();
        
        //register and handle the event fires by the CLEAR button
        bClear.setOnAction((ActionEvent event) -> {
            //clear each field
            cleanFields();
        });
        
        //make button disable if one of the text fields is empty
        bRegister.disableProperty().bind(registerFormTextFields[SSN].textProperty().isEmpty().or(
                                            registerFormTextFields[FIRSTNAME].textProperty().isEmpty()).or(
                                            registerFormTextFields[LASTNAME].textProperty().isEmpty()).or(
                                            registerFormTextFields[STREET].textProperty().isEmpty()).or(
                                            registerFormTextFields[CITY].textProperty().isEmpty()).or(
                                            registerFormTextFields[ZIPCODE].textProperty().isEmpty()));
        
        //add grid pane to HBox
        mainBorderPane.setTop(titleHBox);
        mainBorderPane.setCenter(mainGridPane);
    }
  
    private void initializeRegisterFormPane()
    {
        //create title
        Label title = new Label("COLLEGE ADMISSIONS");
        title2 = new Label();
        title.setFont(TITLE_FONT_MEDIUM); 
        title.setPrefHeight(TITLE_HEIGHT);
        title2.setFont(TITLE_FONT_SMALL); 
        title2.setPrefHeight(TITLE_HEIGHT_SMALL);
        VBox vbox = new VBox();
        vbox.getChildren().addAll(title, title2);
        titleHBox = new HBox();
        titleHBox.getChildren().add(vbox);


        //initialize grid pane
        mainGridPane = new GridPane();
        
         //initialize labels
        registerFormLabel = createLabel(registerFormLabelName);
        
        //initialize text fields
        registerFormTextFields = createTextField(registerFormPromptText);
        
        //initialize combo box
        cbState = new ComboBox<>();
        
        //initialize date picker
        currentDate = new DatePicker();
        //currentDate.setEditable(false);
        currentDate.setDisable(true);
        currentDate.setStyle("-fx-opacity: 1; -fx-background-color: white; -fx-font-weight: bolder;");
        currentDate.setValue(LocalDate.now());
        
        //create array of list items
        ObservableList<String> stateList = FXCollections.observableArrayList(listOfState);
        cbState.getItems().addAll(stateList);
        
       //create radio button selection
       tgYear = new ToggleGroup();
       rbFreshman= new RadioButton("Freshman");
       rbFreshman.setUserData("Freshman");
       rbFreshman.setToggleGroup(tgYear);
       rbSophomore= new RadioButton("Sophomore");
       rbSophomore.setUserData("Sophomore");
       rbSophomore.setToggleGroup(tgYear);
       rbJunior= new RadioButton("Junior");
       rbJunior.setUserData("Junior");
       rbJunior.setToggleGroup(tgYear);
       rbSenior= new RadioButton("Senior");
       rbSenior.setUserData("Senior");
       rbSenior.setToggleGroup(tgYear);
       HBox hBoxYear = new HBox();
       hBoxYear.getChildren().addAll(rbFreshman, rbSophomore, rbJunior, rbSenior);
       
       tgDegree = new ToggleGroup();
       rbAssocScience= new RadioButton("Associate of Science in Computer Programming");
       rbAssocScience.setUserData("AS");
       rbAssocScience.setToggleGroup(tgDegree);
       rbAssocArt= new RadioButton( "Assosiate of Arts in Humanities");
       rbAssocArt.setUserData("AA");
       rbAssocArt.setToggleGroup(tgDegree);
       VBox vBoxDegree = new VBox();
       vBoxDegree.getChildren().addAll(rbAssocScience, rbAssocArt);
        
       //check box
       chbDiploma = new CheckBox("High School Diploma");
       chbImmunForm = new CheckBox("Immunization record");
       VBox vBoxPrerequisite = new VBox();
       vBoxPrerequisite.getChildren().addAll(chbDiploma, chbImmunForm);
       
       
       //add each label and text field to a grid pane
        mainGridPane.add(registerFormLabel[L_CURRENT_DATE], 0, 0); 
        mainGridPane.add(currentDate, 1, 0, 2, 1); //column, row
        
        
        Label firstL = new Label("Student Personal Information");
        firstL.setFont(MAIN_FONT_MEDIUM);
        firstL.setAlignment(Pos.CENTER);
        mainGridPane.add(firstL, 0, 1);
        GridPane.setConstraints(firstL, 0, 1, 9, 1, HPos.CENTER, VPos.CENTER);
        
        Separator firstS = new Separator(Orientation.HORIZONTAL);
        firstS.setStyle("-fx-border-width: 2px; -fx-background-color: #e6e6e6;");
        mainGridPane.add(firstS, 0, 2);
        GridPane.setConstraints(firstS, 0, 2, 9, 1, HPos.CENTER, VPos.CENTER);
        
        mainGridPane.add(registerFormLabel[L_SSN], 0, 3); 
        mainGridPane.add(registerFormTextFields[SSN], 1, 3, 2,1); //column, row
        
        mainGridPane.add(registerFormLabel[L_FIRSTNAME], 0, 4); 
        mainGridPane.add(registerFormTextFields[FIRSTNAME], 1, 4, 2, 1);
        
        mainGridPane.add(registerFormLabel[L_MIDDLENAME], 4, 4); 
        mainGridPane.add(registerFormTextFields[MIDDLENAME], 5, 4);
        
        mainGridPane.add(registerFormLabel[L_LASTNAME], 6, 4);
        mainGridPane.add(registerFormTextFields[LASTNAME], 7, 4);
        
        mainGridPane.add(registerFormLabel[L_ADDRESS], 0, 6);
        mainGridPane.add(registerFormTextFields[STREET], 1, 6, 2, 1);
        mainGridPane.add(registerFormTextFields[CITY], 4, 6, 2, 1);
        mainGridPane.add(registerFormTextFields[ZIPCODE], 6, 6);
        mainGridPane.add(cbState, 7, 6);
        
        Label secondL = new Label("Educational Information");
        secondL.setFont(MAIN_FONT_MEDIUM);
        secondL.setAlignment(Pos.CENTER);
        mainGridPane.add(secondL, 0, 8);
        GridPane.setConstraints(secondL, 0, 8, 9, 1, HPos.CENTER, VPos.CENTER);
        
        Separator secondS = new Separator(Orientation.HORIZONTAL);
        secondS.setStyle("-fx-border-width: 2px; -fx-background-color: #e6e6e6;");
        mainGridPane.add(secondS, 0, 9);
        GridPane.setConstraints(secondS, 0, 9, 9, 1, HPos.CENTER, VPos.CENTER);
        
        mainGridPane.add(registerFormLabel[L_YEAR], 0, 10, 2, 1);
        mainGridPane.add(hBoxYear, 2, 10, 7, 1);
        
        mainGridPane.add(registerFormLabel[L_DEGREE], 0, 11, 2, 1);
        GridPane.setConstraints(registerFormLabel[L_DEGREE], 0, 11, 2, 1, HPos.LEFT, VPos.TOP);
        registerFormLabel[L_DEGREE].setPadding(new Insets(15, 0, 0, 0));
        mainGridPane.add(vBoxDegree, 1, 11, 8, 1);
        
        mainGridPane.add(registerFormLabel[L_PREREQUISITES], 0, 12);
        mainGridPane.add(vBoxPrerequisite, 1, 12, 4, 1);
        GridPane.setConstraints(registerFormLabel[L_PREREQUISITES], 0, 12, 1, 1, HPos.LEFT, VPos.TOP);
        registerFormLabel[L_PREREQUISITES].setPadding(new Insets(15, 0, 0, 0));
        
        mainGridPane.setAlignment(Pos.CENTER);
       
        //initialize add buttons to a grid pane
        bRegister = new Button(" SUBMIT ");
        bClear = new Button(" RESET A FORM ");
        mainGridPane.add(bClear, 7, 13);
        mainGridPane.add(bRegister, 8, 13);
          
        registerFormStyle(); //style each element of form
        
        addTextFieldListener(); //add change listener to text field which notify whenever the value changes
     
    }// initializeRegisterFormPane
    
   private void addTextFieldListener(){
       
       registerFormTextFields[SSN].textProperty().addListener((obs, oldText, newText)->{
            this.registerFormLabel[L_SSN].textProperty().set(registerFormLabelName[L_SSN]);
            this.registerFormLabel[L_SSN].setTextFill(Color.BLACK);
        });
        
        registerFormTextFields[FIRSTNAME].textProperty().addListener((obs, oldText, newText)->{
            this.registerFormLabel[L_FIRSTNAME].textProperty().set(registerFormLabelName[L_FIRSTNAME]);
            this.registerFormLabel[L_FIRSTNAME].setTextFill(Color.BLACK);
        });
        
        registerFormTextFields[MIDDLENAME].textProperty().addListener((obs, oldText, newText)->{
            this.registerFormLabel[L_MIDDLENAME].textProperty().set(registerFormLabelName[L_MIDDLENAME]);
            this.registerFormLabel[L_MIDDLENAME].setTextFill(Color.BLACK);
        });
        
        registerFormTextFields[LASTNAME].textProperty().addListener((obs, oldText, newText)->{
            this.registerFormLabel[L_LASTNAME].textProperty().set(registerFormLabelName[L_LASTNAME]);
            this.registerFormLabel[L_LASTNAME].setTextFill(Color.BLACK);
        });
        
        registerFormTextFields[STREET].textProperty().addListener((obs, oldText, newText)->{
            this.registerFormLabel[L_ADDRESS].textProperty().set(registerFormLabelName[L_ADDRESS]);
            this.registerFormLabel[L_ADDRESS].setTextFill(Color.BLACK);
        });
        
        registerFormTextFields[CITY].textProperty().addListener((obs, oldText, newText)->{
            this.registerFormLabel[L_ADDRESS].textProperty().set(registerFormLabelName[L_ADDRESS]);
            this.registerFormLabel[L_ADDRESS].setTextFill(Color.BLACK);
        });
        
        registerFormTextFields[ZIPCODE].textProperty().addListener((obs, oldText, newText)->{
            this.registerFormLabel[L_ADDRESS].textProperty().set(registerFormLabelName[L_ADDRESS]);
            this.registerFormLabel[L_ADDRESS].setTextFill(Color.BLACK);
        });
    }//addTextFieldListener
    
    private void registerFormStyle(){
        
        mainBorderPane.setMinSize(HBOX_WIDTH, HBOX_HEIGHT);
        mainBorderPane.setMaxSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        mainBorderPane.setStyle(" -fx-background-color: #fff;");
        
        mainGridPane.setStyle(" -fx-background-color: #f0f0f5;");
        mainGridPane.setVgap(15);
        mainGridPane.setHgap(20);
        mainGridPane.setPadding(new Insets(5));
        mainGridPane.setMinSize(SCREEN_WIDTH-50, SCREEN_HEIGHT-210);
        mainGridPane.setMaxSize(SCREEN_WIDTH-50, SCREEN_HEIGHT-210);
        
       titleHBox.setPrefHeight(90);
       titleHBox.setAlignment(Pos.CENTER);
        
        //initialize text field width
        registerFormTextFields[SSN].setMinWidth(TEXTFIELD_WIDTH);
        registerFormTextFields[FIRSTNAME].setMinWidth(TEXTFIELD_WIDTH);
        registerFormTextFields[MIDDLENAME].setMinWidth(TEXTFIELD_WIDTH/3);
        registerFormTextFields[LASTNAME].setMinWidth(TEXTFIELD_WIDTH);
        registerFormTextFields[STREET].setMinWidth(TEXTFIELD_WIDTH);
        registerFormTextFields[ZIPCODE].setMinWidth(TEXTFIELD_WIDTH/2);
        registerFormTextFields[CITY].setMinWidth(TEXTFIELD_WIDTH);
        cbState.setMinWidth(TEXTFIELD_WIDTH/2);
        currentDate.setMinWidth(TEXTFIELD_WIDTH);
        
        registerFormTextFields[SSN].setMaxWidth(TEXTFIELD_WIDTH);
        registerFormTextFields[FIRSTNAME].setMaxWidth(TEXTFIELD_WIDTH);
        registerFormTextFields[LASTNAME].setMaxWidth(TEXTFIELD_WIDTH);
        registerFormTextFields[MIDDLENAME].setMaxWidth(TEXTFIELD_WIDTH/3);  
        registerFormTextFields[STREET].setMaxWidth(TEXTFIELD_WIDTH);
        registerFormTextFields[ZIPCODE].setMaxWidth(TEXTFIELD_WIDTH/2);
        registerFormTextFields[CITY].setMaxWidth(TEXTFIELD_WIDTH);
        cbState.setMinWidth(TEXTFIELD_WIDTH/2);
        currentDate.setMaxWidth(TEXTFIELD_WIDTH);
        
        //style button
        bRegister.setFont(MAIN_FONT_SMALL);
        bClear.setFont(MAIN_FONT_SMALL);
        
        //set buttons width size
        bRegister.setMinWidth(BUTTON_WIDTH);
        bRegister.setMaxWidth(BUTTON_WIDTH);      
        bClear.setMinWidth(BUTTON_WIDTH);
        bClear.setMaxWidth(BUTTON_WIDTH);
        
        //set buttons height size
        bRegister.setMinHeight(BUTTON_HEIGHT);
        bRegister.setMaxHeight(BUTTON_HEIGHT);
        bClear.setMinHeight(BUTTON_HEIGHT);
        bClear.setMaxHeight(BUTTON_HEIGHT);
        
        //radio buttons style
       rbFreshman.setFont(CHECKBOX_FONT_MEDIUM);
       rbSophomore.setFont(CHECKBOX_FONT_MEDIUM);
       rbJunior.setFont(CHECKBOX_FONT_MEDIUM);
       rbSenior.setFont(CHECKBOX_FONT_MEDIUM);
       rbFreshman.setPadding(new Insets(15));
       rbSophomore.setPadding(new Insets(15));
       rbJunior.setPadding(new Insets(15));
       rbSenior.setPadding(new Insets(15));
       
       rbAssocArt.setFont(CHECKBOX_FONT_MEDIUM);
       rbAssocScience.setFont(CHECKBOX_FONT_MEDIUM);
       rbAssocScience.setPadding(new Insets(15));
       rbAssocArt.setPadding(new Insets(15));
       
       //check box style   
       chbDiploma.setFont(CHECKBOX_FONT_MEDIUM);
       chbImmunForm.setFont(CHECKBOX_FONT_MEDIUM);
       chbDiploma.setPadding(new Insets(15));
       chbImmunForm.setPadding(new Insets(15));
        
    }
    
    public void cleanFields(){
        
        for(int i=0; i<TEXT_FIELD_SIZE; i++){
            registerFormTextFields[i].clear();
        }
       
        cbState.setValue("");
        chbDiploma.setSelected(false);
        chbImmunForm.setSelected(false);
        rbFreshman.setSelected(false);
        rbSophomore.setSelected(false);
        rbJunior.setSelected(false);
        rbSenior.setSelected(false);
        rbAssocScience.setSelected(false);
        rbAssocArt.setSelected(false);
        
        
    }//cleanFields
    
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
    
    public void setDisableFields(boolean flag){
        
        if(flag == true){
            chbDiploma.setDisable(true);
            rbFreshman.setDisable(true);
            rbSophomore.setDisable(true);
            rbJunior.setDisable(true);
            rbSenior.setDisable(true);
            rbAssocScience.setDisable(true);
            rbAssocArt.setDisable(true);
        }
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
    
    public ComboBox<String> getStateBox(){
        return this.cbState;
    }
    //tgYear, tgDegree, tgDegreeType
    public ToggleGroup getYearGroup(){
        return this.tgYear;
    }
    
    public ToggleGroup getDegreeGroup(){
        return this.tgDegree;
    }
    
    public ToggleGroup getDegreeTypeGroup(){
        return this.tgDegreeType;
    }
    
    public CheckBox getDiploma(){
        return this.chbDiploma;
    }
    
    public CheckBox getImmunForm(){
        return this.chbImmunForm;
    }
    
    public DatePicker getCurrentDate(){
        return this.currentDate;
    }
    
    public void setTitle(String value){
        this.title2.setText(value);
    }
}//Matriculated
