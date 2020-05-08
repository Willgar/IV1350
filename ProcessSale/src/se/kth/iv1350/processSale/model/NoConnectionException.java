package se.kth.iv1350.processSale.model;

/**
 * An exception that is thrown when the system can not connect to the database.
 */
public class NoConnectionException extends Exception {
    public NoConnectionException(String msg, Exception cause){
        super(msg, cause);
    }
}
