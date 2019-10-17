/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.coursecollector.esi;


import java.util.Date;

import javax.inject.Inject;

import org.coursecollector.esi.model.Student;
import org.coursecollector.esi.model.StudentRepository;
import org.coursecollector.esi.model.Class;
import org.coursecollector.esi.model.ClassRepository;
import org.coursecollector.esi.model.Course;
import org.coursecollector.esi.model.PublicationRepository;
import org.coursecollector.esi.model.Publication;
import org.coursecollector.esi.model.CourseRepository;
import org.coursecollector.esi.model.Subject;
import org.coursecollector.esi.model.SubjectRepository;
import org.coursecollector.esi.model.Rate;
import org.coursecollector.esi.model.RateRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.ArrayList;
import java.util.Arrays;


@Controller
public class TestController {
    
    @Inject
    StudentRepository studentRepo;

    @Inject
    ClassRepository classRepo;

    @Inject
    SubjectRepository subjectRepo;
    
    @Inject
    PublicationRepository publicationRepo;

    @Inject
    CourseRepository courseRepo;
    
    @Inject
    RateRepository rateRepo;
    
    @RequestMapping("/test-data")
    public String generateTestData(Model model) {
        
        // Create Students test 
        Student[] students = {
            new Student("USER", "1234", "Lebron", "James", "Informatique", 4), 
            new Student("USER", "1234", "Kobe", "Bryant", "Informatique", 4)};
        // Save all Students in DB
        for (int i = 0; i < students.length; i++) {
            studentRepo.save(students[i]);
        }
        
        // Create Course test
        Course r1 = new Course("Prolog", new Date());
        Course r2 = new Course("Prop. Logic", new Date());
        Course r3 = new Course("First Order Logic", new Date());
        Course r4 = new Course("Dynamic Programing", new Date());
        Course r5 = new Course("Greedy Programing", new Date());
        Course r6 = new Course("Division Programing", new Date());
        // save all Course in DB
        courseRepo.save(r1);
        courseRepo.save(r2);
        courseRepo.save(r3);
        courseRepo.save(r4);
        courseRepo.save(r5);
        courseRepo.save(r6);
        
        // Create Publication test
        Publication[] publications = {
            new Publication(r1, students[0]),
            new Publication(r2, students[1]),
            new Publication(r3, students[0]),
            new Publication(r4, students[1]),
            new Publication(r5, students[0]),
            new Publication(r6, students[1])};
        // Save all Publications in DB
        for (int i = 0; i < publications.length; i++) {
            publicationRepo.save(publications[i]);
        }
        
        // Create some Rate 
        Rate[] rates = {
            new Rate(true, publications[0], students[0]),
            new Rate(false, publications[0], students[1])
        };
        // save all Rates in DB
        for (int i = 0; i < rates.length; i++) {
            rateRepo.save(rates[i]);
        }
        
        // add Rates for the first publication
        publications[0].setRates(new ArrayList<Rate>(Arrays.asList(rates)));
        // update this publication in DB
        publicationRepo.save(publications[0]);
        
        // Create Subject Test
        Subject s1 = new Subject("IA", new ArrayList<Publication>(Arrays.asList(publications))); // save all publications in Subject IA
        Subject s2 = new Subject("Advanced Algorithm");
        Subject s3 = new Subject("Complexity");
        Subject s4 = new Subject("Turing Machine");
        Subject s5 = new Subject("Deep Learning");
        Subject s6 = new Subject("Advanced Web");
        // Save all subject in DB
        subjectRepo.save(s1);
        subjectRepo.save(s2);
        subjectRepo.save(s3);
        subjectRepo.save(s4);
        subjectRepo.save(s5);
        subjectRepo.save(s6);

        Class c1 = new Class("Master", 1);
        Class c2 = new Class("Master", 2);
        c1.setSubjects(Arrays.asList(s1, s2, s3));
        c2.setSubjects(Arrays.asList(s4, s5, s6));
        classRepo.save(c1);
        classRepo.save(c2);
        
        return "redirect:/classes";
    }

}
