package com.omar.DataBase.dao;
import com.omar.DataBase.todo.dto.TaskDTO;
import java.sql.SQLException;
import java.util.List;
public interface TaskDAO
{
    int insertTask(TaskDTO taskDTO) throws SQLException;
    List<TaskDTO> getAllTasksForUser(int userID) throws SQLException;
    TaskDTO getTask(int userID, int taskID) throws SQLException;
    int updateTask(TaskDTO taskDTO) throws SQLException;
    int deleteTask(int taskID, int userID) throws SQLException;
}