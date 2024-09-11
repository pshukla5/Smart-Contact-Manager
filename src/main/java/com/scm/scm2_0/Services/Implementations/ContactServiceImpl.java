package com.scm.scm2_0.Services.Implementations;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.scm.scm2_0.Entities.Contact;
import com.scm.scm2_0.Entities.User;
import com.scm.scm2_0.Exceptions.ResourceNotFoundException;
import com.scm.scm2_0.Helper.AppConstants;
import com.scm.scm2_0.Repositories.ContactRepo;
import com.scm.scm2_0.Services.ContactService;

@Service
public class ContactServiceImpl implements ContactService{

    @Autowired
    private ContactRepo contactRepo;

    @Override
    public Contact save(Contact contact) {
        
        String id = UUID.randomUUID().toString();
        contact.setId(id);

        contactRepo.save(contact);

        return contact;
    }

    @Override
    public Contact update(Contact contact) {

        return null;

    }

    @Override
    public Contact getById(String id) {
        
        return contactRepo.findById(id).orElseThrow(()->{

            throw new ResourceNotFoundException("Contact Not Found with ID: "+id);
        });
    }

    @Override
    public void delete(String id) {
        
        Contact contact = contactRepo.findById(id).orElseThrow(()->{

            throw new ResourceNotFoundException("Contact Not Found with ID: "+id);
        });

        contactRepo.delete(contact);
    }

    @Override
    public List<Contact> getByUserId(String id) {
        
        return contactRepo.findByUserId(id);
    }

    @Override
    public Page<Contact> getByUser(User user, int pageNo, int size, String sortBy, String direction) {
        
        Sort sort = direction.equals("asc") ?
                    Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, size, sort);

        return contactRepo.findByUser(user, pageable);
    }

    @Override
    public Page<Contact> findByUserAndEmailContaining(User user, String emailKeyword, int pageNo, int size, String sortBy,
            String direction) {
        
        Sort sort = direction.equals("asc") ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, size, sort);


        // Page
        return contactRepo.findByUserAndEmailContaining(user, emailKeyword, pageable);
    }

    @Override
    public Page<Contact> findByUserAndNameContaining(User user, String nameKeyword, int pageNo, int size, String sortBy,
            String direction) {

        Sort sort = direction.equals("asc") ?
            Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, size, sort);

        return contactRepo.findByUserAndNameContaining(user, nameKeyword, pageable);
    }

    @Override
    public Page<Contact> findByUserAndPhoneNumberContaining(User user, String phoneKeyword, int pageNo, int size, String sortBy,
            String direction) {
                
        Sort sort = direction.equals("asc") ?
            Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, size, sort);

        return contactRepo.findByUserAndPhoneNumberContaining(user, phoneKeyword, pageable);
    }
}
