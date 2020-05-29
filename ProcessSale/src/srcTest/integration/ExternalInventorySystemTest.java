package srcTest.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.processSale.integration.ExternalInventorySystem;

import static org.junit.jupiter.api.Assertions.*;
import static se.kth.iv1350.processSale.integration.ExternalInventorySystem.*;

class ExternalInventorySystemTest {
private int itemIdentifier1;
private ExternalInventorySystem ExtInvSys;
    @BeforeEach
    void setUp() {
        ExtInvSys = new ExternalInventorySystem();
        itemIdentifier1 = 82222102;
    }

    @AfterEach
    void tearDown() {
        itemIdentifier1 = 0;
    }

    @Test
    void testRetrievePrice() {
        int testPrice = ExtInvSys.retrievePrice(itemIdentifier1);
        assertEquals(102, testPrice, "The price is not the same");
    }

    @Test
    void testRetrieveVATRate() {
        int testVAT6 = ExtInvSys.retrieveVATRate(itemIdentifier1);
        assertEquals(6, testVAT6, "The first VAT test is not the same");

        itemIdentifier1 += 3;
        int testVAT12 = ExtInvSys.retrieveVATRate(itemIdentifier1);
        assertEquals(12, testVAT12, "The second VAT test is not the same");

        itemIdentifier1 += 3;
        int testVAT25 = ExtInvSys.retrieveVATRate(itemIdentifier1);
        assertEquals(25, testVAT25, "The third VAT test is not the same");
    }

    @Test
    void testRetrieveItemDescription() {
        String testItemDisc1 = ExtInvSys.retrieveItemDescription(itemIdentifier1);
        assertEquals("An electrical product", testItemDisc1, "The first description is not the same");

        itemIdentifier1 += 10;
        String testItemDisc2 = ExtInvSys.retrieveItemDescription(itemIdentifier1);
        assertEquals("A beverage", testItemDisc2, "The second description is not the same");

        itemIdentifier1 += 10;
        String testItemDisc3 = ExtInvSys.retrieveItemDescription(itemIdentifier1);
        assertEquals("A snack", testItemDisc3, "The third description is not the same");

        itemIdentifier1 += 10;
        String testItemDisc4 = ExtInvSys.retrieveItemDescription(itemIdentifier1);
        assertEquals("Frozen food", testItemDisc4, "The fourth description is not the same");

        itemIdentifier1 += 10;
        String testItemDisc5 = ExtInvSys.retrieveItemDescription(itemIdentifier1);
        assertEquals("Fresh produce", testItemDisc5, "The fifth description is not the same");

        itemIdentifier1 += 10;
        String testItemDisc6 = ExtInvSys.retrieveItemDescription(itemIdentifier1);
        assertEquals("Vegetables", testItemDisc6, "The sixth description is not the same");

        itemIdentifier1 += 10;
        String testItemDisc7 = ExtInvSys.retrieveItemDescription(itemIdentifier1);
        assertEquals("Consumables", testItemDisc7, "The seventh description is not the same");

        itemIdentifier1 += 10;
        String testItemDisc8 = ExtInvSys.retrieveItemDescription(itemIdentifier1);
        assertEquals("Cooking appliances", testItemDisc8, "The eight description is not the same");

        itemIdentifier1 += 10;
        String testItemDisc9 = ExtInvSys.retrieveItemDescription(itemIdentifier1);
        assertEquals("Newspaper", testItemDisc9, "The ninth description is not the same");
    }
}