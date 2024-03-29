package io.github.untalsanders.afrominga.infrastructure.server;

import io.github.untalsanders.afrominga.application.rest.controller.AfroController;
import io.github.untalsanders.afrominga.infrastructure.config.Configuration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

public class JettyServerFactory {
    private static Server server;

    public static void start(String[] args) throws Exception {
        QueuedThreadPool threadPool = new QueuedThreadPool();
        threadPool.setName("server");
        server = new Server(threadPool);

        try (ServerConnector connector = new ServerConnector(server)) {
            connector.setPort(Integer.parseInt(Configuration.get("server.port")));
            connector.setHost(Configuration.get("server.host"));
            connector.setAcceptQueueSize(128);
            server.addConnector(connector);
        }

        ServletHandler servletHandler = new ServletHandler();
        servletHandler.addServletWithMapping(AfroController.class, "/afros");

        server.setHandler(servletHandler);
        server.start();
    }

    public void stop() throws Exception {
        server.stop();
    }

    public static void waitForInterrupt() throws InterruptedException {
        server.join();
    }
}
