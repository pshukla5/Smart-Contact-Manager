package com.scm.scm2_0.Services.Implementations;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.scm2_0.Entities.Contact;
import com.scm.scm2_0.Exceptions.ResourceNotFoundException;
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
    public List<Contact> getAll() {
        
        return contactRepo.findAll();
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
    public List<Contact> search(String name, String email, String phoneNumber) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }

    @Override
    public List<Contact> getByUserId(String id) {
        
        return contactRepo.findByUserId(id);
    }

}
