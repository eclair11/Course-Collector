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
    public String ListClasses(Model model) {
        return "class";
    }
    
}