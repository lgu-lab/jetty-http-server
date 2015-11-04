package org.lgulab.webserver.handlers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
 
public class HelloHandler extends AbstractHandler
{
    final String greeting;
//    final String body;
 
    public HelloHandler()
    {
        this("Hello World");
    }
 
    public HelloHandler( String greeting )
    {
    	super();
//        this(greeting, null);
    	this.greeting = greeting;
    }
 
//    public HelloHandler( String greeting, String body )
//    {
//        this.greeting = greeting;
//        this.body = body;
//    }
 
    @Override
    public void handle( String target,
                        Request baseRequest,
                        HttpServletRequest request,
                        HttpServletResponse response ) throws IOException,
                                                      ServletException
    {
        response.setContentType("text/html; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
 
        PrintWriter out = response.getWriter();
 
        out.println("<h1>" + greeting + "</h1>");
//        if (body != null)
//        {
//            out.println(body);
//        }
 
        out.println("<h2>Request information : </h2>");
        out.println("<ul>");
        out.println("<li> getMethod   : " + baseRequest.getMethod() + "</li>" );
        out.println("<li> getPathInfo : " + baseRequest.getPathInfo() + "</li>" );
        out.println("</ul>");

        out.println("<h2>HttpServletRequest information : </h2>");
        out.println("<ul>");
        out.println("<li> getMethod   : " + request.getMethod() + "</li>" );
        out.println("<li> getPathInfo : " + request.getPathInfo() + "</li>" );
        out.println("</ul>");

        
        baseRequest.setHandled(true);
    }
}