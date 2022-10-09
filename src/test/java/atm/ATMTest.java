package atm;

import lib.DataManagement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;

public class ATMTest {


    @BeforeAll
    public static void setup() {
        System.out.println("Junit Tests for ATM");
    }

    @Test
    public void atmCreation() {
        ATM atm = new ATM(10, 10);
        atm.setCurrentCurrency(Currency.getInstance("USD").getSymbol());
        //Testing that the symbol is the one intended
        assertEquals("US$", atm.getCurrentCurrency());

        //Testing that the add and substract methods work as intended
        atm.addBills20(5);
        atm.addBills50(5);
        assertEquals(15, atm.getBills20());
        assertEquals(15, atm.getBills50());

        atm.subtractBills20(5);
        atm.subtractBills50(5);
        assertEquals(10, atm.getBills20());
        assertEquals(10, atm.getBills50());

        //Checking that the object has the 9 different currencies setted as 20 and 50 bills
        assertEquals(18, atm.getAmountOfEachBill().size());

        //Testing the receipt
        atm.setReceipt("Withdrawing £100\n");
        atm.addToReceipt(20, 5);

        assertEquals("Withdrawing £100\n" +
                "US$20 : 5\n", atm.getReceipt());
    }
}
