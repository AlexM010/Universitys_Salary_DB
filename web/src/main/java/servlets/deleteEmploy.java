package servlets;

import classes.Employee;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import static db.Database.getFromDB;
import static db.Database.getResultsToJSON;

@WebServlet(name = "deleteEmploy", value = "/deleteEmploy")
public class deleteEmploy extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("user");

        try (PrintWriter out = response.getWriter()){

            ResultSet rp = getFromDB("SELECT * FROM permanent WHERE name = '" + username + "'");
            ResultSet rc = getFromDB("SELECT * FROM contracted WHERE name = '" + username + "'");
            ResultSet sal = getFromDB("SELECT main_salary FROM salary WHERE name = '" + username + "'");

            if(rp.next()){
                String json = "{\"contract\":false}";
                JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
                JsonObject newJsonObject = new JsonParser().parse(getResultsToJSON(rp)).getAsJsonObject();
                JsonObject merged = new JsonObject();
                for (Map.Entry<String, JsonElement> entry : newJsonObject.entrySet()) {
                    jsonObject.add(entry.getKey(), entry.getValue());
                }


                json = jsonObject.toString();
                System.out.println(json);
                out.println(json);
                response.setStatus(200);
            }
            else if(rc.next()){
                sal.next();
                JsonObject salary = new JsonParser().parse(getResultsToJSON(sal)).getAsJsonObject();
                String json = "{\"contract\":true}";
                JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
                JsonObject newJsonObject = new JsonParser().parse(getResultsToJSON(rc)).getAsJsonObject();
                for (Map.Entry<String, JsonElement> entry : newJsonObject.entrySet()) {
                    jsonObject.add(entry.getKey(), entry.getValue());
                }
                for (Map.Entry<String, JsonElement> entry : salary.entrySet()) {
                    jsonObject.add(entry.getKey(), entry.getValue());
                }

                json = jsonObject.toString();
                System.out.println(json);
                out.println(json);
                response.setStatus(200);
            }else {
                System.out.println("Not found");
                response.setStatus(403);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json = Employee.ReaderToJson(request.getReader());
        Employee.firePermanentEmployee(Employee.json.getJson(json).get("user").getAsString(),Employee.json.getJson(json).get("date").getAsString());
        System.out.println(json);
    }
}
