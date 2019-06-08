package com.inigoserrano.docker.aplicacion;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({ "/livenessProbe" })
public class LivenessProbe extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8532332299687592651L;

	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		try (final PrintWriter writer = response.getWriter()) {
			writer.println("OK");
			writer.flush();
		}

	}

}
