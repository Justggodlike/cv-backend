package com.epam.cv.controller;

import com.epam.cv.config.JwtTokenProvider;
import com.epam.cv.dto.UserCreateDto;
import com.epam.cv.entity.AuthBody;
import com.epam.cv.entity.User;
import com.epam.cv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    AuthenticationManager authenticationManager;

    @SuppressWarnings("rawtypes")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthBody authBody) {
        try {
            String email = authBody.getEmail();
            User user = userService.findUserByEmail(email);
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, authBody.getPassword()));
            SecurityContextHolder
                    .getContext()
                    .setAuthentication(authentication);
            String token = jwtTokenProvider.createToken(email, user.getRoles());
            Map<Object, Object> model = new HashMap<>();
            model.put("username", email);
            model.put("token", token);
            return ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid email/password supplied");
        }
    }

    @PostMapping("/register")
    public User register(@RequestBody UserCreateDto userCreateDto) {
        return userService.createUser(userCreateDto);
    }

}
