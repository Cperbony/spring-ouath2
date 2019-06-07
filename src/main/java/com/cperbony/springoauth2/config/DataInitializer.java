package com.cperbony.springoauth2.config;

import com.cperbony.springoauth2.entity.Role;
import com.cperbony.springoauth2.entity.User;
import com.cperbony.springoauth2.repository.RoleRepository;
import com.cperbony.springoauth2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    /**
     *
     */
    @Autowired
    PasswordEncoder encoder;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            this.createUsers("Claus", "cperbony@gmail.com", encoder.encode("123456"), "ROLE_ADMIN");
            this.createUsers("Lucas", "lperbony@gmail.com", encoder.encode("123456"), "ROLE_ALUNO");
            this.createUsers("Sarah", "sperbony@gmail.com", encoder.encode("123456"), "ROLE_ALUNO");
        }
    }

    public void createUsers(String name, String email, String password, String role) {
        Role roleObj = new Role();
        roleObj.setName(role);

        this.roleRepository.save(roleObj);

        User user = new User(name, email, password, Arrays.asList(roleObj));
        userRepository.save(user);
    }
}
