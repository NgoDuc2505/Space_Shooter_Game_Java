package com.spaceshooter.controller;
import java.sql.*;

public class DatabaseConnector {
    private Statement statement;
    private Connection connection;
    public DatabaseConnector() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaSpace","root","2505");
            System.out.println("Connected !");
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        }catch (SQLException | ClassNotFoundException e){
            System.out.println("Error !");
            System.out.println(e);
        }
    }


    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public static void main(String[] args) {
        DatabaseConnector db = new DatabaseConnector();
        String query = "SELECT * FROM userTable;";
        try{
            ResultSet rs = db.getStatement().executeQuery(query);
            while (rs.next()){
                System.out.println(rs.getString(1) + " .. "+rs.getString(2));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
