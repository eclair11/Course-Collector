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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

@Controller
public class MainController {
    
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

    @RequestMapping("/")
    public String pageAccueil() {
        return "index";
    }

    @RequestMapping("/classes")
    public String listClasses(Model model) {
        Iterable<Class> classes = classRepo.findAll();
        Iterable<Subject> subjects = subjectRepo.findAll();
        model.addAttribute("classes", classes);
        model.addAttribute("subjects", subjects);
        return "class";
    }
    
    @RequestMapping("/course")
    public String listCourses(Model model, @RequestParam Long subjectId) {
        // fetch the subject that correspond to the id 
        Subject subject = subjectRepo.findById(subjectId).get();
        // send the subject in the view
        model.addAttribute("subject", subject);
        return "course";
    }

    @RequestMapping("/content")
    public String listCoursesContent(Model model) {
        return "content";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @RequestMapping("/request-success")
    public String confirmation(Model model) {
        return "request-success";
    }

    @RequestMapping("/setting")
    public String setting(Model model) {
        return "setting";
    }

    @RequestMapping("/publish-success")
    public String publishSuccess() {
        return "publish-success";
    }

}
