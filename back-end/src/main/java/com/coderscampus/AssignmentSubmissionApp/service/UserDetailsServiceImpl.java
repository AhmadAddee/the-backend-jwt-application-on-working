package com.coderscampus.AssignmentSubmissionApp.service;

import com.coderscampus.AssignmentSubmissionApp.db.repositories.UserRepository;
import com.coderscampus.AssignmentSubmissionApp.util.CustomPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.coderscampus.AssignmentSubmissionApp.db.dbo.UserDb;

import java.util.Optional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private CustomPasswordEncoder customPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserDb> userDbOpt = userRepo.findByUsername(username);
        System.out.println(userDbOpt.get().getFull_name());
        //userDbOpt.get().setPassword(passwordEncoder.getPasswordEncoder().encode(userDbOpt.get().getPassword()));
        System.out.println(userDbOpt.get().getPassword());
        return userDbOpt.orElseThrow(() -> new UsernameNotFoundException("Invalid credentials"));
    }

}



