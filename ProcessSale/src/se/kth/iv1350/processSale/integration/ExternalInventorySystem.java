package se.kth.iv1350.processSale.integration;

import se.kth.iv1350.processSale.model.Transaction;

/**
 * A external inventory system containing information about the items.
 */
public class ExternalInventorySystem {
    /**
     * Updates the system based on the items in the transaction.
     * @param sale The items being bought.
     */
    public static void updateExternalInventorySystem(Transaction sale){
        System.out.println("*Updating external inventory system");
    }

    /**
     * Retrieves the price for the item based on the item identifier value.
     * @param itemIdentifier The value that represents the item.
     * @return Returns the price.
     */
    static public int retrievePrice(int itemIdentifier){
        return (itemIdentifier % 1000);
    }

    /**
     * Retrieves the VAT rate for the item based on the item identifier value.
     * @param itemIdentifier The value that represents the item.
     * @return Returns the VAT rate.
     */
    public static int retrieveVATRate(int itemIdentifier){
        int itemIdentifierValue = itemIdentifier % 10;
        if(itemIdentifierValue < 3){
            return 6;
        }
        else if(itemIdentifierValue > 6){
            return 25;
        }
        else return 12;
    }

    /**
     * Retrieves an item description for the item based on the item identifier value.
     * @param itemIdentifier The value that represents the item.
     * @return Returns an item description.
     */
    public static String retrieveItemDescription(int itemIdentifier){
        int itemIdentifierValue = itemIdentifier % 100;
        if(itemIdentifierValue < 10){
            return "An electrical product";
        }else if(itemIdentifierValue < 20){
            return "A beverage";
        }else if(itemIdentifierValue < 30){
            return "A snack";
        }else if(itemIdentifierValue < 40){
            return "Frozen food";
        }else if(itemIdentifierValue < 50){
            return "Fresh produce";
        }else if(itemIdentifierValue < 60){
            return "Vegetables";
        }else if(itemIdentifierValue < 70){
            return "Consumables";
        }else if(itemIdentifierValue < 80){
            return "Cooking appliances";
        }else return "Newspaper";
    }
}
