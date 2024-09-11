package com.scm.scm2_0.Services;

import java.util.List;

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

    // get contact by ID
    Contact getById(String id);

    // delete contact
    void delete(String id);

    // get contacts by User ID
    List<Contact> getByUserId(String id);

    // get all contacts of a user
    Page<Contact> getByUser(User user, int pageNo, int size, String sortBy, String direction);

    // search contact
    Page<Contact> findByUserAndEmailContaining(User user, String emailKeyword, int pageNo, int size, String sortBy, String direction);
    Page<Contact> findByUserAndNameContaining(User user, String nameKeyword, int pageNo, int size, String sortBy, String direction);
    Page<Contact> findByUserAndPhoneNumberContaining(User user, String phoneKeyword, int pageNo, int size, String sortBy, String direction);


}
