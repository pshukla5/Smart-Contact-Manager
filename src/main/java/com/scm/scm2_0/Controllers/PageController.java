package com.scm.scm2_0.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/home")
    public String home(Model model){
        
        System.out.println("Home Page Handler");

        // Sending data to view
        model.addAttribute("name", "Substring Technologies");
        model.addAttribute("YouTubeChannel", "LCWD");
        model.addAttribute("githubLink", "https://github.com/pshukla5/Group1--Project");
        return "Home";
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
    
    @RequestMapping("/login")
    public String login(){
        
        System.out.println("Login page loading");
        return "login";
    }

    @RequestMapping("/signup")
    public String signup(){
        
        System.out.println("Signup page loading");
        return new String("signup");
    }

    @RequestMapping("/contacts")
    public String contacts(){
        
        System.out.println("Contact page loading");
        return "contacts";
    }
}
