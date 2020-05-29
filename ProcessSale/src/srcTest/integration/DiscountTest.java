package srcTest.integration;

import static se.kth.iv1350.processSale.integration.Discount.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.processSale.integration.Discount;

import static org.junit.jupiter.api.Assertions.*;

class DiscountTest {
private int customerID;
private Discount discount;

    @BeforeEach
    void setUp() {
        customerID = 32812069;
        discount = new Discount();
    }

    @AfterEach
    void tearDown() {
        customerID = 0;
    }

    @Test
    void testGetDiscountRate() {
        double testRate = discount.getDiscountRate(customerID);
        double expectedRate = 0.91;

        assertEquals(expectedRate, testRate, "The discount rate are not the same");
    }

}