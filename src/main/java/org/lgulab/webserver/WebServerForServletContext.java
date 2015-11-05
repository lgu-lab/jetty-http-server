package org.lgulab.webserver;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.lgulab.webserver.servlets.HelloServlet;
import org.lgulab.webserver.servlets.PingServlet;
import org.lgulab.webserver.servlets.SessionServlet;

/**
 * 
 * Maven dependency : artifactId = "jetty-servlet"
 *
 */
public class WebServerForServletContext {

	public static void main(String[] args) throws Exception
    {
		Server server = new Server(8080);
		ServletContextHandler servletContextHandler = new ServletContextHandler(server, "/ctx");
 
        // Passing in the class for the Servlet allows jetty to instantiate an
        // instance of that Servlet and mount it on a given context path.
 
        // IMPORTANT:
        // This is a raw Servlet, not a Servlet that has been configured
        // through a web.xml @WebServlet annotation, or anything similar.
		servletContextHandler.addServlet(HelloServlet.class,    "/hello/*");
		servletContextHandler.addServlet(PingServlet.class,     "/ping/*");
		servletContextHandler.addServlet(SessionServlet.class,  "/session/*");  // Error : "No SessionManager"
 
        // Start things up!
        server.start();
 
        // The use of server.join() the will make the current thread join and
        // wait until the server is done executing.
        // See : http://docs.oracle.com/javase/7/docs/api/java/lang/Thread.html#join()
        server.join();    
    }
}
