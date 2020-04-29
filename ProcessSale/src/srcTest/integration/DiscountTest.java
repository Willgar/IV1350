package srcTest.integration;

import static se.kth.iv1350.processSale.integration.Discount.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
//import static se.kth.iv1350.processSale.integration.Discount.getDiscountRate;

class DiscountTest {
private int customerID;

    @BeforeEach
    void setUp() {
        customerID = 32812069;
    }

    @AfterEach
    void tearDown() {
        customerID = 0;
    }

    @Test
    void testGetDiscountRate() {
        double testRate = getDiscountRate(customerID);
        double expectedRate = 0.91;

        assertEquals(expectedRate, testRate, "The discount rate are not the same");
    }

}