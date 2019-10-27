package org.coursecollector.esi.model;

import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Solofo RABONARIJAONA
 */
@Entity
@Data
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    Date dateCourse;

    String commentary;

    @ManyToOne
    Student student;
    
    @Transient
    Long subjectId;

    @OneToMany
    List<Rate> rates = new ArrayList<>();

    @Transient
    int numberLike;

    @Transient
    int numberDislike;

    @Transient
    MultipartFile[] files;

    @ElementCollection(targetClass = String.class)
    List<String> links = new ArrayList<>();

    /**
     * 
     * @param name
     * @param dateCourse
     */
    public Course(String name, Date dateCourse) {
        this.name = name;
        this.dateCourse = dateCourse;
    }

    /**
     * 
     */
    public Course() {

    }

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
