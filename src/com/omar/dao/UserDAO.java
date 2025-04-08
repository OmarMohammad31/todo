import com.omar.todo.dto.UserDTO;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO
{
    int insertUser(UserDTO userDTO) throws SQLException;
    List<UserDTO> getAllUsers() throws SQLException;
    UserDTO getUser(int id) throws SQLException;
    int updateUser(UserDTO userDTO) throws SQLException;
    int deleteUser(UserDTO userDTO) throws SQLException;
}
