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
                " \"bonus\": 200.00,\n" +
                " \"main_salary\": 1000.00\n" +
                "}");

        Employee e2=Employee.addEmployee("{" +
                " \"contract\": true," +
                " \"name\": \"Theodoros Pontzouktzidis\"," +
                " \"address\": \"Theokritou 18\"," +
                " \"telephone_num\": \"6906045239\"," +
                " \"IBAN\": \"GR4701121149381872825467513\"," +
                " \"bank_name\": \"Pancreta Bank\"," +
                " \"startDate\": \"2001-05-23\"," +
                " \"department\": \"Mathematics\"," +
                " \"numOfChildren\": 2," +
                " \"ages\": [25,17]," +
                " \"married\": true," +
                " \"category\": false," +
                " \"endDate\": \"2022-12-25\"," +
                "   \"bonus\": 0,\n" +
                " \"main_salary\": 100000.00\n" +
                "}");

        e3=Employee.editEmployee("{\n" +
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
                "}");
        Employee e11=Employee.addEmployee("{\n" +
                " \"contract\": false,\n" +
                " \"name\": \"Konstantinos Vlachos\",\n" +
                " \"address\": \"Ioannou Papakyriakou 2\",\n" +
                " \"telephone_num\": \"6987654343\",\n" +
                " \"IBAN\": \"GR390817493816192387569304\",\n" +
                " \"bank_name\": \"Alpha Bank\",\n" +
                " \"startDate\": \"2022-12-01\",\n" +
                " \"department\": \"Support\",\n" +
                " \"numOfChildren\": 0,\n" +
                " \"ages\": [0],\n" +
                " \"married\": true,\n" +
                " \"category\": false,\n" +
                " \"years\": 10,\n" +
                " \"bonus\": 200.00,\n" +
                " \"main_salary\": 1000.00\n" +
                "}");
        Employee e22=Employee.addEmployee("{\n" +
                " \"contract\": false,\n" +
                " \"name\": \"Maria Tsakiri\",\n" +
                " \"address\": \"Aristotelous 8\",\n" +
                " \"telephone_num\": \"6981234567\",\n" +
                " \"IBAN\": \"GR35011211493818728254888\",\n" +
                " \"bank_name\": \"Proton Bank\",\n" +
                " \"startDate\": \"2022-07-01\",\n" +
                " \"department\": \"Sales\",\n" +
                " \"numOfChildren\": 0,\n" +
                " \"ages\": [0],\n" +
                " \"married\": false,\n" +
                " \"category\": false,\n" +
                " \"years\": 7,\n" +
                " \"bonus\": 200.00,\n" +
                " \"main_salary\": 1000.00\n" +
                "}");
        Employee e4=Employee.addEmployee("{\n" +
                " \"contract\": false,\n" +
                " \"name\": \"Eleni Papadopoulou\",\n" +
                " \"address\": \"Ermou 22\",\n" +
                " \"telephone_num\": \"6980987654\",\n" +
                " \"IBAN\": \"GR39011211193818728254444\",\n" +
                " \"bank_name\": \"Eurobank\",\n" +
                " \"startDate\": \"2022-06-15\",\n" +
                " \"department\": \"Marketing\",\n" +
                " \"numOfChildren\": 0,\n" +
                " \"ages\": [0],\n" +
                " \"married\": false,\n" +
                " \"category\": true,\n" +
                " \"years\": 8,\n" +
                " \"bonus\": 200.00,\n" +
                " \"main_salary\": 1000.00\n" +
                "}");
        Employee e44=Employee.addEmployee("{\n" +
                " \"contract\": true,\n" +
                " \"name\": \"Andreas Karatzas\",\n" +
                " \"address\": \"Vassileos Pavlou 12\",\n" +
                " \"telephone_num\": \"6987654321\",\n" +
                " \"IBAN\": \"GR32011211493818728254111\",\n" +
                " \"bank_name\": \"National Bank of Greece\",\n" +
                " \"startDate\": \"2022-09-01\",\n" +
                " \"department\": \"Human Resources\",\n" +
                " \"numOfChildren\": 0,\n" +
                " \"ages\": [0],\n" +
                " \"married\": true,\n" +
                " \"category\": true,\n" +
                " \"endDate\": \"2022-12-25\"," +
                " \"bonus\": 0,\n" +
                " \"main_salary\": 10000.00\n" +
                "}");
        System.in.read();
        Employee.changeContractedSalary("Emily Johnson", 100);
        Employee.changeBonuses(100,50,100,50);
        System.out.println(Payment.doPayments("2022-01-05",100,50,100,50));
        System.out.println(Payment.paymentsPerCategory());
        System.out.println(Salary.getSalaryStatistics());
        System.out.println(Employee.getEmployeeInfo("Alexandros Markodimitrakis"));
        System.out.println(Employee.getEmployeeInfo("Emily Johnson"));
        System.out.println(Salary.getTotalSalaryStatistics());
        Employee.changePermanentEducationalSalaries(15000);

    }

}
