package com.scm.scm2_0.Services.Implementations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scm.scm2_0.Entities.User;
import com.scm.scm2_0.Exceptions.ResourceNotFoundException;
import com.scm.scm2_0.Helper.AppConstants;
import com.scm.scm2_0.Repositories.UserRepo;
import com.scm.scm2_0.Services.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public User saveUser(User user) {

        // userId: have to generate
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        // encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // set the user role
        user.setRoleList(List.of(AppConstants.ROLE_USER));
        return userRepo.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {

        return userRepo.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user){

        User user2 =  userRepo.findById(user.getUserId()).orElseThrow(()->{
            throw new ResourceNotFoundException("User not found");
        });

        // Updating user2

        user2.setName(user.getName());
        user2.setEmail(user2.getEmail());
        user2.setAbout(user.getAbout());
        user2.setPassword(user.getPassword());
        user2.setPhoneNumber(user.getPhoneNumber());
        user2.setProfilePic(user.getProfilePic());
        user2.setEnabled(user.isEnabled());
        user2.setEmailVerified(user.getEmailVerified());
        user2.setPhoneVerified(user.getPhoneVerified());
        user2.setProvider(user.getProvider());
        user2.setProviderUserId(user.getProviderUserId());

        // save the user2 indatabase and return user2

        return Optional.ofNullable(userRepo.save(user2));

    }

    @Override
    public void deleteUser(String id) {
        
        User user2 =  userRepo.findById(id).orElseThrow(()->{
            return new ResourceNotFoundException("User not found");
        });

        userRepo.deleteById(user2.getUserId());

    }

    @Override
    public Boolean isUserExist(String id) {
        
        User user2 =  userRepo.findById(id).orElse(null);

        return user2==null ? false : true;
    }

    @Override
    public Boolean isUserExistByEmail(String email) {
        
        User user = userRepo.findByEmail(email).orElse(null);

        return user == null ? false : true;
    
    }

    @Override
    public List<User> getAllUsers() {
        
        return userRepo.findAll();
    }

    @Override
    public User findUserByEmail (String email) {

        return userRepo.findByEmail(email).orElse(null);
    }
}