/*
 * Programming Assignment 1 - Advanced JAVA
 * Create an integrated graphical application for 
 * BOOLA BOOLA University. 
 *
 * @Created by Olga Gavrylchenko, 10/12/2017
 */

package UI;


import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCombination;

public class MenuBarUI {
    
    private MenuBar menuBar;
    private Menu mainMenu[]; //array of menu item
    private MenuItem admissions[], registration[], reports[];
    
    private final String menuBarList[] = {"Admissions", "Registration", "Reports"};
    private final String menuAdmissions[] = {"Matriculated", "Non-matriculated", "", "Quit"};
    private final String menuRegistration[] = {"Full-time", "Part-time", "Non-credit"};
    private final String menuReports[] = {"Receivables", "Class Schedule"};
    
    //constants representing menu items in the menu bar
    public final static int ADMISSIONS = 0, REGISTRATION = 1, REPORTS = 2, QUIT = 3;
    public final static int MATRICULATED = 0, NONMATRICULATED = 1;
    public final static int FULLTIME = 0, PARTTIME = 1, NONCREDIT = 2;
    public final static int RECEIVABLES = 0, SCHEDULE = 1;
    
    
    //define default constructor
    public MenuBarUI(){
        //initialize menu bar
        this.menuBar = new MenuBar();
        
        this.mainMenu = createMenu(this.menuBarList); //create menu
        
        //create sub menus
        this.admissions = createMenuItems(this.menuAdmissions);
        this.registration = createMenuItems(this.menuRegistration);
        this.reports = createMenuItems(this.menuReports);
        
        this.mainMenu[MenuBarUI.ADMISSIONS].getItems().addAll(this.admissions);
        this.mainMenu[MenuBarUI.REGISTRATION].getItems().addAll(this.registration);
        this.mainMenu[MenuBarUI.REPORTS].getItems().addAll(this.reports);
        
        setAccelerator();
        //add menu to menubar
        this.menuBar.getMenus().addAll(this.mainMenu);
    }
    
    private Menu[] createMenu(String arr[]){
        Menu menu[] = new Menu[arr.length];
        //initialize each menu item
        for(int i=0; i< arr.length; i++){
           menu[i]= new Menu(arr[i]);
        }
        return menu;
    }//createMenu

    private MenuItem[] createMenuItems(String item[]){
        
        MenuItem menuItems[] = new MenuItem[item.length];
        
        for(int i=0; i < item.length; i++){
            if(item[i].isEmpty()){
                menuItems[i] = new SeparatorMenuItem();
            }else{
                menuItems[i] = new MenuItem(item[i]);
            }
        }
        return menuItems;
    }
    
    private void setAccelerator(){
        
        this.admissions[MATRICULATED].setAccelerator(KeyCombination.keyCombination("Ctrl+Q"));
        this.admissions[NONMATRICULATED].setAccelerator(KeyCombination.keyCombination("Ctrl+W"));
        this.admissions[QUIT].setAccelerator(KeyCombination.keyCombination("Esc"));
        this.registration[FULLTIME].setAccelerator(KeyCombination.keyCombination("Ctrl+F"));
        this.registration[PARTTIME].setAccelerator(KeyCombination.keyCombination("Ctrl+D"));
        this.registration[NONCREDIT].setAccelerator(KeyCombination.keyCombination("Ctrl+G"));
        this.reports[RECEIVABLES].setAccelerator(KeyCombination.keyCombination("Ctrl+R"));
        this.reports[SCHEDULE].setAccelerator(KeyCombination.keyCombination("Ctrl+E"));
     
    }//setAccelerator
    
    public MenuBar getMenuBar(){
        return this.menuBar;
    }
    
    public MenuItem[] getAdmission(){
        return this.admissions;
    }
    
    public MenuItem[] getRegistration(){
        return this.registration;
    }
    
    public MenuItem[] getReports(){
        return this.reports;
    }
    
    
}//MenuBarUI
