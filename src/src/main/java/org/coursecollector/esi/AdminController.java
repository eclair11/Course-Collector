package org.coursecollector.esi;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    
    @Inject
    OptionRepository optionRepo;

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
     * Add new class API
     * @author Solofo R.
     * @param @RequestParam String className
     * @param @RequestParam int classLevel
     * @return String
     */
    @PostMapping("/addClass")
    public String addNewClass(@RequestParam String className, @RequestParam int classLevel) {
        Class newClass = new Class(className, classLevel);
        // save new class in DB
        classRepo.save(newClass);
        // add new class to the list of classes of the default student
        Student student = studentRepo.findById(TestController.defaultStudentId).get();
        student.getClasses().add(newClass);
        // update student
        studentRepo.save(student);
        
        return "redirect:/admin#bottom";
    }
    
    /**
     * Update class API
     * @author Solofo R.
     * @param @RequestParam Long classId
     * @param @RequestParam String newClassName
     * @param @RequestParam int newClassLevel
     * @return String
     */
    @PostMapping("/updateClass")
    public String updateClass(@RequestParam Long classId, @RequestParam String newClassName, @RequestParam int newClassLevel) {
        Class currentClass = classRepo.findById(classId).get();
        currentClass.setName(newClassName);
        currentClass.setLevel(newClassLevel);
        // update class
        classRepo.save(currentClass);
        
        return "redirect:/admin#" + classId;
    }
    
    /**
     * Delete class API
     * @author Solofo R.
     * @param @RequestParam Long classId
     * @return String
     */
    @RequestMapping("/deleteClass")
    public String deleteClass(@RequestParam Long classId) {
        classRepo.deleteById(classId);
        
        return "redirect:/admin";
    }
    
    /**
     * Add new Subject API
     * @author Solofo R.
     * @param @RequestParam Long optionId
     * @param @RequestParam String subjectName
     * @return String
     */
    @PostMapping("addSubject")
    public String addNewSubject(@RequestParam Long optionId, @RequestParam String subjectName) {
        Option option = optionRepo.findById(optionId).get();
        Subject newSubject = new Subject(subjectName);
        // save subject in DB
        subjectRepo.save(newSubject);
        // add new subject to the option
        option.getSubjects().add(newSubject);
        // update current option
        optionRepo.save(option);
        
        return "redirect:/admin#option-" + optionId;
    }
    
    /**
     * Update subject API
     * @author Solofo R.
     * @param @RequestParam Long subjectId
     * @param @RequestParam String newSubjectName
     * @return String
     */
    @PostMapping("updateSubject")
    public String updateSubject(@RequestParam Long subjectId, @RequestParam String newSubjectName) {
        Subject subject = subjectRepo.findById(subjectId).get();
        subject.setName(newSubjectName);
        // update subject
        subjectRepo.save(subject);
        
        return "redirect:/admin#subject-" + subjectId;
    }
    
    /**
     * Delete subject API
     * @author Solofo R.
     * @param @RequestParam Long subjectId
     * @param @RequestParam Long optionId
     * @param @RequestParam Long classId
     */
    @RequestMapping("/deleteSubject")
    public String deleteSubject(@RequestParam Long subjectId, @RequestParam Long optionId, @RequestParam Long classId) {
        Subject subject = subjectRepo.findById(subjectId).get();
        // get all option that contains the subject and update them
        for (int i = 0; i < subject.getOptions().size(); i++) {
            Option option = subject.getOptions().get(i);
            boolean isRemoved = false;
            // delete from list of subject in option the subject with id subjectId
            for (int j = 0; j < option.getSubjects().size() && !isRemoved; j++) {
                if (option.getSubjects().get(j).getId() == subjectId) { // if found the subject
                    option.getSubjects().remove(j); // then remove it
                    isRemoved = true; // stop searching
                }
            }
            // update option in DB
            optionRepo.save(option);
        }
        // delete subject from DB
        subjectRepo.deleteById(subjectId);
        
        return "redirect:/admin#option-" + optionId;
    }
    
    
    /**
     * Add new Option API
     * @author Solofo R.
     * @param @RequestParam Long classId
     * @param @RequestParam String optionName
     * @return String
     */
    @PostMapping("addOption")
    public String addNewOption(@RequestParam Long classId, @RequestParam String optionName) {
        Class currentClass = classRepo.findById(classId).get();
        Option newOption = new Option(optionName);
        // save option in DB
        optionRepo.save(newOption);
        // add new option to the current class
        currentClass.getOptions().add(newOption);
        // update current class
        classRepo.save(currentClass);
        
        return "redirect:/admin#" + classId;
    }
    
    /**
     * Update option API
     * @author Solofo R.
     * @param @RequestParam Long optionId
     * @param @RequestParam String newOptionName
     * @return String
     */
    @PostMapping("updateOption")
    public String updateOption(@RequestParam Long optionId, @RequestParam String newOptionName) {
        Option option = optionRepo.findById(optionId).get();
        option.setName(newOptionName);
        // update option
        optionRepo.save(option);
        
        return "redirect:/admin#option-" + optionId;
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
