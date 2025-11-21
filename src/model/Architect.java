package model;

/**
 * Entity class representing an Architect.
 * 
 * @author Demayne Govender
 * @version 2.0
 */
public class Architect extends Person {
    
    public Architect() {
        super();
    }
    
    public Architect(String id, String firstName, String surname, 
                     String telephone, String email, String physicalAddress) {
        super(id, firstName, surname, telephone, email, physicalAddress);
    }
}
