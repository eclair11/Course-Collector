package org.coursecollector.esi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import javax.persistence.ManyToMany;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Solofo RABONARIJAONA
 */
@Entity
@Data
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    String name;
    
    @ManyToMany
    List<Subject> subjects = new ArrayList<>();

    public Option(String name) {
        this.name = name;
    }

    public Option() {

    }

}
