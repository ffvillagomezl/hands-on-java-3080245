package bank;

import java.util.Scanner;

import javax.security.auth.login.LoginException;

import bank.exceptions.AmountException;

public class Menu {

  private Scanner scanner;

  public static void main(String[] args) {
    System.out.println("Welcome to the Bank Application!");
    Menu menu = new Menu();
    menu.scanner = new Scanner(System.in);

    Customer customer = menu.loginCustomer();
    if (customer != null) {
      System.out.println("Login successful! Welcome, " + customer.getName() + "!");
      Account account = DataSource.getAccountById(customer.getAccountId());
      System.out.println("Your account details: " + account);
      menu.showMenuOptions(customer, account);
    } else {
      System.out.println("Login failed. Please try again.");
    }
    menu.scanner.close();
    System.out.println("Thank you for using the Bank Application. Goodbye!");
  }

  private void showMenuOptions(Customer customer, Account account) {
    while (customer.getAuthenticated()) {
      System.out.println("\nMenu Options:");
      System.out.println("1. Deposit");
      System.out.println("2. Withdraw");
      System.out.println("3. Check Balance");
      System.out.println("4. Logout");
      System.out.print("Please select an option: ");
      String choice = scanner.nextLine();
      switch (choice) {
        case "1":
          System.out.print("Enter amount to deposit: ");
          double depositAmount = Double.parseDouble(scanner.nextLine());
          try {
            account.deposit(depositAmount);
            System.out.println("Deposit successful! New balance: " + account.getBalance());
          } catch (AmountException e) {
            System.out.println(e.getMessage());
          }
          break;
        case "2":
          System.out.print("Enter amount to withdraw: ");
          double withdrawAmount = Double.parseDouble(scanner.nextLine());
          try {
            if (account.withdraw(withdrawAmount)) {
              System.out.println("Withdrawal successful! New balance: " + account.getBalance());
            } else {
              System.out.println("Insufficient funds. Withdrawal failed.");
            }
          } catch (AmountException e) {
            e.printStackTrace();
          }
          break;
        case "3":
          System.out.println("Current balance: " + account.getBalance());
          break;
        case "4":
          Authenticator.logout(customer);
          break;
        default:
          System.out.println("Invalid option. Please try again.");
      }
    }
  }

  private Customer loginCustomer() {
    System.out.print("Enter username: ");
    String username = scanner.nextLine();
    System.out.print("Enter password: ");
    String password = scanner.nextLine();
    try {
      return Authenticator.authenticate(username, password);
    } catch (LoginException e) {
      System.out.println(e.getMessage());
      return null;
    }
  }

}
