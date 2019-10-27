package org.coursecollector.esi;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

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
import org.coursecollector.esi.model.Option;
import org.coursecollector.esi.model.OptionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MainController {

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
    
    @Inject
    OptionRepository optionRepo;

    private static final int MAX_NOTIFICATION_PER_STUDENT = 300;

    @RequestMapping("/")
    public String pageAccueil(Model model) {
        // get notifications that concern the student
        model.addAttribute("notifications",
                MainController.listNotifications(studentRepo, TestController.defaultStudentId));
        return "index";
    }

    @RequestMapping("/class")
    public String listClasses(Model model) {
        Student student = studentRepo.findById(TestController.defaultStudentId).get();
        List<Class> listClass = student.getClasses();
        model.addAttribute("class", listClass);
        // get notifications that concern the student
        model.addAttribute("notifications",
                MainController.listNotifications(studentRepo, TestController.defaultStudentId));
        return "class";
    }

    @RequestMapping("/course")
    public String listCourses(Model model, @RequestParam Long subjectId) {
        Subject subject = subjectRepo.findById(subjectId).get();
        Iterable<Request> requests = requestRepo.findAll();
        Course course = new Course();
        Request boundedReq = new Request();
        boundedReq.setSubjectId(subject.getId());
        model.addAttribute("subject", subject);
        model.addAttribute("actreq", requests);
        model.addAttribute("course", course);
        model.addAttribute("request", boundedReq);
        // get notifications that concern the student
        model.addAttribute("notifications",
                MainController.listNotifications(studentRepo, TestController.defaultStudentId));
        return "course";
    }
    
    /**
     * @author s.rabonarijaona
     * 
     * Get list of class where the student is subscribed
     * Get the option that concern the student
     * then go through all the subject in this option
     * and finally get all request of each of those subject
     * 
     * @return String[][] - all the notification that concern the student
     */
    public static String[][] listNotifications(StudentRepository studentRepo, Long studentId) {
        int numberNotificationInfos = 4;
        int notifCounter = 0;
        String[][] notifications = new String[MAX_NOTIFICATION_PER_STUDENT][numberNotificationInfos];
        Student student = studentRepo.findById(studentId).get();
        List<Class> studentClasses = student.getClasses();
        for (int i = 0; i < studentClasses.size(); i++) {
            List<Option> options = studentClasses.get(i).getOptions();
            for (int j = 0; j < options.size(); j++) {
                if (options.get(j).getName() == student.getOption()) {
                    List<Subject> subjects = options.get(j).getSubjects();
                    for (int k = 0; k < subjects.size(); k++) {
                        List<Request> requests = subjects.get(k).getRequests();
                        for (int l = 0; l < requests.size(); l++) {
                            notifications[notifCounter][0] = "" + subjects.get(k).getId();
                            notifications[notifCounter][1] = studentClasses.get(i).getName() + " "
                                + studentClasses.get(i).getLevel() + " " + options.get(j).getName();
                            notifications[notifCounter][2] = subjects.get(k).getName();
                            notifications[notifCounter][3] = requests.get(l).getDateCourse().toString();
                            notifCounter++;
                        }
                    }
                }
            }
        }

        // return extracted notifications
        return Arrays.copyOfRange(notifications, 0, notifCounter);
    }

    @RequestMapping("/content")
    public String listCoursesContent(Model model, @RequestParam Long courseId) {
        Course course = courseRepo.findById(courseId).get();
        model.addAttribute("course", course);
        // get notifications that concern the student
        model.addAttribute("notifications",
                MainController.listNotifications(studentRepo, TestController.defaultStudentId));
        return "content";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        // get notifications that concern the student
        model.addAttribute("notifications",
                MainController.listNotifications(studentRepo, TestController.defaultStudentId));
        return "login";
    }

    @PostMapping("/addRequest")
    public String addRequest(Model model, @ModelAttribute Request newRequest) {
        // save the new request in DB
        requestRepo.save(newRequest);
        // get the subject
        Subject subject = subjectRepo.findById(newRequest.getSubjectId()).get();
        // add new request to the current subject
        subject.getRequests().add(newRequest);
        // update subject
        subjectRepo.save(subject);
        return "redirect:/request-success";
    }

    @RequestMapping("/request-success")
    public String confirmation(Model model) {
        // get notifications that concern the student
        model.addAttribute("notifications",
                MainController.listNotifications(studentRepo, TestController.defaultStudentId));
        return "request-success";
    }

    @RequestMapping("/setting")
    public String setting(Model model) {
        // get notifications that concern the student
        model.addAttribute("notifications",
                MainController.listNotifications(studentRepo, TestController.defaultStudentId));
        return "setting";
    }

    @RequestMapping(value = "/publish-success", method = RequestMethod.POST)
    public String publishSuccess(Model model, @ModelAttribute("course") Course course) {
        // get notifications that concern the student
        model.addAttribute("notifications",
                MainController.listNotifications(studentRepo, TestController.defaultStudentId));
        return this.multiFileUpload(model, course);
    }

    private String multiFileUpload(Model model, Course course) {
        String uploadRootPath = "./src/main/resources/static/img/courses/";
        String imgFolderPath = "img/courses/";
        File uploadRootDir = new File(uploadRootPath);
        if (!uploadRootDir.exists()) {
            uploadRootDir.mkdirs();
        }

        MultipartFile[] fileDatas = course.getFiles();
        List<String> linkedFiles = new ArrayList<String>();
        List<String> failedFiles = new ArrayList<String>();

        for (MultipartFile fileData : fileDatas) {
            String name = fileData.getOriginalFilename();
            if (name != null && name.length() > 0) {
                try {
                    File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                    stream.write(fileData.getBytes());
                    stream.close();
                    linkedFiles.add(imgFolderPath + name);
                } catch (Exception e) {
                    System.out.println("Error Write file: " + name);
                    failedFiles.add(name);
                }
            }
        }

        course.setStudent(studentRepo.findById(TestController.defaultStudentId).get());
        course.setLinks(linkedFiles);
        courseRepo.save(course);
        model.addAttribute("failedFiles", failedFiles);
        return "publish-success";
    }

}
