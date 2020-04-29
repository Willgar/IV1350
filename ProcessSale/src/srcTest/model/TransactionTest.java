package srcTest.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.processSale.model.Item;
import se.kth.iv1350.processSale.model.Transaction;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {
    Transaction sale;
    Item item1;
    Item item2;
    Item item3;
    List<Item> saleList;

    @BeforeEach
    void setUp() {
        item1 = new Item(15151515);
        item2 = new Item(30303030);
        item3 = new Item(50505050);
        sale = new Transaction("Coop", "Road");
    }

    @AfterEach
    void tearDown() {
        item1 = null;
        item2 = null;
        item3 = null;
        sale = null;
    }

    @Test
    void testRegisterItem() {
        sale.registerItem(item1, 1);
        saleList = sale.getItemRegistry();

        List<Item> comparisonList = new ArrayList<Item>();
        comparisonList.add(item1);
        assertEquals(saleList.get(0), comparisonList.get(0), "The first item in the lists are not the same");

    }

    @Test
    void testRegisterMultipleItems() {
        sale.registerItem(item2, 2);
        sale.registerItem(item3, 1);

        saleList = sale.getItemRegistry();

        List<Item> comparisonList = new ArrayList<Item>();
        comparisonList.add(item2);
        comparisonList.add(item3);

        assertEquals(saleList.get(0), comparisonList.get(0), "The first item in the lists are not the same");
        assertEquals(saleList.get(1), comparisonList.get(1), "The second item in the lists are not the same");

    }

    @Test
    void getTotal() {
        sale.registerItem(item1, 2);
        int correctTotal = 1030;

        assertEquals(sale.getTotalPrice(), correctTotal, "The total value is not the same");

    }

    @Test
    void updateTotalPrice() {
        sale.registerItem(item1, 1);
        sale.registerItem(item2, 1);
        sale.registerItem(item1, 1);

        sale.updateTotal(0.8);
        int newTotal = sale.getTotalPrice();
        int correctTotal = 412*2+24;

        assertEquals(correctTotal, newTotal, "The new total is not the same");
    }
}