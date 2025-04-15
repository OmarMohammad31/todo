package com.omar;

import com.omar.DataBase.dao.UserDAOImp;
import com.omar.DataBase.todo.dto.UserDTO;
import java.sql.SQLException;
public class currentLoggedUser
{
    private currentLoggedUser(){}
    private static UserDTO userDTO = null;
    public static boolean allocateUser(String Email) throws SQLException
    {
        if (Email != null){
            userDTO = UserDAOImp.getInstance().getUser(Email);
            return true;
        }
        return false;
    }
    public static UserDTO getUserDTO(){return userDTO;}
    public static void logOut(){userDTO = null;}
}
