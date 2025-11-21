package model;

/**
 * Entity class representing a Customer.
 * 
 * @author Demayne Govender
 * @version 2.0
 */
public class Customer extends Person {
    
    public Customer() {
        super();
    }
    
    public Customer(String id, String firstName, String surname, 
                    String telephone, String email, String physicalAddress) {
        super(id, firstName, surname, telephone, email, physicalAddress);
    }
}
