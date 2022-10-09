package atm;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static atm.ATMCommands.mainCommands;
import static atm.ATMCommands.withdraw;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ATMCommandsTest {

    @BeforeAll
    public static void setup() {
        System.out.println("Junit Tests for ATM Commands");
    }

    @Test
    public void mainCommandsTest() {

        //This is used to test the command prints
        PrintStream mocked = mock(PrintStream.class);
        PrintStream old = System.out;
        System.setOut(mocked);

        //This is used for the input of keyboard by changing the System.in with a custom inputstream
        //It is needed here to break the switch and proceed to test it
        String input = "5";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ATM atm = new ATM(10, 10);
        String atmFile = "atm.ser";
        Scanner keyboard = new Scanner(System.in);

        mainCommands(keyboard, atm, atmFile);

        Mockito.verify(mocked).println("Do you want to:");
        Mockito.verify(mocked).println(" 1.Deposit money \n" +
                " 2.Withdraw money\n" +
                " 3.Change currency\n" +
                " 4.Check global balance\n" +
                " 5.Receipt\n" +
                " 6.Exit");

        System.setOut(old);
    }

    @Test
    public void workingWithdrawTest() {

        //This is used to test the command prints
        PrintStream mocked = mock(PrintStream.class);
        PrintStream old = System.out;
        System.setOut(mocked);

        //This is used for the input of keyboard by changing the System.in with a custom inputstream
        //It is needed here to break the switch and proceed to test it
        String input = "20";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ATM atm = new ATM(10, 10);
        atm.setCurrentCurrency("US$");
        Scanner keyboard = new Scanner(System.in);

        withdraw(keyboard, atm);

        //Asserting the command prints are what we expect
        Mockito.verify(mocked).println("How much money would you like to withdraw?");
        Mockito.verify(mocked).println("Thank you for withdrawing money with us");

        //Asserting that the money has been taken and that the receipt updated
        assertEquals(9, atm.getBills20());
        assertEquals("nullWithdrawing US$20\n" +
                "US$20 : 1\n", atm.getReceipt());
        System.setOut(old);


    }

    @Test
    public void multipleEscenariosWithdrawTest() {

        //This is used to test the command prints
        PrintStream mocked = mock(PrintStream.class);
        PrintStream old = System.out;
        System.setOut(mocked);

        int[] withdrawals = {20, 40, 50, 60, 70, 80, 100, 110, 150, 170, 200};

        for (int cash : withdrawals) {
            //This is used for the input of keyboard by changing the System.in with a custom inputstream
            //It is needed here to break the switch and proceed to test it
            String input = Integer.toString(cash);
            InputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);

            ATM atm = new ATM(10, 10);
            atm.setCurrentCurrency("US$");
            Scanner keyboard = new Scanner(System.in);

            withdraw(keyboard, atm);

            //Asserting the command prints are what we expect
            Mockito.verify(mocked).println("How much money would you like to withdraw?");
            Mockito.verify(mocked).println("Thank you for withdrawing money with us");

            //I check here if it is not just null, since if any withdrawal has been made
            //the receipt will have updated
            assertNotEquals("null", atm.getReceipt());

            System.setOut(old);
        }


    }
}
