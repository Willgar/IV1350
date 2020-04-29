package srcTest.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.processSale.model.Item;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
    private Item item1;
    private Item item2;

    @BeforeEach
    void setUp() {
        item1 = new Item(32157219);
        item2 = new Item(58105267);
    }

    @AfterEach
    void tearDown() {
        item1 = null;
        item2 = null;
    }

    @Test
    void testIncreaseQuantity() {
        item1.increaseQuantity(3);

        int expectedPrice = 219*4;
        int expectedQuantity = 4;

        assertEquals(expectedQuantity, item1.getQuantity(), "The quantity is not the same");
        assertEquals(expectedPrice, item1.getPrice(), "The Price is not the same");
    }

    @Test
    void setQuantity() {
        item1.setQuantity(5);

        int expectedPrice = 219*5;
        int expectedQuantity = 5;

        assertEquals(expectedQuantity, item1.getQuantity(), "The quantity is not the same");
        assertEquals(expectedPrice, item1.getPrice(), "The Price is not the same");
    }
}