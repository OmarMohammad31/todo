import com.omar.dao.UserDAOImp;
import java.sql.SQLException;
import java.util.Scanner;

public class GUI
{
    private static final GUI instance;
    static
    {
        try {instance = new GUI();}
        catch (SQLException e) {throw new RuntimeException(e);}
    }
    public static GUI getInstance() {return instance;}
    private GUI() throws SQLException
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
                case 4:
                    currentLoggedUser.setEmail(null);
                    getInstance();
                    break;
                case 5:
                    if (currentLoggedUser.getEmail()!=null){
                        UserDAOImp.getInstance().deleteUser(currentLoggedUser.getEmail());
                        System.out.println("Account deleted successfully!");
                    }
                    else System.out.println("there's no logged account");
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
//adding logged window
//moving log out and delete account to logged window