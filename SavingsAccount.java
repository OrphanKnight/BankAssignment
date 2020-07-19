/*--------------------------------------------------------------------

 Name:  Eriel Valdes

 Student ID: 4000099936
 COP 2800 - Java Programming 

 Fall 2018 - T Th 6:15PM - 9:30PM

 Project #2

 Plagiarism Statement

 I certify that this assignment is my own work and that I
 have not copied in part or whole or otherwise plagiarized 
 the work of other students and/or persons.

1234567890123456789012345678901234567890123456789012345678901234567890
--------------------------------------------------------------------*/
package testbankaccounts;

public class SavingsAccount extends BankAccount {

    private String name;
    private double monthlyInterest, accountBalance;

    public SavingsAccount() {
        super();
        monthlyInterest = 0;
        accountBalance = balance;
        System.out.println(accountBalance);
    }

    public SavingsAccount(double balance, double annualInterestRate, String name) {
        super(balance, 0.045, name);
        monthlyInterest = 0;
        accountBalance = super.balance;
    }

    public void addInterest() {
        monthlyInterest += super.getMonthlyInterestRate();
        accountBalance  += monthlyInterest;
        super.setBalance(accountBalance );
    }
}
