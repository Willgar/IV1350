package se.kth.iv1350.processSale.controller;
import se.kth.iv1350.processSale.integration.*;
import se.kth.iv1350.processSale.startup.*;

import se.kth.iv1350.processSale.model.*;

import java.util.List;

/**
 * A controller which controls the actions of the cashier system.
 */
public class Controller {
    private Transaction sale;
    private double discountPercentage = 1;
    private int customerID;
    private int cashierRegister = 5000;


    private static final int EIGHT_FIGURE_NUMBER_MINIMUM = 10000000;
    private static final int EIGHT_FIGURE_NUMBER_MAXIMUM = 99999999;

    /**
     * Starts the by creating a new <code>Transaction</code>, generating a customer ID and checking for discount.
     */
    public void startSale(){
        Transaction sale = new Transaction("coop", "placeholder 42");
        this.sale = sale;
        this.customerID = setCustomerID();
        this.discountPercentage = setDiscountPercentage(this.customerID);
    }

    /**
     * Registers a type of item and the quantity of it. Also displays the item description if it exists and the current total price.
     * @param itemIdentifier The value that represents the item.
     * @param quantity The quantity of which the item has.
     */
    public void registerItem(int itemIdentifier, int quantity){
        if(itemIdentifierValidity(itemIdentifier)) {
            Item item = new Item(itemIdentifier);
            sale.registerItem(item, quantity);
            Display.displayText(item.getItemDescription());
            Display.displayPrice(sale.getTotalPrice());
        }
        else Display.displayText("No such item");
    }

    /**
     * Checks if the item exists.
     * @param itemIdentifier The value that is being checked, representing the item.
     * @return Returns true or false depending on the result
     */
    private boolean itemIdentifierValidity(int itemIdentifier){
        return itemIdentifier < EIGHT_FIGURE_NUMBER_MAXIMUM && itemIdentifier > EIGHT_FIGURE_NUMBER_MINIMUM;
    }

    /**
     * Applies the discount to the current <code>Transaction</code>
     */
    public void applyDiscount(){
        sale.updateTotal(discountPercentage);
        if(discountExists()){
            System.out.println("Your discount is " + sale.getDiscountRate() + "%");
            Display.displayPrice(sale.getTotalPrice());
        }
    }

    /**
     * Checks if the discount exists.
     * @return Returns true or false depending on the result
     */
    private boolean discountExists(){
        return discountPercentage != 1 && discountPercentage != 0;
    }

    /**
     * Finalizing the sale for the customer to see how much needs to be paid.
     */
    public void finalizeSale(){
        System.out.println("Total price: " + sale.getTotalPrice() + " kr, VAT included");
    }

    /**
     * Registers the transaction into external systems and takes the payment and returns the change.
     * @param payment The amount being paid.
     * @return Returns the change.
     */
    public int registerTransaction(int payment){
        AccountingSystem.updateAccountingSystem(sale);
        ExternalInventorySystem.updateExternalInventorySystem(sale);
        Receipt receipt = new Receipt(sale);
        Printer.printReceipt(receipt);
        this.cashierRegister += sale.getTotalPrice();
        return payment - sale.getTotalPrice();
    }

    /**
     * Returns a list of item in the current transaction
     * @return returns the item register from the <code>Transaction</code>
     */
    public List<Item> getItemRegistry(){
        return sale.getItemRegistry();
    }

    /**
     * Creates a customer ID.
     * @return Returns the value of the customer id.
     */
    private int setCustomerID(){
        int customerID = (int)(Math.random() * ((EIGHT_FIGURE_NUMBER_MAXIMUM - EIGHT_FIGURE_NUMBER_MINIMUM) + 1)) + EIGHT_FIGURE_NUMBER_MINIMUM;
        System.out.println("Your customer ID is " + customerID);
        return customerID;
    }

    /**
     * Sets the discount percentage which the price will be adjusted for later.
     * @param customerID The value being compared to get the rate.
     * @return Returns the discount rate.
     */
    private double setDiscountPercentage(int customerID){
        this.discountPercentage = Discount.getDiscountRate(customerID);
        return discountPercentage;
    }
}
