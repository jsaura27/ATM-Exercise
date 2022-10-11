import atm.ATM;

import java.util.Scanner;

import static atm.ATMCommands.*;
import static lib.DataManagement.deSerialize;

public class ATMExec {

    public static void main(String[] args) {
        ATM atm = new ATM();
        String atmFile = "src/main/resources/atm.ser";
        Scanner keyboard = new Scanner(System.in);
        atm = deSerialize(atm, atmFile);
        boolean operating = true;

        selectCurrency(keyboard, atm);

        while (operating) {
            System.out.println("The atm currently have this bills available");
            System.out.println(atm);
            billAmountChecker(atm);
            operating = mainCommands(keyboard, atm, atmFile);
        }
    }
}
