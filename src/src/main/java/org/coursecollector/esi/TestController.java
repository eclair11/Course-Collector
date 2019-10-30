package org.coursecollector.esi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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

        // Create some Rate for the first course
        Rate[] rates = { new Rate(true, courses[0]), new Rate(false, courses[0]) };
        // save all Rates in DB
        for (int i = 0; i < rates.length; i++) {
            rateRepo.save(rates[i]);
        }

        // add Rates for the first course
        courses[0].setRates(new ArrayList<Rate>(Arrays.asList(rates)));
        // update this course in DB
        courseRepo.save(courses[0]);

        // create list of links + pages
        List<String> links0 = new ArrayList<>(
                Arrays.asList("img/courses/001.jpeg", "img/courses/002.jpeg", "img/courses/003.jpeg"));
        List<Integer> pages0 = new ArrayList<>(Arrays.asList(1, 2, 3));
        List<String> links1 = new ArrayList<>(Arrays.asList("img/courses/004.jpeg", "img/courses/005.jpeg"));
        List<Integer> pages1 = new ArrayList<>(Arrays.asList(1, 2));
        List<String> links2 = new ArrayList<>(Arrays.asList("img/courses/006.jpeg"));
        List<Integer> pages2 = new ArrayList<>(Arrays.asList(1));
        List<String> links3 = new ArrayList<>(Arrays.asList("img/courses/007.jpeg"));
        List<Integer> pages3 = new ArrayList<>(Arrays.asList(1));
        List<String> links4 = new ArrayList<>(Arrays.asList("img/courses/008.jpeg", "img/courses/009.jpeg"));
        List<Integer> pages4 = new ArrayList<>(Arrays.asList(1, 2));
        List<String> links5 = new ArrayList<>(Arrays.asList("img/courses/001.jpeg", "img/courses/002.jpeg",
                "img/courses/003.jpeg", "img/courses/004.jpeg", "img/courses/005.jpeg", "img/courses/006.jpeg"));
        List<Integer> pages5 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));

        // Create Subject Test
        // save all courses in Subject IA
        Subject[] subjects = { new Subject("Introduction to IA"), new Subject("Advanced Algorithm"), new Subject("Complexity"),
                new Subject("Data Analysis"), new Subject("Deep Learning"), new Subject("Advanced Web Development") };

        // add some courses in the first subject : IA
        subjects[0].setCourses(new ArrayList<Course>(Arrays.asList(courses)));

        // Save all subject in DB
        for (int i = 0; i < subjects.length; i++) {
            subjectRepo.save(subjects[i]);
        }

        // create some Option
        Option[] options = { new Option("INFORMATIQUE DSC"), new Option("INFORMATIQUE MLDM"),
                new Option("INFORMATIQUE CPS2"), new Option("INFORMATIQUE DSC"), new Option("INFORMATIQUE MLDM") };
        // add some subjects in options
        options[0].setSubjects(Arrays.asList(subjects[0], subjects[1], subjects[2], subjects[5]));
        options[1].setSubjects(Arrays.asList(subjects[0], subjects[1], subjects[2], subjects[5]));
        options[2].setSubjects(Arrays.asList(subjects[0], subjects[1], subjects[2], subjects[5]));
        options[3].setSubjects(Arrays.asList(subjects[3], subjects[4]));
        options[4].setSubjects(Arrays.asList(subjects[3], subjects[4]));    

        // Save all option in DB
        for (int i = 0; i < options.length; i++) {
            optionRepo.save(options[i]);
        }
        
        // update subject's options
        List<Option> optionList = (List<Option>) optionRepo.findAll();
        for (Option option : optionList) {
            for (Subject subj : option.getSubjects()) {
                subj.getOptions().add(option);
                // update subject
                subjectRepo.save(subj);
            }
        }

        // Create Class Test
        Class[] classes = { new Class("Master", 1), new Class("Master", 2) };
        // add some option to classes
        classes[0].setOptions(Arrays.asList(options[0], options[1], options[2]));
        classes[1].setOptions(Arrays.asList(options[3], options[4]));

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

        // add links + pages + uploader for the courses
        courses[0].setLinks(links0);
        courses[0].setPages(pages0);
        courses[0].setStudent(students[0]);
        courses[1].setLinks(links1);
        courses[1].setPages(pages1);
        courses[1].setStudent(students[0]);
        courses[2].setLinks(links2);
        courses[2].setPages(pages2);
        courses[2].setStudent(students[0]);
        courses[3].setLinks(links3);
        courses[3].setPages(pages3);
        courses[3].setStudent(students[1]);
        courses[4].setLinks(links4);
        courses[4].setPages(pages4);
        courses[4].setStudent(students[1]);
        courses[5].setLinks(links5);
        courses[5].setPages(pages5);
        courses[5].setStudent(students[1]);

        // update courses
        for (int i = 0; i < courses.length; i++) {
            courseRepo.save(courses[i]);
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
