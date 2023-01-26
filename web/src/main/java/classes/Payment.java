package classes;
import java.sql.ResultSet;

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
    public static String doPayments(String date,int basic_salary_admin,int search_bonus,int basic_salary_edu,int library_bonus){
        String result="";
        int total=0;
        int salary=0;
        int bonus=0;
        try {
            String query = "SELECT * FROM permanent;";
            ResultSet res = getFromDB(query);
            result+="\nPayments:\n";
            while (res.next()) {
                query = "SELECT * FROM salary WHERE name ='" + res.getString("name") + "';";
                ResultSet res2 = getFromDB(query);
                while (res2.next()) {
                    salary= res2.getInt("main_salary");
                    bonus = res2.getInt("bonus");
                    total = salary + bonus;
                    Payment.addPayment(res.getString("name"), date, total);
                }
                result+="Name: "+res.getString("name")+" - Date: "+date+" - Total: "+total;
                if(res.getInt("category")==0) {
                    result += "€ - Main_Salary: " + salary +"€ [ salary: "+basic_salary_admin+"€ + (15% * salary: "+basic_salary_admin+"€ * years: "+res.getInt("years")+"): "+(0.15*basic_salary_admin*res.getInt("years"))+"€ ] -";
                }else{
                    result += "€ - Main_Salary: " + salary +"€ [ salary: "+basic_salary_edu+"€ + (15% * salary: "+basic_salary_edu+"€ * years: "+res.getInt("years")+"): "+(0.15*basic_salary_edu*res.getInt("years"))+"€ ] -";
                }

                result += " Bonus: "+ bonus+"€ [";
                if(res.getInt("married")==1){
                    int children=1;
                    String queryA = "SELECT * FROM ages WHERE name ='"+res.getString("name")+"';";
                    ResultSet resA = getFromDB(queryA);
                    while(resA.next()){
                        int age = resA.getInt("age");
                        if(age<18){
                            children+=1;
                        }
                    }
                    result+=" Family Bonus: (5% * salary:"+(res.getBoolean("category")?basic_salary_edu:basic_salary_admin)+"€ * dependent_members:"+children+"): "+(0.05*(res.getBoolean("category")?basic_salary_edu:basic_salary_admin)*children)+"€";

                    //category
                }
                if(res.getInt("category")==1) {
                    if(res.getInt("married")==1){
                        result += " + ";
                    }
                    result += "Research Bonus: " + search_bonus+"€";
                }
                result+=" ]\n";

            }
            query = "SELECT * FROM contracted;";
            res = getFromDB(query);
            while (res.next()) {
                query = "SELECT * FROM salary WHERE name ='" + res.getString("name") + "';";
                ResultSet res2 = getFromDB(query);
                while (res2.next()) {
                    salary= res2.getInt("main_salary");
                    bonus = res2.getInt("bonus");
                    total = salary + bonus;
                    Payment.addPayment(res.getString("name"), date, total);
                }
                result+="Name: "+res.getString("name")+" - Date: "+date+" - Total: "+total;
                if(res.getInt("category")==0) {
                    result += "€ - Main_Salary: " + salary +"€ [ salary:"+salary+"€ ] -";
                }else{
                    result += "€ - Main_Salary: " + salary +"€ [ salary:"+salary+"€ ] -";
                }

                result += " Bonus: "+ bonus+"€ [ ";
                if(res.getInt("married")==1){
                    int children=1;
                    String queryA = "SELECT * FROM ages WHERE name ='"+res.getString("name")+"';";
                    ResultSet resA = getFromDB(queryA);
                    while(resA.next()){
                        int age = resA.getInt("age");
                        if(age<18){
                            children+=1;
                        }
                    }
                    result+=" Family Bonus: (5% * salary:"+salary+"€ * dependent_members:"+children+"): "+(0.05*salary*children)+"€";

                    //category
                }
                if(res.getInt("category")==1) {
                    if(res.getInt("married")==1){
                        result += " + ";
                    }
                    result += "Library Bonus: " + library_bonus+"€";
                }
                result+=" ]\n";

            }

        }catch(Exception e){
            throw new RuntimeException(e);
        }
        return result;
    }
    public static String paymentsPerCategory(){
        String result="";
        try{
            String query = "SELECT * FROM payment WHERE name IN (SELECT name FROM permanent WHERE category =0);";
            ResultSet res = getFromDB(query);
            result+="Payments From Permanent Administrative Staff:\n";
            while (res.next()) {
                query = "SELECT * FROM salary WHERE name ='" + res.getString("name") + "';";
                ResultSet res2 = getFromDB(query);
                res2.next();
                result+="Name: "+res.getString("name")+" - Date: "+res.getString("date")+" - Total: "+res.getDouble("amount")+"€"+ " - Main Salary: "+res.getDouble("main_salary")+"€"+ " - Bonus: "+res.getDouble("bonus")+"€\n";
            }
            result+="Payments From Permanent Educational Staff:\n";
            query = "SELECT * FROM payment WHERE name IN (SELECT name FROM permanent WHERE category =1);";
            res = getFromDB(query);
            while (res.next()) {
                query = "SELECT * FROM salary WHERE name ='" + res.getString("name") + "';";
                ResultSet res2 = getFromDB(query);
                res2.next();
                result+="Name: "+res.getString("name")+" - Date: "+res.getString("date")+" - Total: "+res.getDouble("amount")+"€"+ " - Main Salary: "+res2.getDouble("main_salary")+"€"+ " - Bonus: "+res2.getDouble("bonus")+"€\n";
            }
            result+="Payments From Contracted Administrative Staff:\n";
            query = "SELECT * FROM payment WHERE name IN (SELECT name FROM contracted WHERE category =0);";
            res = getFromDB(query);
            while (res.next()) {
                query = "SELECT * FROM salary WHERE name ='" + res.getString("name") + "';";
                ResultSet res2 = getFromDB(query);
                res2.next();
                result+="Name: "+res.getString("name")+" - Date: "+res.getString("date")+" - Total: "+res.getDouble("amount")+"€" + " - Main Salary: "+res2.getDouble("main_salary")+"€"+ " - Bonus: "+res2.getDouble("bonus")+"€\n";
            }
            result+="Payments From Contracted Educational Staff:\n";
            query = "SELECT * FROM payment WHERE name IN (SELECT name FROM contracted WHERE category =1);";
            res = getFromDB(query);
            while (res.next()) {
                query = "SELECT * FROM salary WHERE name ='" + res.getString("name") + "';";
                ResultSet res2 = getFromDB(query);
                res2.next();
                result+="Name: "+res.getString("name")+" - Date: "+res.getString("date")+" - Total: "+res.getDouble("amount")+"€"+ " - Main Salary: "+res2.getDouble("main_salary")+"€"+ " - Bonus: "+res2.getDouble("bonus")+"€\n";
            }
        }catch(Exception e){
            throw new RuntimeException(e);
        }

        return result;
    }
}
