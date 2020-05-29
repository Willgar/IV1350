package srcTest.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.processSale.controller.Controller;
import se.kth.iv1350.processSale.integration.ExternalInventorySystem;
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
    private ExternalInventorySystem ExtInvSys;

    private final int EIGHT_FIGURE_NUMBER_MINIMUM   = 10000000;
    private final int EIGHT_FIGURE_NUMBER_MAXIMUM   = 99999999;
    private final int SIMULATED_ERROR_VALUE         = 22222222;

    @BeforeEach
    void setUp() {
        contr = new Controller();
        view = new View(contr);
        ExtInvSys = new ExternalInventorySystem();
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
            ExtInvSys.itemIdentifierValidity(itemIdentifier1);
        } catch(IncorrectItemIdentifierException itemExc){
            caughtFirstError = true;
        } catch (NoConnectionException dbExc){

        }
        assertEquals(true, caughtFirstError, "First error was missed");

        try{
            ExtInvSys.itemIdentifierValidity(itemIdentifier2);
        } catch(IncorrectItemIdentifierException itemExc){

        } catch (NoConnectionException dbExc){
            caughtSecondError = true;
        }
        assertEquals(true, caughtSecondError, "Second error was missed");

        try{
            ExtInvSys.itemIdentifierValidity(itemIdentifier3);
        } catch(IncorrectItemIdentifierException itemExc){
            caughtThirdError = true;
        } catch (NoConnectionException dbExc){

        }
        assertEquals(true, caughtThirdError, "Third error was missed");

        try{
            ExtInvSys.itemIdentifierValidity(itemIdentifier4);
        } catch(IncorrectItemIdentifierException itemExc){
            caughtFourthError = false;
        } catch (NoConnectionException dbExc){
            caughtFourthError = false;
        }
        assertEquals(true, caughtFourthError, "Fourth error was falsely caught");
    }
}