package lib;

import atm.ATM;
import org.junit.jupiter.api.AfterAll;
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
    }

    @AfterAll
    public static void cleanUp() {
        System.out.println("Junit Tests for DataManagement End");
    }

    @Test
    public void emptyFile() {
        //This is used to test the command prints
        PrintStream mocked = mock(PrintStream.class);
        PrintStream old = System.out;
        System.setOut(mocked);

        String fileName = "potato.ser";
        atm = DataManagement.deSerialize(atm, fileName);

        //Asserting the command prints are what we expect
        Mockito.verify(mocked).println("IOException is caught");
        assertNull(atm.getCurrentCurrency());
        System.setOut(old);

    }

    @Test
    public void noFile() {
        PrintStream mocked = mock(PrintStream.class);
        PrintStream old = System.out;
        System.setOut(mocked);

        String fileName = "banana.ser";
        atm = DataManagement.deSerialize(atm, fileName);

        //Asserting the command prints are what we expect
        Mockito.verify(mocked).println("IOException is caught");
        assertNull(atm.getCurrentCurrency());
        System.setOut(old);

    }

    @Test
    public void correctFile() {
        String fileName = "src/main/resources/atm.ser";
        atm = DataManagement.deSerialize(atm, fileName);
        assertNotNull(atm.getCurrentCurrency());
    }


}
