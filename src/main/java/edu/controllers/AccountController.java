package edu.controllers;

import edu.daos.jpa.UserJpaDao;
import edu.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private UserJpaDao userJpaDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public void register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userJpaDao.save(user);
    }
}
