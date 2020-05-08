package se.kth.iv1350.processSale.model;

public interface RevenueObserver {
    /**
     *
     */
    void newPayment(Money money);
}
