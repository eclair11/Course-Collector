package org.coursecollector.esi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    Boolean dislike;

    @ManyToOne
    Publication publication;

    @ManyToOne
    Student student;

    public Rate(Boolean dislike, Publication publication, Student student) {
        this.dislike = dislike;
        this.publication = publication;
        this.student = student;
    }

    public Rate() {

    }

}