package com.scm.scm2_0.Controllers;


import com.scm.scm2_0.Entities.Contact;
import com.scm.scm2_0.Entities.User;
import com.scm.scm2_0.Forms.ContactForm;
import com.scm.scm2_0.Helper.Helper;
import com.scm.scm2_0.Services.ContactService;
import com.scm.scm2_0.Services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/user/contacts")
public class ContactController {

    @Autowired
    ContactService contactService;

    @Autowired
    UserService userService;

    // User add contact page
    @RequestMapping(path = "/add")
    public String addContact(Model model) {

        ContactForm contactForm = new ContactForm();

        // contactForm.setName("Prabhat");
        // contactForm.setAddress("Noida");
        // contactForm.setDescription("This is testing");
        // contactForm.setEmail("pk@gmail.com");
        // contactForm.setLinkedInLink("linked.com");
        // contactForm.setWebsiteLink("scm.com");
        // contactForm.setPhoneNumber("9873483244");
        // contactForm.setFavorite(true);

        model.addAttribute("contactForm", contactForm);

        System.out.println("sending contact form to add new contact");

        return new String("user/addContact");
    }

    @RequestMapping(path = "/add", method=RequestMethod.POST)
    public String requestMethodName(@ModelAttribute ContactForm contactForm, Authentication authentication) {

        System.out.println("Reading Contact form");
        System.out.println(contactForm);

        String username = Helper.getEmailofLoggedInUser(authentication);

        User user = userService.findUserByEmail(username);

        Contact contact = new Contact();

        // ContactForm -> Contact

        contact.setName(contactForm.getName());
        contact.setAddress(contactForm.getAddress());
        contact.setDescription(contactForm.getDescription());
        contact.setEmail(contactForm.getEmail());
        contact.setLinkedInLink(contactForm.getLinkedInLink());
        contact.setWebsiteLink(contactForm.getWebsiteLink());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setFavorite(contactForm.getFavorite());
        contact.setUser(user);
        contact.setPicture(null);

        // saving contact
        contactService.save(contact);


        return new String("redirect:/user/contacts/add");
    }
    
    // User view contact page

    // user edit contact page

    // user edit contact page

    // user search contact page

}
