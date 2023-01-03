import java.sql.*;

public class Payment {
    private String pid;
    private String name;
    private Date date;
    private double amount;
    public Payment(String name, Date date,double amount){
        this.name=name;
        this.date=date;
        this.amount=amount;
    }
}
