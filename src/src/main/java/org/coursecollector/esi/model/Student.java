package org.coursecollector.esi.model;

import javax.persistence.Entity;


/**
 *
 * @author Ibrahima DIALLO
 */


@Entity
public class Student  extends User{
    String firsnameStudent;
    String lastnameStudent;
    String option;
    int level;


    public Student(String firstnameStudent, String lastnameStudent, String option, int level) {
        this.firsnameStudent=firstnameStudent;
        this.lastnameStudent=lastnameStudent;
        this.option=option;
        this.level=level;
    }


    public Student(String role, String password, String firstnameStudent, String lastnameStudent, String option, int level) {
        super(role, password);
        this.firsnameStudent=firstnameStudent;
        this.lastnameStudent=lastnameStudent;
        this.option=option;
        this.level=level;
    }


    public Student(){

    }


}