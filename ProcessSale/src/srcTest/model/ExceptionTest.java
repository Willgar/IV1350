package srcTest.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.processSale.controller.Controller;
import se.kth.iv1350.processSale.model.IncorrectItemIdentifierException;
import se.kth.iv1350.processSale.model.NoConnectionException;
import se.kth.iv1350.processSale.view.View;

import static org.junit.jupiter.api.Assertions.*;

class ExceptionTest {
    View view;
    Controller contr;
    private int itemIdentifier1 = 236;
    private int itemIdentifier2 = 22222222;
    private int itemIdentifier3 = 444444445;
    private int itemIdentifier4 = 23589219;

    private final int EIGHT_FIGURE_NUMBER_MINIMUM   = 10000000;
    private final int EIGHT_FIGURE_NUMBER_MAXIMUM   = 99999999;
    private final int SIMULATED_ERROR_VALUE         = 22222222;

    @BeforeEach
    void setUp() {
        contr = new Controller();
        view = new View(contr);

    }

    @AfterEach
    void tearDown() {
    }

    /**
     * Tests each of the exceptions with a copy of the method that throws the exceptions.
     */
    @Test
    public void testExceptionHandling(){
        boolean caughtFirstError = false;
        boolean caughtSecondError = false;
        boolean caughtThirdError = false;
        boolean caughtFourthError = true;

        try{
            copyOfTestMethod(itemIdentifier1);
        } catch(IncorrectItemIdentifierException itemExc){
            caughtFirstError = true;
        } catch (NoConnectionException dbExc){

        }

        try{
            copyOfTestMethod(itemIdentifier2);
        } catch(IncorrectItemIdentifierException itemExc){

        } catch (NoConnectionException dbExc){
            caughtSecondError = true;
        }

        try{
            copyOfTestMethod(itemIdentifier3);
        } catch(IncorrectItemIdentifierException itemExc){
            caughtThirdError = true;
        } catch (NoConnectionException dbExc){

        }

        try{
            copyOfTestMethod(itemIdentifier4);
        } catch(IncorrectItemIdentifierException itemExc){
            caughtFourthError = false;
        } catch (NoConnectionException dbExc){
            caughtFourthError = false;
        }

        assertEquals(true, caughtFirstError, "First error was missed");
        assertEquals(true, caughtSecondError, "Second error was missed");
        assertEquals(true, caughtThirdError, "Third error was missed");
        assertEquals(true, caughtFourthError, "Fourth error was falsely caught");

    }

    /**
     * A copy of the private method from controller that throws the exceptions to test.
     * @param itemIdentifier The value being tested.
     * @throws IncorrectItemIdentifierException The exception being thrown if the value is incorrect.
     * @throws NoConnectionException The value being thrown if there is no connection to the database, but due to simulation
     * purposes there is a different condition.
     */
    public void copyOfTestMethod(int itemIdentifier) throws IncorrectItemIdentifierException, NoConnectionException {
        if(itemIdentifier == SIMULATED_ERROR_VALUE)
            throw new NoConnectionException("Could not update item register", new Exception());
        else if(itemIdentifier < EIGHT_FIGURE_NUMBER_MAXIMUM && itemIdentifier > EIGHT_FIGURE_NUMBER_MINIMUM)
        {
            return;
        }
        else {
            throw new IncorrectItemIdentifierException("No such item", new Exception());
        }
    }
}