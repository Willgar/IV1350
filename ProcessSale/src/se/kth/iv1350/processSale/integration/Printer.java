package se.kth.iv1350.processSale.integration;

import se.kth.iv1350.processSale.model.Receipt;

/**
 * A placeholder system for a printer.
 */
public class Printer {
    /**
     * Shows that the receipt is being printed.
     * @param receipt The receipt which will be printed.
     */
    public static void printReceipt(Receipt receipt){
        System.out.println("*Printing receipt*");
    }
}
