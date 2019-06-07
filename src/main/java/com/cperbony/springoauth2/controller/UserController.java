package com.cperbony.springoauth2.controller;

import com.cperbony.springoauth2.entity.User;
import com.cperbony.springoauth2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;


    @Secured({"ROLE_ADMIN", "ROLE_ALUNO"})
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<User> list() {
        return userRepository.findAll();
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value = "", method = RequestMethod.POST)
    public User save(@RequestBody User user) {
        return this.userRepository.save(user);
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public User edit(@RequestBody User user) {
        return this.userRepository.save(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        this.userRepository.deleteById(id);
    }

}

