package com.scm.scm2_0.Services;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.scm.scm2_0.Entities.Contact;
import com.scm.scm2_0.Entities.User;

@Service
public interface ContactService {

    // save contact
    Contact save(Contact contact);
    
    // Update Contact
    Contact update(Contact contact);

    // get contacts
    List<Contact> getAll();

    // get contact by ID
    Contact getById(String id);

    // delete contact
    void delete(String id);

    // search contact
    List<Contact> search(String name, String email, String phoneNumber);

    // get contacts by User ID
    List<Contact> getByUserId(String id);

    Page<Contact> getByUser(User user, int page, String sortBy, String direction);

}
