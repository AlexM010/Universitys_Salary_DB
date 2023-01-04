import com.mysql.cj.xdevapi.Result;

import java.io.IOException;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    final static String driver="com.mysql.jdbc.Driver";
    final static String url="jdbc:mysql://localhost:3306/";
    final static String username="root";
    final static String password="";
    public static void main(String[] args) throws IOException {
        createDB();
        initDB();
        System.in.read();
        deleteDB();
    }
    public static void createDB(){
        try{

            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url,username,password);
            Statement stmt=conn.createStatement();
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS salary_db;");
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
    public static void deleteDB(){
        updateDB("DROP DATABASE salary_db");
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
        updateDB("CREATE TABLE administrative( " +
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
        updateDB("CREATE TABLE educational( " +
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
        updateDB("CREATE TABLE salary ( " +
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
        updateDB("CREATE TABLE payment ( " +
                "    pid VARCHAR(255) PRIMARY KEY, " +
                "    name VARCHAR(255), " +
                "    date DATE, " +
                "    amount DOUBLE " +
                ");");
    }


}

