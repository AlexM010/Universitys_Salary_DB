package classes;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.sql.ResultSet;
import java.io.BufferedReader;
import static db.Database.getFromDB;
import static db.Database.updateDB;

enum Category{
    ADMINISTRATIVE,EDUCATIONAL
    }

 enum Contract{
    PERMANENT,CONTRACTED
}

public class Employee {
    private String name;
    /* Info */
    private String address;
    private String telephone_num;
    private String IBAN;
    private String bank_name;
    private String startDate;
    private String department;

    /* Family Status*/
    private boolean married;
    private int numOfChildren;
    private int[] children;


    /* Staff type */

    Contract contract;
    Category category;
    public int c;
    private String endDate;
    private int years;


    public static Employee addPermanentEmployee(String Json) {
        Employee e = new Employee();
        JsonObject json= Employee.json.getJson(Json);
        e.setName(json.get("name").getAsString());
        e.setAddress(json.get("address").getAsString());
        e.setTelephone_num(json.get("telephone_num").getAsString());
        e.setIBAN(json.get("IBAN").getAsString());
        e.setBank_name(json.get("bank_name").getAsString());
        e.setStartDate(json.get("startDate").getAsString());
        e.setDepartment(json.get("department").getAsString());
        e.setNumOfChildren(json.get("numOfChildren").getAsInt());
        e.setMarried(json.get("married").getAsBoolean());
        if (json.get("category").getAsBoolean()) {
            e.setCategory(Category.EDUCATIONAL);
            e.c = 1;
        } else {
            e.setCategory(Category.ADMINISTRATIVE);
            e.c=0;
        }
        e.setYears(json.get("years").getAsInt());
        e.setEndDate(null);
        e.setContract(Contract.PERMANENT);
        JsonArray arr= json.get("ages").getAsJsonArray();
        int[] numbers = new int[e.getNumOfChildren()];

        // Extract numbers from JSON array.
        for (int i = 0; i < e.getNumOfChildren(); ++i) {
            numbers[i] = arr.get(i).getAsInt();
        }
        e.setChildren(numbers);
        updateDB("INSERT INTO permanent(name, address, phone_number, iban, bank_name, start_date, department, children, married, category, years)" + "SELECT"+
                " '"+e.getName()+"','"+e.getAddress()+"','"+e.getTelephone_num()+"','"+e.getIBAN()+"','"+e.getBank_name()+"','"+e.getStartDate()+"','"+e.getDepartment()+"',"+ e.getNumOfChildren() +","+ (e.isMarried() ? 1 : 0) +","+ e.c +","+ e.getYears() +" WHERE NOT EXISTS (SELECT 1 FROM contracted WHERE name = '"+e.getName()+"');");
        numbers=e.getChildren();
        for(int i=0;i<numbers.length;i++) {
            updateDB("INSERT INTO ages(name, age)VALUES('" + e.getName() + "'," +numbers[i]+");");
        }
//        Salary s=Salary.addSalary(e.getName(),json.get("main_salary").getAsDouble());
        return e;
    }
    public static Employee addEmployee(String Json){
        JsonObject json= Employee.json.getJson(Json);
        if(!json.get("contract").getAsBoolean()){
            return addPermanentEmployee(Json);
        }else{
            return addContractedEmployee(Json);
        }
    }

