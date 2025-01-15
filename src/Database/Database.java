/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import Classes.Book;
import Classes.Person;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.time.temporal.ChronoUnit;
/**
 *
 * @author kali
 */
public class Database {
    private String working_dir = System.getProperty("user.dir"); //get the current working directory
    
    public Person login(String email, String pass) {
        String filepath = working_dir + "/src/Database/data/login.txt";
        
        try {
            BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream(filepath)));
            
            String line;
            
            while((line = file.readLine()) != null) {
                String[] data = line.split("\\|");
                
                if(email.trim().equalsIgnoreCase(data[1].trim())) {
                    if(pass.trim().equals(data[2].trim())) {
                        Person user = new Person(data);
                        
                        return user;
                    }
                    return null;
                }
            }
            
            file.close();
        } catch (FileNotFoundException ex) {
            return null;
        } catch (IOException ex) {
            return null;
        }
        return null;
    }
    
    public boolean check_email_exist(String email) {
        String filepath = working_dir + "/src/Database/data/login.txt";
        
        try {
            BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream(filepath)));
            
            String line;
            
            while((line = file.readLine()) != null) {
                String[] data = line.split("\\|");

                if(email.trim().equalsIgnoreCase(data[1].trim())) {
                    return true;
                }
            }
            
            file.close();
        } catch (FileNotFoundException ex) {
            return false;
        } catch (IOException ex) {
            return false;
        }
        
        return false;
    }
    
    public void view_all_books() {
        String filepath = working_dir + "/src/Database/data/books.txt";
        
        try {
            BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream(filepath)));
            
            String line;
            
            while((line = file.readLine()) != null) {
                String[] data = line.split("\\|");
                Book book = new Book(data[0], data[1],data[2],data[3]);
                
                book.set_title((book.get_title().length() > 53) ? book.get_title().substring(0,49) + "..." : data[0]);
                book.set_author((book.get_author().length() > 25) ? book.get_author().substring(0,21) + "..." : data[1]);
                
                System.out.println(String.format("%-" + 20 + "s|", padding(book.get_ISBN(), 20)) +
                        String.format("%-" + (54-1) + "s|", book.get_title()) +
                        String.format("%-" + (25-1) + "s|", book.get_author()));
            }
            
            file.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean create_acc(String[] data) {
        String filepath = working_dir + "/src/Database/data/login.txt";
        return save_to_file(filepath, data);
    }
    
    public boolean add_book(String[] data) {
        String filepath = working_dir + "/src/Database/data/books.txt";
        //this data[3] is the status of the book whether its available or not.
        data[3] = "Available";
        data[1] = title(data[1]);
        data[0] = title(data[0]);
        
        return save_to_file(filepath, data);
    }
    
    private boolean save_to_file(String filepath, String[] data) {
        try {
            BufferedWriter file = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filepath, true)));
            
            String string_data = "";
            
            for(int i = 0; i < data.length; i++) {
                string_data += (i == data.length - 1) ? data[i] : data[i] + "|";
            }
            
            file.write(string_data);
            file.newLine();
            
            file.close();
        } catch (FileNotFoundException ex) {
            return false;
        } catch (IOException ex) {
            return false;
        }
        return true;
    }
    
    private boolean save_to_file(String filepath, String[] data, boolean rewrite) {
        try {
            BufferedWriter file = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filepath, !rewrite)));
            
            String string_data = "";
            
            for(int i = 0; i < data.length; i++) {
                string_data += (i == data.length - 1) ? data[i] : data[i] + "|";
            }
            
            file.write(string_data);
            file.newLine();
            
            file.close();
        } catch (FileNotFoundException ex) {
            return false;
        } catch (IOException ex) {
            return false;
        }
        return true;
    }
    
    public void view_available_books() {
        String filepath = working_dir + "/src/Database/data/books.txt";
        
        try {
            BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream(filepath)));
            
            String line;
            boolean found_flag = false;
            
            while((line = file.readLine()) != null) {
                String[] data = line.split("\\|");
                Book book = new Book(data[0], data[1],data[2],data[3]);
                
                book.set_title((book.get_title().length() > 53) ? book.get_title().substring(0,49) + "..." : data[0]);
                book.set_author((book.get_author().length() > 25) ? book.get_author().substring(0,21) + "..." : data[1]);
                
                if(book.get_borrow_date().equalsIgnoreCase("available")) {
                    System.out.println(String.format("%-" + 20 + "s|", padding(book.get_ISBN(), 20)) +
                            String.format("%-" + (54-1) + "s|", book.get_title()) +
                            String.format("%-" + (25-1) + "s|", book.get_author()) +
                            String.format("%-" + (25-1) + "s|", padding(book.get_borrow_date(), 25)));
                    found_flag = true;
                }
            }
            
            if(!found_flag) {
                System.out.println("\n\n" + padding("There's no avaialbe books.", 125) + "\n\n");
            }
            
            file.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean return_book(String ISBN, LocalDate current_date) {
        String filepath = working_dir + "/src/Database/data/books.txt";
        ArrayList<Book> bookList = new ArrayList<>();
        
        try {
            BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream(filepath)));
            
            String line;
            boolean found_flag = false;
            
            for(int i = 0; (line = file.readLine()) != null; i++) {
                String[] data = line.split("\\|");
                bookList.add(new Book(data[0], data[1],data[2],data[3]));
            }
            
            
            for(Book book : bookList) {
                if(book.get_ISBN().equals(ISBN)) {
                    if(!book.get_borrow_date().equalsIgnoreCase("available")) {
                        book.set_borrow_date("available");
                        found_flag = true;
                        break;
                    }
                }
            }
            
            if(!found_flag) {
                return false;
            }
            
            for(Book book : bookList) {
                String[] data = {book.get_title(), book.get_author(), book.get_ISBN(), book.get_borrow_date()};
                save_to_file(filepath, data, true);
            }
            
            file.close();
        } catch (FileNotFoundException ex) {
            return false;
        } catch (IOException ex) {
            return false;
        }
        return true;
    }
    
    public boolean get_num_days(String ISBN, LocalDate current_date, int daysBetween) {
        String filepath = working_dir + "/src/Database/data/books.txt";
        ArrayList<Book> bookList = new ArrayList<>();
        
        try {
            BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream(filepath)));
            
            String line;
            boolean found_flag = false;
            
            for(int i = 0; (line = file.readLine()) != null; i++) {
                String[] data = line.split("\\|");
                bookList.add(new Book(data[0], data[1],data[2],data[3]));
            }
            
            
            for(Book book : bookList) {
                if(book.get_ISBN().equals(ISBN)) {
                    if(!book.get_borrow_date().equalsIgnoreCase("available")) {
                        daysBetween = (int)ChronoUnit.DAYS.between(LocalDate.parse(book.get_borrow_date(), DateTimeFormatter.ofPattern("MMM-d-yyyy")), current_date);
                        found_flag = true;
                    }
                }
            }
            
            if(!found_flag) {
                return false;
            }

            file.close();
        } catch (FileNotFoundException ex) {
            return false;
        } catch (IOException ex) {
            return false;
        }
        return true;
    }
    
    public boolean set_borrow_date(String ISBN, LocalDate current_date) {
        String filepath = working_dir + "/src/Database/data/books.txt";
        ArrayList<Book> bookList = new ArrayList<>();
        DateTimeFormatter date_format = DateTimeFormatter.ofPattern("MMM-d-yyyy");
        
        try {
            BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream(filepath)));
            
            String line;
            boolean found_flag = false;
            
            for(int i = 0; (line = file.readLine()) != null; i++) {
                String[] data = line.split("\\|");
                bookList.add(new Book(data[0], data[1],data[2],data[3]));
            }
            
            for(Book book : bookList) {
                if(book.get_ISBN().equals(ISBN)) {
                    if(book.get_borrow_date().equalsIgnoreCase("available")) {
                        book.set_borrow_date(current_date.format(date_format));
                        found_flag = true;
                    }
                }
            }
            
            if(!found_flag) {
                return false;
            }
            
            for(Book book : bookList) {
                String[] data = {book.get_title(), book.get_author(), book.get_ISBN(), book.get_borrow_date()};
                save_to_file(filepath, data, true);
            }
            
            file.close();
        } catch (FileNotFoundException ex) {
            return false;
        } catch (IOException ex) {
            return false;
        }
        return true;
    }
    
    public void search_book(String search_input) {
        String filepath = working_dir + "/src/Database/data/books.txt";
        
        try {
            BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream(filepath)));
            
            String line;
            boolean found_flag = false;
            
            while((line = file.readLine()) != null) {
                String[] data = line.split("\\|");
                Book book = new Book(data[0], data[1],data[2],data[3]);
                
                book.set_title((book.get_title().length() > 53) ? book.get_title().substring(0,49) + "..." : data[0]);
                book.set_author((book.get_author().length() > 25) ? book.get_author().substring(0,21) + "..." : data[1]);
                
                if(book.get_title().toLowerCase().startsWith(search_input) || book.get_author().toLowerCase().startsWith(search_input)) {
                    System.out.println(String.format("%-" + 20 + "s|", padding(book.get_ISBN(), 20)) +
                            String.format("%-" + (54-1) + "s|", book.get_title()) +
                            String.format("%-" + (25-1) + "s|", book.get_author()) +
                            String.format("%-" + (25-1) + "s|", padding(book.get_borrow_date(), 25)));
                    
                    found_flag = true;
                }
            }
            
            if(!found_flag) {
                System.out.println("\n\n" + padding("Book not found.", 125) + "\n\n");
            }
            
            file.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String padding(String str, int size) {
        String padded_string = "";
        
        for(int i = 0; i < (size-str.length())/2; i++) {
            padded_string += " ";
        }
        
        padded_string += str;
        
        return padded_string;
    }
    
    private String title(String str) {
        return str.substring(0,1).toUpperCase() + str.substring(1).toLowerCase();
    }
    
}
