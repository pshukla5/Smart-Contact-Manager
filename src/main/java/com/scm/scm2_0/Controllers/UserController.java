package com.scm.scm2_0.Controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.scm2_0.Helper.Helper;

@Controller
@RequestMapping("/user")
public class UserController {

    // User Dashboard page
    @RequestMapping(value = "/dashboard", method=RequestMethod.GET)
    public String userDashboard(Authentication authentication) {


        System.out.println("call /user/dashboard");
        String email = Helper.getEmailofLoggedInUser(authentication);


        System.out.println(email);



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
