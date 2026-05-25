/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */


package com.mycompany.loginsysyem;
import static com.mycompany.loginsysyem.scanner.nextLine;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;
import org.json.simple.JSONObject;
/**
 *
 * @author Student
 */
public class LoginSysyem {
    static Scanner input =  new Scanner (System.in);
    private static int messageCounter;
    
    public static boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }
    
    public static boolean checkPasswordComplexity(String password) {
        String regex = "(?=.[A-Z])(?=.*\\d)9?=.*[@$!%?&].{8,}$";
        return Pattern.matches(regex, password); 
    }
    
    public static boolean checkCellPhoneNumber(String number) {
        String regex = "\\+27\\d{9}$";
        return Pattern.matches(regex, number);
    }
    
    public static String registerUser(String username, String Password) {
        if (!checkUserName(username)) {
            return "Username is not correct. Must have _ and max 5 chars.";
        }
        if (!checkPasswordComplexity(Password)) {
            return "Password is not correct. must have 8+ chars, capital letter, number";
        }
        return "Username and password succesfully captured. User registered!";
    }
    
    public static boolean loginUser(String username, String password, String storedUsername, String storedPassword) {
        return username.equals(storedUsername) && password.equals(storedPassword);
    }
    
    public static String returnLoginStatus(boolean status) {
        if (status) {
            return "Login successful! Welcome back!";
        }else{
            return "username or password incorrect, please try again";
        }
    }
    
    static void sendMessage() {
        long messageId = 10000000000L + new Random().nextInt(900000000);
        messageCounter++;
        
        System.out.print("Input recipient number (+CCXXXXXXXXXX):");
        String recipient = scanner.nextLine(); 
              recipient = CheckRecipient(recipient);
        if (recipient == null) return;
        
        System.out.print("Enter your message (max 250 characters): ");
        String message = scanner.nextLine();
        
        if (message.length() > 250) {
            System.out.println("Message exceeds 250 characters");
            return;
        } 
        
        String[] words = message.trim().split("\\s+");
        String hash = String.format("%02d:%d:%s:%s",
                Long.valueOf(Long.toString(messageId).substring(0,2)),
                messageCounter,
                words[0].toUpperCase(),
                words.length > 1 ? words[words.length - 1].toUpperCase() : "");
        
        System.out.println("\nSelect action:");
        System.out.println("1. Post");
        System.out.println("2. Cancel");
        System.out.println("3. Archive");
        
        int action = Integer.parseInt(scanner.nextLine());
        
        if (action == 2) {
            System.out.println("Message Cancelled");
            return;
        }
        
        JSONObject jsonMessage = new JSONObject();
        jsonMessage.put("MessageID", messageId);
        jsonMessage.put("MessageHash", hash);
        jsonMessage.put("Recipient", recipient);
        jsonMessage.put("Message", message);
        
        if (action == 3) {
            messageStorage.add(jsonMessage);
            System.out.println("Message stored.");
            return;
        }
        int Total_messages = 0;
        
        Total_messages++;
        
        System.out.println("\nMessage Sent!");
        System.out.println("Message ID: " + hash);
        System.out.println("Recipient: " + recipient);
        System.out.println("Message: " + message);
    }
    
    public static void main(String[] args) {
        System.out.println("Welcome to the login System");
        
        
        System.out.println("Registration");
        String username;
        while (true) {
            System.out.println("Enter your username (must contain '_' and max 5 chars):");
            username = input.nextLine();
            if (checkUserName(username)) break;
            System.out.println("Invalid username. Example user_");
        }
        String password;
        while (true) {
            System.out.println("Enter password (8+ chars, 1 capital letter, 1 number, 1 special char):");
            password = input.nextLine();
            if(checkPasswordComplexity(password))break;
            System.out.println("Invalid password. Example: password1!\n");
        }
        //String username = null;
        String registrationMessage = LoginSysyem.registerUser(username, password);
        System.out.println(registrationMessage); 
        String storedUsername = username;
        String storedPassword = password;
        
        while (true){
            System.out.println("Enter Cell Phone (+27 followed by 9 digits):");
            String CellPhone = input.nextLine();
            if (checkCellPhone(CellPhone)) {
                System.out.println("Cell phone number successfulle added.");
                break;
            }
            System.out.println("Invalid number. Must start with +27 and followed by 9 digits. Example: +27123456789\n");
        }
        System.out.println("\nLogin");
        
        String loginUser;
        String LoginPass;
        boolean status;
        
        while(true) {
            System.out.println("\nLogin");
            
            
            String loginPass;
           
            
            while (true) {
                System.out.println("Enter Username:");
                loginUser = input.nextLine();
                
                System.out.println("Enter Password");
                loginPass = input.nextLine();
                
                status = LoginSysyem.loginUser(loginUser, loginPass, storedUsername, storedPassword);
                System.out.println(LoginSysyem.returnLoginStatus(status));
                
                if (status) break;
                System.out.println("Pleas try again.\n");
            }
            System.out.println("\n You are no logged in.");
        }
    }

    private static boolean checkCellPhone(String CellPhone) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static String CheckRecipient(String recipient) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
