import com.omar.dao.TaskDAOImp;
import com.omar.todo.dto.Priority;
import com.omar.todo.dto.Status;
import com.omar.todo.dto.TaskDTO;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class LoggedInWindow
{
    private Scanner input = new Scanner(System.in);
    private LoggedInWindow() throws SQLException
    {
        while (true){
            showOptions();
            int choice = input.nextInt();

            switch (choice){
                case 1:
                    TaskDAOImp.getInstance().insertTask(constructTask());
                    System.out.println("Task added successfully!");
                    break;
                case 2:
                    int taskID = getTaskID();
                    input.nextLine();
                    String title = getTitle();
                    String content = getContent();
                    Status status = Status.valueOf(getStatus());
                    Priority priority = Priority.valueOf(getPriority());
                    LocalDateTime dueDate = getDueDate();
                    TaskDTO updatedTask = new TaskDTO(taskID, currentLoggedUser.getUserDTO().getId(), title, content, status, priority, dueDate);
                    TaskDAOImp.getInstance().updateTask(updatedTask);
                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                default:
                    System.out.println("invalid choice");
            }
        }


    }
    private static final LoggedInWindow instance;
    static {
        try {instance = new LoggedInWindow();}
        catch (SQLException e) {throw new RuntimeException(e);}
    }
    public static LoggedInWindow getInstance(){return instance;}
    private void showOptions(){
        System.out.println("1-Add task");
        System.out.println("2-Update task");
        System.out.println("3-Delete task");
        System.out.println("4-Show all tasks");
        System.out.println("5-Delete all tasks");
    }
    private TaskDTO constructTask(){
        String title = getTitle();
        String content = getContent();
        Status status = Status.valueOf(getStatus());
        Priority priority = Priority.valueOf(getPriority());
        LocalDateTime dueDate = getDueDate();
        return new TaskDTO(currentLoggedUser.getUserDTO().getId(), title, content, status, priority, dueDate);
    }
    private int getTaskID(){
        System.out.println("Enter task ID: ");
        int taskID = input.nextInt();
        return taskID;
    }
    private String getTitle(){
        System.out.println("Enter task Title: ");
        String title = input.nextLine();
        return title;
    }
    private String getContent(){
        StringBuilder contentBuilder = new StringBuilder();
        System.out.println("Enter task content (type 'END' on a new line to finish):");
        while (true) {
            String line = input.nextLine();
            if (line.equals("END")) break;
            contentBuilder.append(line).append("\n");
        }
        return contentBuilder.toString();
    }
    private String getStatus(){
        System.out.println("Enter task status ('in_progress','done','not_started','cancelled','on_hold'): ");
        String status = input.nextLine();
        return status;
    }
    private String getPriority(){
        System.out.println("Enter task priority ('high', 'low', 'medium'): ");
        String priority = input.nextLine();
        return priority;
    }
    private LocalDateTime getDueDate(){
        LocalDateTime dueDate = null;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        while (dueDate == null) {
            System.out.println("Enter task due date and time (yyyy-MM-dd HH:mm): ");
            Scanner input = new Scanner(System.in);
            String dueDateString = input.nextLine();
            try {dueDate = LocalDateTime.parse(dueDateString, dateTimeFormatter);}
            catch (Exception e) {System.out.println("Invalid format! Please try again.");}
        }
        return dueDate;
    }
}
