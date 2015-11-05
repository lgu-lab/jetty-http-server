package org.lgulab.webserver;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.lgulab.webserver.servlets.HelloServlet;
import org.lgulab.webserver.servlets.PingServlet;
import org.lgulab.webserver.servlets.SessionServlet;

/**
 * Minimal Servlet Example
 * 
 * This example shows the bare minimum required for deploying a servlet into jetty. 
 * Note that this is strictly a servlet, not a servlet in the context of a web application.
 * This is purely just a servlet deployed and mounted on a context and able to process requests.
 * This example is excellent for situations where you have a simple servlet that you need to unit test, 
 * just mount it on a context and issue requests using your favorite http client library 
 * ( like "Jetty client" ).
 * 
 * Maven dependency : artifactId = "jetty-servlet"
 *
 */
public class WebServerForServlets {

	public static void main(String[] args) throws Exception
    {
        // Create a basic jetty server object that will listen on port 8080.
        // Note that if you set this to port 0 then a randomly available port
        // will be assigned that you can either look in the logs for the port,
        // or programmatically obtain it for use in test cases.
        Server server = new Server(8080);
 
        // The ServletHandler is a dead simple way to create a context handler
        // that is backed by an instance of a Servlet.
        // This handler then needs to be registered with the Server object.
        ServletHandler servletHandler = new ServletHandler();
        server.setHandler(servletHandler);
 
        // Passing in the class for the Servlet allows jetty to instantiate an
        // instance of that Servlet and mount it on a given context path.
 
        // IMPORTANT:
        // This is a raw Servlet, not a Servlet that has been configured
        // through a web.xml @WebServlet annotation, or anything similar.
        servletHandler.addServletWithMapping(HelloServlet.class,    "/hello/*");
        servletHandler.addServletWithMapping(PingServlet.class,     "/ping/*");
        servletHandler.addServletWithMapping(SessionServlet.class,  "/session/*"); // Error : "No SessionManager"
 
        // Start things up!
        server.start();
 
        // The use of server.join() the will make the current thread join and
        // wait until the server is done executing.
        // See : http://docs.oracle.com/javase/7/docs/api/java/lang/Thread.html#join()
        server.join();    
    }
}
