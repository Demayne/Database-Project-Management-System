package model;

/**
 * Entity class representing a Person (base class for Architect, Contractor, Customer).
 * 
 * @author Demayne Govender
 * @version 2.0
 */
public abstract class Person {
    private String id;
    private String firstName;
    private String surname;
    private String telephone;
    private String email;
    private String physicalAddress;
    
    // Constructors
    public Person() {}
    
    public Person(String id, String firstName, String surname, 
                  String telephone, String email, String physicalAddress) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
        this.telephone = telephone;
        this.email = email;
        this.physicalAddress = physicalAddress;
    }
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getSurname() {
        return surname;
    }
    
    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    public String getTelephone() {
        return telephone;
    }
    
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhysicalAddress() {
        return physicalAddress;
    }
    
    public void setPhysicalAddress(String physicalAddress) {
        this.physicalAddress = physicalAddress;
    }
    
    public String getFullName() {
        return firstName + " " + surname;
    }
    
    @Override
    public String toString() {
        return String.format("%s[%s - %s %s]", 
                           getClass().getSimpleName(), id, firstName, surname);
    }
}
