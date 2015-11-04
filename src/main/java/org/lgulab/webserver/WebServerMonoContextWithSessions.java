package org.lgulab.webserver;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.SessionManager;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.session.HashSessionIdManager;
import org.eclipse.jetty.server.session.HashSessionManager;
import org.eclipse.jetty.server.session.SessionHandler;
import org.lgulab.webserver.handlers.SessionStatusHandler;

public class WebServerMonoContextWithSessions {

	public static void main(String[] args) throws Exception
    {
        Server server = new Server(8080);
        
        // Specify the Session ID Manager (only if sessions are used)
        HashSessionIdManager idmanager = new HashSessionIdManager();
        server.setSessionIdManager(idmanager);
        
        // Sessions are bound to a context => create a ContextHandler
        ContextHandler contextHandler = new ContextHandler("/");
        server.setHandler(contextHandler);
        
        // Create the SessionHandler (to handle the sessions) and bound it to the context
        SessionManager sessionManager = new HashSessionManager();
        SessionHandler sessionHandler = new SessionHandler(sessionManager);
        contextHandler.setHandler(sessionHandler);
        
        // Put the specific handler inside of SessionHandler 
        sessionHandler.setHandler(new SessionStatusHandler() );

        // Tree is now
        // Server
        //   + ContextHandler("/")
        //       + SessionHandler(HashSessionManager)
        //           + SessionStatusHandler
        
        server.start();
        // server.dumpStdErr();
        server.join();
    }

}
