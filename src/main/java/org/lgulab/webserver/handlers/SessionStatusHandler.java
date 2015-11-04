package org.lgulab.webserver.handlers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
 
public class SessionStatusHandler extends AbstractHandler
{
    @Override
    public void handle( String target, Request baseRequest,
                        HttpServletRequest request, HttpServletResponse response ) 
                        throws IOException, ServletException
    {
        response.setContentType("text/plain; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

        PrintWriter out = response.getWriter();
 
        if ( "/delete".equals(request.getPathInfo())) {
            out.println("Try to invalidate session : " );
            HttpSession session = request.getSession(false);
            if ( session != null ) {
            	session.invalidate();
                out.println("Session deleted " );
            }
            else {
                out.println("Session doesn't exist" );
            }
        }
        else if ( "/create".equals(request.getPathInfo())) {
            out.println("Try to create session : " );
            HttpSession session = request.getSession(false);
            if ( session != null ) {
                out.println("Session already exists, id = " + session.getId() );
            }
            else {
            	session = request.getSession();
                out.println("Session created, id = " + session.getId() );
            }
        }
        else {
            out.println("Session status : " );
            HttpSession session = request.getSession(false);
            if ( session != null ) {
                out.println("Session exists, id = " + session.getId() );
            }
            else {
                out.println("Session doesn't exist"  );
            }
        }
        
        baseRequest.setHandled(true);
    }
} 