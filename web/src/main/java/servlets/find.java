package servlets;

import classes.Employee;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

import static classes.Employee.getEmployeeInfo;

@WebServlet(name = "find", value = "/find")
public class find extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("user");
        System.out.println(username);
        try (PrintWriter out = response.getWriter()){
            String result = getEmployeeInfo(username);
            System.out.println(result);
            out.println(result);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
