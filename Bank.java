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

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JOptionPane;
import static testbankaccounts.BankAccount.display;

/**
 *
 * @author Eriel
 */
public class Bank {

    private boolean answer = false;
    private boolean run = false;

    private int menuOpt, menuOpt2, index, id;
    private int userId = 0;
    private double money;

    private String information, names, password;
    private String userName = " ";
    private char[] intake = new char[4];

    ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();
    BankAccount bank = new BankAccount();
    SavingsAccount savings = new SavingsAccount();
    CheckingAccount check = new CheckingAccount();
    Scanner scanning = new Scanner(System.in);

    public void menu() {
        accounts.add(new BankAccount(999, 0.045, "Goldie"));
        accounts.add(new BankAccount(9882, 0.045, "Fish"));
        accounts.add(new SavingsAccount(229, 0.045, "Katie"));
        accounts.add(new SavingsAccount(239, 0.045, "Mike"));
        accounts.add(new SavingsAccount(289, 0.045, "Tatie"));
        accounts.add(new CheckingAccount(289, 0.045, "Mikey"));
        accounts.add(new CheckingAccount(289, 0.045, "Harry"));
        accounts.add(new CheckingAccount(289, 0.045, "Potter"));

        for (index = 0; index < accounts.size(); index++) {
            display(accounts.get(index));
        }

        while (!run) {
            Object[] possibleValues = {"Create new Account", "Deposit", "Withdraw", "Display Balance", "Exit"};

            Object selectedValue = JOptionPane.showInputDialog(null,
                    "Please Choose an Action:", "Input",
                    JOptionPane.INFORMATION_MESSAGE,
                    null, possibleValues,
                    possibleValues[0]);

//            display(selectedValue.toString().equals("Deposit"));
//            display(selectedValue.toString());
            if (selectedValue != null) {
                if (selectedValue.toString().equals("Create new Account")) {

                    Object[] possibleOptions = {"Bank Account", "Savings Account", "Checking Account"};

                    Object selectedOption = JOptionPane.showInputDialog(null,
                            "Please Choose the Account Type:", "Input",
                            JOptionPane.INFORMATION_MESSAGE,
                            null, possibleOptions,
                            possibleOptions[0]);

                    if (selectedOption != null) {
                        if (selectedOption.toString().equals("Bank Account")) {
                            bank.setAccntType("Bank Account");
                            money = initialBal();
                            information = naming();
                            bank.setName(information);
                            bank.setBalance(money);
                            accounts.add(bank);
                            display(bank + "\nPassword: " + bank.getPassword() + "\nID: " + bank.getId()); //display in terminal to for debugging/developer use
                            JOptionPane.showMessageDialog(null, bank + "\nPassword: " + bank.getPassword() + "\nID: " + bank.getId());
                        }
                        if (selectedOption.toString().equals("Savings Account")) {
                            bank.setAccntType("Savings Account");
                            money = initialBal();
                            information = naming();
                            savings.setName(information);
                            savings.setBalance(money);
                            accounts.add(savings);
                            display(bank + "\nPassword: " + savings.getPassword() + "\nID: " + savings.getId());
                            JOptionPane.showMessageDialog(null, savings + "\nPassword: " + savings.getPassword() + "\nID: " + savings.getId());
                        }
                        if (selectedOption.toString().equals("Checking Account")) {
                            bank.setAccntType("Checking Account");
                            money = initialBal();
                            information = naming();
                            check.setName(information);
                            check.setBalance(money);
                            accounts.add(check);
                            display(bank + "\nPassword: " + check.getPassword() + "\nID: " + check.getId());
                            JOptionPane.showMessageDialog(null, check + "\nPassword: " + check.getPassword() + "\nID: " + check.getId());
                        }
                    }
                }
                if (selectedValue.toString().equals("Deposit")) {
                    accountVerification(accounts, bank, selectedValue);
                }
                if (selectedValue.toString().equals("Withdraw")) {
                    accountVerification(accounts, bank, selectedValue);
                }
                if (selectedValue.toString().equals("Display Balance")) {
                    accountVerification(accounts, bank, selectedValue);
                }
                if (selectedValue.toString().equals("Exit")) {
                    run = exitConf(answer);
                }
            }
        }
    }

    public Double initialBal() {
        answer = false;

        while (!answer) {
            String information = JOptionPane.showInputDialog("Enter an Initial Balance");
            display(information);
            if (information != null) {
                try {
                    money = Double.parseDouble(information);
                    if (money < 100) {
                        JOptionPane.showMessageDialog(null, "Initial Blance has to be 100 or more");
                    } else if (money > 99) {
                        answer = true;
                    }
                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(null, "Just use numbers");
                }
            } else {
                answer = true;
            }
        }
        return money;
    }

