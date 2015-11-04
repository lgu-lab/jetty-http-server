package org.lgulab.webserver.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class HelloServlet extends HttpServlet
{
    @Override
    protected void doGet( HttpServletRequest request,
                          HttpServletResponse response ) throws ServletException,
                                                        IOException
    {
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter out = response.getWriter();
        
        out.println("<h1>Hello from HelloServlet</h1>");
        out.println("<h2>Request info :</h2>");
        out.println("<ul>");
        out.println("<li>path info : " + request.getPathInfo() + "</li>");
        out.println("<li>servlet info : " + request.getServletPath() + "</li>");
        out.println("</ul>");
    }
}