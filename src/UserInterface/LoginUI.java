/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserInterface;
import java.util.*;
/**
 *
 * @author kali
 */
public class LoginUI {
    String border60 = "====================================================================================================";
    String space10 = "          ";
    String space5 = "     ";
    
    UIFunction mod = new UIFunction();
    Scanner input =new Scanner(System.in);
    
    public String menu(int attempt) {
        System.out.println(this.border60 + "\n" +
                mod.padding("Library Management System") + "\n" +
                this.border60);
        
        System.out.print(this.space10 + "[1] Login\n" +
                this.space10 + "[2] Signup\n" +
                this.space10 + "[0] Exit\n" + this.space5 +"~ $: ");
        
        return input.next();
    }
    
    public String[] login(int login_attempt) {
        String[] data = new String[2];
        
        System.out.println(this.border60 + "\n" +
                mod.padding("Login") + "\n" +
                this.border60);
        
        if (login_attempt != 0) {
            System.out.println(this.space10 + "Invalid login credentials!");
        }
        
        System.out.print(this.space10 + "Email: ");
        data[0] = input.next();
        System.out.print(this.space10 + "Password: ");
        data[1] = input.next();
        
        return data;
    }
    
    public void signup() {
        System.out.println(this.border60);
        
    }
    
}
