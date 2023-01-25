package servlets;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static db.Database.getFromDB;
import static db.Database.getResultsToJSON;

@WebServlet(name = "edit", value = "/edit")
public class edit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("user");
        try (PrintWriter out = response.getWriter()){

            ResultSet rp = getFromDB("SELECT * FROM permanent WHERE name = '" + username + "'");
            ResultSet rc = getFromDB("SELECT * FROM contracted WHERE name = '" + username + "'");
            if(rp.next()){
                String json = "{\"contract\":false}";
                JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
                JsonObject newJsonObject = new JsonParser().parse(getResultsToJSON(rp)).getAsJsonObject();
                jsonObject.add("newData",newJsonObject);
                json = jsonObject.toString();

                out.println(json);
                response.setStatus(200);
            }
            else if(rc.next()){
                String json = "{\"contract\":true}";
                JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
                JsonObject newJsonObject = new JsonParser().parse(getResultsToJSON(rc)).getAsJsonObject();
                jsonObject.add("newData",newJsonObject);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
