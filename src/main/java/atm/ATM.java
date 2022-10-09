package atm;

import java.util.Currency;
import java.util.HashMap;
import java.util.Set;

public class ATM implements java.io.Serializable {

    String currentCurrency;
    String receipt;
    HashMap<String, Integer> amountOfEachBill = new HashMap<>();

    //9 currencies used in the atm
    Currency[] currencies = {Currency.getInstance("USD"), Currency.getInstance("EUR"), Currency.getInstance("JPY"),
            Currency.getInstance("GBP"), Currency.getInstance("AUD"), Currency.getInstance("CAD"),
            Currency.getInstance("CHF"), Currency.getInstance("HKD"), Currency.getInstance("NZD")};

    //I left this here for future support of all currencies
    //I felt over 200 currencies would be a bit overwhelming
    Set<Currency> currencySet = Currency.getAvailableCurrencies();

    //Constructor to initialize the atm with a certain amount of bills
    public ATM(int bills20, int bills50) {
        amountOfEachBill.put("US$20", bills20);
        amountOfEachBill.put("US$50", bills50);
        amountOfEachBill.put("€20", bills20);
        amountOfEachBill.put("€50", bills50);
        amountOfEachBill.put("JP¥20", bills20);
        amountOfEachBill.put("JP¥50", bills50);
        amountOfEachBill.put("£20", bills20);
        amountOfEachBill.put("£50", bills50);
        amountOfEachBill.put("A$20", bills20);
        amountOfEachBill.put("A$50", bills50);
        amountOfEachBill.put("CA$20", bills20);
        amountOfEachBill.put("CA$50", bills50);
        amountOfEachBill.put("CHF20", bills20);
        amountOfEachBill.put("CHF50", bills50);
        amountOfEachBill.put("HK$20", bills20);
        amountOfEachBill.put("HK$50", bills50);
        amountOfEachBill.put("NZ$20", bills20);
        amountOfEachBill.put("NZ$50", bills50);
    }

    //Empty constructor, mainly there to deserialize the saved data
    public ATM() {

    }

    public void addBills20(int noBills) {
        this.amountOfEachBill.merge(currentCurrency + 20, noBills, Integer::sum);
        //this.bills20 = bills20 + noBills;
    }

    public void addBills50(int noBills) {
        this.amountOfEachBill.merge(currentCurrency + 50, noBills, Integer::sum);
        //this.bills50 = bills50 + noBills;
    }

    public void subtractBills20(int noBills) {
        this.amountOfEachBill.merge(currentCurrency + 20, -noBills, Integer::sum);
        //this.bills20 = bills20 - noBills;
    }

    public void subtractBills50(int noBills) {
        this.amountOfEachBill.merge(currentCurrency + 50, -noBills, Integer::sum);
        //this.bills50 = bills50 - noBills;
    }

    public int getBills20() {
        return this.amountOfEachBill.get(currentCurrency + 20);
    }

    public void setBills20(int bills20) {
        this.amountOfEachBill.put(currentCurrency + 20, bills20);
        //this.bills20 = bills20;
    }

    public int getBills50() {
        return this.amountOfEachBill.get(currentCurrency + 50);
    }

    public void setBills50(int bills50) {
        this.amountOfEachBill.put(currentCurrency + 50, bills50);
        //this.bills50 = bills50;
    }

    public HashMap<String, Integer> getAmountOfEachBill() {
        return amountOfEachBill;
    }

    public void setAmountOfEachBill(HashMap<String, Integer> amountOfEachBill) {
        this.amountOfEachBill = amountOfEachBill;
    }

    public Currency[] getCurrencies() {
        return currencies;
    }

    public String getCurrencyCode(int currency) {
        return currencies[currency].getSymbol();
    }

    public void setCurrencies(Currency[] currencies) {
        this.currencies = currencies;
    }

    public String printCurrencies() {
        String answer = "Select a currency: \n ";
        for (int i = 0; i < currencies.length; i++) {
            answer = answer + i + "." + currencies[i].getSymbol() + " \n ";
        }
        return answer;

    }

    public String getCurrentCurrency() {
        return currentCurrency;
    }

    public void setCurrentCurrency(String currentCurrency) {
        this.currentCurrency = currentCurrency;
    }

    public String toString() {
        return getCurrentCurrency() + "20 : " +
                amountOfEachBill.get(getCurrentCurrency() + 20) +
                ", " + getCurrentCurrency() + "50 : " +
                amountOfEachBill.get(getCurrentCurrency() + 50);
    }

    //I added this in case in the future it would be needed to support the 229 different currencies
    //For now it felt a bit of an overkill
    public void atmCurrencyInitializer() {
        for (Currency c : currencySet) {
            amountOfEachBill.put(c.getSymbol() + 20, 0);
            amountOfEachBill.put(c.getSymbol() + 50, 0);
        }
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public void addToReceipt(int bill, int amount) {
        String withdrawal = currentCurrency + bill + " : " + amount + "\n";
        this.receipt = receipt + withdrawal;
    }

    public void addToReceipt(String comment) {
        this.receipt = receipt + comment + "\n";
    }
}
