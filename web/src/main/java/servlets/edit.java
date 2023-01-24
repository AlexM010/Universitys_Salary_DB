package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static db.Database.getFromDB;

@WebServlet(name = "edit", value = "/edit")
public class edit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try (PrintWriter out = response.getWriter()){
            int count = 0;
            int count2 = 0;
            String username=request.getParameter("name");
            ResultSet rp = getFromDB("SELECT * FROM permanent WHERE name = '" + username + "'");
            ResultSet rc = getFromDB("SELECT * FROM contracted WHERE name = '" + username + "'");
            while(rp.next()){
                count++;
            }
            while (rc.next()){
                count2++;
            }
            if(count>0 || count2>0){
//            out.println("Already exists");
                System.out.println("Status code 403 - username is used");
                response.setStatus(403);
            }
            else{
//            out.println();
                response.setStatus(200);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
