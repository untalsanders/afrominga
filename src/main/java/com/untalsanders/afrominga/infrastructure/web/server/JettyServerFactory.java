package com.untalsanders.afrominga.infrastructure.web.server;

import com.untalsanders.afrominga.infrastructure.web.rest.controller.AfrosController;
import com.untalsanders.afrominga.infrastructure.web.rest.controller.TasksController;
import com.untalsanders.afrominga.shared.core.config.Configuration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

public class JettyServerFactory {
    public static void start(String[] args) throws Exception {
        QueuedThreadPool threadPool = new QueuedThreadPool();
        threadPool.setName("server");
        Server server = new Server(threadPool);

        try (ServerConnector connector = new ServerConnector(server)) {
            connector.setPort(Integer.parseInt(Configuration.get("server.port")));
            connector.setHost(Configuration.get("server.host"));
            connector.setAcceptQueueSize(128);
            server.addConnector(connector);
        }

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/api");
        context.addServlet(TasksController.class, "/tasks");
        context.addServlet(AfrosController.class, "/afros");
        server.setHandler(context);

        server.start();
        server.join();
    }
}
