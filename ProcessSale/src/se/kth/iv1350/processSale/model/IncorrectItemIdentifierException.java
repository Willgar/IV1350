package se.kth.iv1350.processSale.model;

/**
 * An exception that is thrown when the item identifier value does not meet the correct criteria.
 */
public class IncorrectItemIdentifierException extends Exception {
    /**
     * Creates a new exception
     */
    public IncorrectItemIdentifierException(String msg, Exception cause) {
        super(msg, cause);
    }
}
