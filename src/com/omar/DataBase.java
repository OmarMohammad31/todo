package com.omar;

import java.sql.*;
public class DataBase
{
    private static String url = "jdbc:sqlserver://localhost:1433;databaseName=todo;AUTO_SERVER=TRUE;integratedSecurity=true;encrypt=true;trustServerCertificate=true";
    private DataBase(){}
    public static Connection getConnection() throws SQLException {return DriverManager.getConnection(url);}
    public static void closeConnection(Connection connection) throws SQLException{connection.close();}
    public static void closePreparedStatement(PreparedStatement preparedStatement) throws SQLException {preparedStatement.close();}
    public static void closeResultSet(ResultSet resultSet)throws SQLException {resultSet.close();}
}