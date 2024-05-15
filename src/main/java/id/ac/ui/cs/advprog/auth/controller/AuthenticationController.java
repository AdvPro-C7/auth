package id.ac.ui.cs.advprog.auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.Collections;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import id.ac.ui.cs.advprog.auth.model.User;
import id.ac.ui.cs.advprog.auth.model.request.LoginRequest;
import id.ac.ui.cs.advprog.auth.model.request.RegisterRequest;
import id.ac.ui.cs.advprog.auth.service.invoker.AuthenticationInvokerImpl;

@CrossOrigin()
@RestController
public class AuthenticationController {
    private final AuthenticationInvokerImpl service;

    public AuthenticationController(AuthenticationInvokerImpl service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@Valid @RequestBody RegisterRequest httpRequest) {
        if (this.service.insertUser(httpRequest)) {
            return ResponseEntity.ok().body(Collections.singletonMap("message", "registration successful"));
        } else {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "registration failed"));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@Valid @RequestBody LoginRequest httpRequest) {
        if (this.service.authenticateUser(httpRequest)) {
            String token = this.service.createToken(httpRequest.getId()).toString();
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.SET_COOKIE, token);
            return ResponseEntity.ok().headers(headers).body(Collections.singletonMap("message", "login successful"));
        } else {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "invalid credentials"));
        }
    }

    @GetMapping("/authenticate")
    public ResponseEntity<User> authenticateAndGetUserDetails(HttpServletRequest httpRequest) {
        User retrievedUserDetails = this.service.getUserDetails(httpRequest);
        return retrievedUserDetails != null
                ? ResponseEntity.ok(retrievedUserDetails)
                : ResponseEntity.badRequest().body(null);
    }
}
