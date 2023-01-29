package servlets;

import classes.Employee;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import static classes.Employee.changeBonuses;

@WebServlet(name = "change_bonus", value = "/change_bonus")
public class change_bonus extends HttpServlet {
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json = Employee.ReaderToJson(request.getReader());
        changeBonuses(Employee.json.getJson(json).get("admin_salary").getAsInt(),Employee.json.getJson(json).get("bonus_search").getAsInt(),Employee.json.getJson(json).get("edu_salary").getAsInt(),Employee.json.getJson(json).get("bonus_lib").getAsInt());
        System.out.println("Updated Bonus: "+json);
    }
}
