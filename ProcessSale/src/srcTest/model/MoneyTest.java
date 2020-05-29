package srcTest.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.processSale.model.Money;

import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {
    private Money testMoney;
    private Money correctMoney;
    @BeforeEach
    void setUp() {
        testMoney = new Money(100);
    }

    @AfterEach
    void tearDown() {
        testMoney = null;
    }

    @Test
    void testAddMoney() {
        testMoney.addMoney(100);
        correctMoney = new Money(200);
        assertEquals(correctMoney.getAmount(), testMoney.getAmount());
    }

    @Test
    void subtractMoney() {
        testMoney.subtractMoney(50);
        correctMoney = new Money(50);
        assertEquals(correctMoney.getAmount(), testMoney.getAmount());
    }

    @Test
    void setAmount() {
        testMoney.setAmount(200);
        correctMoney = new Money(200);
        assertEquals(correctMoney.getAmount(), testMoney.getAmount());
    }
}