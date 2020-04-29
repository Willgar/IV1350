package se.kth.iv1350.processSale.model;

import static se.kth.iv1350.processSale.integration.ExternalInventorySystem.*;

/**
 * An item containing the items identifier value, the price of the item, an description of the item and the quantity of which the item is grouped with.
 */
public class Item {
    private int itemIdentifier;
    private int price;
    private String itemDescription;
    private int quantity;
    private int VATRate;

    /**
     * Creates a new item based on the value of the <code>itemIdentifier</code>.
     * @param itemIdentifier The value which represents the item, and which the price is based on.
     */
    public Item(int itemIdentifier){
        this.itemIdentifier = itemIdentifier;
        this.quantity = 1;
        this.price = retrievePrice(itemIdentifier)*quantity;
        this.VATRate = retrieveVATRate(itemIdentifier);
        this.itemDescription = retrieveItemDescription(itemIdentifier);
    }

    /**
     * Returns the price of the item.
     * @return Returns the price.
     */
    public int getPrice(){
        return this.price;
    }

    /**
     * Changes the items current price
     * @param newPrice The new value of which the item will cost.
     */
    public void setPrice(int newPrice){
        this.price = newPrice;
    }

    /**
     * Increases the quantity of which the item is grouped with, and adjusts the price.
     * @param newQuantity The new quantity of the item added upon the old quantity.
     */
    public void increaseQuantity(int newQuantity){
        this.quantity += newQuantity;
        this.price = (itemIdentifier % 1000)*quantity;
    }
    /**
     * Sets the quantity of which the item is grouped with, and adjusts the price.
     * @param quantity The quantity of the item.
     */
    public void setQuantity(int quantity){
        this.quantity = quantity;
        this.price = (itemIdentifier % 1000)*quantity;
    }

    /**
     * Returns the quantity of which exists of the item.
     * @return Returns the quantity value.
     */
    public int getQuantity(){
        return quantity;
    }

    /**
     * Returns the value that represents the item.
     * @return Returns the item identifier.
     */
    public int getItemIdentifier(){return this.itemIdentifier;}
    /**
     * Returns the description that represents the item.
     * @return Returns the item description.
     */
    public String getItemDescription(){
        return itemDescription;
    }
}
