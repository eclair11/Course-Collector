package org.coursecollector.esi;

import java.util.List;
import java.util.Arrays;

import javax.inject.Inject;

import org.coursecollector.esi.model.Class;
import org.coursecollector.esi.model.ClassRepository;
import org.coursecollector.esi.model.CourseRepository;
import org.coursecollector.esi.model.PublicationRepository;
import org.coursecollector.esi.model.Student;
import org.coursecollector.esi.model.StudentRepository;
import org.coursecollector.esi.model.Subject;
import org.coursecollector.esi.model.SubjectRepository;
import org.coursecollector.esi.model.Request;
import org.coursecollector.esi.model.RequestRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    
    @Inject
    RequestRepository requestRepo;
    
    
    private static final int MAX_NOTIFICATION_PER_STUDENT = 100;
    

    @RequestMapping("/")
    public String pageAccueil(Model model) {
        // get notifications that concern the student
        model.addAttribute("notifications", MainController.listNotifications(studentRepo, TestController.defaultStudentId));
        return "index";
    }

    @RequestMapping("/class")
    public String listClasses(Model model, @RequestParam Long studentId) {
        Student student = studentRepo.findById(studentId).get();
        List<Class> listClass = student.getClasses();
        model.addAttribute("class", listClass);
        // get notifications that concern the student
        model.addAttribute("notifications", MainController.listNotifications(studentRepo, TestController.defaultStudentId));
        return "class";
    }

    @RequestMapping("/course")
    public String listCourses(Model model, @RequestParam Long subjectId) {
        // fetch the subject that correspond to the id
        Subject subject = subjectRepo.findById(subjectId).get();
        // send the subject in the view
        model.addAttribute("subject", subject);
        // get notifications that concern the student
        model.addAttribute("notifications", MainController.listNotifications(studentRepo, TestController.defaultStudentId));
        return "course";
    }
    

    public static String[][] listNotifications(StudentRepository studentRepo, Long studentId) {
        int numberNotificationInfos = 4;
        int notifCounter = 0;
        String[][] notifications = new String[MAX_NOTIFICATION_PER_STUDENT][numberNotificationInfos];
        Student student = studentRepo.findById(studentId).get();
        List<Class> studentClasses = student.getClasses();
        for (int i = 0; i < studentClasses.size(); i++) {
            List<Subject> subjects = studentClasses.get(i).getSubjects();
            for (int j = 0; j < subjects.size(); j++) {
                List<Request> requests = subjects.get(j).getRequests();
                for (int k = 0; k < requests.size(); k++) {
                    notifications[notifCounter][0] = "" + subjects.get(j).getId();
                    notifications[notifCounter][1] = studentClasses.get(i).getName() + " " + studentClasses.get(i).getLevel();
                    notifications[notifCounter][2] = subjects.get(j).getName();
                    notifications[notifCounter][3] = requests.get(k).getDateCourse().toString();
                    notifCounter++;
                }
            }
        }
        
        // return extracted notifications
        return Arrays.copyOfRange(notifications, 0, notifCounter);
    }

    @RequestMapping("/content")
    public String listCoursesContent(Model model) {
        // get notifications that concern the student
        model.addAttribute("notifications", MainController.listNotifications(studentRepo, TestController.defaultStudentId));
        return "content";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        // get notifications that concern the student
        model.addAttribute("notifications", MainController.listNotifications(studentRepo, TestController.defaultStudentId));
        return "login";
    }

    @RequestMapping("/request-success")
    public String confirmation(Model model) {
        // get notifications that concern the student
        model.addAttribute("notifications", MainController.listNotifications(studentRepo, TestController.defaultStudentId));
        return "request-success";
    }

    @RequestMapping("/setting")
    public String setting(Model model) {
        // get notifications that concern the student
        model.addAttribute("notifications", MainController.listNotifications(studentRepo, TestController.defaultStudentId));
        return "setting";
    }

    @RequestMapping("/publish-success")
    public String publishSuccess(Model model) {
        // get notifications that concern the student
        model.addAttribute("notifications", MainController.listNotifications(studentRepo, TestController.defaultStudentId));
        return "publish-success";
    }

}
