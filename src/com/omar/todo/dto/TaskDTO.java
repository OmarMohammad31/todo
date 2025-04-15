package com.omar.todo.dto;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class TaskDTO
{
    private int taskID;
    private int userID;
    private String title;
    private String content;
    private Status status;
    private Priority priority;
    private LocalDateTime dueDate;
    public TaskDTO(int taskID,int userID, String title, String content, Status status, Priority priority, LocalDateTime dueDate){
        setTaskID(taskID);
        setUserID(userID);
        setTitle(title);
        setContent(content);
        setStatus(status);
        setPriority(priority);
        setDueDate(dueDate);
    }
    public TaskDTO(int userID, String title, String content, Status status, Priority priority, LocalDateTime dueDate){
        setUserID(userID);
        setTitle(title);
        setContent(content);
        setStatus(status);
        setPriority(priority);
        setDueDate(dueDate);
    }
    @Override
    public String toString() {return "TaskDTO{"+"taskID="+taskID+", userID="+userID+", title='"+title+'\''+", content='"+content+'\''+", status="+status+'}';}
    public int getTaskID() {return taskID;}
    public void setTaskID(int taskID) {this.taskID = taskID;}
    public int getUserID() {return userID;}
    public void setUserID(int userID) {this.userID = userID;}
    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}
    public String getContent() {return content;}
    public void setContent(String content) {this.content = content;}
    public Status getStatus() {return status;}
    public String getStatusString(){return status.toString();}
    public void setStatus(Status status) {this.status = status;}
    public Priority getPriority() {return priority;}
    public String getPriorityString(){return priority.toString();}
    public void setPriority(Priority priority) {this.priority = priority;}
    public LocalDateTime getDueDate() {return dueDate;}
    public String getDueDateString(){return dueDate.toString();}
    public void setDueDate(LocalDateTime dueDate) {this.dueDate = dueDate;}
}