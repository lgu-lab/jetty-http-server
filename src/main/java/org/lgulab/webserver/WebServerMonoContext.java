package org.lgulab.webserver;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.lgulab.webserver.handlers.HelloHandler;

public class WebServerMonoContext {

	public static void main(String[] args) throws Exception
    {
        Server server = new Server(8080);
        
        // Set handler for a specific context : "/hello" and "/hello/*"
        ContextHandler contextHandler = new ContextHandler();
        contextHandler.setContextPath( "/hello" );
        contextHandler.setHandler( new HelloHandler() );
        
        // Set the unique handler 
        server.setHandler( contextHandler );

        // Tree is now
        // Server
        //   + ContextHandler("/hello")
        //           + HelloHandler
                
        server.start();
        // server.dumpStdErr();
        server.join();
    }

}
