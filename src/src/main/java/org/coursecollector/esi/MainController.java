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
import org.coursecollector.esi.model.CourseRepository;
import org.coursecollector.esi.model.Publication;
import org.coursecollector.esi.model.PublicationRepository;
import org.coursecollector.esi.model.Request;
import org.coursecollector.esi.model.RequestRepository;
import org.coursecollector.esi.model.Student;
import org.coursecollector.esi.model.StudentRepository;
import org.coursecollector.esi.model.Subject;
import org.coursecollector.esi.model.SubjectRepository;
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
    PublicationRepository publicationRepo;

    @Inject
    CourseRepository courseRepo;
    
    @Inject
    RequestRepository requestRepo;
    
    
    private static final int MAX_NOTIFICATION_PER_STUDENT = 300;
    

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
        model.addAttribute("student", student);
        model.addAttribute("class", listClass);
        // get notifications that concern the student
        model.addAttribute("notifications", MainController.listNotifications(studentRepo, TestController.defaultStudentId));
        return "class";
    }

    @RequestMapping("/course")
    public String listCourses(Model model, @RequestParam Long studentId, @RequestParam Long subjectId) {
        Student student = studentRepo.findById(studentId).get();
        Subject subject = subjectRepo.findById(subjectId).get();
        Publication publication = new Publication();
        // bound new Request that will permit to add request using form in modal
        Request boundedReq = new Request();
        boundedReq.setSubjectId(subject.getId());
        model.addAttribute("student", student);
        model.addAttribute("subject", subject);
        model.addAttribute("publication", publication);
        model.addAttribute("request", boundedReq);
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
        model.addAttribute("notifications", MainController.listNotifications(studentRepo, TestController.defaultStudentId));
        return "request-success";
    }

    @RequestMapping("/setting")
    public String setting(Model model) {
        // get notifications that concern the student
        model.addAttribute("notifications", MainController.listNotifications(studentRepo, TestController.defaultStudentId));
        return "setting";
    }
        
    @RequestMapping(value = "/publish-success", method = RequestMethod.POST)
    public String publishSuccess(Model model, @ModelAttribute("publication") Publication publication) {
        // get notifications that concern the student
        model.addAttribute("notifications", MainController.listNotifications(studentRepo, TestController.defaultStudentId));
        return this.multiFileUpload(model, publication);
    }

    private String multiFileUpload(Model model, Publication publication) {
        String uploadRootPath = "./src/main/resources/static/img/courses/";
        File uploadRootDir = new File(uploadRootPath);
        if (!uploadRootDir.exists()) {
            uploadRootDir.mkdirs();
        }

        MultipartFile[] fileDatas = publication.getFiles();
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
                    linkedFiles.add(serverFile.getAbsolutePath());
                } catch (Exception e) {
                    System.out.println("Error Write file: " + name);
                    failedFiles.add(name);
                }
            }
        }

        publication.setLinks(linkedFiles);
        publicationRepo.save(publication);
        model.addAttribute("failedFiles", failedFiles);
        return "publish-success";
    }

}
