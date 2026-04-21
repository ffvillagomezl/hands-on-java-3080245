package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataSource {
  
  public static Connection getConnection() throws SQLException {
    Connection connection = null;
    try {
      connection = DriverManager.getConnection("jdbc:sqlite:resources/bank.db");
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return connection;
  }

  public static Account getAccountById(int accountId) {
    try (Connection connection = getConnection()) {
      String query = "SELECT * FROM accounts WHERE id = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setInt(1, accountId);
        try (ResultSet resultSet = statement.executeQuery()) {
          if (resultSet.next()) {
            return new Account(
              resultSet.getInt("id"), 
              resultSet.getString("type"),
              resultSet.getDouble("balance"));
            }
          } catch (Exception e) {

          }
        } catch (Exception e) {

        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    return null;
  }

  public static Customer getCustomerByUsername(String username) {
    try (Connection connection = getConnection()) {
      String query = "SELECT * FROM customers WHERE username = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, username);
        try (ResultSet resultSet = statement.executeQuery()) {
          if (resultSet.next()) {
            return new Customer(
              resultSet.getInt("id"), 
              resultSet.getString("name"),
              resultSet.getString("username"),
              resultSet.getString("password"),
              resultSet.getInt("account_id"));
            }
          } catch (Exception e) {

          }
        } catch (Exception e) {

        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    return null;
  }

  public static void updateAccountBalance(int accountId, double newBalance) {
    try (Connection connection = getConnection()) {
      String query = "UPDATE accounts SET balance = ? WHERE id = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setDouble(1, newBalance);
        statement.setInt(2, accountId);
        statement.executeUpdate();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }


  public static void main(String[] args) {
    try {
      Connection connection = getConnection();
      if (connection != null) {
        System.out.println("Connection successful!");
        Customer customer = getCustomerByUsername("clillea8@nasa.gov");
        System.out.println("Customer: " + customer);
        Account account = getAccountById(customer.getAccountId());
        System.out.println("Account: " + account);
        connection.close();
      } else {
        System.out.println("Failed to make connection!");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
