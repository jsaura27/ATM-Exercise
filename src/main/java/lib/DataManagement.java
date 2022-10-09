package lib;

import atm.ATM;

import java.io.*;

public class DataManagement {

    public static void serialize(ATM atm, String atmFile) {
        try {
            FileOutputStream file = new FileOutputStream(atmFile);
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(atm);

            out.close();
            file.close();

        } catch (IOException ex) {
            System.out.println("IOException caught");
        }

    }

    public static ATM deSerialize(ATM atm, String atmFile) {
        try {
            // Reading the object from a file
            FileInputStream file = new FileInputStream(atmFile);
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            atm = (ATM) in.readObject();

            in.close();
            file.close();

        } catch (IOException ex) {
            System.out.println("IOException is caught");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }
        return atm;
    }
}
