package se.kth.iv1350.processSale.model;

import java.time.LocalTime;
import java.util.List;

/**
 * A receipt which shows the details of the transaction and proves it.
 */
public class Receipt {
    private int totalCost;
    private double discountRate;
    List<Item> itemRegistry;
    private String storeName;
    private String storeAddress;
    private LocalTime saleTime;

    /**
     * Creates a new receipt at the end of the transaction.
     * @param sale The details of the sale gets transferred to the receipt.
     */
    public Receipt(Transaction sale){
        this.totalCost = sale.getTotalPrice();
        this.discountRate = sale.getDiscountRate();
        this.itemRegistry = sale.getItemRegistry();
        this.storeName = sale.getStoreName();
        this.storeAddress = sale.getStoreAddress();
        this.saleTime = LocalTime.now();
    }
}
