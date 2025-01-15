/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interface;

/**
 *
 * @author kali
 */
public class DisplayFunctions {
    public String border125 = "============================================================================================================================="; 
    public String border100 = "====================================================================================================";
    public String space15 = "               ";
    public String space10 = "          ";
    public String space5 = "     ";
    
    public String padding(String str) {
        String padded_string = "";
        
        for(int i = 0; i < (100-str.length())/2; i++) {
            padded_string += " ";
        }
        
        padded_string += str;
        
        return padded_string;
    }
    
    public String padding(String str, int size) {
        String padded_string = "";
        
        for(int i = 0; i < (size-str.length())/2; i++) {
            padded_string += " ";
        }
        
        padded_string += str;
        
        for(int i = 0; i < (size-str.length())/2; i++) {
            padded_string += " ";
        }
        
        return padded_string;
    }
    
    public String title(String str) {
        return str.substring(0,1).toUpperCase() + str.substring(1).toLowerCase();
    }
    
    public static void clear() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
    
    public void delay(int millisecond) {
        try {
            Thread.sleep(millisecond);
        }
        catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
