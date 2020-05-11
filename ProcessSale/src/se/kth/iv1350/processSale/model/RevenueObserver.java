package se.kth.iv1350.processSale.model;

/**
 * An interface class for an observer that observes the revenue of each sale.
 */
public interface RevenueObserver {
    /**
     * A method that performs an action and adds the new payment to the observer.
     * @param money The money being added.
     */
    void newPayment(Money money);
}
