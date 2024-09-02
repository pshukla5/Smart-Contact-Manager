package com.scm.scm2_0.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scm.scm2_0.Entities.User;


@Repository
public interface UserRepo extends JpaRepository<User,String>{

    Optional<User> findByEmail(String email); 

}
