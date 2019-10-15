/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.coursecollector.esi.model;

import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    
     @Temporal(TemporalType.DATE)
     Date dateCourse;
     
     /* Correspondant list of contents */
     @OneToMany
     List<Content> contents = new ArrayList<>();
     
     public Course(String name, Date dateCourse) {
         this.name = name;
         this.dateCourse = dateCourse;
     }
     
     public Course(String name, Date dateCourse, List<Content> contents) {
         this(name, dateCourse);
         this.contents = contents;
     }
     
     public Course() {
         
     }
    
    
}
