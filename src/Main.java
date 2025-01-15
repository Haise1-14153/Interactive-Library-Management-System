

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import Interface.User;
import Interface.Admin;
import Classes.Person;
import Interface.Mainmenu;
import Interface.DisplayFunctions;
import Database.Database;
/**
 *
 * @author kali
 */

/**
 * This is the main file.
 * 
 * The flow of this program:
 * start:
 * step 1: start the main menu interface. The user can choice if log-in, sign up, or exit.
 * Step 2.1.1: if the user choose log-in. After logging in, the program identifies if the acc was admin or user.
 *             if admin acc, the program will go to the Admin.java.
 *             Else, the program will go to the User.java.
 * step 2.1.2: if the person logging out, it will go to the main menu(going back to step 1).
 * step 2.2.1: if the user choose sign up, the user fill out the details needed.
 * step 2.2.2: after signing up, the user will go back to the main menu(going back to step 1).
 * step 2.3.1: if the user choose exit, the program will terminate.
 * end:
 * 
 * Group members:
 * Leader: 
 *      - Jaramel,Danriel V.
 * Members:
 *      - Durano, Roselle
 *      - Jumawan, Jonel
 *      - Necesario, Kimberly
 */

public class Main extends DisplayFunctions{
    private static Database database = new Database();
    private static Mainmenu display = new Mainmenu();
    
    public static void main(String[] args) {
        Person user;
        while(true) {
            while(true) {
                String choice =  display.menu();

                if(choice.equals("1")) {
                    user = login();
                    break;
                }
                else if(choice.equals("2")) {
                    signup();
                }
                else if(choice.equals("0")) {
                    System.exit(0);
                }

                clear();
            }
            //after logging in, it will go directly on the user porgram using the "user_program()".
            user_program(user);
            //after logging out, it will go back to the main menu.
        }
    }
    
    private static void user_program(Person user) {
        //checks if the acc was an admin acc.
        if(user.get_adminAccount().equalsIgnoreCase("true")) {
            Admin run = new Admin(user);
            
            clear();
            run.main();
        }
        else {
            User run = new User(user);
            
            clear();
            run.main();
        }
        //if the program control was going back here and
        //going back to main menu.
    }
    
    //UI for login page
    private static Person login() {
        int login_attempt = 0;
        String[] login_credential = new String[2]; // this variable contains email and pass.
        Person user;
        while(true) {
            clear();
            
            login_credential = display.login_page(login_attempt); //this method throws data(email and pass). The arguments was used for graphical purposes only.

            String email = login_credential[0];
            String pass = login_credential[1];
            
            user = database.login(email, pass);
            //this method checks if the login credentials was on the database. this throws Person value
            if (user != null) {
                return user;
            } //if true, this will get the data(username, acc type, emaill, and pass)
            

            login_attempt += 1;
        }
    }
    //UI for signup
    private static void signup() {
        String[] signup_data = new String[5]; //this variable holds the data of the acc created
        
        clear();

        signup_data = display.signup_page(); //this throws an array data contains: acc type, email,pass, and username.
        
        if(database.check_email_exist(signup_data[1])) { //this checks if the email was already used
            display.email_existed_notif(); //display if true and going back to menu.
            return;
        }
        
        if(display.sign_up_confirmation()) { //if false, it ask the user to confirm acc creation.
            if(database.create_acc(signup_data)) {//if the acc creation was true, then save it to a file
                display.acc_created_notif(); //inform the user that the acc created
            }
        }
        else {
                display.acc_creation_cancelled_notif(); //inform the user that the acc creation was cancelled
            }
    }
}
