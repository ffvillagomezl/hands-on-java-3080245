package bank;

import javax.security.auth.login.LoginException;

public class Authenticator {

  public static Customer authenticate(String username, String password) throws LoginException {
    Customer customer = DataSource.getCustomerByUsername(username);
    if (customer == null) {
      throw new LoginException("Authentication failed: User not found.");
    }
    if (customer != null && customer.getPassword().equals(password)) {
      customer.setAuthenticated(true);
      return customer;
    } else {
      throw new LoginException("Authentication failed: Invalid password.");
    }
  }

  public static void logout(Customer customer) {
    customer.setAuthenticated(false);
    System.out.println("Customer " + customer.getName() + " has been logged out.");
  }
}
