import com.omar.todo.dto.UserDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class UserDAOImp implements UserDAO
{
    private static final String insertUserQuery = "INSERT INTO users(name, Email, password) VALUES(?, ?, ?)";
    private static final String getAllUsersQuery = "SELECT * FROM users";
    private static final String getUserQuery = " SELECT * FROM users WHERE id = ?";
    private static final String updateUserQuery = "UPDATE users SET name = ?, Email = ?, password = ? WHERE id = ?";
    private static final String deleteUserQuery = "DELETE FROM users WHERE id = ?";
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
        PreparedStatement preparedStatement = DataBase.getConnection().prepareStatement(getUserQuery);
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
    public int deleteUser(UserDTO userDTO) throws SQLException
    {
        PreparedStatement preparedStatement = DataBase.getConnection().prepareStatement(deleteUserQuery);
        preparedStatement.setInt(1,userDTO.getId());
        int numOfDeletedRecords = preparedStatement.executeUpdate();
        DataBase.closePreparedStatement(preparedStatement);
        return numOfDeletedRecords;
    }
}