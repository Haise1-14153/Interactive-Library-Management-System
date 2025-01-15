/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interface;

import Classes.Person;
import Database.Database;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 *
 * @author kali
 */
public class User extends DisplayFunctions {
    LocalDate current_date = LocalDate.now();
    DateTimeFormatter date_format = DateTimeFormatter.ofPattern("MMM-d-yyyy");
    Database database = new Database();
    DisplayFunctions gui = new DisplayFunctions();
    
    Person user = null;
    
    //constructor
    public User(Person person) {
        this.user = person;
    }
    
    public void main() {
        while(true) {
            String choice = null;
            choice = home();
            
            if(choice.equals("1")) {
                view_available_books();
            }
            else if(choice.equals("2")) {
                search_book();
            }
            else if(choice.equals("3")) {
                borrow_book();
            }
            else if(choice.equals("4")) {
                return_book();
            }
            else if(choice.equals("0")) {
                log_out_message();
                delay(1500);
                clear();
                return;
            }
        }
    }
    
    private void return_book() {
        gui.clear();
        
        String ISBN = null;
        
        gui.clear();
        
        main_header();
        System.out.println(gui.space10 + "-Return book-");
        System.out.print(gui.space10 + "Enter ISBN: ");
        
        ISBN = user.input.nextLine();
        
        if(ISBN.equals("")) {
            ISBN_empty_message();
            delay(1500);
            return;
        }
        
        if(return_confirmation(ISBN)) {
            return_notif();
            delay(1500);
        }
        else {
            cancelled_return_notif();
            delay(1500);
        }
    }
    
    private void borrow_book() {
        gui.clear();
        
        String ISBN = null;
        
        gui.clear();
        
        main_header();
        System.out.println(gui.space10 + "-Borrow book-");
        System.out.print(gui.space10 + "Enter ISBN: ");
        
        ISBN = user.input.nextLine();
        
        if(ISBN.equals("")) {
            ISBN_empty_message();
            delay(1500);
            return;
        }
        
        if(borrow_confirmation(ISBN)) {
            borrow_notif();
            delay(1500);
        }
        else {
            cancelled_borrow_notif();
            delay(1500);
        }
    }
    
    private boolean return_confirmation(String ISBN) {
        gui.clear();
        String choice = null;
        int daysBetween = 0;
        double fee_per_day = 10;
        //this one is im using the tranferring the values through argument
        if(!database.get_num_days(ISBN, current_date, daysBetween)) {
            //if database.get_num_days() throws false, it means the book not found or it is available.
            return false;
        }
        
        while(true) {
            confirmation_header();
            System.out.println(gui.space10 + "-Late fee-\n" +
                    gui.space15 + "The late fee is $" + calculate_fee(daysBetween, fee_per_day) + "\n");
            
            System.out.print(gui.space10 + "-Confirm returning?-\n" +
                    gui.space15 + "[1] Yes\n" +
                    gui.space15 + "[0] No\n" +
                    gui.space10 + "~ $: " ); 

            choice = user.input.nextLine();
            
            if(choice.equals("1") || choice.equals("0")) {
                break;
            }
        }
        
        if(choice.equals("1")) {
            return database.return_book(ISBN, current_date);
        }
        else {
            return false;
        }
    }
    
    private double calculate_fee(int daysBetween, double fee_per_day) {
        return (daysBetween > 14) ? daysBetween * fee_per_day : 0;
    }
    
    private boolean borrow_confirmation(String ISBN) {
        gui.clear();
        String choice = null;
        
        while(true) {
            confirmation_header();
            
            System.out.print(gui.space10 + "-Confirm borrowing?-\n" +
                    gui.space15 + "[1] Yes\n" +
                    gui.space15 + "[0] No\n" +
                    gui.space10 + "~ $: " ); 

            choice = user.input.nextLine();
            
            if(choice.equals("1") || choice.equals("0")) {
                break;
            }
        }
        
        if(choice.equals("1")) {
            return database.set_borrow_date(ISBN, current_date);
        }
        else {
            return false;
        }
    }
    
    private void view_available_books() {
        gui.clear();
        
        System.out.println(gui.border125);
        
        System.out.println(gui.padding("ISBN", 20) + "|" +
                gui.padding("Title", 53) + "|" +
                gui.padding("Author", 25) + "|" + 
                gui.padding("Status", 25) + "|\n" + gui.border125);
        
        database.view_available_books();
        
        System.out.println(gui.border125 + "\n" + gui.space15 + "Press ENTER key to continue.");
        
        user.input.nextLine();
        gui.clear();
    }
    
    private void search_book() {
        String search_input = null;
        
        gui.clear();
        
        main_header();
        System.out.println(gui.space10 + "-Search book-");
        System.out.print(gui.space10 + "Enter title or author: ");
        
        search_input = user.input.nextLine();
        
        System.out.println("\n");
        
        System.out.println(gui.border125 + "\n" + 
                gui.padding("ISBN", 20) + "|" +
                gui.padding("Title", 53) + "|" +
                gui.padding("Author", 25) + "|" + 
                gui.padding("Borrow date", 25) + "|\n" + gui.border125);
        
        database.search_book(search_input);
        
        System.out.println(gui.border125 + "\n" + gui.space15 + "Press ENTER key to continue.");
        
        user.input.nextLine();
        gui.clear();
    }
    
    private String home() {
        clear();
        
        main_header();
        System.out.print("\n" + space15 + "[1] View available books\n" +
                space15 + "[2] Search book\n" +
                space15 + "[3] Borrow book\n" +
                space15 + "[4] Return book\n" +
                space15 + "[0] Log out\n\n" +
                title(user.get_last_name()) + title(user.get_first_name()) +
                "@user: " );
        return user.input.nextLine();
    }
    
    private void log_out_message() {
        clear();
        
        main_header();
        System.out.println(space10 + "\n\n" + padding("-Logging out.-") + "\n\n");
    }
    
    private void return_notif() {
        clear();
        
        main_header();
        System.out.println(space10 + "\n\n" + padding("-Return book successful.-") + "\n\n");
    }
    
    private void borrow_notif() {
        clear();
        
        main_header();
        System.out.println(space10 + "\n\n" + padding("-Borrow book successful.-") + "\n\n");
    }
    
    private void ISBN_empty_message() {
        clear();
        
        main_header();
        System.out.println(space10 + "\n\n" + padding("-ISBN must not be empty.-") + "\n\n");
    }
    
    private void cancelled_borrow_notif() {
        clear();
        
        main_header();
        System.out.println(space10 + "\n\n" + padding("-Borrow book was canceled or not found.-") + "\n\n");
    }
    
    private void cancelled_return_notif() {
        clear();
        
        main_header();
        System.out.println(space10 + "\n\n" + padding("-Return book was canceled or not found.-") + "\n\n");
    }
    
    private void main_header() {
        System.out.println(border100 + "\n" +
                padding("Library Management System") + "\n" +
                border100);
    }
    
    private void confirmation_header() {
        gui.clear();
        
        System.out.println(gui.border100 + "\n" +
                gui.padding("Confirmation") + "\n" +
                gui.border100);
    }
}
