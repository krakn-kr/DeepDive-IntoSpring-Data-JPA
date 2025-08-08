package com.example.demo.component;

import com.example.demo.entity.*;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public void run(String... args) {
        // Create test data
        User user = new User("John Doe");
        // Create profile for user
        Profile profile = new Profile();
        profile.setBio("Java Developer with 5 years of experience");
        profile.setAvatarUrl("https://example.com/avatars/john.jpg");
        profile.setBirthDate(LocalDate.of(1990, 5, 15));
        user.setProfile(profile);
        Address newYork = new Address("New York");
        newYork.setUser(user);
        Address london = new Address("London");
        london.setUser(user);
        List<Address> addressList = new ArrayList<>();
        addressList.add(newYork);
        addressList.add(london);
        user.setAddresses(addressList);
        userRepository.save(user);

        Permission p1 = new Permission("READ");
        Permission p2 = new Permission("WRITE");
        Role role = new Role("ADMIN");
        role.getPermissions().add(p1);
        role.getPermissions().add(p2);
        roleRepository.save(role);
    }
}
