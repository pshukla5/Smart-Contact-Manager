package com.scm.scm2_0.Controllers;


import com.scm.scm2_0.Entities.Contact;
import com.scm.scm2_0.Entities.User;
import com.scm.scm2_0.Forms.ContactForm;
import com.scm.scm2_0.Helper.Helper;
import com.scm.scm2_0.Helper.Message;
import com.scm.scm2_0.Helper.Enums.MessageType;
import com.scm.scm2_0.Services.ContactService;
import com.scm.scm2_0.Services.ImageService;
import com.scm.scm2_0.Services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Controller
@RequestMapping("/user/contacts")
public class ContactController {

    private Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    ImageService imageService;

    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;

    // User add contact page
    @RequestMapping(path = "/add", method = RequestMethod.GET)
    public String addContact(Model model) {

        ContactForm contactForm = new ContactForm();

        contactForm.setName("Prabhat");
        contactForm.setAddress("Noida");
        contactForm.setDescription("This is testing");
        contactForm.setEmail("pk@gmail.com");
        contactForm.setLinkedInLink("linked.com");
        contactForm.setWebsiteLink("scm.com");
        contactForm.setPhoneNumber("9873483244");
        contactForm.setFavorite(true);

        model.addAttribute("contactForm", contactForm);

        System.out.println("sending contact form to add new contact");

        return new String("user/addContact");
    }

    @RequestMapping(path = "/add", method=RequestMethod.POST)
    public String addContact(@Valid @ModelAttribute ContactForm contactForm,BindingResult result,
                                Authentication authentication, HttpSession session
                            ) {

        System.out.println("Reading Contact form");
        System.out.println(contactForm);

        // Validate Form
        // TODO: Add validation logic here

        if(result.hasErrors()){

            session.setAttribute("message", Message.builder()
                                                   .content("Please correct the following errors")
                                                   .type(MessageType.red)
                                                   .build());

            result.getAllErrors().forEach(error->{

                System.out.println(error.toString());
            });

            System.out.println("returning to add contact page due to errors");
            return "user/addContact";
        }


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

        // Process the contact picture
        // image upload krne ka code

        if(!contactForm.getContactImage().isEmpty()){

            String cloudinaryImagePublicId = UUID.randomUUID().toString();
            String fileURL = imageService.uploadImage(contactForm.getContactImage(), cloudinaryImagePublicId);

            contact.setPicture(fileURL);
            contact.setCloudinaryImagePublicId(cloudinaryImagePublicId);
        }

        // saving contact
        contactService.save(contact);

        // Set the message to be displayed on view
        Message message = new Message();
        message.setContent("Added Contact Successfully");
        message.setType(MessageType.green);

        session.setAttribute("message", message);


        return new String("redirect:/user/contacts/add");
    }
    
    // User view contact page

    @RequestMapping(method=RequestMethod.GET)
    public String getAllContactByUser(Model model, Authentication authentication) {

        // String username = Helper.getEmailofLoggedInUser(authentication);

        User user = (User) model.getAttribute("loggedInUser");

        List<Contact> contacts = user.getContacts();

        model.addAttribute("contacts", contacts);

        return new String("user/viewContact");
    }
    

    // user edit contact page

    // user edit contact page

    // user search contact page

}
