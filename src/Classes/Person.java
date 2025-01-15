/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;
import Interface.DisplayFunctions;
import java.util.*;
/**
 *
 * @author kali
 */

public class Person {
    private String adminAccount = null;
    private String first_name = null;
    private String last_name = null;
    private String email = null;
    private String pass = null;
    
    public static Scanner input = new Scanner(System.in);
    
    //constructor
    public Person(String[] data) {
        this.adminAccount = data[0];
        this.email = data[1];
        this.pass = data[2];
        this.first_name = data[3];
        this.last_name = data[4];
    }
    //setters and getters
    //adminAccount
    public void set_adminAccount(String first_name) {
        this.adminAccount = adminAccount;
    }
    public String get_adminAccount() {
        return this.adminAccount;
    }
    //first_name
    public void set_first_name(String first_name) {
        this.first_name = first_name;
    }
    public String get_first_name() {
        return this.first_name;
    }
    //last_name
    public void set_last_name(String last_name) {
        this.last_name = last_name;
    }
    public String get_last_name() {
        return this.last_name;
    }
    //email
    public void set_email(String email) {
        this.email = email;
    }
    public String get_email() {
        return this.email;
    }
    //pass
    public void set_pass(String pass) {
        this.pass = pass;
    }
    public String get_pass() {
        return this.pass; 
    }
    
}
