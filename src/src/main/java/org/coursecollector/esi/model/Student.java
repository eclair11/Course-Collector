package org.coursecollector.esi.model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Ibrahima DIALLO
 * @author Solofo R.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Student extends User {

    @Transient
    Long classId;

    String option;

    int level;

    @ManyToMany
    List<Class> classes = new ArrayList<>();

    /**
     * @param String
     * @param String
     * @param int
     */
    public Student(String username, String option, int level) {
        super(username);
        this.option = option;
        this.level = level;
    }

    /**
     * 
     * @param role
     * @param password
     * @param firstName
     * @param lastName
     * @param option
     * @param level
     * @param classes
     */
    public Student(String username, String option, int level, List<Class> classes) {
        this(username, option, level);
        this.classes = classes;
    }

    /**
     * 
     */
    public Student() {

    }

}
