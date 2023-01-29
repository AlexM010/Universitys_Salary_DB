package testFiles;

import classes.Employee;
import classes.Payment;
import classes.Salary;

import java.io.IOException;

import static db.Database.*;

public class Main {

    public static void main(String[] args) throws IOException {
        createDB();
        initDB();
        Employee e3=Employee.addEmployee("{\n" +
                " \"contract\": false,\n" +
                " \"name\": \"Alexandros Markodimitrakis\",\n" +
                " \"address\": \"Fourtounatou 6\",\n" +
                " \"telephone_num\": \"6981073112\",\n" +
                " \"IBAN\": \"GR2701121149381872825494513\",\n" +
                " \"bank_name\": \"Piraeus Bank\",\n" +
                " \"startDate\": \"2022-05-23\",\n" +
                " \"department\": \"Programming\",\n" +
                " \"numOfChildren\": 4,\n" +
                " \"ages\": [23,18,16,12],\n" +
                " \"married\": true,\n" +
                " \"category\": true,\n" +
                " \"years\": 12,\n" +
                " \"bonus\": 0,\n" +
                " \"main_salary\": 100.00\n" +
                "}");
        Employee e4=Employee.addEmployee("{\n" +
                "   \"contract\": true,\n" +
                "   \"name\": \"Emily Johnson\",\n" +
                "   \"address\": \"123 Main Street\",\n" +
                "   \"telephone_num\": \"555-555-1212\",\n" +
                "   \"IBAN\": \"US012345678901234567890\",\n" +
                "   \"bank_name\": \"Chase Bank\",\n" +
                "   \"startDate\": \"2022-01-01\",\n" +
                "   \"department\": \"Marketing\",\n" +
                "   \"numOfChildren\": 0,\n" +
                "   \"married\": false,\n" +
                "   \"category\": false,\n" +
                "   \"endDate\": \"2023-01-01\",\n" +
                "   \"bonus\": 1000.00,\n" +
                " \"main_salary\": 30000.00\n" +
                "}");
        System.in.read();
       /* e3=Employee.editEmployee("{\n" +
                " \"pname\": \"Alexandros Markodimitrakis\",\n" +
                " \"contract\": false,\n" +
                " \"name\": \"Alex Markodimitrakis\",\n" +
                " \"address\": \"Fourt 6\",\n" +
                " \"telephone_num\": \"6981111173112\",\n" +
                " \"IBAN\": \"GR51701121149381872825494513\",\n" +
                " \"bank_name\": \"Pancreta Bank\",\n" +
                " \"startDate\": \"2022-05-20\",\n" +
                " \"department\": \"Prog\",\n" +
                " \"numOfChildren\": 2,\n" +
                " \"ages\": [32,16],\n" +
                " \"married\": false,\n" +
                " \"category\": true,\n" +
                " \"main_salary\": 100.00,\n" +
                "\"bonus\": 50.00,\n" +
                " \"years\": 7\n" +
                "}");*/
                Employee.changeContractedSalary("Emily Johnson", 100);
                Employee.changeBonuses(100,50,100,50);
                System.out.println(Payment.doPayments("2022-01-05",100,50,100,50));
                System.out.println(Payment.paymentsPerCategory());
                System.out.println(Salary.getSalaryStatistics());
                System.out.println(Employee.getEmployeeInfo("Alexandros Markodimitrakis"));
                System.out.println(Employee.getEmployeeInfo("Emily Johnson"));
                System.out.println(Salary.getTotalSalaryStatistics());
   //     Employee.changePermanentEducationalSalaries(15000);
      /*  Employee e4=Employee.addEmployee("{\n" +
                "   \"contract\": true,\n" +
                "   \"name\": \"Emily Johnson\",\n" +
                "   \"address\": \"123 Main Street\",\n" +
                "   \"telephone_num\": \"555-555-1212\",\n" +
                "   \"IBAN\": \"US012345678901234567890\",\n" +
                "   \"bank_name\": \"Chase Bank\",\n" +
                "   \"startDate\": \"2022-01-01\",\n" +
                "   \"salaryId\": 5,\n" +
                "   \"department\": \"Marketing\",\n" +
                "   \"numOfChildren\": 0,\n" +
                "   \"married\": false,\n" +
                "   \"category\": true,\n" +
                "   \"endDate\": \"2023-01-01\"\n" +
                "}");
        System.in.read();
        e4=Employee.editEmployee("{\n" +
                "   \"pname\": \"Emily Johnson\",\n" +
                "   \"contract\": true,\n" +
                "   \"name\": \"Emily Johnson\",\n" +
                "   \"address\": \"123 Main Street\",\n" +
                "   \"telephone_num\": \"555-555-1212\",\n" +
                "   \"IBAN\": \"gr012345678901234567890\",\n" +
                "   \"bank_name\": \"Chase Bank\",\n" +
                "   \"startDate\": \"2022-01-01\",\n" +
                "   \"salaryId\": 6,\n" +
                "   \"department\": \"Marketing\",\n" +
                "   \"numOfChildren\": 3,\n" +
                " \"ages\": [24,17,12]," +
                "   \"married\": true,\n" +
                "   \"category\": false,\n" +
                "   \"endDate\": \"2023-01-01\"\n" +
                "}");
        Employee e2=Employee.addEmployee("{" +
                " \"contract\": false," +
                " \"name\": \"Theodoros Pontzouktzidis\"," +
                " \"address\": \"Theokritou 18\"," +
                " \"telephone_num\": \"6906045239\"," +
                " \"IBAN\": \"GR4701121149381872825467513\"," +
                " \"bank_name\": \"Pancreta Bank\"," +
                " \"startDate\": \"2001-05-23\"," +
                " \"salaryId\": 4," +
                " \"department\": \"Mathematics\"," +
                " \"numOfChildren\": 2," +
                " \"ages\": [25,17]," +
                " \"married\": true," +
                " \"category\": false," +
                " \"years\": 11" +
                "}");
        Employee e5=Employee.addEmployee("{\n" +
                "   \"contract\": false,\n" +
                "   \"name\": \"James Smith\",\n" +
                "   \"address\": \"456 Park Avenue\",\n" +
                "   \"telephone_num\": \"555-555-1212\",\n" +
                "   \"IBAN\": \"US012345678901234567890\",\n" +
                "   \"bank_name\": \"Chase Bank\",\n" +
                "   \"startDate\": \"2019-07-01\",\n" +
                "   \"salaryId\": 9,\n" +
                "   \"department\": \"Sales\",\n" +
                "   \"numOfChildren\": 3,\n" +
                "   \"ages\": [2, 5, 8],\n" +
                "   \"married\": true,\n" +
                "   \"category\": false,\n" +
                "   \"years\": 3\n" +
                "}");
        System.in.read();
        e5=Employee.editEmployee("{\n" +
                "\"pname\": \"James Smith\",\n"+
                "   \"contract\": false,\n" +
                "   \"name\": \"Jams Smith\",\n" +
                "   \"address\": \"456 Park Avenue\",\n" +
                "   \"telephone_num\": \"555-555-1212\",\n" +
                "   \"IBAN\": \"US012345678901234567890\",\n" +
                "   \"bank_name\": \"Chase Bank\",\n" +
                "   \"startDate\": \"2019-07-01\",\n" +
                "   \"salaryId\": 9,\n" +
                "   \"department\": \"Sales\",\n" +
                "   \"numOfChildren\": 1,\n" +
                "   \"ages\": [22],\n" +
                "   \"married\": true,\n" +
                "   \"category\": false,\n" +
                "   \"years\": 3\n" +
                "}");
        Employee e6=Employee.addEmployee("{\n" +
                "   \"contract\": true,\n" +
                "   \"name\": \"Sophia Kim\",\n" +
                "   \"address\": \"789 Market Street\",\n" +
                "   \"telephone_num\": \"555-555-1212\",\n" +
                "   \"IBAN\": \"US012345678901234567890\",\n" +
                "   \"bank_name\": \"Chase Bank\",\n" +
                "   \"startDate\": \"2021-01-01\",\n" +
                "   \"salaryId\": 7,\n" +
                "   \"department\": \"Human Resources\",\n" +
                "   \"numOfChildren\": 1,\n" +
                "   \"ages\": [1],\n" +
                "   \"married\": true,\n" +
                "   \"category\": true,\n" +
                "   \"endDate\": \"2022-01-01\"\n" +
                "}");*/
    }

}

