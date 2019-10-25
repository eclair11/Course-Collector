package org.coursecollector.esi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

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

    public Option(String name) {
        this.name = name;
    }

    public Option() {

    }

}
