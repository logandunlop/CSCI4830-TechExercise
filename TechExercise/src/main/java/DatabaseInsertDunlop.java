import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DatabaseInsertDunlop")
public class DatabaseInsertDunlop extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public DatabaseInsertDunlop() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String firstName = request.getParameter("firstName");
      String lastName = request.getParameter("lastName");
      String email = request.getParameter("email");
      String phone = request.getParameter("phone");
      String admission = request.getParameter("admission");
      String graduation = request.getParameter("graduation");
      String GPA = request.getParameter("GPA");

      Connection connection = null;
      String insertSql = " INSERT INTO MyTableDunlopTechExercise (id, FIRST_NAME, LAST_NAME, EMAIL, PHONE, ADMISSION_YEAR, GRADUATION_YEAR, GPA) values (default, ?, ?, ?, ?, ?, ?, ?)";

      try {
         DBConnectionDunlop.getDBConnection();
         connection = DBConnectionDunlop.connection;
         PreparedStatement preparedStmt = connection.prepareStatement(insertSql);
         preparedStmt.setString(1, firstName);
         preparedStmt.setString(2, lastName);
         preparedStmt.setString(3, email);
         preparedStmt.setString(4, phone);
         preparedStmt.setString(5, admission);
         preparedStmt.setString(6, graduation);
         preparedStmt.setString(7, GPA);
         preparedStmt.execute();
         connection.close();
      } catch (Exception e) {
         e.printStackTrace();
      }

      // Set response content type
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Insert Data to DB table";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h2 align=\"center\">" + title + "</h2>\n" + //
            "<ul>\n" + //

            "  <li><b>First Name</b>: " + firstName + "\n" + //
            "  <li><b>Last Name</b>: " + lastName + "\n" + //
            "  <li><b>Email</b>: " + email + "\n" + //
            "  <li><b>Phone</b>: " + phone + "\n" + //
            "  <li><b>Admission Year</b>: " + admission + "\n" + //
            "  <li><b>Graduation Year</b>: " + graduation + "\n" + //
            "  <li><b>Grade Point Average</b>: " + GPA + "\n" + //

            "</ul>\n");

      out.println("<a href=/webproject-techexercise-ldunlop/DatabaseSearchDunlop.html>Search Database</a> <br>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
