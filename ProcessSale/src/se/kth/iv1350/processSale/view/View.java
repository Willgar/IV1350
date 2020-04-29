package se.kth.iv1350.processSale.view;

import se.kth.iv1350.processSale.controller.Controller;

/**
 * The view that the cashier has of the system to control.
 */
public class View {
    private Controller contr;
    /**
     * Creates a new view connected to a controller
     * @param contr The controller which the view uses.
     */
    public View(Controller contr){
        this.contr = contr;
    }

    /**
     * A test run with hardcoded methods too display the functions.
     */
    public void testRun(){
        contr.startSale();
        contr.registerItem(82145252, 1);
        contr.registerItem(56791275, 2);

        contr.applyDiscount();
        contr.finalizeSale();
        int change = contr.registerTransaction(2000);
        System.out.println("Change back was " + change + " kr");
    }
}
