import com.mysql.cj.xdevapi.Result;

import java.io.IOException;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws IOException {
        createDB();
        initDB();
        System.in.read();
        deleteDB();
    }
    public static void createDB(){
        try{
            String driver="com.mysql.jdbc.Driver";
            String url="jdbc:mysql://localhost:3306/";
            String username="root";
            String password="";
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url,username,password);
            Statement stmt=conn.createStatement();
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS salary_db;");


            System.out.println("DB Connected");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection getConnection(String query){
        try{
            String driver="com.mysql.jdbc.Driver";
            String url="jdbc:mysql://localhost:3306/salary_db";
            String username="root";
            String password="";
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url,username,password);
            Statement stmt=conn.createStatement();
            stmt.executeUpdate(query);


            System.out.println("DB Connected");
            return conn;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void initDB(){
        /* CREATE DATABASE employee_database;

USE employee_database;

CREATE TABLE employee (
    name VARCHAR(255) PRIMARY KEY,
    married BIT,
    num_children INTEGER,
    children_ages ARRAY,
    start_date DATE,
    department VARCHAR(255),
    address VARCHAR(255),
    telephone VARCHAR(255),
    bank_name VARCHAR(255),
    iban VARCHAR(255),
    category_type BIT,
    salary_id VARCHAR(255)
);

CREATE TABLE salary (
    salary_id VARCHAR(255) PRIMARY KEY,
    contract_type BIT,
    start_date DATE,
    end_date DATE,
    years INTEGER,
    married BIT,
    underaged_children INTEGER,
    total_salary DOUBLE,
    main_salary DOUBLE,
    bonus DOUBLE
);

CREATE TABLE payment (
    pid VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255),
    date DATE,
    amount DOUBLE
);*/
        getConnection("CREATE TABLE administrative( " +
                "name VARCHAR(255) PRIMARY KEY, " +
                "married BIT, " +
                "num_children INTEGER, " +
                "children_age JSON, " +
                "start_date DATE, " +
                "department VARCHAR(255), " +
                "address VARCHAR(255), " +
                "telephone VARCHAR(255), " +
                "bank_name VARCHAR(255), " +
                "iban VARCHAR(255), " +
                "salary_id VARCHAR(255) " +
                ")");
        getConnection("CREATE TABLE educational( " +
                "name VARCHAR(255) PRIMARY KEY, " +
                "married BIT, " +
                "num_children INTEGER, " +
                "children_age JSON, " +
                "start_date DATE, " +
                "department VARCHAR(255), " +
                "address VARCHAR(255), " +
                "telephone VARCHAR(255), " +
                "bank_name VARCHAR(255), " +
                "iban VARCHAR(255), " +
                "salary_id VARCHAR(255) " +
                ")");
        getConnection("CREATE TABLE salary ( " +
                "    salary_id VARCHAR(255) PRIMARY KEY, " +
                "    contract_type BIT, " +
                "    start_date DATE, " +
                "    end_date DATE, " +
                "    years INTEGER, " +
                "    married BIT, " +
                "    underaged_children INTEGER, " +
                "    total_salary DOUBLE, " +
                "    main_salary DOUBLE, " +
                "    bonus DOUBLE " +
                ");");
        getConnection("CREATE TABLE payment ( " +
                "    pid VARCHAR(255) PRIMARY KEY, " +
                "    name VARCHAR(255), " +
                "    date DATE, " +
                "    amount DOUBLE " +
                ");");
    }
    public static void deleteDB(){
        getConnection("DROP DATABASE salary_db");
    }

}

