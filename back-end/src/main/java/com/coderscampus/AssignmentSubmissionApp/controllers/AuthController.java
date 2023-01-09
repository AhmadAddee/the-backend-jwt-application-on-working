package com.coderscampus.AssignmentSubmissionApp.controllers;

import com.coderscampus.AssignmentSubmissionApp.db.dbo.UserDb;
import com.coderscampus.AssignmentSubmissionApp.db.repositories.UserRepository;
import com.coderscampus.AssignmentSubmissionApp.dto.AuthCredentialsRequest;
import com.coderscampus.AssignmentSubmissionApp.dto.User;
import com.coderscampus.AssignmentSubmissionApp.service.IUserService;
import com.coderscampus.AssignmentSubmissionApp.util.CustomPasswordEncoder;
import com.coderscampus.AssignmentSubmissionApp.util.JwtResponse;
import com.coderscampus.AssignmentSubmissionApp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.nio.file.FileAlreadyExistsException;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private final IUserService iUserService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomPasswordEncoder passwordEncoder;

    public AuthController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody AuthCredentialsRequest req) {

        try {
            Authentication authentication = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    req.getUsername(), req.getPassword()
                            )
                    );
           // SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDb userDb = (UserDb) authentication.getPrincipal();
            userDb.setPassword(null);

            String jwt = jwtUtil.generateToken(userDb);
            List<String> roles = userDb.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());

            return ResponseEntity.ok(new JwtResponse(jwt, userDb.getUsername(), roles));
                   // .header(
                    //        HttpHeaders.AUTHORIZATION,
                    //        jwtUtil.generateToken(userDb)
                    //)
                    //.body(userDb);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public String AddUser(@RequestBody User user) throws FileAlreadyExistsException {
        if (this.userRepository.findByUsername(user.getUsername()).isPresent())
            throw new FileAlreadyExistsException("The username already exist, try with another username");
        String passwordEncoded = passwordEncoder.getPasswordEncoder().encode(user.getPassword());
        user.setPassword(passwordEncoded);
        return this.iUserService.addUser(user);
    }
}
