/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interface;
import java.util.*;
/**
 *
 * @author kali
 */
public class Mainmenu extends DisplayFunctions {
    Scanner input = new Scanner(System.in);
    
    public String menu() {
        main_header();
        System.out.print("\n" + space10 + "[1] Login\n" +
                space10 + "[2] Signup\n" +
                space10 + "[0] Exit\n\n" +
                space5 + "~ $: ");
        
        return input.nextLine();
    }
    
    public String[] login_page(int login_attempt) {
        String[] data = new String[2];
        String[] text = {"Email: ", "Password: "};
        
        login_header();
        if (login_attempt != 0) {
            System.out.println(space10 + "Invalid login credentials!");
        }
        
        for(int i = 0; i < text.length; i++) {
            System.out.print(space10 + text[i]);
            data[i] = input.nextLine();
        }
 
        return data;
    }
    
    public String[] signup_page() {
        String[] data = new String[5];
        String[] text = {"Email: ", "Password: ", "Firstname: ", "Lastname: "};
        
        signup_header();
        for(int i = 0; i < text.length; i++) {
            System.out.print(space10 + text[i]);
            data[i+1] = input.nextLine();
        }
        
        data[0] = acc_type().trim();
        data[3] = title(data[3]).trim();
        data[4] = title(data[4]).trim();
        
        return data;
    }
    
    public boolean sign_up_confirmation() {
        String choice = null;
        
        while(true) {
            clear();
            
            signup_header();
            System.out.print(space5 + "-Create account?-\n" +
                    space10 + "[1] Yes\n" +
                    space10 + "[0] No\n" +
                    space5 + "~ $: " );

            choice = input.nextLine();
            
            if(choice.equals("1") || choice.equals("0")) {
                return choice.equals("1");
            }
        }
    }
    
    public String acc_type() {
        String choice = null;
        
        while(true) {
            clear(); 

            System.out.println(border100 + "\n" +
                    padding("Sign up") + "\n" +
                    border100);
            System.out.print(space10 + "Account type\n" +
                    space10 + "[1] Admin account\n" +
                    space10 + "[0] User account\n" +
                    space5 + "~ $: ");
            
            choice = input.nextLine();
             
            if(choice.equals("1") || choice.equals("0")) {
                return (choice.equals("1")) ? "true" : "false";
            }
        }
    }
    
    public void acc_created_notif() {
        clear();
        signup_header();
        System.out.println("\n\n" + padding("Account created successfully.") + "\n\n");
        
        delay(1200);
    }
    
    public void acc_creation_cancelled_notif() {
        clear();
        signup_header();
        System.out.println("\n\n" + padding("Account creation was cancelled.") + "\n\n");
        
        delay(1200);
    }
    
    public void email_existed_notif() {
        clear();
        signup_header();
        System.out.println("\n\n" + padding("Email already existed.") + "\n\n");
        
        delay(1200);
    }
    
    private void signup_header() {
        System.out.println(border100 + "\n\n" +
                padding("Sign up") + "\n\n" +
                border100);
    }
    
    private void login_header() {
        System.out.println(border100 + "\n\n" +
                padding("Login") + "\n\n" +
                border100);
    }
    
    private void main_header() {
        System.out.println(border100 + "\n\n" +
                padding("Library Management System") + "\n\n" +
                border100);
    }
}
