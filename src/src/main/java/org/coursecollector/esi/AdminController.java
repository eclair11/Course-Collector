/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.coursecollector.esi;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.coursecollector.esi.model.StudentRepository;

/**
 *
 * @author nerd
 */
@Controller
public class AdminController {
    
    @Inject 
    StudentRepository studentRepo;

    @RequestMapping("/admin")
    public String page(Model model) {
        // get notifications that concern the student
        model.addAttribute("notifications", MainController.listNotifications(studentRepo, TestController.defaultStudentId));
        return "admin";
    }

    @RequestMapping("/users")
    public String listUsers(Model model) {
        // get notifications that concern the student
        model.addAttribute("notifications", MainController.listNotifications(studentRepo, TestController.defaultStudentId));
        return "user";
    }


    @RequestMapping("/statut")
    public String changeStatut(Model model) {
        // get notifications that concern the student
        model.addAttribute("notifications", MainController.listNotifications(studentRepo, TestController.defaultStudentId));
        return "change-statut";
    }

}
