package com.scm.scm2_0.Controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/contacts")
public class ContactController {

    // User add contact page
    @RequestMapping(path = "/add")
    public String addContact() {

        return new String("user/addContact");
    }
    // User view contact page

    // user edit contact page

    // user edit contact page

    // user search contact page

}
