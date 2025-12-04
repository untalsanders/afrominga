package com.untalsanders.afrominga.infrastructure.persistence;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.untalsanders.afrominga.shared.core.config.Configuration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public final class Database {
    private Database() {}

    public static Connection getConnection() throws SQLException {
        return DataSource.getConnection();
    }

    private static final class DataSource {
        private static final HikariConfig config = new HikariConfig();
        private static final HikariDataSource ds;
        private static final String TEMPLATE_URL = "jdbc:%s://%s:%s/%s";

        private DataSource() {}

        static {
            try {
                final String DRIVER = Configuration.get("database.db-driver");
                final String DB_HOST = Configuration.get("database.db-host");
                final String DB_PORT = Configuration.get("database.db-port");
                final String DB_USER = Configuration.get("database.db-user");
                final String DB_PASS = Configuration.get("database.db-pass");
                final String DB_NAME = Configuration.get("database.db-name");

                final String URL = String.format(TEMPLATE_URL, DRIVER, DB_HOST, DB_PORT, DB_NAME);

                config.setJdbcUrl(URL);
                config.setUsername(DB_USER);
                config.setPassword(DB_PASS);
                config.addDataSourceProperty( "cachePrepStmts" , "true" );
                config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
                config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );

                ds = new HikariDataSource(config);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public static Connection getConnection() throws SQLException {
            return ds.getConnection();
        }
    }
}
