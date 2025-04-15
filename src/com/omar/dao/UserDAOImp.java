package com.omar.dao;
import com.omar.DataBase;
import com.omar.todo.dto.UserDTO;

import javax.xml.crypto.Data;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class UserDAOImp implements UserDAO
{
    private static final String insertUserQuery = "INSERT INTO users(name, Email, password) VALUES(?, ?, ?)";
    private static final String getAllUsersQuery = "SELECT * FROM users";
    private static final String getUserByIDQuery = "SELECT * FROM users WHERE id = ?";
    private static final String getUserByEmailQuery = "SELECT * FROM users WHERE Email = ?";
    private static final String updateUserQuery = "UPDATE users SET name = ?, Email = ?, password = ? WHERE id = ?";
    private static final String deleteUserQuery = "DELETE FROM users WHERE Email = ?";
    private static final String searchForUserQuery = "SELECT * FROM users WHERE Email = ?";
    private static final String checkCredentialsQuery = "SELECT * FROM users WHERE Email = ? AND password = ?";
    private static final UserDAOImp instance = new UserDAOImp();
    //delte query
    private UserDAOImp(){}
    public static UserDAOImp getInstance() {return instance;}
    @Override
    public int insertUser(UserDTO userDTO) throws SQLException
    {
        PreparedStatement preparedStatement = DataBase.getConnection().prepareStatement(insertUserQuery);
        preparedStatement.setString(1,userDTO.getName());
        preparedStatement.setString(2,userDTO.getEmail());
        preparedStatement.setString(3,userDTO.getPassword());
        int numOfInsertedRecords = preparedStatement.executeUpdate();
        DataBase.closePreparedStatement(preparedStatement);
        return numOfInsertedRecords;
    }
    @Override
    public List<UserDTO> getAllUsers() throws SQLException
    {
        PreparedStatement preparedStatement = DataBase.getConnection().prepareStatement(getAllUsersQuery);
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<UserDTO> users = new ArrayList<>();
        while (resultSet.next()){
           users.add(new UserDTO(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("Email"), resultSet.getString("password")));
        }
        DataBase.closeResultSet(resultSet);
        DataBase.closePreparedStatement(preparedStatement);
        return users;
    }
    @Override
    public UserDTO getUser(int id) throws SQLException
    {
        PreparedStatement preparedStatement = DataBase.getConnection().prepareStatement(getUserByIDQuery);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            UserDTO userDTO =  new UserDTO(resultSet.getInt("id"), resultSet.getString("name"),resultSet.getString("Email"), resultSet.getString("password"));
            DataBase.closeResultSet(resultSet);
            DataBase.closePreparedStatement(preparedStatement);
            return userDTO;
        }
        DataBase.closeResultSet(resultSet);
        DataBase.closePreparedStatement(preparedStatement);
        return null;
    }
    @Override
    public UserDTO getUser(String Email) throws SQLException{
        PreparedStatement preparedStatement = DataBase.getConnection().prepareStatement(getUserByEmailQuery);
        preparedStatement.setString(1,Email);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            UserDTO userDTO = new UserDTO(resultSet.getInt("id"), resultSet.getString("name"),Email, resultSet.getString("password"));
            DataBase.closeResultSet(resultSet);
            DataBase.closePreparedStatement(preparedStatement);
            return userDTO;
        }
        DataBase.closeResultSet(resultSet);
        DataBase.closePreparedStatement(preparedStatement);
        return null;
    }
    @Override
    public int updateUser(UserDTO userDTO) throws SQLException
    {
        PreparedStatement preparedStatement = DataBase.getConnection().prepareStatement(updateUserQuery);
        preparedStatement.setString(1, userDTO.getName());
        preparedStatement.setString(2,userDTO.getEmail());
        preparedStatement.setString(3,userDTO.getPassword());
        preparedStatement.setInt(4,userDTO.getId());
        int numOfUpdatedRecords = preparedStatement.executeUpdate();
        DataBase.closePreparedStatement(preparedStatement);
        return numOfUpdatedRecords;
    }
    @Override
    public int deleteUser(String Email) throws SQLException
    {
        PreparedStatement preparedStatement = DataBase.getConnection().prepareStatement(deleteUserQuery);
        preparedStatement.setString(1,Email);
        int numOfDeletedRecords = preparedStatement.executeUpdate();
        DataBase.closePreparedStatement(preparedStatement);
        return numOfDeletedRecords;
    }
    public boolean searchForUser(String Email) throws SQLException{
        PreparedStatement preparedStatement = DataBase.getConnection().prepareStatement(searchForUserQuery);
        preparedStatement.setString(1,Email);
        ResultSet resultSet = preparedStatement.executeQuery();
        boolean isFound = resultSet.next();
        DataBase.closeResultSet(resultSet);
        DataBase.closePreparedStatement(preparedStatement);
        return isFound ;
    }
    public boolean checkUserCredentials(String Email, String password) throws SQLException{
        PreparedStatement preparedStatement = DataBase.getConnection().prepareStatement(checkCredentialsQuery);
        preparedStatement.setString(1,Email);
        preparedStatement.setString(2,password);
        ResultSet resultSet = preparedStatement.executeQuery();
        boolean isValid = resultSet.next();
        DataBase.closeResultSet(resultSet);
        DataBase.closePreparedStatement(preparedStatement);
        return isValid;
    }
}