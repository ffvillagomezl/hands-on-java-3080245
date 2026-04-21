package bank;
import java.util.Objects;

public class Customer {
  private int id;
  private String name;
  private String username;
  private String password;
  private int accountId;
  private boolean authenticated;
  
  public Customer(int id, String name, String username, String password, int accountId) {
    this.id = id;
    this.name = name;
    this.username = username;
    this.password = password;
    this.accountId = accountId;
    this.authenticated = false;
  }

  public Customer() {
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getAccountId() {
    return this.accountId;
  }

  public void setAccountId(int accountId) {
    this.accountId = accountId;
  }

  public boolean getAuthenticated() {
    return this.authenticated;
  }

  public void setAuthenticated(boolean authenticated) {
    this.authenticated = authenticated;
  }

  public Customer id(int id) {
    setId(id);
    return this;
  }

  public Customer name(String name) {
    setName(name);
    return this;
  }

  public Customer username(String username) {
    setUsername(username);
    return this;
  }

  public Customer password(String password) {
    setPassword(password);
    return this;
  }

  public Customer accountId(int accountId) {
    setAccountId(accountId);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Customer)) {
            return false;
        }
        Customer customer = (Customer) o;
        return id == customer.id && Objects.equals(name, customer.name) && Objects.equals(username, customer.username) && Objects.equals(password, customer.password) && accountId == customer.accountId;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, username, password, accountId);
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", name='" + getName() + "'" +
      ", username='" + getUsername() + "'" +
      ", password='" + getPassword() + "'" +
      ", accountId='" + getAccountId() + "'" +
      "}";
  }
  
}
