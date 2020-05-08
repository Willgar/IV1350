package se.kth.iv1350.processSale.view;

import se.kth.iv1350.processSale.controller.Controller;
import se.kth.iv1350.processSale.model.IncorrectItemIdentifierException;
import se.kth.iv1350.processSale.model.Money;
import se.kth.iv1350.processSale.model.NoConnectionException;

/**
 * The view that the cashier has of the system to control.
 */
public class View {
    private Controller contr;
    private Money payment = new Money(0);

    /**
     * Creates a new view connected to a controller
     *
     * @param contr The controller which the view uses.
     */
    public View(Controller contr) {
        this.contr = contr;
        contr.addRevenueObserver(new TotalRevenueView());
    }

    /**
     * A test run with hardcoded methods too display the functions.
     */
    public void testRun() {
        contr.startSale();


        registerItem(82145252, 1);
        registerItem(56791275, 2);
        registerItem(22222222, 2);
        registerItem(931234100, 2);



        contr.applyDiscount();
        contr.finalizeSale();
        payment.setAmount(2000);
        int change = contr.registerTransaction(payment);
        System.out.println("Change back was " + change + " kr");
    }

    private void registerItem(int itemIdentifier, int quantity) {
        try {
            contr.registerItem(itemIdentifier, quantity);

        } catch (IncorrectItemIdentifierException exc) {
            System.out.println("VIEW: No such item");
        } catch (NoConnectionException exc) {
            System.out.println("VIEW: Error registering item, try again later");
            System.out.println("DEVLOG: " + exc.getCause());
        }
    }

}
