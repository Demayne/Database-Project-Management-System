package util;

import config.DatabaseConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * ConnectionPool manages database connections using HikariCP for optimal performance.
 * This ensures efficient connection management in production environments.
 * 
 * @author Demayne Govender
 * @version 2.0
 */
public class ConnectionPool {
    private static HikariDataSource dataSource;
    
    static {
        try {
            initializePool();
        } catch (Exception e) {
            System.err.println("❌ Failed to initialize connection pool: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Initializes the HikariCP connection pool with configuration settings.
     */
    private static void initializePool() {
        HikariConfig config = new HikariConfig();
        
        config.setJdbcUrl(DatabaseConfig.getUrl());
        config.setUsername(DatabaseConfig.getUsername());
        config.setPassword(DatabaseConfig.getPassword());
        config.setDriverClassName(DatabaseConfig.getDriver());
        
        // Connection pool settings
        config.setMaximumPoolSize(DatabaseConfig.getPoolSize());
        config.setConnectionTimeout(DatabaseConfig.getConnectionTimeout());
        config.setIdleTimeout(600000); // 10 minutes
        config.setMaxLifetime(1800000); // 30 minutes
        config.setConnectionTestQuery("SELECT 1");
        
        // Performance optimizations
        config.setAutoCommit(true);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.addDataSourceProperty("useServerPrepStmts", "true");
        
        dataSource = new HikariDataSource(config);
        System.out.println("✅ Connection pool initialized successfully.");
    }
    
    /**
     * Gets a connection from the pool.
     * 
     * @return Database connection
     * @throws SQLException if unable to get connection
     */
    public static Connection getConnection() throws SQLException {
        if (dataSource == null) {
            throw new SQLException("Connection pool not initialized");
        }
        return dataSource.getConnection();
    }
    
    /**
     * Closes the connection pool and releases all resources.
     */
    public static void closePool() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
            System.out.println("✅ Connection pool closed successfully.");
        }
    }
    
    /**
     * Gets pool statistics for monitoring.
     * 
     * @return Pool statistics as string
     */
    public static String getPoolStats() {
        if (dataSource != null) {
            return String.format("Active: %d, Idle: %d, Total: %d, Waiting: %d",
                dataSource.getHikariPoolMXBean().getActiveConnections(),
                dataSource.getHikariPoolMXBean().getIdleConnections(),
                dataSource.getHikariPoolMXBean().getTotalConnections(),
                dataSource.getHikariPoolMXBean().getThreadsAwaitingConnection());
        }
        return "Pool not initialized";
    }
}
