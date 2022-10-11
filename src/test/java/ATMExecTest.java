import atm.ATM;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static atm.ATMCommands.checkBalance;
import static atm.ATMCommands.mainCommands;
import static lib.DataManagement.deSerialize;
import static lib.DataManagement.serialize;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

public class ATMExecTest {

    @BeforeAll
    public static void setup() {
        System.out.println("ATMExec Test");
    }

    @AfterAll
    public static void cleanUp() {
        System.out.println("ATMExec Test End");
    }

    @Test
    public void atmExecFullTest() {

        //This is used to test the command prints
        PrintStream mocked = mock(PrintStream.class);
        PrintStream old = System.out;
        System.setOut(mocked);

        //This is used for the input of keyboard by changing the System.in with a custom inputstream
        //It is needed here to break the switch and proceed to test it

        int mainCurrencyInput = 0;
        int[] depositInputs = {5, 5};
        int withdrawalInput = 110;
        int changeCurrencyInput = 2;

        int[] mainMenuSelections = {1, 2, 3, 4, 5, 6};

        String input = mainCurrencyInput + "\n" +   //Select the currency
                mainMenuSelections[0] + "\n" +      //Choose to deposit
                depositInputs[0] + "\n" +           //number of bills of 20
                depositInputs[1] + "\n\n" +         //number of bills of 50
                mainMenuSelections[1] + "\n" +      //Choose to withdraw
                withdrawalInput + "\n\n" +          //Withdraw 110
                mainMenuSelections[2] + "\n" +      //Choose to change currency
                changeCurrencyInput + "\n" +        //Select Japanese yen
                mainMenuSelections[3] + "\n" +      //Choose to check the balance
                mainMenuSelections[4] + "\n" +      //Choose to check the receipt
                mainMenuSelections[5] + "\n";       //Exiting the project

        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        //We initialize atm to 10 to test
        ATM atm = new ATM(10, 10);
        String atmFile = "src/main/resources/atm.ser";
        serialize(atm, atmFile);


        String[] args = null;
        ATMExec.main(args);

        //Here mockito is used to check if all the console prints are as expected
        Mockito.verify(mocked, times(2)).println("Select a currency: \n" +
                " 0.US$ \n" +
                " 1.€ \n" +
                " 2.JP¥ \n" +
                " 3.£ \n" +
                " 4.A$ \n" +
                " 5.CA$ \n" +
                " 6.CHF \n" +
                " 7.HK$ \n" +
                " 8.NZ$ \n ");

        Mockito.verify(mocked, times(6)).println("The atm currently have this bills available");
        Mockito.verify(mocked, times(6)).println("Do you want to:");
        Mockito.verify(mocked, times(6)).println(" 1.Deposit money \n" +
                " 2.Withdraw money\n" +
                " 3.Change currency\n" +
                " 4.Check global balance\n" +
                " 5.Receipt\n" +
                " 6.Exit");

        Mockito.verify(mocked).println("How many " + atm.getCurrentCurrency() + "20 do you want to deposit:");
        Mockito.verify(mocked).println("How many " + atm.getCurrentCurrency() + "50 do you want to deposit:");

        Mockito.verify(mocked).println("How much money would you like to withdraw?");
        Mockito.verify(mocked).println("Thank you for withdrawing money with us");

        Mockito.verify(mocked).println("US Dollar - Total balance : 940");
        Mockito.verify(mocked).println("US$20 : 12");
        Mockito.verify(mocked).println("US$50 : 14");
        Mockito.verify(mocked).println("Euro - Total balance : 700");
        Mockito.verify(mocked).println("€20 : 10");
        Mockito.verify(mocked).println("€50 : 10");
        Mockito.verify(mocked).println("Japanese Yen - Total balance : 700");
        Mockito.verify(mocked).println("JP¥20 : 10");
        Mockito.verify(mocked).println("JP¥50 : 10");
        Mockito.verify(mocked).println("British Pound - Total balance : 700");
        Mockito.verify(mocked).println("£20 : 10");
        Mockito.verify(mocked).println("£50 : 10");
        Mockito.verify(mocked).println("Australian Dollar - Total balance : 700");
        Mockito.verify(mocked).println("A$20 : 10");
        Mockito.verify(mocked).println("A$50 : 10");
        Mockito.verify(mocked).println("Canadian Dollar - Total balance : 700");
        Mockito.verify(mocked).println("CA$20 : 10");
        Mockito.verify(mocked).println("CA$50 : 10");
        Mockito.verify(mocked).println("Swiss Franc - Total balance : 700");
        Mockito.verify(mocked).println("CHF20 : 10");
        Mockito.verify(mocked).println("CHF50 : 10");
        Mockito.verify(mocked).println("Hong Kong Dollar - Total balance : 700");
        Mockito.verify(mocked).println("HK$20 : 10");
        Mockito.verify(mocked).println("HK$50 : 10");
        Mockito.verify(mocked).println("New Zealand Dollar - Total balance : 700");
        Mockito.verify(mocked).println("NZ$20 : 10");
        Mockito.verify(mocked).println("NZ$50 : 10");

        System.setOut(old);
    }
}
