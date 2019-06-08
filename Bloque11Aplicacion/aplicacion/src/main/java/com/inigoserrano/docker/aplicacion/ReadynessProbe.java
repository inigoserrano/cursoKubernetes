package com.inigoserrano.docker.aplicacion;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({ "/readynessProbe" })
public class ReadynessProbe extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 8532332299687592651L;



    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {

        final PrintWriter writer = response.getWriter();
        try {
            final Connection connection = this.connectDatabase();
            final Statement stmt = connection.createStatement();
            final ResultSet resultSet = stmt.executeQuery("SELECT * FROM Personas");
            if (resultSet.next()) {
                System.out.println("OK");
                writer.println("OK");
            }
            stmt.close();
            connection.close();

        } catch (final ClassNotFoundException e) {
            response.sendError(500);
            System.out.println("No OK");
        } catch (final SQLException e) {
            response.sendError(500);
            System.out.println("No OK");
        }

        writer.flush();
    }

    private Connection connectDatabase() throws ClassNotFoundException, SQLException {
        Class.forName("org.mariadb.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://mariadb-service:3306/curso", "root", "admin123");
    }

}
