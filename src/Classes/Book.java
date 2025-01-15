/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author kali
 */
public class Book {
    private String ISBN;
    private String title = null;
    private String author = null;
    private String borrow_date = null;
    
    public Book(String title, String author, String ISBN, String borrow_date) {
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.borrow_date = borrow_date;
    }
    
    //setters and getters
    //ISBN
    public void set_ISBN(String ISBN) {
        this.ISBN = ISBN;
    }
    public String get_ISBN() {
        return this.ISBN;
    }
    //title
    public void set_title(String title) {
        this.title = title;
    }
    public String get_title() {
        return this.title;
    }
    //author
    public void set_author(String author) {
        this.author = author;
    }
    public String get_author() {
        return this.author;
    }
    //borrow_date
    public void set_borrow_date(String borrow_date) {
        this.borrow_date = borrow_date;
    }
    public String get_borrow_date() {
        return this.borrow_date;
    }
}
