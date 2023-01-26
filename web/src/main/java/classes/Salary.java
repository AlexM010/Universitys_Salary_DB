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
    public static  Salary addSalary(String name, double salary,double bonus){
        Salary s= new Salary();
        s.setMainSalary(salary);
        s.setName(name);
        System.out.println("INSERT INTO salary(name,main_salary,bonus) VALUES('"+s.getName()+"',"+s.getMainSalary()+","+bonus+");");

        updateDB("INSERT INTO salary(name,main_salary,bonus) VALUES('"+s.getName()+"',"+s.getMainSalary()+","+bonus+");");

        return s;
    }
    //get min , max  and average salary for 4 categories (table permanent category=0,table permanent category=1,table contracted category=0,table contracted category=1) using sql max min avg
    public static String getSalaryStatistics() {
        String result = "";
        try {
            String query = "SELECT MIN(main_salary) FROM salary WHERE name IN (SELECT name FROM permanent WHERE category=0);";
            ResultSet res = getFromDB(query);
            while (res.next()) {
                result += "\nMin Salary for Permanent Admins: " + res.getInt("MIN(main_salary)") + "€";
            }
            query = "SELECT MAX(main_salary) FROM salary WHERE name IN (SELECT name FROM permanent WHERE category=0);";
            res = getFromDB(query);
            while (res.next()) {
                result += "\nMax Salary for Permanent Admins: " + res.getInt("MAX(main_salary)") + "€";
            }
            query = "SELECT AVG(main_salary) FROM salary WHERE name IN (SELECT name FROM permanent WHERE category=0);";
            res = getFromDB(query);
            while (res.next()) {
                result += "\nAverage Salary for Permanent Admins: " + res.getInt("AVG(main_salary)") + "€";
            }

            query = "SELECT MIN(main_salary) FROM salary WHERE name IN (SELECT name FROM permanent WHERE category=1);";
            res = getFromDB(query);
            while (res.next()) {
                result += "\nMin Salary for Permanent Researchers: " + res.getInt("MIN(main_salary)") + "€";
            }
            query = "SELECT MAX(main_salary) FROM salary WHERE name IN (SELECT name FROM permanent WHERE category=1);";
            res = getFromDB(query);
            while (res.next()) {
                result += "\nMax Salary for Permanent Researchers: " + res.getInt("MAX(main_salary)") + "€";
            }
            query = "SELECT AVG(main_salary) FROM salary WHERE name IN (SELECT name FROM permanent WHERE category=1);";
            res = getFromDB(query);
            while (res.next()) {
                result += "\nAverage Salary for Permanent Researchers: " + res.getInt("AVG(main_salary)") + "€";
            }
            query = "SELECT MIN(main_salary) FROM salary WHERE name IN (SELECT name FROM contracted WHERE category=0);";
            res = getFromDB(query);
            while (res.next()) {
                result += "\nMin Salary for Contracted Admins: " + res.getInt("MIN(main_salary)") + "€";
            }
            query = "SELECT MAX(main_salary) FROM salary WHERE name IN (SELECT name FROM contracted WHERE category=0);";
            res = getFromDB(query);
            while (res.next()) {
                result += "\nMax Salary for Contracted Admins: " + res.getInt("MAX(main_salary)") + "€";
            }
            query = "SELECT AVG(main_salary) FROM salary WHERE name IN (SELECT name FROM contracted WHERE category=0);";
            res = getFromDB(query);
            while (res.next()) {
                result += "\nAverage Salary for Contracted Admins: " + res.getInt("AVG(main_salary)") + "€";
            }
            query = "SELECT MIN(main_salary) FROM salary WHERE name IN (SELECT name FROM contracted WHERE category=1);";
            res = getFromDB(query);
            while (res.next()) {
                result += "\nMin Salary for Contracted Researchers: " + res.getInt("MIN(main_salary)") + "€";
            }
            query = "SELECT MAX(main_salary) FROM salary WHERE name IN (SELECT name FROM contracted WHERE category=1);";
            res = getFromDB(query);
            while (res.next()) {
                result += "\nMax Salary for Contracted Researchers: " + res.getInt("MAX(main_salary)") + "€";
            }
            query = "SELECT AVG(main_salary) FROM salary WHERE name IN (SELECT name FROM contracted WHERE category=1);";
            res = getFromDB(query);
            while (res.next()) {
                result += "\nAverage Salary for Contracted Researchers: " + res.getInt("AVG(main_salary)") + "€";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    // sum of all salaries and bonus in total for each category (table permanent category=0,table permanent category=1,table contracted category=0,table contracted category=1) using sql sum
    public static String getTotalSalaryStatistics() {
        String result = "";
        try {
            String query = "SELECT SUM(main_salary+bonus) FROM salary WHERE name IN (SELECT name FROM permanent WHERE category=0);";
            ResultSet res = getFromDB(query);
            while (res.next()) {
                result += "\nTotal Salary for Permanent Admins: " + res.getInt("SUM(main_salary+bonus)") + "€";
            }
            query = "SELECT SUM(main_salary+bonus) FROM salary WHERE name IN (SELECT name FROM permanent WHERE category=1);";
            res = getFromDB(query);
            while (res.next()) {
                result += "\nTotal Salary for Permanent Researchers: " + res.getInt("SUM(main_salary+bonus)") + "€";
            }
            query = "SELECT SUM(main_salary+bonus) FROM salary WHERE name IN (SELECT name FROM contracted WHERE category=0);";
            res = getFromDB(query);
            while (res.next()) {
                result += "\nTotal Salary for Contracted Admins: " + res.getInt("SUM(main_salary+bonus)") + "€";
            }
            query = "SELECT SUM(main_salary+bonus) FROM salary WHERE name IN (SELECT name FROM contracted WHERE category=1);";
            res = getFromDB(query);
            while (res.next()) {
                result += "\nTotal Salary for Contracted Researchers: " + res.getInt("SUM(main_salary+bonus)") + "€";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    // sum of all salaries and bonus in total for each category (table permanent category=0,table permanent category=1,table contracted category=0,table contracted category=1) using sql sum
    public static String getMinSalaryStatistics() {
        String result = "";
        try {
            String query = "SELECT MIN(main_salary+bonus) FROM salary WHERE name IN (SELECT name FROM permanent WHERE category=0);";
            ResultSet res = getFromDB(query);
            while (res.next()) {
                result += "\nMin Salary for Permanent Admins: " + res.getInt("MIN(main_salary+bonus)") + "€";
            }
            query = "SELECT MIN(main_salary+bonus) FROM salary WHERE name IN (SELECT name FROM permanent WHERE category=1);";
            res = getFromDB(query);
            while (res.next()) {
                result += "\nMin Salary for Permanent Researchers: " + res.getInt("MIN(main_salary+bonus)") + "€";
            }
            query = "SELECT MIN(main_salary+bonus) FROM salary WHERE name IN (SELECT name FROM contracted WHERE category=0);";
            res = getFromDB(query);
            while (res.next()) {
                result += "\nMin Salary for Contracted Admins: " + res.getInt("MIN(main_salary+bonus)") + "€";
            }
            query = "SELECT MIN(main_salary+bonus) FROM salary WHERE name IN (SELECT name FROM contracted WHERE category=1);";
            res = getFromDB(query);
            while (res.next()) {
                result += "\nMin Salary for Contracted Researchers: " + res.getInt("MIN(main_salary+bonus)") + "€";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    // sum of all salaries and bonus in total for each category (table permanent category=0,table permanent category=1,table contracted category=0,table contracted category=1) using sql sum
    public static String totalSalariesPerCategory(){
        String result = "";
        try {
            String query = "SELECT SUM(main_salary+bonus) FROM salary WHERE name IN (SELECT name FROM permanent WHERE category=0);";
            ResultSet res = getFromDB(query);
            while (res.next()) {
                result += "\nTotal Salary for Permanent Admins: " + res.getInt("SUM(main_salary+bonus)") + "€";
            }
            query = "SELECT SUM(main_salary+bonus) FROM salary WHERE name IN (SELECT name FROM permanent WHERE category=1);";
            res = getFromDB(query);
            while (res.next()) {
                result += "\nTotal Salary for Permanent Researchers: " + res.getInt("SUM(main_salary+bonus)") + "€";
            }
            query = "SELECT SUM(main_salary+bonus) FROM salary WHERE name IN (SELECT name FROM contracted WHERE category=0);";
            res = getFromDB(query);
            while (res.next()) {
                result += "\nTotal Salary for Contracted Admins: " + res.getInt("SUM(main_salary+bonus)") + "€";
            }
            query = "SELECT SUM(main_salary+bonus) FROM salary WHERE name IN (SELECT name FROM contracted WHERE category=1);";
            res = getFromDB(query);
            while (res.next()) {
                result += "\nTotal Salary for Contracted Researchers: " + res.getInt("SUM(main_salary+bonus)") + "€";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
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
