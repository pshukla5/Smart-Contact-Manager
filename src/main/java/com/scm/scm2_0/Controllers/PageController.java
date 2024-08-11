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

}
