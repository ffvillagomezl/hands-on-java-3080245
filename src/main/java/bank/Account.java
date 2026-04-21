package bank;
import java.util.Objects;

import bank.exceptions.AmountException;

public class Account {
  private int id;
  private String type;
  private double balance;

  public Account(int id, String type, double balance) {
    this.id = id;
    this.type = type;
    this.balance = balance;
  }

  public Account() {
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public double getBalance() {
    return this.balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }


  public Account id(int id) {
    setId(id);
    return this;
  }

  public Account type(String type) {
    setType(type);
    return this;
  }

  public Account balance(double balance) {
    setBalance(balance);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Account)) {
            return false;
        }
        Account account = (Account) o;
        return id == account.id && Objects.equals(type, account.type) && balance == account.balance;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, balance);
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", type='" + getType() + "'" +
      ", balance='" + getBalance() + "'" +
      "}";
  }

  public void deposit(double depositAmount) throws AmountException {
    if (depositAmount > 0) {
      this.balance += depositAmount;
      DataSource.updateAccountBalance(this.id, this.balance);
    } else {
      throw new AmountException("Deposit amount must be positive.");
    }
  }

  public boolean withdraw(double withdrawAmount) throws AmountException {
    if (withdrawAmount > 0 && withdrawAmount <= this.balance) {
      this.balance -= withdrawAmount;
      DataSource.updateAccountBalance(this.id, this.balance);
      return true;
    } else if (withdrawAmount > 0 && withdrawAmount > this.balance) {
      return false;
    } else {
      throw new AmountException("Withdrawal amount must be positive AND less than or equal to the current balance.");
    }
  }
  
}
