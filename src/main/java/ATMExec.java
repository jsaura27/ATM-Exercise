import atm.ATM;

import java.util.Scanner;

import static atm.ATMCommands.*;
import static lib.DataManagement.deSerialize;

public class ATMExec {

    public static void main(String[] args) {
        ATM atm = new ATM();
        String atmFile = "atm.ser";
        Scanner keyboard = new Scanner(System.in);
        atm = deSerialize(atm, atmFile);
        //atm = new ATM(10,10);
        boolean operating = true;

        System.out.println("Welcome, please select a currency:");
        selectCurrency(keyboard, atm);

        while (operating) {
            System.out.println("The atm currently have this bills available");
            System.out.println(atm);
            billAmountChecker(atm);
            mainCommands(keyboard, atm, atmFile);
        }
    }
}
