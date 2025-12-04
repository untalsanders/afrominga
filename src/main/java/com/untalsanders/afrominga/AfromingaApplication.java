package com.untalsanders.afrominga;

import com.untalsanders.afrominga.infrastructure.web.server.JettyServerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AfromingaApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(AfromingaApplication.class);

    public static void main(String[] args) throws Exception {
        LOGGER.info("Profile active: {}", System.getenv("profile"));
        LOGGER.info("Started application...!!!");
        JettyServerFactory.start(args);
    }
}
