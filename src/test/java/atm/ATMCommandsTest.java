package atm;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Currency;
import java.util.Scanner;

import static atm.ATMCommands.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


public class ATMCommandsTest {

    @BeforeAll
    public static void setup() {
        System.out.println("Junit Tests for ATM Commands");
    }

    @AfterAll
    public static void cleanUp() {
        System.out.println("Junit Tests for ATM Commands End");
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

    @Test
    public void multipleEscenariosNotPossibleWithdrawTest() {

        //This is used to test the command prints
        PrintStream mocked = mock(PrintStream.class);
        PrintStream old = System.out;
        System.setOut(mocked);

        int[] withdrawals = {25, 30, 93, 134, 232, 10000};

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
            Mockito.verify(mocked).println("Sorry we can't provide that amount \n");

            //I check here if it is not just null, since if any withdrawal has been made
            //the receipt will have updated
            assertNotEquals("null", atm.getReceipt());

            System.setOut(old);
        }
    }

    @Test
    public void multipleEscenariosBigAmountWithdrawTest() {

        //This is used to test the command prints
        PrintStream mocked = mock(PrintStream.class);
        PrintStream old = System.out;
        System.setOut(mocked);

        int[] withdrawals = {200, 420, 550, 670, 700, 840, 1000};

        for (int cash : withdrawals) {
            //This is used for the input of keyboard by changing the System.in with a custom inputstream
            //It is needed here to break the switch and proceed to test it
            String input = Integer.toString(cash);
            InputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);

            ATM atm = new ATM(50, 50);
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

    @Test
    public void withdraw200Test() {
        //This is used to test the command prints
        PrintStream mocked = mock(PrintStream.class);
        PrintStream old = System.out;
        System.setOut(mocked);

        int amount = 200;

        //This is used for the input of keyboard by changing the System.in with a custom inputstream
        //It is needed here to break the switch and proceed to test it
        String input = Integer.toString(amount);
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ATM atm = new ATM(8, 3);
        atm.setCurrentCurrency("US$");
        Scanner keyboard = new Scanner(System.in);

        withdraw(keyboard, atm);

        //Asserting the command prints are what we expect
        Mockito.verify(mocked).println("How much money would you like to withdraw?");
        Mockito.verify(mocked).println("Thank you for withdrawing money with us");

        //I check here if it is not just null, since if any withdrawal has been made
        //the receipt will have updated
        assertNotEquals("null", atm.getReceipt());
        assertEquals(1, atm.getBills50());
        assertEquals(3, atm.getBills20());

        System.setOut(old);

    }

    @Test
    public void withdraw100OptionOpenTest() {
        //This is used to test the command prints
        PrintStream mocked = mock(PrintStream.class);
        PrintStream old = System.out;
        System.setOut(mocked);

        int amount = 100;

        //This is used for the input of keyboard by changing the System.in with a custom inputstream
        //It is needed here to break the switch and proceed to test it
        String input = Integer.toString(amount);
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ATM atm = new ATM(5, 1);
        atm.setCurrentCurrency("US$");
        Scanner keyboard = new Scanner(System.in);

        withdraw(keyboard, atm);

        //Asserting the command prints are what we expect
        Mockito.verify(mocked).println("How much money would you like to withdraw?");
        Mockito.verify(mocked).println("Thank you for withdrawing money with us");

        //I check here if it is not just null, since if any withdrawal has been made
        //the receipt will have updated
        assertNotEquals("null", atm.getReceipt());
        assertEquals(1, atm.getBills50());
        assertEquals(0, atm.getBills20());

        System.setOut(old);

    }


    @Test
    public void depositSmallAmountTest() {
        //This is used to test the command prints
        PrintStream mocked = mock(PrintStream.class);
        PrintStream old = System.out;
        System.setOut(mocked);

        int[] deposit = {5, 5};

        //Here the input has to be divided in multiple lines to take each as a separate input
        String input = deposit[0] + "\n" + deposit[1];
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ATM atm = new ATM(10, 10);
        atm.setCurrentCurrency("US$");
        Scanner keyboard = new Scanner(System.in);

        deposit(keyboard, atm);

        //Asserting the command prints are what we expect
        Mockito.verify(mocked).println("How many " + atm.getCurrentCurrency() + "20 do you want to deposit:");
        Mockito.verify(mocked).println("How many " + atm.getCurrentCurrency() + "50 do you want to deposit:");

        assertEquals(15, atm.getBills50());
        assertEquals(15, atm.getBills20());

        System.setOut(old);

    }

    @Test
    public void depositBigAmountTest() {
        //This is used to test the command prints
        PrintStream mocked = mock(PrintStream.class);
        PrintStream old = System.out;
        System.setOut(mocked);

        int[] deposit = {50, 50};

        //Here the input has to be divided in multiple lines to take each as a separate input
        String input = deposit[0] + "\n" + deposit[1];
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ATM atm = new ATM(10, 10);
        atm.setCurrentCurrency("US$");
        Scanner keyboard = new Scanner(System.in);

        deposit(keyboard, atm);

        //Asserting the command prints are what we expect
        Mockito.verify(mocked).println("How many " + atm.getCurrentCurrency() + "20 do you want to deposit:");
        Mockito.verify(mocked).println("How many " + atm.getCurrentCurrency() + "50 do you want to deposit:");

        assertEquals(60, atm.getBills50());
        assertEquals(60, atm.getBills20());

        System.setOut(old);

    }

    @Test
    public void billAmountChecketTest() {
        PrintStream mocked = mock(PrintStream.class);
        PrintStream old = System.out;
        System.setOut(mocked);

        ATM atm = new ATM(2, 2);
        atm.setCurrentCurrency("US$");

        billAmountChecker(atm);

        //Asserting the command prints are what we expect
        Mockito.verify(mocked).println("We highly recommend to deposit some " + atm.getCurrentCurrency() + "50 bills, since there is only " + atm.getBills50() + " left");
        Mockito.verify(mocked).println("We highly recommend to deposit some " + atm.getCurrentCurrency() + "20 bills, since there is only " + atm.getBills20() + " left");

        System.setOut(old);

    }

    @Test
    public void checkBalanceTest() {
        PrintStream mocked = mock(PrintStream.class);
        PrintStream old = System.out;
        System.setOut(mocked);

        ATM atm = new ATM(10, 10);
        atm.setCurrentCurrency("US$");

        checkBalance(atm);

        //Asserting the command prints are what we expect
        for (Currency c : atm.getCurrencies()) {
            Mockito.verify(mocked).println(c.getDisplayName() + " - Total balance : " + 700);
            Mockito.verify(mocked).println(c.getSymbol() + 20 + " : 10");
            Mockito.verify(mocked).println(c.getSymbol() + 50 + " : 10");

        }

        System.setOut(old);

    }
}
