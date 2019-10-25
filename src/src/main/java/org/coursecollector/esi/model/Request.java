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
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Temporal(TemporalType.DATE)
    Date dateCourse;

    @ManyToOne
    Student student;

    @OneToOne
    Course course;

    @Transient
    Long subjectId;

    public Request(Date dateCourse, Student student) {
        this.student = student;
        this.dateCourse = dateCourse;
    }

    public Request() {

    }

    // surcharged setters
    public void setDateCourse(String date) {
        int year = Integer.parseInt(date.split("-")[0]);
        int month = Integer.parseInt(date.split("-")[1]);
        int day = Integer.parseInt(date.split("-")[2]);
        this.dateCourse = new Date(year - 1900, month, day);
    }

}
