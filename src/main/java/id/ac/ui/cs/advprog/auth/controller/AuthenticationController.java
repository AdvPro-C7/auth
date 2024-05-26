package id.ac.ui.cs.advprog.auth.controller;

import jakarta.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import id.ac.ui.cs.advprog.auth.model.User;
import id.ac.ui.cs.advprog.auth.model.request.LoginRequest;
import id.ac.ui.cs.advprog.auth.model.request.RegisterRequest;
import id.ac.ui.cs.advprog.auth.service.invoker.AuthenticationInvokerImpl;

@RestController
public class AuthenticationController {
    private final AuthenticationInvokerImpl service;

    public AuthenticationController(AuthenticationInvokerImpl service) {
        this.service = service;
    }

    final static String messageLabel = "message";

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@Valid @RequestBody RegisterRequest httpRequest) {
        boolean userInserted = this.service.insertUser(httpRequest);

        if (userInserted) {
            return ResponseEntity.ok().body(Collections.singletonMap(messageLabel, "registration successful"));
        } else {
            return ResponseEntity.badRequest().body(Collections.singletonMap(messageLabel, "registration failed"));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginUser(@Valid @RequestBody LoginRequest httpRequest) {
        User savedUser = this.service.authenticateUser(httpRequest);

        if (savedUser != null) {
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put(messageLabel, "login successful");
            responseBody.put("user", savedUser);

            return ResponseEntity.ok().body(responseBody);
        } else {
            return ResponseEntity.badRequest().body(Collections.singletonMap(messageLabel, "invalid credentials"));
        }
    }

    @GetMapping("/get-user-details")
    public ResponseEntity<User> getUserDetails(String uid) {
        User retrievedUserDetails = this.service.getUserDetails(uid);
        return retrievedUserDetails != null
                ? ResponseEntity.ok(retrievedUserDetails)
                : ResponseEntity.badRequest().body(null);
    }
}
