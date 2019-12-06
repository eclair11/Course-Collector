package org.coursecollector.esi.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Subject> subjects = new ArrayList<>();

    @ManyToMany
    List<Class> classes = new ArrayList<>();

    /**
     * 
     * @param name
     */
    public Option(String name) {
        this.name = name;
    }

    /**
     * 
     */
    public Option() {

    }

}
