package org.coursecollector.esi;

import javax.inject.Inject;

import org.coursecollector.esi.model.Class;
import org.coursecollector.esi.model.ClassRepository;
import org.coursecollector.esi.model.CourseRepository;
import org.coursecollector.esi.model.RequestRepository;
import org.coursecollector.esi.model.StudentRepository;
import org.coursecollector.esi.model.SubjectRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

    /**
     * 
     * @param model
     * @return
     */
    @RequestMapping("/admin")
    public String page(Model model) {
        // get notifications that concern the student
        model.addAttribute("notifications",
                MainController.listNotifications(studentRepo, TestController.defaultStudentId));
        // get all class
        Iterable<Class> classes = classRepo.findAll();
        // send list of all class in the ModelView
        model.addAttribute("classes", classes);
        return "admin";
    }

    /**
     * 
     * @param model
     * @return
     */
    @RequestMapping("/users")
    public String listUsers(Model model) {
        // get notifications that concern the student
        model.addAttribute("notifications",
                MainController.listNotifications(studentRepo, TestController.defaultStudentId));
        return "user";
    }

    /**
     * 
     * @param model
     * @return
     */
    @RequestMapping("/statut")
    public String changeStatut(Model model) {
        // get notifications that concern the student
        model.addAttribute("notifications",
                MainController.listNotifications(studentRepo, TestController.defaultStudentId));
        return "change-statut";
    }

}
