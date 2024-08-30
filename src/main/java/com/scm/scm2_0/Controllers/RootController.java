package com.scm.scm2_0.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.scm.scm2_0.Entities.User;
import com.scm.scm2_0.Helper.Helper;
import com.scm.scm2_0.Services.UserService;

@ControllerAdvice
public class RootController {

    @Autowired
    private UserService userService;

    @ModelAttribute
    public void addLoggedInUserInformation(Model model, Authentication authentication){

        if(authentication == null) return;

        System.out.println("Adding logged in user information to the model");

        String email = Helper.getEmailofLoggedInUser(authentication);
        System.out.println(email);

        // Getting User from Database

        User user = userService.findUserByEmail(email);

        System.out.println(user.getName());
        System.out.println(user.getEmail());
        

        model.addAttribute("loggedInUser", user);
    }

}
