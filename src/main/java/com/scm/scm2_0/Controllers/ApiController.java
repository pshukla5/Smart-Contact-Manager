package com.scm.scm2_0.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scm.scm2_0.Entities.Contact;
import com.scm.scm2_0.Entities.User;
import com.scm.scm2_0.Services.ContactService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;


@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    ContactService contactService;

    @RequestMapping(value = "/contacts/{contactId}", method = RequestMethod.GET)
    public Contact getContact(@PathVariable String contactId, Model model) {
        
        User user = (User) model.getAttribute("loggedInUser");

        System.out.println(user.getUserId());

        return contactService.getByUserAndId(user, contactId);
    }
    
}
