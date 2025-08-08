package com.example.demo.service;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoadingDemoService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public void demonstrateLazyLoading() {
        System.out.println("\n=== LAZY LOADING DEMO ===");

        // 1. Fetch user (addresses NOT loaded yet)
        System.out.println("1. Fetching user...");
        User user = userRepository.findById(1L).orElseThrow();
        System.out.println("User loaded: " + user.getName());

        // 2. Access addresses (triggers separate query)
        System.out.println("\n2. Accessing addresses...");
        System.out.println("Address count: " + user.getAddresses().size());
        user.getAddresses().forEach(addr ->
                System.out.println(" - " + addr.getCity())
        );
    }

    @Transactional
    public void demonstrateEagerLoading() {
        System.out.println("\n=== EAGER LOADING DEMO ===");

        // 1. Fetch role (permissions loaded immediately)
        System.out.println("1. Fetching role...");
        Role role = roleRepository.findById(1L).orElseThrow();
        System.out.println("Role loaded: " + role.getName());

        // 2. Access permissions (no additional query)
        System.out.println("\n2. Accessing permissions...");
        System.out.println("Permission count: " + role.getPermissions().size());
        role.getPermissions().forEach(perm ->
                System.out.println(" - " + perm.getName())
        );
    }
}
