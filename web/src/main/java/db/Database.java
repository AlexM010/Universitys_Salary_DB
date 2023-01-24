package db;

import com.google.gson.JsonObject;

import java.sql.*;

public class Database {
    final static String driver="com.mysql.cj.jdbc.Driver";
    final static String url="jdbc:mysql://localhost:3306/";
    final static String username="root";
    final static String password="";
    public static void createDB(){
        try{

            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url,username,password);
            Statement stmt=conn.createStatement();
            stmt.executeUpdate("DROP DATABASE IF EXISTS salary_db");
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS salary_db;");
            updateDB("SET GLOBAL FOREIGN_KEY_CHECKS=0;");
            System.out.println("DB Created");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection updateDB(String query){
        try{
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url+"salary_db",username,password);
            Statement stmt=conn.createStatement();
            stmt.executeUpdate(query);
            System.out.println("DB Updated");
            return conn;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static ResultSet getFromDB(String query){
        try{
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url+"salary_db",username,password);
            Statement stmt=conn.createStatement();
            ResultSet res= stmt.executeQuery(query);
            System.out.println("DB Get");
            return res;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void initDB(){
        updateDB("CREATE TABLE permanent( " +
                "name VARCHAR(255) PRIMARY KEY, " +
                "address VARCHAR(255), " +
                "phone_number VARCHAR(255), " +
                "iban VARCHAR(255), " +
                "bank_name VARCHAR(255), " +
                "start_date DATE, " +
                "department VARCHAR(255), " +
                "children INTEGER, " +
                "married BIT, " +
                "category BIT, " +
                "years INTEGER" +
                ")");
        updateDB("CREATE TABLE contracted( " +
                "name VARCHAR(255) PRIMARY KEY, " +
                "address VARCHAR(255), " +
                "phone_number VARCHAR(255), " +
                "iban VARCHAR(255), " +
                "bank_name VARCHAR(255), " +
                "start_date DATE, " +
                "department VARCHAR(255), " +
                "children INTEGER, " +
                "married BIT, " +
                "category BIT, " +
                "end_date DATE" +
                ")");
        updateDB("CREATE TABLE ages( " +
                "name VARCHAR(255), " +
                "age INTEGER," +
                "CONSTRAINT FK_1 FOREIGN KEY (name) REFERENCES permanent(name),"+
                "CONSTRAINT FK_2 FOREIGN KEY (name) REFERENCES contracted(name)"+
                ")");


        updateDB("CREATE TABLE salary( " +
                "    name VARCHAR(255), " +
                "    main_salary DOUBLE, " +
                "    bonus DOUBLE," +
                " CONSTRAINT FK_3 FOREIGN KEY (name) REFERENCES permanent(name),"+
                "CONSTRAINT FK_4 FOREIGN KEY (name) REFERENCES contracted(name)"+
                ");");
        updateDB("CREATE TABLE payment ( " +

                "    name VARCHAR(255), " +
                "    date DATE, " +
                "    amount DOUBLE, " +
                " CONSTRAINT FK_5 FOREIGN KEY (name) REFERENCES permanent(name),"+
                "CONSTRAINT FK_6 FOREIGN KEY (name) REFERENCES contracted(name)"+
                ");");
    }
    public static void deleteDB(){
        updateDB("DROP DATABASE IF EXISTS salary_db");
    }

    public static String getResultsToJSON(ResultSet rs) throws SQLException {
        ResultSetMetaData metadata = rs.getMetaData();
        int columnCount = metadata.getColumnCount();
        JsonObject object = new JsonObject();


        String row = "";
        for (int i = 1; i <= columnCount; i++) {
            String name = metadata.getColumnName(i);
            String value = rs.getString(i);
            object.addProperty(name,value);
        }
        return object.toString();
    }
}

