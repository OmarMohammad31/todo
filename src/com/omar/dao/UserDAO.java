package com.omar.dao;
import com.omar.todo.dto.UserDTO;
import java.sql.SQLException;
import java.util.List;
public interface UserDAO
{
    public int insertUser(UserDTO userDTO) throws SQLException;
    public List<UserDTO> getAllUsers() throws SQLException;
    public UserDTO getUser(int id) throws SQLException;
    public int updateUser(UserDTO userDTO) throws SQLException;
    public int deleteUser(String Email) throws SQLException;
    public boolean searchForUser(String Email) throws SQLException;
    public boolean checkUserCredentials(String Email, String password) throws SQLException;
}