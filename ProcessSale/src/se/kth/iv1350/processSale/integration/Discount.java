package se.kth.iv1350.processSale.integration;

/**
 * A discount that is applicable to a <code>Transaction</code>, depending on the customers ID.
 */
public class Discount {
    /**
     * If the customer is eligible for an discount, then it will return the discount rate.
     * @param customerID The value being checked to see if eligble and if so, for how much.
     * @return Returns the rate which the price will be adjusted after.
     */
    static public double getDiscountRate(int customerID){
        if(checkDiscountEligibility(customerID)){
            return discountPercentage(customerID);
        }
        else return 1;
    }

    /**
     * Checks if the customer is eligble for a discount.
     * @param customerID The value being checked.
     * @return Returns a true or false statement depending on the result.
     */
    static private boolean checkDiscountEligibility(int customerID){
        return OddOrEvenCustomerID(customerID);
    }

    /**
     * Checks if the customer matches the requirements.
     * @param customerID The value being checked.
     * @return Returns true or false depending on the result.
     */
    static private boolean OddOrEvenCustomerID(int customerID){
        return customerID % 2 == 1;
    }

    /**
     * Determines how large discount rate the customer will receive.
     * @param customerID The value being checked.
     * @return Returns the discount rate.
     */
    static private double  discountPercentage(int customerID){
        double step1 = (customerID%10);
        double step2 = step1/100;
        double step3 = 1-step2;
        return step3;
    }
}
