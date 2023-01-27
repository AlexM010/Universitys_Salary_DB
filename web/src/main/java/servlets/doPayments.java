package servlets;

import classes.Employee;
import classes.Payment;
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

import static classes.Employee.changeBonuses;
import static db.Database.getFromDB;
import static db.Database.getResultsToJSON;

@WebServlet(name = "doPayments", value = "/doPayments")
public class doPayments extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try (PrintWriter out = response.getWriter()){
            String json = Payment.paymentsPerCategory();

            out.println(json);
            System.out.println(json);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json = Employee.ReaderToJson(request.getReader());
        Payment.doPayments(Employee.json.getJson(json).get("date").getAsString(),Employee.json.getJson(json).get("admin_salary").getAsInt(),Employee.json.getJson(json).get("bonus_search").getAsInt(),Employee.json.getJson(json).get("edu_salary").getAsInt(),Employee.json.getJson(json).get("bonus_lib").getAsInt());
        System.out.println("Updated Bonus: "+json);
    }
}
