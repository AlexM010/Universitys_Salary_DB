package classes;

import java.sql.*;
import static db.Database.getFromDB;
import static db.Database.updateDB;
public class Salary {
    private String name;
    /* Income */
    private double totalSalary;
    private double currentSalary;
    private double mainSalary;
    private double totalBonus;
    private double familyBonus;
    private double searchBonus;
    private double libraryBonus;
    public static Salary addSalary(String name,double salary){
        Salary s= new Salary();
        s.setMainSalary(salary);
        s.setName(name);
        System.out.println("INSERT INTO salary(name,main_salary,bonus) VALUES('"+s.getName()+"',"+s.getMainSalary()+","+0+");");
        updateDB("INSERT INTO salary(name,main_salary,bonus) VALUES('"+s.getName()+"',"+s.getMainSalary()+","+0+");");

        return s;
    }

    public double calculateSalary(Salary s){

        ResultSet res1=getFromDB("SELECT * FROM permanent WHERE name = '"+s.getName()+"';");
        ResultSet res2=getFromDB("SELECT * FROM contracted WHERE name = '"+s.getName()+"';");
        boolean res;
        try {
            res = res1.next();
            if(!res)
                res2.next();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        totalSalary=currentSalary + totalBonus;
        return totalSalary;
    }

    public double getCurrentSalary() {
        return currentSalary;
    }

    public void setCurrentSalary(double currentSalary) {
        this.currentSalary = currentSalary;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(double totalSalary) {
        this.totalSalary = totalSalary;
    }

    public double getMainSalary() {
        return mainSalary;
    }

    public void setMainSalary(double mainSalary) {
        this.mainSalary = mainSalary;
    }

    public double getTotalBonus() {
        return totalBonus;
    }

    public void setTotalBonus(double totalBonus) {
        this.totalBonus = totalBonus;
    }

    public double getFamilyBonus() {
        return familyBonus;
    }

    public void setFamilyBonus(double familyBonus) {
        this.familyBonus = familyBonus;
    }

    public double getSearchBonus() {
        return searchBonus;
    }

    public void setSearchBonus(double searchBonus) {
        this.searchBonus = searchBonus;
    }

    public double getLibraryBonus() {
        return libraryBonus;
    }

    public void setLibraryBonus(double libraryBonus) {
        this.libraryBonus = libraryBonus;
    }
}
