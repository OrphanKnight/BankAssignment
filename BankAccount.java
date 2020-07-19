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

import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class BankAccount {

    private String name, password, accountId, generatorChar, accntType;
    private char[] intake = new char[4];;
    
    private int id, numberOfDeposits, numberOfWithdrawls;
    private double monthlyInterestRate;
    private double overdraftLimit;
    
    private LocalDate dateCreated;
    private double annualInterestRate;
    protected double balance, money;

    BankAccount() {
        Random random = new Random();
        accntType = "";
        accountId = "";
        password =  "";
        name = "";
        
        balance = 0;
        numberOfDeposits = 0;
        numberOfWithdrawls = 0;
        
        overdraftLimit = 50.0;
        annualInterestRate = 4.5;
        
        id = random.nextInt(99999) + 1;
        this.dateCreated = LocalDate.now();
        
        for (int i = 0; i < 4; i++) {
            int result = ThreadLocalRandom.current().nextInt(97, 122 + 1);
            intake[i] = (char) result;
            password += (char) intake[i];
        }
    }

    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public BankAccount(double balance, double annualInterestRate, String name) {
        for (int i = 0; i < 4; i++) {
            int result = ThreadLocalRandom.current().nextInt(97, 122 + 1);
            intake[i] = (char) result;
            password += (char) intake[i];
        }
        
        Random random = new Random();
        annualInterestRate = 0.045;
        
        this.annualInterestRate = annualInterestRate;
        this.balance = balance;
        this.id = random.nextInt(99999) + 1;        
        this.name = name.toLowerCase();
        password = "bimi";
        dateCreated = LocalDate.now();        
    }
    
        public String getAccntType() {
        return accntType;
    }

    public void setAccntType(String accntType) {
        this.accntType = accntType;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setBalance(double accountBalance) {
        this.balance = accountBalance;
    }
    public double getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getNumberOfDeposits() {
        return numberOfDeposits;
    }

    public int getNumberOfWithdrawls() {
        return numberOfWithdrawls;
    }

    public double getMonthlyInterestRate() {
        return getMonthlyInterest() * balance;
    }

    public double getMonthlyInterest() {
        return annualInterestRate / 12;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void withdrawal(double money) {
        if (money > 0) {
            if(money <= balance){
            this.balance = balance - money;
            numberOfWithdrawls++;
            }
        }
    }

    public void deposit(double money) {
        if (money > 0) {
            balance = balance + money;
            numberOfDeposits++;
        } else {
            display("You cant enter a number less than 100");
        }
    }
    
    public void initialDeposit(double money) {
        if (money > 99) {
            balance = balance + money;
        } else {
            display("You cant enter a number less than 100");
        }
    }

    @Override
    public String toString() {
        return    "Name: " + getName()
                + "\nAccount Type: " + accntType
                + "\nBalance: $" + balance
                + "\nAccount ID: " + getId()
                + "\nMonthly Interest Rate: " + getMonthlyInterest() * 100
                + "%\nMonthly Interest: $" + getMonthlyInterestRate()
                + "\nNumber of Withdrawals: " + numberOfWithdrawls
                + "\nNumber of Deposits: " + numberOfDeposits
                + "\nCreated On: " + getDateCreated();
    }

    public static void display(Object line) {
        System.out.println(line);
    }
}
