import java.sql.*;
import java.util.Scanner;
import util.LoggerUtil;

/**
 * The Main class serves as the entry point for the PoiseDMS project management system.
 * It provides a command-line interface for users to interact with the database,
 * allowing them to view, search, add, update, delete, and finalize projects.
 *
 * <p>This class establishes a database connection, presents a menu to the user,
 * and executes the corresponding actions based on user input.</p>
 * 
 * @author Demayne Govender
 * @version 2.0
 */
public class Main {

  /**
   * The main method initializes the program, establishes a database connection,
   * and handles user interaction through a menu-driven interface.
   *
   * @param args Command-line arguments (not used in this application)
   */
  public static void main(String[] args) {
    LoggerUtil.info("PoiseDMS application started");
    // Graceful shutdown hook to ensure pool closes even on abrupt termination
    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      LoggerUtil.info("Shutdown hook triggered: closing database pool");
      DatabaseConnection.closePool();
    }));
    Scanner scanner = new Scanner(System.in); // Scanner resource initialization
    ProjectManager projectManager = new ProjectManager(); // Initialize ProjectManager instance

    // Display welcome message
    displayWelcomeBanner();
    
    try (Connection connection = DatabaseConnection.getConnection()) {
      LoggerUtil.info("Database connection established successfully");
      
      while (true) {
        // Display options to the user
        System.out.println("\n" + "=".repeat(50));
        System.out.println("PoiseDMS - Please choose an option:");
        System.out.println("=".repeat(50));
        System.out.println("1. View all projects");
        System.out.println("2. View incomplete projects");
        System.out.println("3. View overdue projects");
        System.out.println("4. Search projects by number or name");
        System.out.println("5. Add a new project");
        System.out.println("6. Update an existing project");
        System.out.println("7. Delete a project");
        System.out.println("8. Finalize a project");
        System.out.println("9. Exit");
        System.out.println("=".repeat(50));
        System.out.print("Enter your choice: ");

        int choice;
        try {
          choice = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException ex) {
          System.out.println("❌ Invalid input. Please enter a number between 1 and 9.");
          LoggerUtil.warning("Non-numeric menu input received");
          continue; // restart loop
        }
        
        LoggerUtil.info("User selected menu option: " + choice);

        switch (choice) {
          case 1:
            if (!confirmContinue(scanner)) {
              continue;
            }
            projectManager.viewAllProjects(connection);
            break;

          case 2:
            if (!confirmContinue(scanner)) {
              continue;
            }
            projectManager.viewIncompleteProjects(connection);
            break;

          case 3:
            if (!confirmContinue(scanner)) {
              continue;
            }
            projectManager.viewOverdueProjects(connection);
            break;

          case 4:
            if (!confirmContinue(scanner)) {
              continue;
            }
            projectManager.searchProjects(connection, scanner);
            break;

          case 5:
            if (!confirmContinue(scanner)) {
              continue;
            }
            projectManager.addNewProject(connection, scanner);
            break;

          case 6:
            if (!confirmContinue(scanner)) {
              continue;
            }
            projectManager.updateProject(connection, scanner);
            break;

          case 7:
            if (!confirmContinue(scanner)) {
              continue;
            }
            projectManager.deleteProject(connection, scanner);
            break;

          case 8:
            if (!confirmContinue(scanner)) {
              continue;
            }
            projectManager.finaliseProject(connection, scanner);
            break;

          case 9:
            System.out.println("\n" + "=".repeat(50));
            System.out.println("Thank you for using PoiseDMS!");
            System.out.println("Closing database connections...");
            DatabaseConnection.closePool();
            System.out.println("Application terminated successfully.");
            System.out.println("=".repeat(50));
            LoggerUtil.info("PoiseDMS application terminated by user");
            return;

          default:
            System.out.println("❌ Invalid choice. Please enter a number between 1 and 9.");
            LoggerUtil.warning("Out-of-range menu choice entered: " + choice);
        }
      }
    } catch (SQLException e) {
      LoggerUtil.error("Database error occurred", e);
      System.err.println("❌ Database error: " + e.getMessage());
      e.printStackTrace();
    } catch (Exception e) {
      LoggerUtil.error("Unexpected error occurred", e);
      System.err.println("❌ Unexpected error: " + e.getMessage());
      e.printStackTrace();
    } finally {
      // Closes the scanner to avoid resource leak
      scanner.close();
      LoggerUtil.info("Scanner resources released");
    }
  }
  
  /**
   * Displays welcome banner for the application.
   */
  private static void displayWelcomeBanner() {
    System.out.println("\n" + "=".repeat(60));
    System.out.println(" ".repeat(10) + "POISE DATABASE MANAGEMENT SYSTEM");
    System.out.println(" ".repeat(12) + "Project Management Solution v2.0");
    System.out.println(" ".repeat(18) + "by Demayne Govender");
    System.out.println("=".repeat(60) + "\n");
  }

  /**
   * Prompts the user to confirm whether they want to proceed with an action.
   *
   * @param scanner The scanner object used to capture user input.
   * @return {@code true} if the user confirms to continue, otherwise {@code false}.
   */
  private static boolean confirmContinue(Scanner scanner) {
    System.out.println("Do you want to proceed? (y to continue, n to return to main menu)");
    String response = scanner.nextLine().trim().toLowerCase();
    return response.equals("y");
  }
}
