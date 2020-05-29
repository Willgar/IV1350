package se.kth.iv1350.processSale.model;

/**
 * A class that represents money being moved.
 */
public class Money {
    private int amount;

    /**
     * Creates a new money with a set amount
     * @param amount The amount of money
     */
    public Money(int amount){
        this.amount = amount;
    }

    /**
     * Retrieves the amount of money
     * @return
     */
    public int getAmount(){
        return amount;
    }

    /**
     * Adds more money.
     * @param amount The amount added
     */
    public void addMoney(int amount){
        this.amount += amount;
    }

    /**
     * Adds more money.
     * @param amount The amount added
     */
    public void addMoney(Money amount){
        this.amount += amount.getAmount();
    }

    /**
     * Subtracts money.
     * @param amount The amount subtracted
     */
    public void subtractMoney(int amount){
        this.amount -= amount;
    }

    /**
     * Sets a new amount of money
     * @param amount The amount of money
     */
    public void setAmount(int amount){
        this.amount = amount;
    }
}
