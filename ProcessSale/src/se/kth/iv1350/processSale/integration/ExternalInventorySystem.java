package se.kth.iv1350.processSale.integration;

import se.kth.iv1350.processSale.model.IncorrectItemIdentifierException;
import se.kth.iv1350.processSale.model.NoConnectionException;
import se.kth.iv1350.processSale.model.Transaction;

import java.io.IOException;
import java.sql.SQLException;

/**
 * A external inventory system containing information about the items.
 */
public class ExternalInventorySystem {
    private final int EIGHT_FIGURE_NUMBER_MINIMUM   = 10000000;
    private final int EIGHT_FIGURE_NUMBER_MAXIMUM   = 99999999;
    private final int SIMULATED_ERROR_VALUE         = 22222222;

    /**
     * Checks if the item exists. Runs a unchecked exception check. Also have a database exception added for a simulated scenario.
     * @param itemIdentifier The value that is being checked, representing the item.
     * @return Returns true or false depending on the result
     * @throws IncorrectItemIdentifierException If incorrect itemidentifier value is given.
     * @throws NoConnectionException If there is no connection to the database.
     */
    public void itemIdentifierValidity(int itemIdentifier) throws IncorrectItemIdentifierException, NoConnectionException {
        if(itemIdentifier == SIMULATED_ERROR_VALUE)
            throw new NoConnectionException("Could not update item register", new IOException());
        else if(itemIdentifier < EIGHT_FIGURE_NUMBER_MAXIMUM && itemIdentifier > EIGHT_FIGURE_NUMBER_MINIMUM)
        {
            return;
        }
        else {
            throw new IncorrectItemIdentifierException("No such item", new SQLException());
        }
    }
    /**
     * Updates the system based on the items in the transaction.
     * @param sale The items being bought.
     */
    public void updateExternalInventorySystem(Transaction sale){
        System.out.println("*Updating external inventory system");
    }

    /**
     * Retrieves the price for the item based on the item identifier value.
     * @param itemIdentifier The value that represents the item.
     * @return Returns the price.
     */
    public int retrievePrice(int itemIdentifier){
        return (itemIdentifier % 1000);
    }

    /**
     * Retrieves the VAT rate for the item based on the item identifier value.
     * @param itemIdentifier The value that represents the item.
     * @return Returns the VAT rate.
     */
    public int retrieveVATRate(int itemIdentifier){
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
    public String retrieveItemDescription(int itemIdentifier){
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
