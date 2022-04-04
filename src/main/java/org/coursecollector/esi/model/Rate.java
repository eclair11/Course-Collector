package org.coursecollector.esi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

/**
 *
 * @author Elias ROMDAN
 */
@Data
@Entity
public class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    Boolean dislike;

    @ManyToOne
    Course course;

    @ManyToOne
    Student student;

    public Rate(Boolean dislike, Course course) {
        this.dislike = dislike;
        this.course = course;
    }

    public Rate(Boolean dislike, Course course, Student student) {
        this(dislike, course);
        this.student = student;
    }

    public Rate() {

    }

}
