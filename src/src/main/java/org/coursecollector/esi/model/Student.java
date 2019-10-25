package org.coursecollector.esi.model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Ibrahima DIALLO
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Student extends User {

    String firstName;

    String lastName;

    String option;

    int level;

    @ManyToMany
    List<Class> classes = new ArrayList<>();

    public Student(String role, String password, String firstName, String lastName, String option, int level) {
        super(role, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.option = option;
        this.level = level;
    }

    public Student(String role, String password, String firstName, String lastName, String option, int level,
            List<Class> classes) {
        this(role, password, firstName, lastName, option, level);
        this.classes = classes;
    }

    public Student() {

    }

}
