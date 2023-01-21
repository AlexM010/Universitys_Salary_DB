package classes;

import java.sql.*;
import static db.Database.getFromDB;
import static db.Database.updateDB;

public class Payment {
    private String name;
    private String date;
    private double amount;
    public Payment(String name, String date,double amount){
        this.name=name;
        this.date=date;
        this.amount=amount;
    }

}
