package com.example.pj.pj.controller;
import com.example.pj.pj.entity.Role;
import com.example.pj.pj.entity.User;
import com.example.pj.pj.payload.LoginDto;
import com.example.pj.pj.payload.SignUpDto;
import com.example.pj.pj.payload.UserDto;
import com.example.pj.pj.repository.RoleRepository;
import com.example.pj.pj.repository.UserRepository;
import com.example.pj.pj.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
//@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true") // optional override
public class AuthController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserDto> getUsernamewithUserId(@PathVariable Long userId) {
        Optional<User> userFound = userRepo.findById(userId);
        if (userFound.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        UserDto userDto = new UserDto();
        userDto.setId(userFound.get().getId());
        userDto.setEmail(userFound.get().getEmail());
        userDto.setName(userFound.get().getName());
        userDto.setUsername(userFound.get().getUsername());
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    //change right now
    @GetMapping("{username}")
    public ResponseEntity<UserDto> getCurrentUser(@PathVariable String username) {
        User userFound = userRepo.findByUsername(username);
        if (userFound == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        UserDto userDto = new UserDto();
        userDto.setId(userFound.getId());
        userDto.setEmail(userFound.getEmail());
        userDto.setName(userFound.getName());
        userDto.setUsername(userFound.getUsername());
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
    //change

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@RequestBody SignUpDto signUpDto) {
        if (userRepo.existsByUsername(signUpDto.getUsername())) {
            return new ResponseEntity<>("User already exists", HttpStatus.BAD_REQUEST);
        }
        if (userRepo.existsByEmail(signUpDto.getEmail())) {
            return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
        }

        Optional<Role> roleOpt = roleRepository.findByName("ROLE_USER"); // Default to ROLE_USER
        if (roleOpt.isEmpty()) {
            return new ResponseEntity<>("Default role not found", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (signUpDto.getPassword() == null || signUpDto.getPassword().isBlank()) {
            return ResponseEntity.badRequest().body("Password must not be null or empty");
        }

        User user = new User();
        user.setName(signUpDto.getName());
        user.setEmail(signUpDto.getEmail());
        user.setUsername(signUpDto.getUsername());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        user.setRoles(Set.of(roleOpt.get())); // Assign role

        User savedDetails = userRepo.save(user);

        SignUpDto dto = new SignUpDto();
        dto.setUsername(savedDetails.getUsername());
        dto.setEmail(savedDetails.getEmail());
        dto.setName(savedDetails.getName());

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<? extends Object> authenticateUser(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = userRepo.findByUsername(loginDto.getUsername());

        if (user == null) {
            return new ResponseEntity<>("Database user not found after authentication", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Generate JWT token
        String jwt = jwtTokenProvider.generateToken(authentication);
        return new ResponseEntity<>(new JWTAuthResponse(jwt, loginDto.getUsername(), user.getId()), HttpStatus.OK);
    }

    //Update Features
    @PutMapping("/update/{username}")
    public ResponseEntity<?> updateUser(@PathVariable String username, @RequestBody UserDto userDto) {
        Optional<User> userFound = Optional.ofNullable(userRepo.findByUsername(username));
        if (userFound.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User user = userFound.get();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        userRepo.save(user);

        // Generate a new token with the updated user details
        String newToken = jwtTokenProvider.createToken(new HashMap<>(), user.getUsername());

        return ResponseEntity.ok(Map.of(
                "message", "Profile updated successfully",
                "token", newToken
        ));
    }


    //Change Password
    @PutMapping("/changePassword/{username}")
    public ResponseEntity<Map<String, String>> changePassword(@PathVariable String username, @RequestBody Map<String, String> body) {
        Optional<User> userFound = Optional.ofNullable(userRepo.findByUsername(username));
        if (userFound.isEmpty()) {
            return new ResponseEntity<>(Map.of("message", "User not found"), HttpStatus.NOT_FOUND);
        }

        User user = userFound.get();

        // Validate that both oldPassword and newPassword are present in the request body
        String oldPassword = body.get("oldPassword");
        String newPassword = body.get("newPassword");

        if (oldPassword == null || newPassword == null) {
            return new ResponseEntity<>(Map.of("message", "Both oldPassword and newPassword are required"), HttpStatus.BAD_REQUEST);
        }

        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            return new ResponseEntity<>(Map.of("message", "Old password does not match"), HttpStatus.BAD_REQUEST);
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepo.save(user);

        // Return a JSON response
        return ResponseEntity.ok(Map.of("message", "Password changed successfully"));
    }

    //Delete Account
    @DeleteMapping("/delete/{username}")
    public ResponseEntity<Map<String, String>> deleteAccount(@PathVariable String username) {
        Optional<User> userFound = Optional.ofNullable(userRepo.findByUsername(username));
        if (userFound.isEmpty()) {
            return new ResponseEntity<>(Map.of("message", "User not found"), HttpStatus.NOT_FOUND);
        }

        User user = userFound.get();
        userRepo.delete(user);

        return ResponseEntity.ok(Map.of("message", "Account deleted successfully"));
    }



}




