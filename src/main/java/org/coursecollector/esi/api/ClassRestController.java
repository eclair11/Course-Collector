package org.coursecollector.esi.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.coursecollector.esi.model.ClassRepository;
import org.coursecollector.esi.model.Student;
import org.coursecollector.esi.model.StudentRepository;
import org.coursecollector.esi.AdminController;
import org.coursecollector.esi.model.Class;

@RestController
public class ClassRestController {

    @Inject
    ClassRepository classRepo;

    @Inject
    StudentRepository studentRepo;

    @RequestMapping(value = "/api/classes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Class[] getClassesAction() {
        List<Class> classes = new ArrayList<Class>();
        for (Class classe : classRepo.findAll()) {
            classes.add(classe);
        }
        Class[] classArr = new Class[classes.size()];
        classArr = classes.toArray(classArr);
        return classArr;
    }


    /**
     * Add new class API
     * 
     * @author Solofo R.
     * @param @RequestParam String className
     * @param @RequestParam int classLevel
     * @return String
     */
    @PostMapping("/admin/class/add")
    public ResponseEntity addNewClass(@RequestParam String className, @RequestParam int classLevel) {
        Class newClass = new Class(className, classLevel);
        // save new class in DB
        classRepo.save(newClass);
        // add new class to the list of classes of the admin student
        Student student = studentRepo.findById(AdminController.adminName).get();
        student.getClasses().add(newClass);
        // update student
        studentRepo.save(student);

        return new ResponseEntity(HttpStatus.CREATED);
    }

}