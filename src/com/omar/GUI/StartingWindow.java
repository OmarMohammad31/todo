package com.omar.GUI;
import com.omar.Authentication;
import com.omar.DataBase.dao.UserDAOImp;
import com.omar.currentLoggedUser;
import java.sql.SQLException;
import java.util.Scanner;
public class StartingWindow
{
    private static final StartingWindow instance;
    static
    {
        try {instance = new StartingWindow();}
        catch (SQLException e) {throw new RuntimeException(e);}
    }
    public static StartingWindow getInstance() {return instance;}
    private StartingWindow() throws SQLException
    {
        while (true){
            welcome();
            Scanner input = new Scanner(System.in);
            int option = input.nextInt();
            input.nextLine();
            switch (option){
                case 1:
                    System.out.println("Enter Name: ");
                    String enteredName = input.nextLine();
                    System.out.println("Enter Email: ");
                    String enteredEmail = input.nextLine();
                    System.out.println("Enter Password: ");
                    String enteredPassword = input.nextLine();
                    if (Authentication.signUp(enteredName, enteredEmail, enteredPassword)) LoggedInWindow.getInstance();
                    break;
                case 2:
                    System.out.println("Enter Email: ");
                    enteredEmail = input.nextLine();
                    System.out.println("Enter Password: ");
                    enteredPassword = input.nextLine();
                    if (Authentication.logIn(enteredEmail, enteredPassword)){
                        System.out.println("logged in successfully!");
                        LoggedInWindow.getInstance();
                    }
                    else System.out.println("Something went wrong!");
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
    public void welcome(){
        System.out.println("Welcome to to-do list App!");
        System.out.println("1:Sign up");
        System.out.println("2:Log in");
        System.out.println("3:Exit");
    }
}