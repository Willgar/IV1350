package se.kth.iv1350.processSale.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A transaction containing information about the store, the items registred to the sale, the receit and a discount rate.
 */
public class Transaction {
    private String storeName;
    private String storeAddress;
    private List<Item> itemRegistry;
    private Receipt receipt;
    private double discountRate;
    private List<RevenueObserver> revenueObservers = new ArrayList<>();

    /**
     * Creates a new transaction at the specified store.
     * @param storeName The name of the store which the <code>Transaction</code> occurs.
     * @param storeAddress The address of the store which the <code>Transaction</code> occurs
     */
    public Transaction(String storeName, String storeAddress){
        itemRegistry = new ArrayList<Item>();
        this.storeName = storeName;
        this.storeAddress = storeAddress;
    }

    /**
     * Registers an <code>Item</code> to the current <code>Transaction</code> .
     * @param item The item being added to the transaction.
     * @param quantity The quantity of the specified item being added.
     */
    public void registerItem(Item item, int quantity){
        boolean itemDoesNotExist = true;
        for(Item itemInList : itemRegistry){
            if(itemInList.getItemIdentifier() == item.getItemIdentifier()){
                itemInList.increaseQuantity(quantity);
                itemDoesNotExist = false;
            }
        }
        if(itemDoesNotExist){
            item.setQuantity(quantity);
            this.itemRegistry.add(item);
        }
    }

    /**
     * Updates the total price of the current transaction based on if the customer is eligible for an discount.
     * @param discountRate The rate which the price will be adjusted after.
     */
    public void updateTotal(double discountRate){
        this.discountRate = discountRate;
        for(Item item: itemRegistry){
            item.setPrice(newPrice(item));
        }
    }

    /**
     * Sets the new price of the <code>item</code> based on the current price and discount rate.
     * @param item The item being adjusted.
     * @return Returns the newly adjusted price.
     */
    private int newPrice(Item item){
        return (int)(item.getPrice()*this.discountRate);
    }

    /**
     * Calculates the total price of the current <code>Transaction</code>.
     * @return Returns the value for the total price.
     */
    public int getTotalPrice(){
        int total = 0;
        for(Item item: itemRegistry){
            total += item.getPrice();
        }
        return total;
    }

    public void addRevenueObservers(List<RevenueObserver> obs){
        this.revenueObservers = obs;
    }

    private void notifyObservers(Money payment){
        for(RevenueObserver obs : revenueObservers){
            obs.newPayment(payment);
        }
    }

    public Money finalizeSale(){
        Money totalSale = new Money(getTotalPrice());
        notifyObservers(totalSale);
        return totalSale;
    }
    /**
     * Calculates the percentage of which has been removed from the original price.
     * @return Returns the value of the discount rate.
     */
    public double getDiscountRate(){
        return (int)((1-discountRate)*100);
    }

    /**
     * Returns the register of items
     * @return Returns <code>itemRegistry</code>
     */
    public List<Item> getItemRegistry(){
        return itemRegistry;
    }

    /**
     * Returns the name of the store
     * @return Returns <code>storeName</code>
     */
    public String getStoreName(){return storeName;}
    /**
     * Returns the address of the store
     * @return Returns <code>storeAddress</code>
     */
    public String getStoreAddress(){return storeAddress;}
}
