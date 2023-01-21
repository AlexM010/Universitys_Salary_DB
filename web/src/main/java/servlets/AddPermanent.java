package servlets;

import classes.Employee;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddPermanent", value = "/AddPermanent")
public class AddPermanent extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Gson gson = new Gson();
//        String json = gson.fromJson(request.getReader(), String.class);
        String json = Employee.ReaderToJson(request.getReader());
        System.out.println(json);
        try {
            Employee new_emp = Employee.addEmployee(json);
        } catch (Exception e) {
            response.setStatus(403);
            throw new RuntimeException(e);
        }
        response.setStatus(200);
    }
}
