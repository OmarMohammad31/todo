package com.omar;

import com.omar.DataBase.dao.UserDAOImp;
import com.omar.DataBase.todo.dto.UserDTO;
import java.sql.SQLException;
public class Authentication
{
    public static boolean signUp(String userName,String email, String password) throws SQLException
    {
        if (UserDAOImp.getInstance().searchForUser(email)) {
            System.out.println("Email already registered");
            return false;
        }
        if (UserDAOImp.getInstance().insertUser(new UserDTO(userName, email, password))==1){
            System.out.println("User registered successfully");
            if(!currentLoggedUser.allocateUser(email)){
                System.out.println("Something went wrong!");
                return false;
            }
            return true;
        }
        System.out.println("Something went wrong");
        return false;
    }
    public static boolean logIn(String email, String password) throws SQLException{
        if (UserDAOImp.getInstance().checkUserCredentials(email, password)){
            if(!currentLoggedUser.allocateUser(email)){
                System.out.println("Something went wrong!");
                return false;
            }
            return true;
        }
        return false;
    }
}