/*
        SQL CODE WRITTEN ABOVE:

        CREATE DATABASE IF NOT EXISTS salary_db;

        CREATE TABLE permanent(
            name VARCHAR(255) PRIMARY KEY,
            address VARCHAR(255),
            phone_number VARCHAR(255),
            iban VARCHAR(255),
            bank_name VARCHAR(255),
            start_date DATE,
            salary_id INTEGER,
            department VARCHAR(255),
            children INTEGER,
            married BIT,
            category BIT,
            years INTEGER);

       CREATE TABLE contracted(
                name VARCHAR(255) PRIMARY KEY,
                address VARCHAR(255),
                phone_number VARCHAR(255),
                iban VARCHAR(255),
                bank_name VARCHAR(255),
                start_date DATE,
                salary_id INTEGER,
                department VARCHAR(255),
                children INTEGER,
                married BIT,
                category BIT,
                end_date DATE);
       CREATE TABLE ages(
                name VARCHAR(255) PRIMARY KEY,
                age INTEGER);

       CREATE TABLE salary(
           name VARCHAR(255),
           main_salary DOUBLE,
           bonus DOUBLE,
           FOREIGN KEY (name) REFERENCES permanent(name),
           FOREIGN KEY (name) REFERENCES contracted(name));

        CREATE TABLE payment (
            name VARCHAR(255),
            date DATE,
            amount DOUBLE,
            FOREIGN KEY (name) REFERENCES permanent(name),
            FOREIGN KEY (name) REFERENCES contracted(name));
 */
