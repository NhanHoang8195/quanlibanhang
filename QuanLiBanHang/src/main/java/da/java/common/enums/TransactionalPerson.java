package da.java.common.enums;

/**
 * Define type of mudule send an order to the server
 */
public enum TransactionalPerson {
    /** Customer send an order.
     * Set OperationStatus of Order to NEW.
     * CUSTOMER: Someone orders from browsers
     * */
    CUSTOMER,
    
    /**Branch send request to handle order of a customer. 
     * Change OperationStatus of Order to PROCESSING.
     * VENDER:bo phan xu  li don hang
     * */
    VENDER
}
