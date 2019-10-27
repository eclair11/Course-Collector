package org.coursecollector.esi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.inject.Inject;

import org.coursecollector.esi.model.Class;
import org.coursecollector.esi.model.ClassRepository;
import org.coursecollector.esi.model.Course;
import org.coursecollector.esi.model.CourseRepository;
import org.coursecollector.esi.model.Student;
import org.coursecollector.esi.model.StudentRepository;
import org.coursecollector.esi.model.Subject;
import org.coursecollector.esi.model.SubjectRepository;
import org.coursecollector.esi.model.Rate;
import org.coursecollector.esi.model.RateRepository;
import org.coursecollector.esi.model.Request;
import org.coursecollector.esi.model.RequestRepository;
import org.coursecollector.esi.model.Option;
import org.coursecollector.esi.model.OptionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    public static Long defaultStudentId;

    @Inject
    StudentRepository studentRepo;

    @Inject
    ClassRepository classRepo;

    @Inject
    SubjectRepository subjectRepo;

    @Inject
    CourseRepository courseRepo;

    @Inject
    RateRepository rateRepo;

    @Inject
    RequestRepository requestRepo;

    @Inject
    OptionRepository optionRepo;

    /**
     * Method that set value of defaultStudentId
     * 
     * @param id Default student identifier
     */
    public static void setDefaultStudentId(Long id) {
        defaultStudentId = id;
    }

    /**
     * 
     * @param model
     * @return
     */
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

        // Create some Rate for the first publication
        Rate[] rates = { new Rate(true, courses[0]), new Rate(false, courses[0]) };
        // save all Rates in DB
        for (int i = 0; i < rates.length; i++) {
            rateRepo.save(rates[i]);
        }

        // add Rates for the first publication
        courses[0].setRates(new ArrayList<Rate>(Arrays.asList(rates)));
        // update this publication in DB
        courseRepo.save(courses[0]);

        // Create Subject Test
        // save all publications in Subject IA
        Subject[] subjects = { new Subject("IA"), new Subject("Advanced Algorithm"), new Subject("Complexity"),
                new Subject("Turing Machine"), new Subject("Deep Learning"), new Subject("Advanced Web") };

        // add some publications in the first subject : IA
        subjects[0].setCourses(new ArrayList<Course>(Arrays.asList(courses)));

        // Save all subject in DB
        for (int i = 0; i < subjects.length; i++) {
            subjectRepo.save(subjects[i]);
        }

        // create some Option
        Option[] options = { new Option("INFORMATIQUE DSC"), new Option("INFORMATIQUE MLDM"),
                new Option("INFORMATIQUE CPS2") };
        // add some subjects in options
        options[0].setSubjects(Arrays.asList(subjects[0], subjects[1], subjects[2]));
        options[1].setSubjects(Arrays.asList(subjects[3], subjects[4], subjects[5]));
        options[2].setSubjects(Arrays.asList(subjects[3], subjects[4], subjects[5]));

        // Save all option in DB
        for (int i = 0; i < options.length; i++) {
            optionRepo.save(options[i]);
        }

        // Create Class Test
        Class[] classes = { new Class("Master", 1), new Class("Master", 2) };
        // add some option to classes
        classes[0].setOptions(Arrays.asList(options[0], options[1], options[2]));
        classes[1].setOptions(Arrays.asList(options[0], options[1], options[2]));

        // Save all classes in DB
        for (int i = 0; i < classes.length; i++) {
            classRepo.save(classes[i]);
        }

        // Create Students test
        Student[] students = { new Student("USER", "1234", "Lebron", "James", "Informatique", 4),
                new Student("USER", "1234", "Kobe", "Bryant", "Informatique", 4) };
        // set student class
        students[0].setClasses(Arrays.asList(classes));
        students[1].setClasses(Arrays.asList(classes));
        // set student option
        students[0].setOption(options[0].getName());
        students[1].setOption(options[0].getName());

        // Save all Students in DB
        for (int i = 0; i < students.length; i++) {
            studentRepo.save(students[i]);
        }

        // Create some Request
        Request[] requests = { new Request(new Date(2019 - 1900, 10, 3), students[0]),
                new Request(new Date(2019 - 1900, 10, 4), students[0]),
                new Request(new Date(2019 - 1900, 10, 5), students[0]),
                new Request(new Date(2019 - 1900, 10, 6), students[0]) };
        // save all request
        for (int i = 0; i < requests.length; i++) {
            requestRepo.save(requests[i]);
        }

        // add requests to the first subject in DB
        Subject firstSubjInDb = subjectRepo.findAll().iterator().next();
        for (int i = 0; i < requests.length; i++) {
            firstSubjInDb.getRequests().add(requests[i]);
        }
        // update subject IA
        subjectRepo.save(firstSubjInDb);

        // get the id of Lebron James (saved first)
        Long lebronId = studentRepo.findAll().iterator().next().getId();
        // set Lebron james as default student for this test
        TestController.setDefaultStudentId(lebronId);

        return "redirect:/class";
    }

}
