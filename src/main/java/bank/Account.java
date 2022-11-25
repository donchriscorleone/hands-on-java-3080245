package bank;

import bank.Exceptions.AmountException;

public class Account {
  private int id;
  private String type;
  private double balance;

  public Account(int id, String type, double balance) {
    this.id = id;
    this.type = type;
    this.balance = balance;
  }

  public void deposit(double amount) throws AmountException {
    if (amount < 1) {
      throw new AmountException("The minimum deposit is 1.");
    }

    else {
      double newBalance = this.getBalance() + amount;
      setBalance(newBalance);
      DataSource.updateAccountBalance(id, newBalance);
    }
  }

  public void withdraw(double amount) throws AmountException {
    if (amount < 0) {
      throw new AmountException("The amount must be greater than 0.");
    }

    if (amount > this.getBalance()) {
      throw new AmountException("You did not have sufficient funds for this withdrawal: " + amount);
    }

    else {
      double newBalance = this.getBalance() - amount;
      this.setBalance(newBalance);
      DataSource.updateAccountBalance(id, newBalance);
    }
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public double getBalance() {
    return balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

}
