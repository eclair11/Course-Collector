/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.coursecollector.esi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.inject.Inject;

import org.coursecollector.esi.model.Class;
import org.coursecollector.esi.model.ClassRepository;
import org.coursecollector.esi.model.Course;
import org.coursecollector.esi.model.CourseRepository;
import org.coursecollector.esi.model.Publication;
import org.coursecollector.esi.model.PublicationRepository;
import org.coursecollector.esi.model.Student;
import org.coursecollector.esi.model.StudentRepository;
import org.coursecollector.esi.model.Subject;
import org.coursecollector.esi.model.SubjectRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/test-data")
    public String generateTestData(Model model) {

        // Create Course test
        Course[] courses = { new Course("Prolog", new Date()), new Course("Prop. Logic", new Date()),
                new Course("First Order Logic", new Date()), new Course("Dynamic Programing", new Date()),
                new Course("Greedy Programing", new Date()), new Course("Division Programing", new Date()) };

        // save all Course in DB
        for (int i = 0; i < courses.length; i++) {
            courseRepo.save(courses[i]);
        }

        // Create Publication test
        Publication[] publications = { new Publication(), new Publication(), new Publication(), new Publication(),
                new Publication(), new Publication() };

        // Save all Publications in DB
        for (int i = 0; i < publications.length; i++) {
            publicationRepo.save(publications[i]);
        }

        // Create Subject Test
        // save all publications in Subject IA
        Subject[] subjects = { new Subject("IA"), new Subject("Advanced Algorithm"), new Subject("Complexity"),
                new Subject("Turing Machine"), new Subject("Deep Learning"), new Subject("Advanced Web") };

        // Save all subject in DB
        for (int i = 0; i < subjects.length; i++) {
            subjectRepo.save(subjects[i]);
        }

        // Create Class Test
        Class[] classes = { new Class("Master", 1), new Class("Master", 2) };
        classes[0].setSubjects(Arrays.asList(subjects[0], subjects[1], subjects[2]));
        classes[1].setSubjects(Arrays.asList(subjects[3], subjects[4], subjects[5]));

        // Save all classes in DB
        for (int i = 0; i < classes.length; i++) {
            classRepo.save(classes[i]);
        }

        // Create Students test
        Student[] students = { new Student("USER", "1234", "Lebron", "James", "Informatique", 4),
                new Student("USER", "1234", "Kobe", "Bryant", "Informatique", 4) };

        students[0].setClasses(Arrays.asList(classes[0]));
        students[1].setClasses(Arrays.asList(classes[1]));

        // Save all Students in DB
        for (int i = 0; i < students.length; i++) {
            studentRepo.save(students[i]);
        }

        return "redirect:/login";
    }

}
