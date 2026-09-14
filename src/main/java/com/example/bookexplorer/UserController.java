package com.example.bookexplorer;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {

        user.setRole("USER");

        return userRepository.save(user);
    }

    @PostMapping("/login")
    public User login(@RequestBody User user) {

        User existingUser = userRepository
                .findByUsername(user.getUsername())
                .orElse(null);

        if (existingUser != null &&
            existingUser.getPassword().equals(user.getPassword())) {

            return existingUser;
        }

        return null;
    }
}