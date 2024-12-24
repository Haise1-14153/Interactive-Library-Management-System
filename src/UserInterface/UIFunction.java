/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserInterface;

/**
 *
 * @author kali
 */
public class UIFunction {
    public String padding(String str) {
        String padded_string = "";
        
        for(int i = 0; i < (100-str.length())/2; i++) {
            padded_string += " ";
        }
        
        padded_string += str;
        
        return padded_string;
    }
}
