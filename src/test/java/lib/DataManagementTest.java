package lib;

import atm.ATM;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;

public class DataManagementTest {

    public static ATM atm = new ATM();

    @BeforeAll
    public static void setup() {
        System.out.println("Junit Tests for DataManagement");
        //String atmFile = "test.ser";
        //atm = new ATM(10,10);

    }

    @Test
    public void emptyFile() {
        String fileName = "potato.ser";
        atm = DataManagement.deSerialize(atm, fileName);
        assertNull(atm.getCurrentCurrency());
    }

    @Test
    public void noFile() {
        String fileName = "banana.ser";
        atm = DataManagement.deSerialize(atm, fileName);
        assertNull(atm.getCurrentCurrency());
    }

    @Test
    public void correctFile() {
        String fileName = "atm.ser";
        atm = DataManagement.deSerialize(atm, fileName);
        assertNotNull(atm.getCurrentCurrency());
    }

    @Test
    public void exceptionCaught() {
        PrintStream mocked = mock(PrintStream.class);
        PrintStream old = System.out;
        System.setOut(mocked);
        String fileName = "potato.ser";
        atm = DataManagement.deSerialize(atm, fileName);

        Mockito.verify(mocked).println("IOException is caught");

        System.setOut(old);
    }
}
