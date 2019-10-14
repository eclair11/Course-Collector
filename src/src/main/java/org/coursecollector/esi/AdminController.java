/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.coursecollector.esi;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author nerd
 */
@Controller
public class AdminController {

    @RequestMapping("/admin")
    public String page(Model model) {
        return "admin";
    }

    @RequestMapping("/users")
    public String listUsers(Model model) {
        return "user";
    }

}
