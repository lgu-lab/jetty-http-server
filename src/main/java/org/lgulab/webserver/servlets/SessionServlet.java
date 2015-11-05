package org.lgulab.webserver.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class SessionServlet extends HttpServlet
{
    @Override
    protected void doGet( HttpServletRequest request,
                          HttpServletResponse response ) throws ServletException,
                                                        IOException
    {
        response.setContentType("text/plain; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

        PrintWriter out = response.getWriter();
 
        String pathInfo = request.getPathInfo() ;
        if ( pathInfo == null ) {
        	pathInfo = "/" ;
        }
        else {
        	pathInfo = pathInfo.toLowerCase() ;
        }
        
        switch(pathInfo) {
	        case "/create" :
	        	createSession(request, out ); 
	        	break;
	        case "/delete" :
	        	deleteSession(request, out );
	        	break ;
	        default :
	        	sessionStatus(request, out );
	        	break;
        }
    }
    
    private void sessionStatus(HttpServletRequest request, PrintWriter out ) {
        out.println("Session status : " );
        HttpSession session = request.getSession(false);
        if ( session != null ) {
            out.println("Session exists, id = " + session.getId() );
        }
        else {
            out.println("Session doesn't exist"  );
        }
    }

    private void createSession(HttpServletRequest request, PrintWriter out ) {
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

    private void deleteSession(HttpServletRequest request, PrintWriter out ) {
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

}