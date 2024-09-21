package com.scm.scm2_0.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.scm.scm2_0.Entities.Contact;
import com.scm.scm2_0.Entities.User;

@Repository
public interface ContactRepo extends JpaRepository<Contact, String> {

    // Custom Finder Method
    Page<Contact> findByUser(User user, Pageable pageable);

    Page<Contact> findByUserAndEmailContaining(User user, String emailKeyword, Pageable pageable);
    Page<Contact> findByUserAndNameContaining(User user, String nameKeyword, Pageable pageable);
    Page<Contact> findByUserAndPhoneNumberContaining(User user, String phoneKeyword, Pageable pageable);
    Optional<Contact> getByUserAndId(User user, String id);
    void deleteByUserAndId(User user, String contactId);

    // custom query method
    @Query("SELECT c FROM Contact c WHERE c.user.id = :userId")
    List<Contact> findByUserId(@Param("userId") String id);

}
