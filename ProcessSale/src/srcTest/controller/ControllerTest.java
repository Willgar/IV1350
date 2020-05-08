package srcTest.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.processSale.controller.Controller;
import se.kth.iv1350.processSale.model.IncorrectItemIdentifierException;
import se.kth.iv1350.processSale.model.Item;
import se.kth.iv1350.processSale.model.NoConnectionException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static se.kth.iv1350.processSale.integration.Discount.getDiscountRate;

class ControllerTest {
    private Controller controller;
    private int itemIdentifier1;
    private int itemIdentifier2;
    private int incorrectItemIdentifier1;
    private int incorrectItemIdentifier2;

    @BeforeEach
    void setUp() {
        controller = new Controller();
        controller.startSale();
        itemIdentifier1 = 51722947;
        itemIdentifier2 = 86163582;
        incorrectItemIdentifier1 = 5172294;
        incorrectItemIdentifier2 = 151722947;

    }

    @AfterEach
    void tearDown() {
        itemIdentifier1 = 0;
        itemIdentifier2 = 0;
        incorrectItemIdentifier1 = 0;
        incorrectItemIdentifier2 = 0;
    }

    @Test
    void testRegisterItem() {
            registerItem(itemIdentifier1, 1);
            registerItem(itemIdentifier2, 2);
            registerItem(incorrectItemIdentifier1, 1);
            registerItem(incorrectItemIdentifier2, 1);


        List<Item> itemRegistry = controller.getItemRegistry();

        Item item1 = new Item(itemIdentifier1);
        Item item2 = new Item(itemIdentifier2);
        assertEquals(itemRegistry.get(0).getItemIdentifier(), item1.getItemIdentifier(), "The first item is not the same");
        assertEquals(itemRegistry.get(1).getItemIdentifier(), item2.getItemIdentifier(), "The second item is not the same");
        assertEquals(itemRegistry.size(), 2, "The amount of items is not correct");
    }

    private void registerItem(int itemIdentifier, int quantity) {
        try {
            controller.registerItem(itemIdentifier, quantity);

        } catch (IncorrectItemIdentifierException exc) {
            System.out.println("VIEW: No such item");
        } catch (NoConnectionException exc) {
            System.out.println("VIEW: Error registering item, try again later");
            System.out.println("DEVLOG: " + exc.getCause());
        }
    }
}