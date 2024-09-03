package com.scm.scm2_0.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.scm2_0.Entities.User;
import com.scm.scm2_0.Forms.UserForm;
import com.scm.scm2_0.Helper.Message;
import com.scm.scm2_0.Helper.Enums.MessageType;
import com.scm.scm2_0.Services.UserService;

import io.micrometer.common.lang.Nullable;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PageController {

    @Autowired
    UserService userService;
    @Autowired
    Environment environment;
    
    @GetMapping("/")
    public String index(){

        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String home(Model model){
        
        System.out.println("Home Page Handler");

        // Sending data to view
        model.addAttribute("name", "Substring Technologies");
        model.addAttribute("YouTubeChannel", "LCWD");
        model.addAttribute("githubLink", "https://github.com/pshukla5/Group1--Project");
        return "home";
    }

    @RequestMapping("/tailwindhome")
    public String tailwindhome(Model model){
        
        System.out.println("Home Page Handler");

        // Sending data to view
        model.addAttribute("name", "Substring Technologies");
        model.addAttribute("YouTubeChannel", "LCWD");
        model.addAttribute("githubLink", "https://github.com/pshukla5/Group1--Project");
        return "hometailwind";
    }

    @RequestMapping("/flowbite")
    public String flowbite(Model model){
        
        System.out.println("Home Page Handler");

        // Sending data to view
        model.addAttribute("name", "Substring Technologies");
        model.addAttribute("YouTubeChannel", "LCWD");
        model.addAttribute("githubLink", "https://github.com/pshukla5/Group1--Project");
        return "flowbite";
    }

    // About

    @RequestMapping("/about")
    public String about(Model model){
        
        model.addAttribute("isLogin", false);
        System.out.println("About page loading");
        return "about";
    }

    // Services

    @RequestMapping("/services")
    public String services(){
        
        System.out.println("Services page loading");
        return "services";
    }

    // @RequestMapping("/login")
    // public String login(){
    
    //     System.out.println("Login page loading");
    //     // System.out.println(environment.getProperty("spring.security.oauth2.client.registration.google.client-id"));
    //     // System.out.println(environment.getProperty("spring.security.oauth2.client.registration.google.client-secret"));
    //     // System.out.println(environment.getProperty("spring.security.oauth2.client.registration.github.client-id"));
    //     // System.out.println(environment.getProperty("spring.security.oauth2.client.registration.github.client-secret"));
    //     return "login";
    // }
    
    @RequestMapping("/login")
    public String login(HttpSession session, @Nullable @RequestParam Boolean logout){
    
        System.out.println("Login page loading");
        System.out.println(logout);
        // System.out.println(environment.getProperty("spring.security.oauth2.client.registration.google.client-id"));
        // System.out.println(environment.getProperty("spring.security.oauth2.client.registration.google.client-secret"));
        // System.out.println(environment.getProperty("spring.security.oauth2.client.registration.github.client-id"));
        // System.out.println(environment.getProperty("spring.security.oauth2.client.registration.github.client-secret"));

        if(logout != null && logout == true){
            System.out.println("Logged out");

            session.setAttribute("message", Message.builder()
                                                    .content("You have been logged out.")
                                                    .type(MessageType.green)
                                                    .build()
                                );

            return "redirect:/login";
        }
        return "login";
    }

    @RequestMapping("/signup")
    public String signup(Model model){
        
        UserForm userForm = new UserForm();

        userForm.setName("user");
        userForm.setEmail("user@abc.com");
        userForm.setPassword("user");
        userForm.setPhoneNumber("6746553456");
        userForm.setAbout("this is testing");

        model.addAttribute("userForm", userForm);

        System.out.println("Signup page loading");
        return new String("signup");
    }

    @RequestMapping("/contacts")
    public String contacts(){
        
        System.out.println("Contact page loading");
        return "contacts";
    }

    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String processingRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult, HttpSession session){

        System.out.println("Processing Register");

        // fetch the form data
        System.out.println(userForm);

        // validate the form
        // TODO::Next video
        if(rBindingResult.hasErrors()){

            System.out.println("Error:" + rBindingResult.getErrorCount());
            return "signup";
        }

        // save the data in the database
        // User user = User.builder()
        // .name(userForm.getName())
        // .email(userForm.getEmail())
        // .password(userForm.getPassword())
        // .about(userForm.getAbout())
        // .phoneNumber(userForm.getPhoneNumber())
        // .profilePic("/Images/defaultProfile.jpg")
        // .build();

        User user = new User();

        user.setAbout(userForm.getAbout());
        user.setEmail(userForm.getEmail());
        user.setName(userForm.getName());
        user.setPassword(userForm.getPassword());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setProfilePic("/Images/defaultProfile.jpg");
        
        User savedUser =  userService.saveUser(user);
        System.out.println("User Saved:" + savedUser);

        // messsage = "Registration Successful"
        Message message = Message.builder().content("Registration Successful").type(MessageType.green).build();
        session.setAttribute("message", message);

        // redirect to login page
        return "redirect:/signup";
    }
}
