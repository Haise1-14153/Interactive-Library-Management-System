/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

/**
 *
 * @author kali
 */
public class DatabaseFunction {
    private String[] data = new String[10];
    
    public boolean login(String email, String pass) {
        if (!email.equals("dan") && !pass.equals("123")) {
            return false;
        }
        
        this.data[0] = "true";
        
        return true;
    }
    
    public String[] data() {
        return this.data;
    }
}
