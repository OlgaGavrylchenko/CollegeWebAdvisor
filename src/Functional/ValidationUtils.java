/*
 * Programming Assignment 1 - Advanced JAVA
 * Create an integrated graphical application for 
 * BOOLA BOOLA University. 
 *
 * @Created by Olga Gavrylchenko, 10/12/2017
 */
package Functional;
import java.util.regex.Pattern;

public class ValidationUtils { //design for input validation
    
    //check name on alphabetic characters
    public boolean checkUserName(String name){
        
        return Pattern.matches("^[0-9a-zA-Z]+$", name);
    }
    
    //check name on alphabetic characters
    public boolean checkName(String name){
        
        //"^[a-zA-Z]*$" - ^-start of str, $-end of str, *-0 or more
        //"^[\\p{L} .'-]+$" - \p{L} any kind of letter from any language
        return Pattern.matches("^[\\p{L}]{1,}[ '-]{0,1}[\\p{L}]{1,}[ '-]{0,1}[\\p{L}]{0,}$", name);
    }
    
    public boolean checkMiddleName(String name){
        return Pattern.matches("^[a-zA-Z]+$", name);
    }
    
    //check password
    public boolean checkPassword(String password){
        
        boolean flag = false;
        
            if(password.length() < 8){ //length must be more than 8 characters
                flag = false;
            } else{
                flag = Pattern.matches("^[0-9A-Za-z]+$", password);
            }

        return flag;
    }
    
    //compare two passwords
    public boolean checkEqualPassword(String password, String rePassword){
        
        return password.endsWith(rePassword);
    }
    
    
    public boolean checkEmail(String email){

        boolean flag = false;

            String regExpDomainPart = "^[a-zA-Z]{1,}[.-]{0,1}[a-zA-Z]{2,}[.]{0,1}[a-zA-Z]{2,}$";
            String regExpLocalPart = "^[0-9a-zA-Z]{1,}[.-]{0,1}[0-9a-zA-Z]{1,}[.-]{0,1}[0-9a-zA-Z]{1,}$";
        
            char[] arr = email.toCharArray();
            int index = 0;
            for(int i=0; i<arr.length; i++){
                if(arr[i] == '@'){ //find position of @
                    index = i;
                }
                flag = false;
            }
        
            String localPart = email.substring(0, index);
            String domainPart  = email.substring(index+1);
        
            if(Pattern.matches(regExpLocalPart, localPart) && Pattern.matches(regExpDomainPart, domainPart)){
                flag = true;
            }
        
        return flag;
    }
    
    //check number on digital characters only
    public boolean checkNumber(String number){
            
        return Pattern.matches("^[0-9]+$", number); 
    }
    
    public boolean checkPhoneNumber(String number){

        return Pattern.matches("[+][0-9]{11,11}", number);
    }
    
    //check number on digital characters only
    public boolean checkStreetName(String name){
        //"^\\d+\\s+[a-zA-Z]{1,}[ .-]{0,1}[a-zA-Z]{1,}[ ]{0,1}[a-zA-Z]{0,}$" - 10 Brenton street or 12 levanevskogo-gogolya street
        String regExpAlpha = "^\\d+\\s+[\\p{L}]{1,}[ .-]{0,1}[\\p{L}]{1,}[ ]{0,1}[\\p{L}]{0,}$";

        return Pattern.matches(regExpAlpha, name);
    }
    
    //check ss number on digital characters only and length equal 9 charachters
    public boolean checkSSN(String number){
        return Pattern.matches("^\\d{3}[-]\\d{2}[-]\\d{4}$", number); 
    }
}//ValidationUtils

