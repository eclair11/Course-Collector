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

    Boolean like;

    @ManyToOne
    Publication publication;

    @ManyToOne
    Student student;

    public Rate(Boolean like, Publication publication, Student student) {
        this.like = like;
        this.publication = publication;
        this.student = student;
    }

    public Rate() {

    }

}