    public static Employee addContractedEmployee(String Json) {
        Employee e = new Employee();
        JsonObject json= Employee.json.getJson(Json);
        e.setName(json.get("name").getAsString());
        e.setAddress(json.get("address").getAsString());
        e.setTelephone_num(json.get("telephone_num").getAsString());
        e.setIBAN(json.get("IBAN").getAsString());
        e.setBank_name(json.get("bank_name").getAsString());
        e.setStartDate(json.get("startDate").getAsString());
        e.setDepartment(json.get("department").getAsString());
        e.setNumOfChildren(json.get("numOfChildren").getAsInt());
        e.setMarried(json.get("married").getAsBoolean());
        if (json.get("category").getAsBoolean()) {
            e.setCategory(Category.EDUCATIONAL);
            e.c = 1;
        } else {
            e.setCategory(Category.ADMINISTRATIVE);
            e.c=0;
        }
        e.setYears(-1);
        e.setEndDate(json.get("endDate").getAsString());
        e.setContract(Contract.CONTRACTED);
        if(e.getNumOfChildren()>0){
            JsonArray arr= json.get("ages").getAsJsonArray();
            int[] numbers = new int[e.getNumOfChildren()];

            // Extract numbers from JSON array.
            for (int i = 0; i < e.getNumOfChildren(); ++i) {
                numbers[i] = arr.get(i).getAsInt();
            }
            e.setChildren(numbers);
            numbers=e.getChildren();
            for(int i=0;i<numbers.length;i++) {
                updateDB("INSERT INTO ages(name, age)VALUES('" + e.getName() + "'," +numbers[i]+");");
            }
        }else
            e.setChildren(null);

        updateDB("INSERT INTO contracted(name, address, phone_number, iban, bank_name, start_date,department, children, married, category, end_date)" + "SELECT"+
                " '"+e.getName()+"','"+e.getAddress()+"','"+e.getTelephone_num()+"','"+e.getIBAN()+"','"+e.getBank_name()+"','"+e.getStartDate()+"','"+e.getDepartment()+"',"+ e.getNumOfChildren() +","+ (e.isMarried() ? 1 : 0) +","+ e.c +",'"+ e.getEndDate() +"' WHERE NOT EXISTS (SELECT 1 FROM permanent WHERE name = '"+e.getName()+"');");
//        Salary s=Salary.addSalary(e.getName(),json.get("main_salary").getAsDouble());
        return e;
    }
    public static Employee editEmployee(String Json)  {
        Employee em=new Employee();
        JsonObject json= Employee.json.getJson(Json);
        updateDB("DELETE FROM ages WHERE name =  '"+json.get("pname").getAsString()+"';");
        ResultSet res1=getFromDB("SELECT * FROM permanent WHERE name = '"+json.get("pname").getAsString()+"';");
        ResultSet res2=getFromDB("SELECT * FROM contracted WHERE name = '"+json.get("pname").getAsString()+"';");
        boolean res;
        try {
            res = res1.next();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        em.setContract(res?Contract.PERMANENT:Contract.CONTRACTED);
        if(!json.get("contract").getAsBoolean()){
            if(em.getContract()==Contract.CONTRACTED){
                updateDB("DELETE FROM contracted WHERE name ='" + json.get("pname").getAsString() + "';");
                return addPermanentEmployee(Json);
            }
        }else{
            if(em.getContract()==Contract.PERMANENT) {
                updateDB("DELETE FROM permanent WHERE name ='"+json.get("pname").getAsString()+"';");
                return addContractedEmployee(Json);
            }
        }

        String query;
        try{
            if(res){
                System.out.println(Json+ res1.getInt("children")+res1.getString("name"));
                em.setName(res1.getString("name"));
                em.setAddress(res1.getString("address"));
                em.setTelephone_num(res1.getString("phone_number"));
                em.setIBAN(res1.getString("iban"));
                em.setBank_name(res1.getString("bank_name"));
                em.setStartDate(res1.getString("start_date"));

                em.setDepartment(res1.getString("department"));
                em.setNumOfChildren(res1.getInt("children"));
                em.setMarried(res1.getBoolean("married"));
                em.setCategory(res1.getBoolean("category")?Category.EDUCATIONAL:Category.ADMINISTRATIVE);
                em.setYears(res1.getInt("years"));
                em.setEndDate(null);
                if(json.has("name")){
                    em.setName(json.get("name").getAsString());
                }
                if (json.has("address")) {
                    em.setAddress(json.get("address").getAsString());
                }
                if (json.has("telephone_num")) {
                    em.setTelephone_num(json.get("telephone_num").getAsString());
                }
                if (json.has("IBAN")) {
                    em.setIBAN(json.get("IBAN").getAsString());
                }
                if (json.has("bank_name")) {
                    em.setBank_name(json.get("bank_name").getAsString());
                }
                if (json.has("startDate")) {
                    em.setStartDate(json.get("startDate").getAsString());
                }

                if (json.has("department")) {
                    em.setDepartment(json.get("department").getAsString());
                }
                if (json.has("numOfChildren")) {
                    em.setNumOfChildren(json.get("numOfChildren").getAsInt());
                }
                if (json.has("married")) {
                    em.setMarried(json.get("married").getAsBoolean());
                }
                if (json.has("category")) {
                    em.setCategory(json.get("category").getAsBoolean() ? Category.EDUCATIONAL : Category.ADMINISTRATIVE);
                }
                if (json.has("years")) {
                    em.setYears(json.get("years").getAsInt());
                }

                query="UPDATE permanent SET name = '" + em.getName() + "', address = '" + em.getAddress() + "', phone_number = '" + em.getTelephone_num() + "', iban = '" + em.getIBAN() + "', bank_name = '" + em.getBank_name() + "', start_date = '" + em.getStartDate() + "', department = '" + em.getDepartment() + "', children = " + em.getNumOfChildren() + ", married = " + (em.isMarried()?1:0) + ", category = " + (em.getCategory()==Category.ADMINISTRATIVE?0:1) + ", years = " + em.getYears() + " WHERE name = '" + json.get("pname").getAsString() + "';";
            }else{
                res2.next();
                System.out.println(Json);
                em.setName(res2.getString("name"));
                em.setAddress(res2.getString("address"));
                em.setTelephone_num(res2.getString("phone_number"));
                em.setIBAN(res2.getString("iban"));
                em.setBank_name(res2.getString("bank_name"));
                em.setStartDate(res2.getString("start_date"));
                em.setDepartment(res2.getString("department"));
                em.setNumOfChildren(res2.getInt("children"));
                em.setMarried(res2.getBoolean("married"));
                em.setCategory(res2.getBoolean("category")?Category.EDUCATIONAL:Category.ADMINISTRATIVE);
                em.setYears(-1);
                em.setEndDate(json.get("endDate").getAsString());

                if(json.has("name")){
                    em.setName(json.get("name").getAsString());
                }
                if (json.has("address")) {
                    em.setAddress(json.get("address").getAsString());
                }
                if (json.has("telephone_num")) {
                    em.setTelephone_num(json.get("telephone_num").getAsString());
                }
                if (json.has("IBAN")) {
                    em.setIBAN(json.get("IBAN").getAsString());
                }
                if (json.has("bank_name")) {
                    em.setBank_name(json.get("bank_name").getAsString());
                }
                if (json.has("startDate")) {
                    em.setStartDate(json.get("startDate").getAsString());
                }
                if (json.has("department")) {
                    em.setDepartment(json.get("department").getAsString());
                }
                if (json.has("numOfChildren")) {
                    em.setNumOfChildren(json.get("numOfChildren").getAsInt());
                }
                if (json.has("married")) {
                    em.setMarried(json.get("married").getAsBoolean());
                }
                if (json.has("category")) {
                    em.setCategory(json.get("category").getAsBoolean() ? Category.EDUCATIONAL : Category.ADMINISTRATIVE);
                }
                if (json.has("endDate")) {
                    em.setEndDate(json.get("endDate").getAsString());
                }

                query="UPDATE contracted SET name = '" + em.getName() + "', address = '" + em.getAddress() + "', phone_number = '" + em.getTelephone_num() + "', iban = '" + em.getIBAN() + "', bank_name = '" + em.getBank_name() + "', start_date = '" + em.getStartDate() +  "', department = '" + em.getDepartment() + "', children = " + em.getNumOfChildren() + ", married = " + (em.isMarried()?1:0) + ", category = " + (em.getCategory()==Category.ADMINISTRATIVE?0:1) +", end_date = '" + em.getEndDate() + "' WHERE name = '" + json.get("pname").getAsString() + "';";
            }
            System.out.println(query);
            updateDB(query);
            JsonArray arr= json.get("ages").getAsJsonArray();
            int[] numbers = new int[em.getNumOfChildren()];

            // Extract numbers from JSON array.
            for (int i = 0; i < em.getNumOfChildren(); ++i) {
                numbers[i] = arr.get(i).getAsInt();
            }
            for(int i=0;i<numbers.length;i++) {
                updateDB("INSERT INTO ages(name, age)VALUES('" + em.getName() + "'," +numbers[i]+");");
            }

        }catch(Exception e){
            throw new RuntimeException(e);
        }
        return em;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone_num() {
        return telephone_num;
    }

    public void setTelephone_num(String telephone_num) {
        this.telephone_num = telephone_num;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public boolean isMarried() {
        return married;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

    public int getNumOfChildren() {
        return numOfChildren;
    }

    public void setNumOfChildren(int numOfChildren) {
        this.numOfChildren = numOfChildren;
    }

    public int[] getChildren() {
        return children;
    }

    public void setChildren(int[] children) {
        this.children = children;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }
    static class json{
        static JsonObject getJson(String json){
            JsonParser parser=new JsonParser();
            JsonElement elem= parser.parse(json);
            return elem.getAsJsonObject();
        }
    }
    public static String ReaderToJson(BufferedReader bufferReader){
        StringBuilder sb = new StringBuilder();
        String line;
        while (true) {
            try {
                if (!((line = bufferReader.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            sb.append(line);
        }
        return sb.toString();
    }
}

