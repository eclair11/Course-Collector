/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.coursecollector.esi;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.coursecollector.esi.model.Class;
import org.coursecollector.esi.model.ClassRepository;
import org.coursecollector.esi.model.Course;
import org.coursecollector.esi.model.CourseRepository;
import org.coursecollector.esi.model.Request;
import org.coursecollector.esi.model.RequestRepository;
import org.coursecollector.esi.model.Student;
import org.coursecollector.esi.model.StudentRepository;
import org.coursecollector.esi.model.Subject;
import org.coursecollector.esi.model.SubjectRepository;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 *
 * @author nerd
 */
@Controller
public class AdminController {
    
    @Inject
    StudentRepository studentRepo;

    @Inject
    ClassRepository classRepo;

    @Inject
    SubjectRepository subjectRepo;

    @Inject
    CourseRepository courseRepo;

    @Inject
    RequestRepository requestRepo;

    @RequestMapping("/admin")
    public String page(Model model) {
        // get notifications that concern the student
        model.addAttribute("notifications", MainController.listNotifications(studentRepo, TestController.defaultStudentId));
        // get all class 
        Iterable<Class> classes = classRepo.findAll();
        // send list of all class in the ModelView
        model.addAttribute("classes", classes);
        return "admin";
    }

    @RequestMapping("/users")
    public String listUsers(Model model) {
        // get notifications that concern the student
        model.addAttribute("notifications", MainController.listNotifications(studentRepo, TestController.defaultStudentId));
        return "user";
    }


    @RequestMapping("/statut")
    public String changeStatut(Model model) {
        // get notifications that concern the student
        model.addAttribute("notifications", MainController.listNotifications(studentRepo, TestController.defaultStudentId));
        return "change-statut";
    }

}
