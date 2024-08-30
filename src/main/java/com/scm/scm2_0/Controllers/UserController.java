package com.scm.scm2_0.Controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {


    // User Dashboard page
    @RequestMapping(value = "/dashboard", method=RequestMethod.GET)
    public String userDashboard(Model model, Authentication authentication) {        


        return "user/dashboard";
    }

    // @RequestMapping(value = "/dashboard", method=RequestMethod.POST)
    // public String userDashboardpost() {

    //     return "user/dashboard";
    // }

    // User Profile page
    @RequestMapping(value = "/profile", method=RequestMethod.GET)
    public String userProfile() {

        return "user/profile";
    }
    

    // User add contact page

    // User view contact page

    // user edit contact page

    // user edit contact page

    // user search contact page
}
