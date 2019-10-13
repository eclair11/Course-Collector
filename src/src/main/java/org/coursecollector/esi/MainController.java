package org.coursecollector.esi;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/")
    public String pageAccueil() {
        return "index";
    }

    @RequestMapping("/classes")
    public String listClasses(Model model) {
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

    @RequestMapping("/sucess")
    public String confirmation(Model model) {
        return "request-success";
    }
}