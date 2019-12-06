package org.coursecollector.esi.model;

import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.CascadeType;
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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    List<Request> requests = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    List<Course> courses = new ArrayList<>();

    @ManyToMany
    List<Option> options = new ArrayList<>();

    /**
     * 
     * @param name
     */
    public Subject(String name) {
        this.name = name;
    }

    /**
     * @param String name
     * @param Option option
     */
    public Subject(String name, Option option) {
        this(name);
        this.options.add(option);
    }

    /**
     * 
     * @param name
     * @param courses
     */
    public Subject(String name, List<Course> courses) {
        this(name);
        this.courses = courses;
    }

    /**
     * 
     * @param name
     * @param requests
     * @param courses
     */
    public Subject(String name, List<Request> requests, List<Course> courses) {
        this(name, courses);
        this.requests = requests;
    }

    /**
     * 
     */
    public Subject() {

    }

}
