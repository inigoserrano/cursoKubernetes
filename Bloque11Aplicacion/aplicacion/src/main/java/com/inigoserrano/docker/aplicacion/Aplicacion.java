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

@WebServlet({ "/aplicacion" })
public class Aplicacion extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8532332299687592651L;

	@Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Salida GET con BD");
        final PrintWriter writer = response.getWriter();
        writer.println("Salida GET con BD");
        try{
            final Connection connection = this.connectDatabase();
            final Statement stmt = connection.createStatement();
            final ResultSet resultSet = stmt.executeQuery("SELECT * FROM Personas");
            while(resultSet.next()){
                writer.println("PersonaID: " + resultSet.getInt("PersonaID"));
                writer.println("Nombre: " + resultSet.getString("Nombre"));
                writer.println("Apellido1: " + resultSet.getString("Apellido1"));
                writer.println("Apellido2: " + resultSet.getString("Apellido2"));
                writer.println("Direccion: " + resultSet.getString("Direccion"));
                writer.println("Poblacion: " + resultSet.getString("Poblacion"));
                writer.println("-------------------------------------------------");
            }
            stmt.close();
            connection.close();

        }catch(final ClassNotFoundException e){
            e.printStackTrace(writer);
        }catch(final SQLException e){
            e.printStackTrace(writer);
        }

        writer.flush();
    }

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Salida POST");
        response.getWriter().println("Salida POST");
        response.getWriter().flush();

    }

    private Connection connectDatabase() throws ClassNotFoundException, SQLException {
        Class.forName("org.mariadb.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://mariadb-service:3306/curso", "root", "admin123");
    }

}
