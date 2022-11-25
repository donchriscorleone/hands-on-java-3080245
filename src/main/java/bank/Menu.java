package bank;

import java.util.Scanner;

import javax.security.auth.login.LoginException;

public class Menu {
  private Scanner scanner;

  public static void main(String[] args) {
    System.out.println("Welcome to Globe Bank International!");

    Menu menu = new Menu();
    menu.scanner = new Scanner(System.in);

    Customer customer = menu.autheticateUser();

    if (customer != null) {
      Account account = DataSource.getAccount(customer.getAccountId());
      menu.showMenu(customer, account);
    }

    menu.scanner.close();
  }

  private Customer autheticateUser() {
    System.out.println("Please enter your username: ");
    String username = this.scanner.next();

    System.out.println("Please enter your password: ");
    String password = this.scanner.next();

    Customer customer = null;
    try {
      customer = Authenticator.login(username, password);
    } catch (LoginException e) {
      e.printStackTrace();
      System.out.println("There was an error: " + e.getMessage());
    }

    return customer;
  }

  private void showMenu(Customer customer, Account account) {
    int selection = 0;
    
    while (selection != 4 && customer.isAuthenticated()) {
      System.out.println("===========================================");
      System.out.println("Please select one of the following options: ");
      System.out.println("1 Deposit");
      System.out.println("2 Withdraw");
      System.out.println("3 Check Balance");
      System.out.println("4 Exit");
      System.out.println("===========================================");
      
      selection = this.scanner.nextInt();
      double amount = 0;
      switch(selection) {
        case 1:
          System.out.println("How much would you like to deposit?");
          amount = this.scanner.nextDouble();
          account.deposit(amount);
          break;

        case 2:
          System.out.println("How much would you like to deposit?");
          amount = this.scanner.nextDouble();
          account.withdraw(amount);
          break;

        case 3:
          System.out.println("Current Balance: " + account.getBalance());
          break;

        case 4:
          Authenticator.logout(customer);
          System.out.println("Thanks for banking at Globe Bank International");
          break;

        default:
          System.out.println("Please try again.");
          break;
      }
    }
  }
}
