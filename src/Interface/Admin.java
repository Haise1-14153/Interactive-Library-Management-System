/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interface;
import Classes.Person;
import Database.Database;
import Interface.DisplayFunctions;
/**
 *
 * @author kali
 */

/**
 * This file was the object admin.
 */
public class Admin extends DisplayFunctions {
    Database database = new Database();
    DisplayFunctions gui = new DisplayFunctions();
    Person admin = null;
    //constructor
    public Admin(Person person) {
        this.admin = person;
    }
    //main() method is the holds the admin program;
    public void main() {
        while(true) {
            String choice = null;
            choice = home();
            
            if(choice.equals("1")) {
                main_header();
                
                if(add_book()) {
                    book_added_notif();
                }
                else {
                    book_not_added_notif();
                }
            }
            else if(choice.equals("2")) {
                view_all_book();
            }
            else if(choice.equals("0")) {
                log_out_message();
                return;
            }
        }
    }
    
    private boolean add_book() {
        String[] data = new String[4];
        String choice = null;
        String[] text = {"Title: ", "Author: ", "ISBN: "};
        
        System.out.println(gui.space10 + "-Add book-");
        
        for(int i = 0; i < text.length; i++) {
            System.out.print(gui.space10 + text[i]);
            data[i] = admin.input.nextLine();
        }
        
        return confirmation(data);
    }
    
    private void view_all_book() {
        gui.clear();
        
        System.out.println(gui.border100);
        
        System.out.println(gui.padding("ISBN", 20) + "|" +
                gui.padding("Title", 53) + "|" +
                gui.padding("Author", 25) + "|\n" + gui.border100);
        
        database.view_all_books();
        
        System.out.println(gui.border100 + "\n" + gui.space15 + "Press ENTER key to continue.");
        admin.input.nextLine();
        gui.clear();
        //no return needed
    }
    
    private boolean confirmation(String[] data) {
        String choice = null;
        
        while(true) {
            confirmation_header();
            
            System.out.print(gui.space10 + "-Add this book?-\n" +
                    gui.space15 + "[1] Yes\n" +
                    gui.space15 + "[0] No\n" +
                    gui.space10 + "~ $: " ); 

            choice = admin.input.nextLine();
            
            if(choice.equals("1") || choice.equals("0")) {
                break;
            }
        }
        
        if(choice.equals("1")) {
            return database.add_book(data);
        }
        
        return false;
    }
    
    private void confirmation_header() {
        gui.clear();
        
        System.out.println(gui.border100 + "\n" +
                gui.padding("Confirmation") + "\n" +
                gui.border100);
    }
    
    private String home() {
        main_header();
        System.out.print("\n" + space15 + "[1] Add book\n" +
                space15 + "[2] View all books\n" +
                space15 + "[0] Log out\n\n" +
                title(admin.get_last_name()) + title(admin.get_first_name()) +
                "@admin: " );
        return admin.input.nextLine();
    }
    
    private void book_added_notif() {
        main_header();
        System.out.println("\n\n" + padding("-The book added successfully!") + "\n\n");
        delay(1300);
    }
    
    private void book_not_added_notif() {
        main_header();
        System.out.println("\n\n" + padding("-The book was not added on the list.") + "\n\n");
        delay(1300);
    }
    
    private void log_out_message() {
        main_header();
        System.out.println("\n\n" + padding("--Logging out.--") + "\n\n");
        delay(1500);
        clear();
    }
    
    private void main_header() {
        clear();
        
        System.out.println(border100 + "\n\n" +
                padding("Library Management System") + "\n" +
                padding("--Admin--") + "\n\n" +
                border100);
    }
}
