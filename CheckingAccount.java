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

public class CheckingAccount extends BankAccount {

    private static double overdraftLimit;
    private int menuOpt;

    CheckingAccount() {
        super();
        overdraftLimit = 50d;
    }

    CheckingAccount(double balance, double annualInterestRate, String name) {
        super(balance, annualInterestRate, name);
        overdraftLimit = 50d;
    }

    @Override
    public void withdrawal(double money) {
        if ( money > super.getBalance() + overdraftLimit || money <= 0) {
            display("Overdraft Limit Reached");
        } else {
            super.withdrawal(money);
            
        }
    }

}
