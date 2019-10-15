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
@Data
@Entity
public class Class {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    
    String name;
    int level;
    
    @OneToMany
    List <Option> options = new ArrayList<>();
    
    public Class(String name, int level) {
        this.name = name;
        this.level = level;
    }
    
    public Class(String name, int level, List<Option> options) {
        this(name, level);
        this.options = options;
    }
    
    public Class() {
        
    }

}