    public String naming() {
        answer = false;
        while (!answer) {
            information = JOptionPane.showInputDialog("Enter name");
            display(information);
            if (information != null) {
                try {
                    if (information.matches("[a-zA-Z]+")) { //[a-zA-Z] matches the string input to only contain letters and the + compares the next char to be a letter
                        answer = true;
                    } else {
                        JOptionPane.showMessageDialog(null, "Input letters only");
                    }
                } catch (NullPointerException nullPointerException) {
                    answer = true;
                }
            } else {
                answer = true;
            }
        }
        return information;
    }

    public void accountVerification(ArrayList<BankAccount> accounts, BankAccount bank, Object selectedValue) {
        boolean answer = false;
        while (!answer) {
            String information = JOptionPane.showInputDialog("Enter ID");
            display(information);
            String pass = JOptionPane.showInputDialog("Enter password");
            display(pass);

            if (information != null) {
                try {

                    if (information.matches("\\d+")) { //  \\d+ matches the string input to only contain number 0-9 and the + means more than one regex is going to be matched
                        if (pass.matches("[a-zA-Z]+")) { //[a-zA-Z] matches the string input to only contain letters and the + compares the next char to be a letter
                            id = Integer.parseInt(information);
                            for (index = 0; index < accounts.size(); index++) { // searches through all accounts to get a valid combination
                                if (accounts.get(index).getId() == id && accounts.get(index).getPassword().equals(pass)) {
                                    display(accounts.get(index));
                                    bankV(index, accounts, bank, selectedValue);
                                    answer = !false;
                                }
                            }

                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Make sure to only input the correct inputs for ID and password \n   ID is numbers \n   Password is letters");
                    }

                } catch (NullPointerException nullPointerException) {
                    answer = true;
                }
            } else {
                answer = true;
            }

        }
    }

    public void bankV(int index, ArrayList<BankAccount> accounts, BankAccount bank, Object selectedValue) {
        update(index, accounts, bank, selectedValue);
        if (selectedValue.toString().equals("Deposit")) {

            String information = JOptionPane.showInputDialog("Deposit Amount");
            display(information);
            double money = Double.parseDouble(information);
            if (information != null) {
                try {
                    if (information.matches("\\d+")) { //[a-zA-Z] matches the string input to only contain letters and the + compares the next char to be a letter

                        accounts.get(index).deposit(money);
                        display(accounts.get(index));
                    } else {
                        JOptionPane.showMessageDialog(null, "Input letters only");
                    }
                } catch (NullPointerException nullPointerException) {
                }
            }
        }

        if (selectedValue.toString().equals("Withdraw")) {
//            a.	Display an input dialog(s) asking for the amount to withdraw and the accountId and password for the account from where the money will be withdrawn. 
//            b.	Verify the count has enough money to satisfy the transaction. Show an error message if it doesn’t.
//            c.	Subtract the withdraw amount from the appropriate account. 
//            d.	Redisplay the main menu.
            String information = JOptionPane.showInputDialog("Withdrawal Amount");
            display(information);
            double money = Double.parseDouble(information);
            if (information != null) {
                try {
                    if (information.matches("\\d+")) { //[a-zA-Z] matches the string input to only contain letters and the + compares the next char to be a letter
                        accounts.get(index).withdrawal(money);
                        display(accounts.get(index));
                    } else {
                        JOptionPane.showMessageDialog(null, "Input letters only");
                    }
                } catch (NullPointerException nullPointerException) {
                }
            }
        }
        if (selectedValue.toString().equals("Display Balance")) {
            display(accounts.get(index));
            JOptionPane.showMessageDialog(null, accounts.get(index));
        }

    }

    public void update(int index, ArrayList<BankAccount> accounts, BankAccount bank, Object selectedValue) {

        for (index = 0; index < accounts.size(); index++) {

            if (accounts.get(index) instanceof SavingsAccount) {
                accounts.get(index).setAccntType("Savings Account");

            } else if (accounts.get(index) instanceof CheckingAccount) {
                accounts.get(index).setAccntType("Checking Account");
            } else {
                accounts.get(index).setAccntType("Bank Account");
            }
        }
    }

    public static boolean exitConf(boolean run) {
        Object[] options = {"Exit", "CANCEL"};

        int switchO = JOptionPane.showOptionDialog(null, "You are about to exit the program!", "Warning",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
                options, options[0]);
        display(switchO);
        if (switchO == 1) {
            run = false;
        } else if (switchO == -1) {
            run = true;
        } else if (switchO == 0) {
            run = true;
        }
        return run;
    }

//        
//        for (int index = 0; index < accounts.size(); index++) {
//            display(accounts.get(index));
//        }
//        
//        display("Are you a returning user?");
//        display("        1. Yes");
//        display("        2. No");
//        information = scanning.nextLine();
//        if (information.equals("yes")) {
//            display("What account you want to access:");
//            display("        1. Regular");
//            display("        2. Savings");
//            display("        3. Checking");
//            information = scanning.nextLine();
//            information = information.toLowerCase();
//
//            if (information.equals("regular") || information.equals("1")) {
//                regularAcc();
//
//            } else if (information.equals("savings") || information.equals("2")) {
//                savingAcc();
//
//            } else if (information.equals("checking") || information.equals("3")) {
//                checkAcc();
//
//            } else {
//                tryAgain();
//                menu();
//
//            }
//        } else if (information.equals("no")) {
//            openMenu();
//        } else {
//            menu();
//        }    
//    public void regularAcc() {
//        run = true;
//        for (int index = 0; index < accounts.size(); index++) {
//            display(accounts.get(index));
//        }
//        do {
//            display("Enter Name");
//            if (scanning.hasNextLine()) { //Checks if input string is a string and if not it will repeate the message
//                userName = scanning.nextLine();
//                userName = userName.toLowerCase(); //lowercase the message to match the name in file
//                for (int index = 0; index < accounts.size(); index++) {
//                    if (accounts.get(index).getName().equals(userName)) { //validates that account exists
//                        display("Enter ID");
//                        if (scanning.hasNextInt()) {
//                            userId = scanning.nextInt(); //Checks if input string is a integer and if not it will repeate the message
//                            for (int i = 0; i < accounts.size(); i++) {
//                                if (accounts.get(i).getId() == userId) { // validates that id exists
//                                    run = false; //Exits the loop
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        } while (run == true);
//
//        for (int index = 0; index < accounts.size(); index++) { //goes through loop to validate the account
//
//            if ((accounts.get(index).getName().equals(userName)) && (accounts.get(index).getId() == userId) && (accounts.get(index) instanceof BankAccount)) {
//                display(accounts.get(index).toString()); //displays BankAccount
//            }
//        }
//    }
//
//    public void savingAcc() {
//        run = true;
//        for (int index = 0; index < accounts.size(); index++) {
//            display(accounts.get(index));
//        }
//        do {
//            display("Enter Name");
//            if (scanning.hasNextLine()) { //Checks if input string is a string and if not it will repeate the message
//                userName = scanning.nextLine();
//                userName = userName.toLowerCase(); //lowercase the message to match the name in file
//                for (int index = 0; index < accounts.size(); index++) {
//                    if (accounts.get(index).getName().equals(userName)) { //validates that account exists
//                        display("Enter ID");
//                        if (scanning.hasNextInt()) {
//                            userId = scanning.nextInt(); //Checks if input string is a integer and if not it will repeate the message
//                            for (int i = 0; i < accounts.size(); i++) {
//                                if (accounts.get(i).getId() == userId) { // validates that id exists
//                                    run = false; //Exits the loop
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        } while (run == true);
//
//        for (int index = 0; index < accounts.size(); index++) { //goes through loop to validate the account
//
//            if ((accounts.get(index).getName().equals(userName)) && (accounts.get(index).getId() == userId) && (accounts.get(index) instanceof SavingsAccount)) {
//                ((SavingsAccount) accounts.get(index)).addInterest();
//                display("New Balance" + accounts.get(index).getBalance());//displays savings
//            }
//        }
//    }
//
//    public void checkAcc() {
//        run = true;
//        for (int index = 0; index < accounts.size(); index++) {
//            display(accounts.get(index));
//        }
//        do {
//            display("Enter Name");
//            if (scanning.hasNextLine()) { //Checks if input string is a string and if not it will repeate the message
//                userName = scanning.nextLine();
//                userName = userName.toLowerCase(); //lowercase the message to match the name in file
//                for (int index = 0; index < accounts.size(); index++) {
//                    if (accounts.get(index).getName().equals(userName)) { //validates that account exists
//                        display("Enter ID");
//                        if (scanning.hasNextInt()) {
//                            userId = scanning.nextInt(); //Checks if input string is a integer and if not it will repeate the message
//                            for (int i = 0; i < accounts.size(); i++) {
//                                if (accounts.get(i).getId() == userId) { // validates that id exists
//                                    run = false; //Exits the loop
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        } while (run == true);
//        run = true;
//        for (int index = 0; index < accounts.size(); index++) { //goes through loop to validate the account
//
//            if ((accounts.get(index).getName().equals(userName)) && (accounts.get(index).getId() == userId) && (accounts.get(index) instanceof CheckingAccount)) {
//                display("Enter how much money you want to withdraw: ");
//                if (scanning.hasNextDouble()) {
//                    money = scanning.nextDouble(); //Checks if input string is a integer and if not it will repeate the message
//                    ((CheckingAccount) accounts.get(index)).withdrawal(money);
//                }
//                display("New balnce" + accounts.get(index).getBalance());//displays savings
//            }
//        }
//    }
//
//    public void openMenu() {
//        display("Do you want to create an account?");
//        display("        Yes or No");
//        information = scanning.nextLine();
//        if (information.equals("yes")) {
//            openAccount();
//        } else if (information.equals("no")) {
//            closeMenu();
//        } else {
//            openMenu();
//        }
//    }
//
//    public void closeMenu() {
//        display("Do you want to Remove an account?");
//        display("        Yes or No");
//        information = scanning.nextLine();
//        if (information.equals("yes")) {
//            closeAccount();
//        } else if (information.equals("no")) {
//            System.exit(0);
//        } else {
//            closeMenu();
//        }
//    }
//
//    public void update() {
//
//        for (int index = 0; index < accounts.size(); index++) {
//            if ((accounts.get(index) instanceof BankAccount)) {
//                display(((BankAccount) accounts.get(index)).toString());
//            } else if (accounts.get(index) instanceof SavingsAccount) {
//                display(((SavingsAccount) accounts.get(index)).toString());
//            } else if (accounts.get(index) instanceof CheckingAccount) {
//                display(((CheckingAccount) accounts.get(index)).toString());
//            }
//        }
////    }
//
//    public void openAccount() {
//        display("What account you want to add:");
//        display("        1. Regular");
//        display("        2. Savings");
//        display("        3. Checking");
//        information = scanning.nextLine();
//        information = information.toLowerCase();
//        if (information.equals("regular") || information.equals("1")) {
//            display("Enter the first name you want to add: ");
//            names = scanning.nextLine();
//            names = names.toLowerCase();
//            bank.setName(names);
//
//            do {
//                display("Enter number to diposit");
//                money = scanning.nextDouble();
//                if (money > 100) {
//                    bank.deposit(money);
//                    accounts.add(bank);
//                    display(bank);
//                    run = false;
//                } else {
//                    display("You cannot have an initial balance less than 100");
//                    display(" ");
//                }
//            } while (run == true);
//
//        } else if (information.equals("savings") || information.equals("2")) {
//            display("Enter the first name you want to add: ");
//            names = scanning.nextLine();
//            names = names.toLowerCase();
//            bank.setName(names);
//
//            do {
//                display("Enter number to diposit");
//                money = scanning.nextDouble();
//                if (money > 100) {
//                    bank.deposit(money);
//                    accounts.add(savings);
//                    display(bank);
//                    run = false;
//                } else {
//                    display("You cannot have an initial balance less than 100");
//                    display(" ");
//                }
//            } while (run == true);
//
//        } else if (information.equals("checking") || information.equals("3")) {
//            display("Enter the first name you want to add: ");
//            names = scanning.nextLine();
//            names = names.toLowerCase();
//            bank.setName(names);
//            do {
//                display("Enter number to diposit");
//                money = scanning.nextDouble();
//                if (money > 100) {
//                    bank.deposit(money);
//                    accounts.add(check);
//                    display(bank);
//                    run = false;
//                } else {
//                    display("You cannot have an initial balance less than 100");
//                    display(" ");
//                }
//            } while (run == true);
//        } else {
//            tryAgain();
//            openAccount();
//        }
//        display(accounts.size());
//    }
//
//    public void closeAccount() {
//        while (run == true) {
//            display("Enter Name");
//            if (scanning.hasNextLine()) {
//                userName = scanning.nextLine();
//                for (int index = 0; index < accounts.size(); index++) {
//                    if (accounts.get(index).getName().equals(userName)) {
//                        run = false;
//                    }
//                }
//            }
//
//            run = true;
//            while (run == true) {
//                display("Enter ID");
//                if (scanning.hasNextInt()) {
//                    userId = scanning.nextInt();
//                    for (int index = 0; index < accounts.size(); index++) {
//                        if (accounts.get(index).getId() == userId) {
//                            run = false;
//                        }
//                    }
//                }
//            }
//
//            for (int index = 0; index < accounts.size(); index++) {
//
//                if ((accounts.get(index).getName().equals(userName)) && (accounts.get(index).getId() == userId)) {
//                    accounts.remove(index);
//                }
//            }
//        }
//    }
//
//    public void tryAgain() {
//        display("Invalid INPUT");
//        display("Valid Inputs are number or the name next to the number.");
//        display(" ");
//    }
}
