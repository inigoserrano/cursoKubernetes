package com.inigoserrano.docker.aplicacion;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

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

    private static final Date inicio = new Date();

    private static final long espera = 1 * 60 * 1000;

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        final PrintWriter writer = response.getWriter();
        Date ahora = new Date();
        if (inicio.getTime() + espera > ahora.getTime()){
            writer.println("OK");
        }else{
            response.sendError(500);
        } 
        writer.flush();
    }

}
