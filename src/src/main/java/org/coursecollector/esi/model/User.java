package org.coursecollector.esi.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;
import lombok.Data;

/**
 *
 * @author Ibrahima DIALLO
 */
@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    String role;

    String password;

    /**
     * 
     * @param role
     * @param password
     */
    public User(String role, String password) {
        this.role = role;
        this.password = password;
    }

    /**
     * 
     */
    public User() {

    }

}
