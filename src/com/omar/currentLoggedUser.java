package com.omar;

import com.omar.DataBase.dao.UserDAOImp;
import com.omar.DataBase.todo.dto.UserDTO;
import java.sql.SQLException;
public class currentLoggedUser
{
    private currentLoggedUser(){}
    private static String Email = null;
    private static UserDTO userDTO = null;
    public static String getEmail(){return Email;}
    public static void setEmail(String newEmail){Email = newEmail;}
    public static boolean allocateUser(String Email) throws SQLException
    {
        if (Email != null){
            userDTO = UserDAOImp.getInstance().getUser(Email);
            return true;
        }
        return false;
    }
    public static UserDTO getUserDTO(){return userDTO;}
}
