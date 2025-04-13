package com.omar.todo.dto;
public class UserDTO
{
    private int id;
    private String name;
    private String Email;
    private String password;
    public UserDTO(int id, String name, String Email, String password){
        setId(id);
        setName(name);
        setEmail(Email);
        setPassword(password);
    }
    public UserDTO(String name, String Email, String password){
        setName(name);
        setEmail(Email);
        setPassword(password);
    }
    @Override
    public String toString() {return "UserDTO{"+"id="+id+", name='"+name+'\''+", Email='"+Email+'\''+", password='"+password+'\''+'}';}
    public UserDTO getUser(){return this;}
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getEmail() {return Email;}
    public void setEmail(String email) {Email = email;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
}