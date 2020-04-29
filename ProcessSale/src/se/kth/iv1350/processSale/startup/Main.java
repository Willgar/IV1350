package se.kth.iv1350.processSale.startup;

import se.kth.iv1350.processSale.controller.*;
import se.kth.iv1350.processSale.integration.*;
import se.kth.iv1350.processSale.model.*;
import se.kth.iv1350.processSale.view.*;

/**
 * The class which starts up the program and runs the testrun.
 */
public class Main {
    public static void main(String[] args) {
        startup();
    }

    /**
     * Creates the different systems.
     */
    static void startup(){
        AccountingSystem accountingSystem = new AccountingSystem();
        Display display = new Display();
        ExternalInventorySystem ExtInvSys = new ExternalInventorySystem();
        Printer printer = new Printer();

        Controller controller = new Controller();
        View view = new View(controller);
        view.testRun();
    }
}
