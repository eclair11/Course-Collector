package org.coursecollector.esi.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
@Entity
public class Publication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String date;

    String commentary;

    @ElementCollection(targetClass = String.class)
    List<String> links = new ArrayList<>();

    @OneToOne
    Course course;

    @ManyToOne
    Student student;

    /* List of rate given for this publication */
    @OneToMany
    List<Rate> rates = new ArrayList<>();

    /* number of likes */
    @Transient // not saved in DB
    int numberLike;

    /* number of dislikes */
    @Transient // not saved in DB
    int numberDislike;

    @Transient
    MultipartFile[] files;

    public Publication(Course course) {
        this.course = course;
    }

    public Publication(Course course, Student student) {
        this.course = course;
        this.student = student;
    }

    public Publication(Course course, Student student, List<Rate> rates) {
        this(course, student);
        this.rates = rates;
    }

    public Publication() {

    }

    /* SPECIFIC METHODS */

    /**
     * Calculate the number of likes and dislikes based on the list of Rate
     */
    private void calculateLikeDislike() {
        int numberDislike = 0;
        for (int i = 0; i < this.rates.size(); i++) {
            numberDislike += (rates.get(i).dislike) ? 1 : 0;
        }
        this.numberDislike = numberDislike;
        this.numberLike = this.rates.size() - numberDislike;
    }

    /* Overriden GETTERS */

    /**
     * Override getter for numberDislike to calculate it number before
     */
    public int getNumberDislike() {
        this.calculateLikeDislike();
        return this.numberDislike;
    }

    /**
     * Override getter for numberLike to calculate it number before
     */
    public int getNumberLike() {
        this.calculateLikeDislike();
        return this.numberLike;
    }
}