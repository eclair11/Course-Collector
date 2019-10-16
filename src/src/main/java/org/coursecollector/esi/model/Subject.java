/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.coursecollector.esi.model;

import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Solofo RABONARIJAONA
 */
@Entity
@Data
public class Subject {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    
    String name;
    
    /* Correspondant list of courses */
    @OneToMany
    List<Request> requests = new ArrayList<>();

    @OneToMany
    List<Publication> publications = new ArrayList<>();
    
    public Subject(String name) {
        this.name = name;
    }
    
    public Subject(String name, List<Publication> publications) {
        this(name);
        this.publications = publications;
    }

    public Subject(String name, List<Request> requests, List<Publication> publications) {
        this(name, publications);
        this.requests = requests;
    }
    
    public Subject() {
        
    }


}
