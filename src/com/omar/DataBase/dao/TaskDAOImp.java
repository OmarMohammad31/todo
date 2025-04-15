package com.omar.DataBase.dao;
import com.omar.DataBase.DataBaseConnector;
import com.omar.DataBase.todo.dto.Priority;
import com.omar.DataBase.todo.dto.Status;
import com.omar.DataBase.todo.dto.TaskDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
public class TaskDAOImp implements TaskDAO
{
    private static final String col_task_id = "task_id";
    private static final String col_title = "title";
    private static final String col_content = "content";
    private static final String col_status = "status";
    private static final String col_priority = "priority";
    private static final String col_dueDate = "dueDate";
    private static final String insertTaskQuery = "INSERT INTO tasks(user_id, title, content, status, priority, dueDate) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String getAllTasksForUserQuery = "SELECT * FROM tasks WHERE user_id = ?";
    private static final String getTaskQuery = "SELECT * FROM tasks WHERE user_id = ? AND task_id = ?";
    private static final String updateTaskQuery = "UPDATE tasks SET title = ?, content = ?, status = ?, priority = ?, dueDate = ? WHERE user_id = ? AND task_id = ?";
    private static final String deleteTaskQuery = "DELETE FROM tasks WHERE user_id = ? AND task_id=?";
    private static final String clearTasksForUserQuery = "DELETE FROM tasks WHERE user_id = ?";
    private static final String resetIdentityCount = "DBCC CHECKIDENT (tasks, RESEED, 0)";
    private static final TaskDAOImp instance = new TaskDAOImp();
    private TaskDAOImp(){}
    public static TaskDAOImp getInstance(){return instance;}
    public int insertTask(TaskDTO taskDTO) throws SQLException
    {
        PreparedStatement preparedStatement = DataBaseConnector.getConnection().prepareStatement(insertTaskQuery);
        preparedStatement.setInt(1,taskDTO.getUserID());
        preparedStatement.setString(2,taskDTO.getTitle());
        preparedStatement.setString(3,taskDTO.getContent());
        preparedStatement.setString(4,taskDTO.getStatusString());
        preparedStatement.setString(5,taskDTO.getPriorityString());
        preparedStatement.setObject(6,taskDTO.getDueDate());
        int numOfInsertedRecords = preparedStatement.executeUpdate();
        DataBaseConnector.closePreparedStatement(preparedStatement);
        return numOfInsertedRecords;
    }
    @Override
    public List<TaskDTO> getAllTasksForUser(int userID) throws SQLException
    {
        ArrayList<TaskDTO> tasks = new ArrayList<>();
        PreparedStatement preparedStatement = DataBaseConnector.getConnection().prepareStatement(getAllTasksForUserQuery);
        preparedStatement.setInt(1, userID);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            tasks.add(new TaskDTO(resultSet.getInt(col_task_id), userID, resultSet.getString(col_title), resultSet.getString(col_content), Status.valueOf(resultSet.getString(col_status)), Priority.valueOf(resultSet.getString(col_priority)), resultSet.getObject(col_dueDate, LocalDateTime.class)));
        }
        DataBaseConnector.closeResultSet(resultSet);
        DataBaseConnector.closePreparedStatement(preparedStatement);
        return tasks;
    }
    @Override
    public TaskDTO getTask(int userID, int taskID) throws SQLException
    {
        PreparedStatement preparedStatement = DataBaseConnector.getConnection().prepareStatement(getTaskQuery);
        preparedStatement.setInt(1,userID);
        preparedStatement.setInt(2,taskID);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            TaskDTO taskDTO = new TaskDTO(taskID ,userID ,resultSet.getString(col_title),resultSet.getString(col_content), Status.valueOf(resultSet.getString(col_status)), Priority.valueOf(resultSet.getString(col_priority)), resultSet.getObject(col_dueDate, LocalDateTime.class));
            DataBaseConnector.closeResultSet(resultSet);
            DataBaseConnector.closePreparedStatement(preparedStatement);
            return taskDTO;
        }
        return null;
    }
    @Override
    public int updateTask(TaskDTO taskDTO) throws SQLException
    {
        PreparedStatement preparedStatement = DataBaseConnector.getConnection().prepareStatement(updateTaskQuery);
        preparedStatement.setString(1,taskDTO.getTitle());
        preparedStatement.setString(2,taskDTO.getContent());
        preparedStatement.setString(3,taskDTO.getStatusString());
        preparedStatement.setString(4,taskDTO.getPriorityString());
        preparedStatement.setObject(5,taskDTO.getDueDate());
        preparedStatement.setInt(6,taskDTO.getUserID());
        preparedStatement.setInt(7,taskDTO.getTaskID());
        int numOfUpdatedRecords = preparedStatement.executeUpdate();
        DataBaseConnector.closePreparedStatement(preparedStatement);
        return numOfUpdatedRecords;
    }
    @Override
    public int deleteTask(TaskDTO taskDTO) throws SQLException
    {
        PreparedStatement preparedStatement = DataBaseConnector.getConnection().prepareStatement(deleteTaskQuery);
        preparedStatement.setInt(1,taskDTO.getUserID());
        preparedStatement.setInt(2,taskDTO.getTaskID());
        int numOfDeletedTasks = preparedStatement.executeUpdate();
        DataBaseConnector.closePreparedStatement(preparedStatement);
        return numOfDeletedTasks;
    }
    public int clearTasksForUser(int userID) throws SQLException{
        PreparedStatement preparedStatement = DataBaseConnector.getConnection().prepareStatement(clearTasksForUserQuery);
         preparedStatement.setInt(1,userID);
         int numOfDeletedRecords = preparedStatement.executeUpdate();
         preparedStatement = DataBaseConnector.getConnection().prepareStatement(resetIdentityCount);
         preparedStatement.execute();
         DataBaseConnector.closePreparedStatement(preparedStatement);
         return numOfDeletedRecords;
    }
}