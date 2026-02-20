import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/register")
public class register extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");
        String address = request.getParameter("address");
        String role = request.getParameter("role");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/userdb", "root", "");

            String query = "INSERT INTO users(first_name,last_name,password,email,mobile,address,role) VALUES(?,?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, password);
            ps.setString(4, email);
            ps.setString(5, mobile);
            ps.setString(6, address);
            ps.setString(7, role);

            int i = ps.executeUpdate();

            if (i > 0) 
            {
                out.println("<h3>Registration Successful!</h3>");
            } else {
                out.println("<h3>Registration Failed!</h3>");
            }

            con.close();

        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }
}
