package org.coursecollector.esi;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/cours")
    public String ListeDesCours(Model model) {
        return "cours";
    }
    
}