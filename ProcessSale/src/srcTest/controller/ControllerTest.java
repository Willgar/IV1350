package srcTest.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.processSale.controller.Controller;
import se.kth.iv1350.processSale.model.IncorrectItemIdentifierException;
import se.kth.iv1350.processSale.model.Item;
import se.kth.iv1350.processSale.model.Money;
import se.kth.iv1350.processSale.model.NoConnectionException;
import se.kth.iv1350.processSale.view.View;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    private Controller controller;
    private int itemIdentifier1;
    private int itemIdentifier2;
    private int incorrectItemIdentifier1;
    private int incorrectItemIdentifier2;
    private Money money;
    private View view;

    @BeforeEach
    void setUp() {
        controller = new Controller();
        view = new View(controller);
        itemIdentifier1 = 82145252;
        itemIdentifier2 = 56791275;
        incorrectItemIdentifier1 = 5172294;
        incorrectItemIdentifier2 = 151722947;
        money = new Money(3000);
    }

    @AfterEach
    void tearDown() {
        itemIdentifier1 = 0;
        itemIdentifier2 = 0;
        incorrectItemIdentifier1 = 0;
        incorrectItemIdentifier2 = 0;
        controller = null;
    }

    @Test
    void testRegisterItem() {
        view.testRun();
        List<Item> itemRegistry = controller.getItemRegistry();

        Item item1 = new Item(itemIdentifier1);
        assertEquals(itemRegistry.get(0).getItemIdentifier(), item1.getItemIdentifier(), "The first item is not the same");
        Item item2 = new Item(itemIdentifier2);
        assertEquals(itemRegistry.get(1).getItemIdentifier(), item2.getItemIdentifier(), "The second item is not the same");
        assertEquals(itemRegistry.size(), 2, "The amount of items is not correct");
    }

    @Test
    void testApplyDiscount(){
        controller.startSale(48484847);
        registerMultipleItems();
        controller.applyDiscount();
        int totalWithDiscount = controller.getTotalPrice();

        assertEquals(totalWithDiscount, 745, "The new total is not equal to the calculated total based on a 7% discount");
    }

    @Test
    void testApplyNoDiscount(){
        controller.startSale(48484848);
        registerMultipleItems();
        controller.applyDiscount();
        int totalWithoutDiscount = controller.getTotalPrice();

        assertEquals(totalWithoutDiscount, 802, "The total has been changed when there should be no change");
    }

    @Test
    void testRegisterTransaction(){
        controller.startSale(48484848);
        registerMultipleItems();

        int change = controller.registerTransaction(money);
        assertEquals(change, 2198, "The money returned is not the correct amount");
    }

    private void registerMultipleItems(){
        try {
            controller.registerItem(itemIdentifier1, 1);

        } catch (IncorrectItemIdentifierException exc) {
            System.out.println("VIEW: No such item");
        } catch (NoConnectionException exc) {
            System.out.println("VIEW: Error registering item, try again later");
            System.out.println("DEVLOG: " + exc.getCause());
        }
        try {
            controller.registerItem(itemIdentifier2, 2);

        } catch (IncorrectItemIdentifierException exc) {
            System.out.println("VIEW: No such item");
        } catch (NoConnectionException exc) {
            System.out.println("VIEW: Error registering item, try again later");
            System.out.println("DEVLOG: " + exc.getCause());
        }
    }
}