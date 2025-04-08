public class TaskDTO
{
    private int taskID;
    private int userID;
    private String title;
    private String content;
    private Status status;
    public TaskDTO(int taskID, int userID, String title, String content, Status status){
        setTaskID(taskID);
        setUserID(userID);
        setTitle(title);
        setContent(content);
        setStatus(status);
    }
    public int getTaskID() {return taskID;}
    public void setTaskID(int taskID) {this.taskID = taskID;}
    public int getUserID() {return userID;}
    public void setUserID(int userID) {this.userID = userID;}
    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}
    public String getContent() {return content;}
    public void setContent(String content) {this.content = content;}
    public Status getStatus() {return status;}
    public void setStatus(Status status) {this.status = status;}
}
