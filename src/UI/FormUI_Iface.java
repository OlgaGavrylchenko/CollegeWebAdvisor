/*
 * Programming Assignment 1 - Advanced JAVA
 * Create an integrated graphical application for 
 * BOOLA BOOLA University. 
 *
 * @Created by Olga Gavrylchenko, 10/12/2017
 */

package UI;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;


public interface FormUI_Iface {
    //BUTTON SIZE
    public double BUTTON_WIDTH = 220.0;
    public double BUTTON_HEIGHT = 30.0;
    
    //FONT
    public Font MAIN_FONT_SMALL = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 16);
    public Font MAIN_FONT_MEDIUM = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 18);
    public Font CHECKBOX_FONT_MEDIUM = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 16);
    public Font TITLE_FONT_SMALL = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20);
    public Font TITLE_FONT_MEDIUM = Font.font("Times New Roman", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 22);
    public Font TITLE_FONT_LARGE = Font.font("Times New Roman", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 36);

    public double SCREEN_WIDTH = 1200.0;
    public double SCREEN_HEIGHT = 900.0;
    
    public double GRID_WIDTH = 400.0;
    public double GRID_HEIGHT = 250.0;
    
    public double REGISTER_FORM_WIDTH = 700.0;
    public double REGISTER_FORM_HEIGHT = 700.0;
    
    //TABLE
    public double COLUMN_WIDTH =225.0;
    public double COLUMN_WIDTH_MIN = 60.0;
    public double COLUMN_WIDTH_MID = 100.0;
    public double COLUMN_WIDTH_MID2 = 160.0;
    
    //TEXTFIELD
    public double TEXTFIELD_WIDTH = 180.0;

    public double HBOX_WIDTH = 900.0;
    public double HBOX_HEIGHT = 600.0;
    
    public double TABLE_WIDTH = 1100.0;
    
    public double TITLE_HEIGHT = 70.0;
    public double TITLE_HEIGHT_SMALL = 50.0;
    
}
