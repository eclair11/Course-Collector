package org.coursecollector.esi;

import java.util.Date;

import javax.inject.Inject;

import org.coursecollector.esi.model.Class;
import org.coursecollector.esi.model.ClassRepository;
import org.coursecollector.esi.model.Course;
import org.coursecollector.esi.model.CourseRepository;
import org.coursecollector.esi.model.Subject;
import org.coursecollector.esi.model.SubjectRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @Inject
    ClassRepository classRepo;

    @Inject
    SubjectRepository subjectRepo;

    @Inject
    CourseRepository courseRepo;

    @RequestMapping("/gentestdata")
    public String generateTestData(Model model) {
        Class c1 = new Class("Master", 1);
        Class c2 = new Class("Master", 2);
        classRepo.save(c1);
        classRepo.save(c2);
        Subject s1 = new Subject("IA");
        Subject s2 = new Subject("Advanced Algorithm");
        Subject s3 = new Subject("Complexity");
        Subject s4 = new Subject("Turing Machine");
        Subject s5 = new Subject("Deep Learning");
        Subject s6 = new Subject("Advanced Web");
        subjectRepo.save(s1);
        subjectRepo.save(s2);
        subjectRepo.save(s3);
        subjectRepo.save(s4);
        subjectRepo.save(s5);
        subjectRepo.save(s6);
        Course r1 = new Course("Prolog", new Date());
        Course r2 = new Course("Prop. Logic", new Date());
        Course r3 = new Course("First Order Logic", new Date());
        Course r4 = new Course("Dynamic Programing", new Date());
        Course r5 = new Course("Greedy Programing", new Date());
        Course r6 = new Course("Division Programing", new Date());
        courseRepo.save(r1);
        courseRepo.save(r2);
        courseRepo.save(r3);
        courseRepo.save(r4);
        courseRepo.save(r5);
        courseRepo.save(r6);
        return "redirect:/classes";
    }

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

    @RequestMapping("/courses")
    public String listCourses(Model model) {
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
