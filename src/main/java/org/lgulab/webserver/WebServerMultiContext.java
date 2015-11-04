package org.lgulab.webserver;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.lgulab.webserver.handlers.HelloHandler;
import org.lgulab.webserver.handlers.PingHandler;

public class WebServerMultiContext {

    public static void main(String[] args) throws Exception
    {
        Server server = new Server(8080);

        
        // Create a ContextHandlerCollection and set the context handlers to it.
        // This will let jetty process urls against the declared contexts in
        // order to match up content.
        
        // Here : 1 context = 1 handler = 1 action
        ContextHandlerCollection contextHandlers = new ContextHandlerCollection();
        registerContextHanlder(contextHandlers, "/hello", new HelloHandler() );
        registerContextHanlder(contextHandlers, "/ping",  new PingHandler() );
        
        // Set the handlers collection in the server
        server.setHandler(contextHandlers);
        
        server.start();
        // server.dumpStdErr();
        server.join();
    }
	
	private static void registerContextHanlder(ContextHandlerCollection contextHandlers, String path,	AbstractHandler handler) {
        ContextHandler contextHandler = new ContextHandler();
        contextHandler.setContextPath( path );
        contextHandler.setHandler( handler );
        contextHandlers.addHandler(contextHandler);
    }
}