/*
{
 "contract": true,
 "name": "Alexandros Markodimitrakis",
 "address": "Fourtounatou 6",
 "telephone_num": "6981073112",
 "IBAN": "GR2701121149381872825494513",
 "bank_name": "Piraeus Bank",
 "startDate": "2022-05-23",
 "salaryId": 12,
 "department": "Programming",
 "numOfChildren": 4,
 "ages": [23,18,16,12],
 "married": true,
 "category": true,
 "endDate": "2023-01-01"
}
{
 "contract": false,
 "name": "Theodoros Pontzouktzidis",
 "address": "Theokritou 18",
 "telephone_num": "6906045239",
 "IBAN": "GR4701121149381872825467513",
 "bank_name": "Pancreta Bank",
 "startDate": "2001-05-23",
 "salaryId": 4,
 "department": "Mathematics",
 "numOfChildren": 2,
 "ages": [25,17],
 "married": true,
 "category": false,
 "years": 11
}
{
   "contract": true,
   "name": "Emily Johnson",
   "address": "123 Main Street",
   "telephone_num": "555-555-1212",
   "IBAN": "US012345678901234567890",
   "bank_name": "Chase Bank",
   "startDate": "2022-01-01",
   "salaryId": 5,
   "department": "Marketing",
   "numOfChildren": 0,
   "married": false,
   "category": true,
   "endDate": "2023-01-01"
}

{
   "contract": false,
   "name": "James Smith",
   "address": "456 Park Avenue",
   "telephone_num": "555-555-1212",
   "IBAN": "US012345678901234567890",
   "bank_name": "Chase Bank",
   "startDate": "2019-07-01",
   "salaryId": 9,
   "department": "Sales",
   "numOfChildren": 3,
   "ages": [2, 5, 8],
   "married": true,
   "category": false,
   "years": 3
}

{
   "contract": true,
   "name": "Sophia Kim",
   "address": "789 Market Street",
   "telephone_num": "555-555-1212",
   "IBAN": "US012345678901234567890",
   "bank_name": "Chase Bank",
   "startDate": "2021-01-01",
   "salaryId": 7,
   "department": "Human Resources",
   "numOfChildren": 1,
   "ages": [1],
   "married": true,
   "category": true,
   "endDate": "2022-01-01"
}
 */