package org.coursecollector.esi.model;

import javax.persistence.Entity;

/**
 *
 * @author Ibrahima DIALLO
 */
@Entity
public class Admin extends User {

    String pseudo;

    /**
     * 
     * @param pseudo
     */
    public Admin(String pseudo) {
        this.pseudo = pseudo;
    }

    /**
     * 
     * @param role
     * @param password
     * @param pseudo
     */
    public Admin(String role, String password, String pseudo) {
        super(role, password);
        this.pseudo = pseudo;
    }

    /**
     * 
     */
    public Admin() {

    }

}
