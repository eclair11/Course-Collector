package org.coursecollector.esi.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
public class Publication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Temporal(TemporalType.DATE)
    Date date;

    @OneToOne
    Course course;

    @ManyToOne
    Student student;

    public Publication(Course course, Student student) {
        this.course = course;
        this.student = student;
        this.date = new Date();
    }

    public Publication() {

    }

}