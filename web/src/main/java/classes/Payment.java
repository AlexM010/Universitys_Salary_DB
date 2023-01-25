package classes;
import static db.Database.updateDB;
import static db.Database.getFromDB;

public class Payment {
    private String name;
    private String date;
    private double amount;
    public Payment(String name, String date,double amount){
        this.name=name;
        this.date=date;
        this.amount=amount;
    }
    public static void addPayment(String name, String date, double amount){
        updateDB("INSERT INTO payment VALUES ('"+name+"','"+date+"',"+amount+")");
    }
}
