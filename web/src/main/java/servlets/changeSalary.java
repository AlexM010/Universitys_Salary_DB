package servlets;

import classes.Employee;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import static classes.Employee.changePermanentAdministrativeSalaries;

@WebServlet(name = "changeSalary", value = "/changeSalary")
public class changeSalary extends HttpServlet {
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json = Employee.ReaderToJson(request.getReader());
        changePermanentAdministrativeSalaries(Integer.parseInt(json) );
        System.out.println("Update Administrative Salary: "+json);
    }
}
