/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import UserInterface.*;
import Database.*;
import UserInterface.AdminInterface.*;
/**
 *
 * @author kali
 */
public class Main {
    private static String[] data = new String[10];
    private static boolean admin_acc = false;
    
    public static void main(String[] args) {
        LoginUI display = new LoginUI();
        DatabaseFunction check = new DatabaseFunction();
        
        String[] login_credential = new String[2];
        int login_attempt = 0;
        int input_attempt = 0;
        
        while(true) {
            String choice =  display.menu(input_attempt);
            
            if(choice.equals("1")) {
                clear_console();
                login_credential = display.login(login_attempt);

                if (check.login(login_credential[0], login_credential[1])) {
                    data = check.data();
                    break;
                }

                login_attempt += 1;
                
                break;
            }
            else if(choice.equals("2")) {
                clear_console();
                    
            }
            else if(choice.equals("0")) {
                System.exit(0);
            }
            
            input_attempt += 1;
            clear_console();
        }
        
        admin_acc = (data[0] == "true") ? true : false;
        
        if(admin_acc) {
            Admin run = new Admin();
            
            run.home();
        }
        else {
            
        }
        
        
    }
    
    private static void clear_console() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
}
