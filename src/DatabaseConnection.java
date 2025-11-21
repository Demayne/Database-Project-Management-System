import java.sql.Connection;
import java.sql.SQLException;
import util.ConnectionPool;
import util.LoggerUtil;

/**
 * DatabaseConnection class is used to establish a connection to the PoiseDMS database.
 * Now uses connection pooling for better performance and resource management.
 * 
 * @author Demayne Govender
 * @version 2.0
 * @deprecated Use ConnectionPool.getConnection() directly for better control
 */
public class DatabaseConnection {

  /**
   * Establishes a connection to the database using connection pool.
   * This method is maintained for backward compatibility.
   *
   * @return Connection object for interacting with the database.
   * @throws SQLException If a database access error occurs.
   */
  public static Connection getConnection() throws SQLException {
    try {
      Connection connection = ConnectionPool.getConnection();
      LoggerUtil.debug("Database connection obtained from pool");
      return connection;
    } catch (SQLException e) {
      LoggerUtil.error("Failed to get database connection", e);
      System.err.println("❌ Connection failed: " + e.getMessage());
      throw e;
    }
  }
  
  /**
   * Closes the connection pool when application shuts down.
   * Should be called during application shutdown.
   */
  public static void closePool() {
    ConnectionPool.closePool();
    LoggerUtil.info("Database connection pool closed");
  }
}
