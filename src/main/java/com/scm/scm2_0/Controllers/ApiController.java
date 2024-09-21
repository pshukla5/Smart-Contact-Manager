package com.scm.scm2_0.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scm.scm2_0.Entities.Contact;
import com.scm.scm2_0.Entities.User;
import com.scm.scm2_0.Repositories.ContactRepo;
import com.scm.scm2_0.Services.ContactService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;


@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    ContactService contactService;
    @Autowired
    ContactRepo contactRepo;

    @RequestMapping(value = "/contacts/{contactId}", method = RequestMethod.GET)
    public Contact getContact(@PathVariable String contactId, Model model) {
        
        User user = (User) model.getAttribute("loggedInUser");

        System.out.println(user.getUserId());
        
        User user1 = User.builder()
                        .userId(user.getUserId())
                        .enabled(user.isEnabled())
                        .build();

        Contact contact = new Contact();
        System.out.println(contact.isFavorite());
        // contact.setId(contactId);
        // contact.setAddress("xy");
        contact.setUser(user1);
        // name.ifPresent(contact::setName);

        ExampleMatcher matcher  = ExampleMatcher.matching()
                                                .withIgnoreNullValues()
                                                .withIgnorePaths("favorite")
                                                .withMatcher("id", ExampleMatcher.GenericPropertyMatchers.exact())
                                                // .withMatcher("address", ExampleMatcher.GenericPropertyMatchers.contains())
                                                .withMatcher("user", ExampleMatcher.GenericPropertyMatchers.exact())
                                                ;

        Example<Contact> example = Example.of(contact,matcher);
        System.out.println(matcher.getDefaultStringMatcher());
        System.out.println("------------------------------------------");
        System.out.println(contactRepo.findAll(example));
        System.out.println("-----------------------------------------------");

        return contactService.getByUserAndId(user, contactId);
    }


    
}
