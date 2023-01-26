package servlets;

import classes.Employee;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import static classes.Employee.changePermanentAdministrativeSalaries;
import static classes.Employee.changePermanentEducationalSalaries;

@WebServlet(name = "changeEdu_salary", value = "/changeEdu_salary")
public class changeEdu_salary extends HttpServlet {
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json = Employee.ReaderToJson(request.getReader());
        changePermanentEducationalSalaries(Integer.parseInt(json));
        System.out.println("Update Educational Salary: "+json);
    }
}
