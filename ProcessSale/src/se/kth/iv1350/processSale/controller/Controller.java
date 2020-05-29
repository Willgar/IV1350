package se.kth.iv1350.processSale.controller;
import se.kth.iv1350.processSale.integration.*;

import se.kth.iv1350.processSale.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * A controller which controls the actions of the cashier system.
 */
public class Controller {
    private Transaction sale;
    private double discountPercentage = 1;
    private int customerID;
    private Money cashierRegister = new Money(5000);
    private List<RevenueObserver> revenueObservers = new ArrayList<>();
    private ExternalInventorySystem ExtInvSys;
    private Discount discount = new Discount();

    /**
     * Starts the sale by creating a new <code>Transaction</code>, generating a customer ID and checking for discount.
     */
    public void startSale(){
        Transaction sale = new Transaction("coop", "placeholder 42");
        this.sale = sale;
        this.customerID = setCustomerID();
        this.discountPercentage = setDiscountPercentage(this.customerID);
        sale.addRevenueObservers(revenueObservers);
        this.ExtInvSys = new ExternalInventorySystem();

    }

    /**
     * Starts the sale by creating a new Transaction, with a predetermined customer ID and checks for the discount.
     * @param customerID
     */
    public void startSale(int customerID){
        Transaction sale = new Transaction("coop", "placeholder 42");
        this.sale = sale;
        this.customerID = customerID;
        this.discountPercentage = setDiscountPercentage(this.customerID);
        sale.addRevenueObservers(revenueObservers);
    }

    /**
     * Registers a type of item and the quantity of it. Also displays the item description if it exists and the current total price.
     * @param itemIdentifier The value that represents the item.
     * @param quantity The quantity of which the item has.
     * @throws IncorrectItemIdentifierException If incorrect itemidentifier value is given.
     * @throws NoConnectionException If there is no connection to the database.
     */
    public void registerItem(int itemIdentifier, int quantity) throws IncorrectItemIdentifierException, NoConnectionException {
        ExtInvSys.itemIdentifierValidity(itemIdentifier);
        Item item = new Item(itemIdentifier);
        sale.registerItem(item, quantity);
        Display.displayText(item.getItemDescription());
        Display.displayPrice(sale.getTotalPrice());
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
     * Registers the transaction into external systems and takes the payment and returns the change. The sale is fully completed after this.
     * @param payment The amount being paid.
     * @return Returns the change.
     */
    public int registerTransaction(Money payment){
        Money saleTotal = sale.finalizeSale();
        AccountingSystem.updateAccountingSystem(sale);
        ExtInvSys.updateExternalInventorySystem(sale);
        Receipt receipt = new Receipt(sale);
        Printer.printReceipt(receipt);
        cashierRegister.addMoney(saleTotal);
        payment.subtractMoney(sale.getTotalPrice());
        return payment.getAmount();
    }

    /**
     * Returns a list of item in the current transaction
     * @return returns the item register from the <code>Transaction</code>
     */
    public List<Item> getItemRegistry(){
        return sale.getItemRegistry();
    }

    /**
     * Returns the total price of the transaction so far.
     * @return returns an integer with the total.
     */
    public int getTotalPrice(){
        return sale.getTotalPrice();
    }

    /**
     * Creates a customer ID.
     * @return Returns the value of the customer id.
     */
    private int setCustomerID(){
        int EIGHT_FIGURE_NUMBER_MINIMUM = 10000000;
        int EIGHT_FIGURE_NUMBER_MAXIMUM = 99999999;
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
        this.discountPercentage = discount.getDiscountRate(customerID);
        return discountPercentage;
    }

    /**
     * Adds a new Revenue Observer.
     * @param obs The observer being added.
     */
    public void addRevenueObserver(RevenueObserver obs){
        revenueObservers.add(obs);
    }
}
