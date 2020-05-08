package se.kth.iv1350.processSale.view;

import se.kth.iv1350.processSale.model.Money;
import se.kth.iv1350.processSale.model.RevenueObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * A view to observe the total revenue of the sales conducted. Implemented after the Revenue Observer class in Model.
 */
public class TotalRevenueView implements RevenueObserver {
    List<Money> TotalRevenue = new ArrayList<>();

    /**
     * Adds the money to a list of money and shows the total amount of money.
     * @param money The money being added.
     */
    @Override
    public void newPayment(Money money) {
        TotalRevenue.add(money);
        printCurrentTotalRevenue();
    }
    private void printCurrentTotalRevenue(){
        int total = 0;
        for(Money sale : TotalRevenue){
            total += sale.getAmount();
        }
        System.out.println("Total revenue collected: " + total);
    }
}
