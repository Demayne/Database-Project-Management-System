package model;

/**
 * Entity class representing a Contractor.
 * 
 * @author Demayne Govender
 * @version 2.0
 */
public class Contractor extends Person {
    
    public Contractor() {
        super();
    }
    
    public Contractor(String id, String firstName, String surname, 
                      String telephone, String email, String physicalAddress) {
        super(id, firstName, surname, telephone, email, physicalAddress);
    }
}
