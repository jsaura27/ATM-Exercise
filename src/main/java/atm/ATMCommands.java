package atm;

import java.util.Currency;
import java.util.Scanner;

import static java.lang.System.exit;
import static lib.DataManagement.serialize;

public class ATMCommands {

    //Main option menu
    public static void mainCommands(Scanner keyboard, ATM atm, String atmFile) {

        System.out.println("Do you want to:");
        System.out.println(" 1.Deposit money \n" +
                " 2.Withdraw money\n" +
                " 3.Change currency\n" +
                " 4.Check global balance\n" +
                " 5.Receipt\n" +
                " 6.Exit");

        switch (keyboard.nextInt()) {

            case 1:
                deposit(keyboard, atm);
                keyboard.nextLine();
                break;
            case 2:
                withdraw(keyboard, atm);
                billAmountChecker(atm);
                keyboard.nextLine();
                break;
            case 3:
                keyboard.nextLine();
                selectCurrency(keyboard, atm);
                break;
            case 4:
                checkBalance(atm);
                break;
            case 5:
                System.out.println(atm.getReceipt());
                break;
            case 6:
                atm.setReceipt("");
                serialize(atm, atmFile);
                exit(0);
                break;
        }
    }

    //Method to select a different currency
    public static void selectCurrency(Scanner keyboard, ATM atm) {
        System.out.println(atm.printCurrencies());
        int input = keyboard.nextInt();
        keyboard.nextLine();
        atm.setCurrentCurrency(atm.getCurrencyCode(input));
    }

    //Method to deposit money in the ATM
    public static void deposit(Scanner keyboard, ATM atm) {
        System.out.println("How many " + atm.getCurrentCurrency() + "20 do you want to deposit:");
        int noBills = keyboard.nextInt();
        if (noBills >= 0) {
            atm.addBills20(noBills);
        } else {
            System.out.println("Trying to be smart? Deducting all the money from your account..... Nah, just messing");
        }
        System.out.println("How many " + atm.getCurrentCurrency() + "50 do you want to deposit:");
        noBills = keyboard.nextInt();
        if (noBills >= 0) {
            atm.addBills50(noBills);
        } else {
            System.out.println("Trying to be smart? Deducting all the money from your account..... Nah, just messing");
        }
        System.out.println("Thank you for your deposit");
    }

    //Method to withdraw money from the ATM
    public static void withdraw(Scanner keyboard, ATM atm) {
        System.out.println("How much money would you like to withdraw?");
        int amount = keyboard.nextInt();

        //Consideration to leave options open
        if (amount % 50 == 0 && amount % 20 == 0) {
            int noBills50 = amount / 50;
            int noBills20 = amount / 20;
            if (noBills50 <= atm.getBills50() && noBills20 <= atm.getBills20()) {
                if (noBills20 > noBills50 * 2) {
                    atm.subtractBills20(noBills20);
                    atm.addToReceipt("Withdrawing " + atm.getCurrentCurrency() + amount);
                    atm.addToReceipt(20, noBills20);
                } else {
                    atm.subtractBills50(noBills50);
                    atm.addToReceipt("Withdrawing " + atm.getCurrentCurrency() + amount);
                    atm.addToReceipt(50, noBills50);
                }
            }else if(noBills50 <= atm.getBills50()){
                atm.subtractBills50(noBills50);
                atm.addToReceipt("Withdrawing " + atm.getCurrentCurrency() + amount);
                atm.addToReceipt(50, noBills50);
            }else if(noBills20 <= atm.getBills20()){
                atm.subtractBills20(noBills20);
                atm.addToReceipt("Withdrawing " + atm.getCurrentCurrency() + amount);
                atm.addToReceipt(20, noBills20);
            } else {
                multipleBills(amount, atm);
            }
            System.out.println("Thank you for withdrawing money with us");
            //Perfect amount for bills of 50
        } else if (amount % 50 == 0) {
            int noBills50 = amount / 50;
            if (noBills50 <= atm.getBills50()) {
                atm.subtractBills50(noBills50);
                atm.addToReceipt("Withdrawing " + atm.getCurrentCurrency() + amount);
                atm.addToReceipt(50, noBills50);
            } else {
                multipleBills(amount, atm);
            }
            System.out.println("Thank you for withdrawing money with us");
        //Perfect for bills of 20
        } else if (amount % 20 == 0) {
            int noBills20 = amount / 20;
            if (noBills20 <= atm.getBills20()) {
                atm.subtractBills20(noBills20);
                atm.addToReceipt("Withdrawing " + atm.getCurrentCurrency() + amount);
                atm.addToReceipt(20, noBills20);
            } else {
                multipleBills(amount, atm);
            }
            System.out.println("Thank you for withdrawing money with us");
        } else {
            multipleBills(amount, atm);
            System.out.println("Thank you for withdrawing money with us");
        }
    }

    //Method to split the amount on different kind of bills
    public static void multipleBills(int amount, ATM atm) {
        boolean enoughBills = true;
        boolean possible = false;
        int counter = 0;
        while (enoughBills && !possible) {
            int atmNoBills = atm.getBills50() - counter;
            if (atmNoBills == 0) {
                enoughBills = false;
                System.out.println("Sorry we can't provide that amount \n");
            } else {
                int amount2 = amount - atmNoBills * 50;
                if (amount2 % 20 == 0 && amount2 > 0) {
                    int noBills20 = amount2 / 20;
                    if (noBills20 <= atm.getBills20()) {
                        atm.subtractBills20(noBills20);
                        atm.addToReceipt("Withdrawing " + atm.getCurrentCurrency() + amount);
                        atm.addToReceipt(20, noBills20);
                        atm.subtractBills50(atmNoBills);
                        atm.addToReceipt(50, atmNoBills);
                        possible = true;
                    } else {
                        enoughBills = false;
                        System.out.println("Sorry we can't provide that amount \n");
                    }
                } else {
                    counter++;
                }
            }
        }
    }

    //Method to warn that the atm is running out of bills
    public static void billAmountChecker(ATM atm) {
        if (atm.getBills50() < 3) {
            System.out.println("We highly recommend to deposit some " + atm.getCurrentCurrency() + "50 bills, since there is only " + atm.getBills50() + " left");
        }
        if (atm.getBills20() < 3) {
            System.out.println("We highly recommend to deposit some " + atm.getCurrentCurrency() + "20 bills, since there is only " + atm.getBills20() + " left");
        }
    }

    //Method to show the user the amount of bills of each currency
    public static void checkBalance(ATM atm) {
        //I wanted to add a lambda since they are quite comfortable to go through maps,
        //but if I want things a bit ordered I am afraid it is not an option
        //atm.getAmountOfEachBill().entrySet().forEach(entry -> {
        //    System.out.println(entry.getKey() + " : " + entry.getValue());
        //});
        //So for the shake of ordering a for will do
        for (Currency c : atm.getCurrencies()) {
            String symbol20 = c.getSymbol() + 20;
            String symbol50 = c.getSymbol() + 50;
            int bills20 = atm.getAmountOfEachBill().get(symbol20);
            int bills50 = atm.getAmountOfEachBill().get(symbol50);
            int total = bills20 * 20 + bills50 * 50;
            System.out.println(c.getDisplayName() + " - Total balance : " + total);

            System.out.println(symbol20 + " : " + bills20);
            System.out.println(symbol50 + " : " + bills50);
        }
    }
}
