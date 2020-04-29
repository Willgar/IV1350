package srcTest.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static se.kth.iv1350.processSale.integration.ExternalInventorySystem.*;

class ExternalInventorySystemTest {
private int itemIdentifier1;

    @BeforeEach
    void setUp() {
        itemIdentifier1 = 82222102;
    }

    @AfterEach
    void tearDown() {
        itemIdentifier1 = 0;
    }

    @Test
    void testRetrievePrice() {
        int testPrice = retrievePrice(itemIdentifier1);
        assertEquals(102, testPrice, "The price is not the same");
    }

    @Test
    void testRetrieveVATRate() {
        int testVAT6 = retrieveVATRate(itemIdentifier1);
        itemIdentifier1 += 3;
        int testVAT12 = retrieveVATRate(itemIdentifier1);
        itemIdentifier1 += 3;
        int testVAT25 = retrieveVATRate(itemIdentifier1);

        assertEquals(6, testVAT6, "The first VAT test is not the same");
        assertEquals(12, testVAT12, "The second VAT test is not the same");
        assertEquals(25, testVAT25, "The third VAT test is not the same");

    }

    @Test
    void testRetrieveItemDescription() {
        String testItemDisc1 = retrieveItemDescription(itemIdentifier1);
        itemIdentifier1 += 10;
        String testItemDisc2 = retrieveItemDescription(itemIdentifier1);
        itemIdentifier1 += 10;
        String testItemDisc3 = retrieveItemDescription(itemIdentifier1);
        itemIdentifier1 += 10;
        String testItemDisc4 = retrieveItemDescription(itemIdentifier1);
        itemIdentifier1 += 10;
        String testItemDisc5 = retrieveItemDescription(itemIdentifier1);
        itemIdentifier1 += 10;
        String testItemDisc6 = retrieveItemDescription(itemIdentifier1);
        itemIdentifier1 += 10;
        String testItemDisc7 = retrieveItemDescription(itemIdentifier1);
        itemIdentifier1 += 10;
        String testItemDisc8 = retrieveItemDescription(itemIdentifier1);
        itemIdentifier1 += 10;
        String testItemDisc9 = retrieveItemDescription(itemIdentifier1);

        assertEquals("An electrical product", testItemDisc1, "The first description is not the same");
        assertEquals("A beverage", testItemDisc2, "The second description is not the same");
        assertEquals("A snack", testItemDisc3, "The third description is not the same");
        assertEquals("Frozen food", testItemDisc4, "The fourth description is not the same");
        assertEquals("Fresh produce", testItemDisc5, "The fifth description is not the same");
        assertEquals("Vegetables", testItemDisc6, "The sixth description is not the same");
        assertEquals("Consumables", testItemDisc7, "The seventh description is not the same");
        assertEquals("Cooking appliances", testItemDisc8, "The eight description is not the same");
        assertEquals("Newspaper", testItemDisc9, "The ninth description is not the same");
    }
}