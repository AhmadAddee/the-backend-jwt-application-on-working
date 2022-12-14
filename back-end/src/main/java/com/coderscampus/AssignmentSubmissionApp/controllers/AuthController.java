package com.coderscampus.AssignmentSubmissionApp.controllers;

import com.coderscampus.AssignmentSubmissionApp.db.dbo.UserDb;
import com.coderscampus.AssignmentSubmissionApp.dto.AuthCredentialsRequest;
import com.coderscampus.AssignmentSubmissionApp.dto.UserDto;
import com.coderscampus.AssignmentSubmissionApp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("login")
    public ResponseEntity<?> login (@RequestBody AuthCredentialsRequest req) {

        try {
            Authentication authentication = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    req.getUsername(), req.getPassword()
                            )
                    );
            UserDb userDb = (UserDb) authentication.getPrincipal();
            userDb.setPassword(null);
            /*UserDto userDto = new UserDto();
            userDto.setUsername(userDb.getUsername());
            userDto.setFull_name(userDb.getFull_name());

             */
            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.AUTHORIZATION,
                            jwtUtil.generateToken(userDb)
                    )
                    .body(userDb);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
