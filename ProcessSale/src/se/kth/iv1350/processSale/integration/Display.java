package se.kth.iv1350.processSale.integration;

/**
 * A placeholder system for a display.
 */
public class Display {
    /**
     * Writes out in the console the items description.
     * @param text The text that is to be written out.
     */
    static public void displayText(String text){
        System.out.println("Itemdescription: " + text);
    }

    /**
     * Writes out in the console the price.
     * @param price The price that is to be written.
     */
    static public void displayPrice(int price){
        System.out.println("Current total price: " + price + " kr, VAT included");
    }
}
